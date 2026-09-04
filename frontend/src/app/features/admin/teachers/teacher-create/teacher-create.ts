import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { BreadCrumb } from '../../../../shared/ui/bread-crumb/bread-crumb';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';
import { AlertService } from '../../../../core/services/alert.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SelectFilterOption } from '../../../../core/models/select-filter-option';
import { TeacherRequest } from '../../../../core/modules/teacher/models/teacher-request';
import { toApiDate } from '../../../../core/utils/date.util';
import { firstValueFrom } from 'rxjs';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';
import { HttpErrorService } from '../../../../core/services/http-error.service';
import { FormValidationService } from '../../../../core/services/form-validation.service';

@Component({
  selector: 'app-teacher-create',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    BreadCrumb,
    PageHeader,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  templateUrl: './teacher-create.html',
  styleUrl: './teacher-create.css',
})
export class TeacherCreate {
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly fotoPreview = signal<string | null>(null);
  readonly cvNombre = signal<string | null>(null);
  readonly icon = 'person_add';
  readonly title = 'Registrar profesor';
  readonly subtitle = 'Ingrese los datos personales, profesionales y laborales del nuevo profesor.';

  readonly fotoFile = signal<File | null>(null);
  readonly cvFile = signal<File | null>(null);

  private readonly fb = inject(FormBuilder);
  private readonly alertService = inject(AlertService);
  private readonly router = inject(Router);
  private readonly teacherService = inject(TeacherService);
  private readonly httpErrorService = inject(HttpErrorService);
  private readonly formValidationService = inject(FormValidationService);
  readonly form: FormGroup = this.fb.group({
    personal: this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      paternalLastName: ['', Validators.required],
      maternalLastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      birthDate: [''],
      gender: [''],
      maritalStatus: [''],
      phone: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      address: [''],
    }),
    profesional: this.fb.group({
      specialty: ['', Validators.required],
      academicDegree: [''],
      professionalLicenseNumber: [''],
      university: [''],
      graduationDate: [''],
      yearsOfExperience: [null],
      notes: [''],
    }),
    laboral: this.fb.group({
      contractType: [''],
      startDate: ['', Validators.required],
      endDate: [''],
      position: [''],
      weeklyHours: [null],
      salary: [null],
    }),
  });

  ngOnInit(): void {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Profesores' },
      { label: 'Registrar Profesores' },
    ]);
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

    const payload: TeacherRequest = {
      firstName: valores.personal.firstName,
      middleName: valores.personal.middleName,
      paternalLastName: valores.personal.paternalLastName,
      maternalLastName: valores.personal.maternalLastName,
      email: valores.personal.email,
      dni: valores.personal.dni,
      birthDate: toApiDate(valores.personal.birthDate) ?? '',
      gender: valores.personal.gender,
      maritalStatus: valores.personal.maritalStatus,
      phone: valores.personal.phone,
      address: valores.personal.address,

      specialty: valores.profesional.specialty,
      academicDegree: valores.profesional.academicDegree,
      professionalLicenseNumber: valores.profesional.professionalLicenseNumber,
      university: valores.profesional.university,
      graduationDate: toApiDate(valores.profesional.graduationDate) ?? '',
      yearsOfExperience: valores.profesional.yearsOfExperience,
      notes: valores.profesional.notes,

      contractType: valores.laboral.contractType,
      startDate: toApiDate(valores.laboral.startDate) ?? '',
      endDate: toApiDate(valores.laboral.endDate) ?? '',
      position: valores.laboral.position,
      weeklyHours: valores.laboral.weeklyHours,
      salary: valores.laboral.salary,
    };

    try {
      await firstValueFrom(
        this.teacherService.create(payload, this.fotoFile(), this.cvFile()),
      );

      this.alertService.success('Profesor registrado', 'El profesor se registró correctamente.');

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
  
  readonly contractTypeOptions: SelectFilterOption[] = [
    { value: '', label: 'Seleccione tipo de contrato' },
    { value: 'Tiempo completo', label: 'Tiempo completo' },
    { value: 'Tiempo parcial', label: 'Tiempo parcial' },
    { value: 'Por horas', label: 'Por horas' },
    { value: 'Temporal', label: 'Temporal' },
  ];

  readonly academicDegreeOptions: SelectFilterOption[] = [
    { value: '', label: 'Seleccione grado academico' },
    { value: 'Bachiller', label: 'Bachiller' },
    { value: 'Licenciado', label: 'Licenciado' },
    { value: 'Magíster', label: 'Magíster' },
    { value: 'Doctor', label: 'Doctor' },
  ];

  readonly maxBirthDate = new Date(
    new Date().getFullYear() - 18,
    new Date().getMonth(),
    new Date().getDate(),
  );
}
