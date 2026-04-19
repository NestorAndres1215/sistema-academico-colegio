import { Component } from '@angular/core';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { Button } from "../../../../../shared/ui/button/button";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TeacherService } from '../../../../../core/services/teacher.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Teacher } from '../../../../../core/models/teacher';
import { AlertService } from '../../../../../core/services/alert.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-teacher-update',
  imports: [PageTitle, Button, ReactiveFormsModule, CommonModule],
  templateUrl: './teacher-update.html',
  styleUrl: './teacher-update.css',
})
export class TeacherUpdate {
  form!: FormGroup;
  selectedFile!: File;
  teacherId: string = "";

  constructor(
    private fb: FormBuilder,
    private teacherService: TeacherService,
    private alertService: AlertService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.teacherId = this.route.snapshot.params['id'];
    this.loadTeacher(this.teacherId);
  }

  initForm() {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      paternalLastName: ['', Validators.required],
      maternalLastName: ['', Validators.required],
      dni: [''],
      phone: [''],
      email: [''],
      password: [''],
      gender: [''],
      nationality: [''],
      birthDate: [''],
      specialization: [''],
      hireDate: ['']
    });
  }

  loadTeacher(id: string) {
    this.teacherService.getById(id).subscribe({
      next: (data: any) => {
        this.form.patchValue(data);
        this.form.patchValue({
          ...data,
          email: data.user?.email,
          password: data.user?.password
        });
      },
    });
  }


  operar(): void {

    const teacher: Teacher = {
      email: this.form.value.email,
      password: this.form.value.password,
      firstName: this.form.value.firstName,
      middleName: this.form.value.middleName,
      paternalLastName: this.form.value.paternalLastName,
      maternalLastName: this.form.value.maternalLastName,
      dni: this.form.value.dni,
      phone: this.form.value.phone,
      birthDate: this.form.value.birthDate,
      nationality: this.form.value.nationality,
      gender: this.form.value.gender,
      specialization: this.form.value.specialization,
      hireDate: this.form.value.hireDate,
      age: 0,
      profile: '',
    };
    const formData = new FormData();
    console.log(teacher);

    formData.append(
      'teacher',
      new Blob([JSON.stringify(teacher)], {
        type: 'application/json'
      })
    );
    console.log(teacher);
    this.teacherService.update(this.teacherId, formData).subscribe({
      next: () => {
        this.alertService.success('Teacher updated successfully');
        this.router.navigate(['/profesores/datos']);
      },
      error: (err) => {
        console.error(err);
        this.alertService.error('Error updating teacher');
      }
    });
  }
}
