import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Password } from '../../../../../core/models/password';
import { AlertService } from '../../../../../core/services/alert.service';
import { AuthService } from '../../../../../core/services/auth.service';
import { CommonModule } from '@angular/common';
import { Button } from '../../../../../shared/ui/button/button';
import { PageTitle } from '../../../../../shared/ui/page-title/page-title';

@Component({
  selector: 'app-reset-password',
  imports: [ Button, CommonModule, ReactiveFormsModule, PageTitle],
  templateUrl: './reset-password.html',
  styleUrl: './reset-password.css',
})
export class ResetPassword implements OnInit {


  form!: FormGroup;
  userId: string = '';
  showNewPassword: boolean = false;
  showConfirmPassword: boolean = false;
  showCurrentPassword: boolean = false;

  
  constructor(private authService: AuthService, private router: Router,
    private fb: FormBuilder, private alertService: AlertService
  ) { }

  ngOnInit() {
    this.getCurrentUser();
    this.initForm();
  }


  getCurrentUser(): void {
    this.authService.getCurrentUser().subscribe(user => {
      this.userId = user.id;
      this.form.patchValue({ email: user.email });
    });
  }

  toggleNewPassword() {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleConfirmPassword() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }


  toggleCurrentPassword() {
    this.showCurrentPassword = !this.showCurrentPassword;
  }

  initForm() {
    this.form = this.fb.group({
      email: [{ value: '', disabled: true }, Validators.required],
      currentPassword: ['', Validators.required],
      newPassword: ['', Validators.required],
      confirmNewPassword: ['', Validators.required],
    });
  }

  operar(): void {
    if (this.form.invalid) {
      this.alertService.warning('Formulario inválido', 'Por favor, completa todos los campos requeridos');
      this.form.markAllAsTouched();
      return;
    }
    const userId = this.userId;
    const data: Password = {
      currentPassword: this.form.value.currentPassword,
      newPassword: this.form.value.newPassword,
      confirmNewPassword: this.form.value.confirmNewPassword
    };


    this.authService.changePassword(userId, data).subscribe({
      next: () => {
        this.alertService.success('Contraseña actualizada correctamente');
        this.form.reset();
      },
      error: (err) => {

        this.alertService.error(err.error.message);
      }
    });
  }
}
