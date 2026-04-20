import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AdminService } from '../../../../../core/services/admin.service';
import { AlertService } from '../../../../../core/services/alert.service';
import { getMaxBirthDate, filterOnlyNumbers } from '../../../../../core/utils/validators';
import { Button } from '../../../../../shared/ui/button/button';
import { PageTitle } from '../../../../../shared/ui/page-title/page-title';
import { Admin } from '../../../../../core/models/admin';

@Component({
  selector: 'app-user-create',
   imports: [Button, CommonModule, ReactiveFormsModule, PageTitle],
  templateUrl: './user-create.html',
  styleUrl: './user-create.css',
})
export class UserCreate {
  maxBirthDate: string = '';
  form!: FormGroup;

  constructor(
    private alertService: AlertService,
    private router: Router,
    private fb: FormBuilder,
    private adminService: AdminService) { }

  initForm() {
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      middleName: ['', Validators.required],
      paternalLastName: ['', Validators.required],
      maternalLastName: ['', Validators.required],
      dni: ['', Validators.required],
      phone: ['', Validators.required],
      birthDate: ['', Validators.required],
      nationality: ['', Validators.required],
      gender: ['', Validators.required],
      profile: ['', Validators.required],

    })
  }

  ngOnInit(): void {
    this.maxBirthDate = getMaxBirthDate(18);
    this.initForm();
  }

  onlyNumbersInput(event: Event) {
    const input = event.target as HTMLInputElement;
    input.value = filterOnlyNumbers(input.value);
  }

  operar(): void {

    const register: Admin = {
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
      age: 0,
      profile: ''
    };

    const formData = new FormData();

    formData.append('file', this.selectedFile);

    formData.append(
      'user',
      new Blob([JSON.stringify(register)], {
        type: 'application/json'
      })
    );

    this.adminService.create(formData).subscribe({
      next: () => {
        this.alertService.success('User created');
        this.router.navigate(['/usuarios/listado-usuario']);
      },
      error: (error) => {
        console.error(error.error.message);
        this.alertService.error(error.error.message);
      }
    });
  }

  selectedFile!: File;

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }
}
