import { Component, inject, signal } from '@angular/core';
import { SelectFilterOption } from '../../../../core/models/select-filter-option';
import { firstValueFrom } from 'rxjs';
import { UpdateTeacherRequest } from '../../../../core/modules/teacher/models/update-teacher-request';
import { toApiDate } from '../../../../core/utils/date.util';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormValidationService } from '../../../../core/services/form-validation.service';
import { HttpErrorService } from '../../../../core/services/http-error.service';
import { TeacherDetailService } from '../../../../core/modules/teacher/services/teacher-detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from '../../../../core/services/alert.service';
import { Button } from '../../../../shared/ui/button/button';
import { MatFormFieldModule } from '@angular/material/form-field';

import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { FileService } from '../../../../core/services/file.service';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';

@Component({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    PageHeader,
    MatDatepickerModule,
    MatNativeDateModule,
    Button,
  ],
  selector: 'app-teacher-edit',
  styleUrl: './teacher-edit.css',
  templateUrl: './teacher-edit.html',
})
export class TeacherEdit {
  readonly fotoPreview = signal<string | null>(null);
  readonly cvNombre = signal<string | null>(null);
  readonly icon = 'edit';
  readonly title = 'Actualizar profesor';
  readonly subtitle = 'Modifique los datos del profesor.';

  readonly fotoFile = signal<File | null>(null);
  readonly cvFile = signal<File | null>(null);
  readonly cargando = signal(true);

  private readonly fb = inject(FormBuilder);
  private readonly alertService = inject(AlertService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  private readonly teacherDeatilService = inject(TeacherDetailService);
  private readonly teacherService = inject(TeacherService);
  private readonly httpErrorService = inject(HttpErrorService);
  private readonly formValidationService = inject(FormValidationService);
  private readonly fileService = inject(FileService);

  private teacherId!: number;

  readonly form: FormGroup = this.fb.group({
    personal: this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      paternalLastName: ['', Validators.required],
      maternalLastName: ['', Validators.required],
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      birthDate: ['', Validators.required],
      gender: ['', Validators.required],
      maritalStatus: ['', Validators.required],
      phone: ['', Validators.pattern(/^\d{9}$/)],
      address: [''],
    }),
    profesional: this.fb.group({
      specialty: [''],
      academicDegree: [''],
      professionalLicenseNumber: [''],
    }),
  });

  constructor() {
    this.teacherId = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarProfesor();
  }

  private async cargarProfesor(): Promise<void> {
    try {
      const teacher = await firstValueFrom(
        this.teacherDeatilService.findByTeacherId(this.teacherId),
      );

      this.form.patchValue({
        personal: {
          firstName: teacher.firstName,
          middleName: teacher.middleName,
          paternalLastName: teacher.paternalLastName,
          maternalLastName: teacher.maternalLastName,
          dni: teacher.dni,
          birthDate: teacher.birthDate,
          gender: teacher.gender,
          maritalStatus: teacher.maritalStatus,
          phone: teacher.phone,
          address: teacher.address,
        },
        profesional: {
          specialty: teacher.specialty,
          academicDegree: teacher.academicDegree,
          professionalLicenseNumber: teacher.professionalLicenseNumber,
        },
      });

      if (teacher.photo) {
        this.fotoPreview.set(this.fileService.getFileUrl(teacher.photo));
      }
      if (teacher.cv) {
        this.cvNombre.set(this.fileService.getFileUrl(teacher.cv));
      }
    } catch (error: unknown) {
      this.alertService.error(this.httpErrorService.getMessage(error));
      this.router.navigate(['/admin/profesores/listar']);
    }
  }

  // Foto
  triggerFoto(): void {
    document.getElementById('foto-input')?.click();
  }

  onFotoChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.fotoFile.set(file);

    const reader = new FileReader();
    reader.onload = (e) => {
      this.fotoPreview.set(e.target?.result as string);
    };
    reader.readAsDataURL(file);
  }

  // CV
  triggerCv(): void {
    document.getElementById('cv-input')?.click();
  }

  onCvChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    const file = input.files[0];
    this.cvFile.set(file);
    this.cvNombre.set(file.name);
  }

  removeCv(event: MouseEvent): void {
    event.stopPropagation();
    this.cvFile.set(null);
    this.cvNombre.set(null);
  }

  async guardar(): Promise<void> {
    if (!this.formValidationService.validate(this.form)) {
      return;
    }

    const valores = this.form.getRawValue();

    const payload: UpdateTeacherRequest = {
      firstName: valores.personal.firstName,
      middleName: valores.personal.middleName,
      paternalLastName: valores.personal.paternalLastName,
      maternalLastName: valores.personal.maternalLastName,
      dni: valores.personal.dni,
      birthDate: toApiDate(valores.personal.birthDate) ?? '',
      gender: valores.personal.gender,
      maritalStatus: valores.personal.maritalStatus,
      phone: valores.personal.phone,
      address: valores.personal.address,
      specialty: valores.profesional.specialty,
      academicDegree: valores.profesional.academicDegree,
      professionalLicenseNumber: valores.profesional.professionalLicenseNumber,
    };

    try {
      await firstValueFrom(
        this.teacherService.update(this.teacherId, payload, this.fotoFile(), this.cvFile()),
      );

      this.alertService.success('Profesor actualizado', 'Los datos se actualizaron correctamente.');
      this.router.navigate(['/admin/profesores/listar']);
    } catch (error: unknown) {
      this.alertService.error(this.httpErrorService.getMessage(error));
    }
  }

  cancelar(): void {
    this.router.navigate(['/admin/profesores/listar']);
  }

  readonly maritalStatusOptions: SelectFilterOption[] = [
    { value: '', label: 'Seleccione estado civil' },
    { value: 'Soltero', label: 'Soltero/a' },
    { value: 'Casado', label: 'Casado/a' },
    { value: 'Divorciado', label: 'Divorciado/a' },
    { value: 'Viudo', label: 'Viudo/a' },
  ];

  readonly genderOptions: SelectFilterOption[] = [
    { value: '', label: 'Seleccione genero' },
    { value: 'Masculino', label: 'Masculino' },
    { value: 'Femenino', label: 'Femenino' },
    { value: 'Otro', label: 'Otro' },
  ];

  readonly academicDegreeOptions: SelectFilterOption[] = [
    { value: '', label: 'Seleccione grado academico' },
    { value: 'Bachiller', label: 'Bachiller' },
    { value: 'Licenciado', label: 'Licenciado' },
    { value: 'Magíster', label: 'Magíster' },
    { value: 'Doctor', label: 'Doctor' },
  ];
}
