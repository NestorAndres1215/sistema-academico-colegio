import { Component } from '@angular/core';

import { PageTitle } from "../../../../../../shared/ui/page-title/page-title";
import { Button } from "../../../../../../shared/ui/button/button";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { filterOnlyNumbers, getMaxBirthDate } from '../../../../../../core/utils/validators';
import { AlertService } from '../../../../../../core/services/alert.service';
import { TeacherService } from '../../../../../../core/services/teacher.service';
import { Router } from '@angular/router';
import { Teacher } from '../../../../../../core/models/teacher';

@Component({
  selector: 'app-teacher-register',
  imports: [Button, CommonModule, ReactiveFormsModule, PageTitle],
  templateUrl: './teacher-register.html',
  styleUrl: './teacher-register.css',
})
export class TeacherRegister {

  maxBirthDate: string = '';
  form!: FormGroup;

  
  ngOnInit(): void {
    this.maxBirthDate = getMaxBirthDate(18);
    this.initForm();
  }

  onlyNumbersInput(event: Event) {
    const input = event.target as HTMLInputElement;
    input.value = filterOnlyNumbers(input.value);
  }

  constructor(
    private alertService: AlertService,
    private router: Router,
    private fb: FormBuilder,
    private teacherService: TeacherService) { }


  initForm() {
    this.form = this.fb.group({
      firstName: [''],
      middleName: [''],
      paternalLastName: [''],
      maternalLastName: [''],
      dni: [''],
      phone: [''],
      email: [''],
      password: [''],
      gender: [''],
      nationality: [''],
      birthDate: [''],
      specialization: [''],
      hireDate: [''],
      profile: ['', Validators.required],

    })
  }


  selectedFile!: File;

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }


  operar(): void {

  const register: Teacher = {
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
    profile: ''
  };

  const formData = new FormData();

  if (this.selectedFile) {
    formData.append('file', this.selectedFile);
  }

  formData.append(
    'teacher',
    new Blob([JSON.stringify(register)], {
      type: 'application/json'
    })
  );

  this.teacherService.create(formData).subscribe({
    next: () => {
      this.alertService.success('Teacher created');
      this.router.navigate(['/teachers/datos']);
    },
    error: (error) => {
      console.error(error.error.message);
      this.alertService.error(error.error.message);
    }
  });
}
}
