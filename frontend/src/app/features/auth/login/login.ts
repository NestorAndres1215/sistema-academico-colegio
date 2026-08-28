import { Component, inject, signal } from '@angular/core';
import { AlertService } from '../../../core/services/alert.service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormValidationService } from '../../../core/services/form-validation.service';
import { AuthService } from '../../../core/auth/service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginRequest } from '../../../core/auth/models/login.model';
import { firstValueFrom } from 'rxjs';
import { ROLES } from '../../../core/auth/constants/roles';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Button } from '../../../shared/ui/button/button';
import { CompanyService } from '../../../core/company/services/company.service';
@Component({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    Button,
  ],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})
export class Login {
  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);
  private readonly companyService = inject(CompanyService);
  private readonly alertService = inject(AlertService);
  private readonly formBuilder = inject(FormBuilder);
  private readonly formValidationService = inject(FormValidationService);

  readonly hidePassword = signal(true);
  readonly logoUrl = signal<string | null>(null);
  readonly companyName = signal<string>('');

  readonly loginForm = this.formBuilder.group({
    login: ['', Validators.required],
    password: ['', Validators.required],
  });

  togglePassword(): void {
    this.hidePassword.update((value) => !value);
  }

  get inicial(): string {
    return this.companyName()?.charAt(0).toUpperCase() ?? '';
  }

  async operar(): Promise<void> {
    if (!this.formValidationService.validate(this.loginForm)) {
      return;
    }

    const login: LoginRequest = {
      login: this.loginForm.value.login!,
      password: this.loginForm.value.password!,
    };

    try {

      await firstValueFrom(this.authService.generateToken(login));
      this.authService.clearCurrentUser();
      const user = await firstValueFrom(this.authService.getCurrentUser());

      if (!user?.role) {
        this.alertService.error('El usuario no tiene un rol asignado.');
        return;
      }

      this.navigateByRole(user.role);
    } catch (error: any) {
      this.alertService.error(error?.error?.message);
    }
  }

  private navigateByRole(role: string): void {
    const routes: Record<string, string> = {
      [ROLES.ROLE_ADMIN]: '/admin',
      [ROLES.ROLE_GUARDIAN]: '/guardian',
      [ROLES.ROLE_STAFF]: '/staff',
      [ROLES.ROLE_TEACHER]: '/teacher',
      [ROLES.ROLE_STUDENT]: '/student',
    };

    const route = routes[role];

    if (!route) {
      this.alertService.error('El usuario no tiene una ruta asignada para su rol.');
      return;
    }

    this.router.navigate([route]);
  }

  async getCompanyLogo(): Promise<void> {
    const company = await firstValueFrom(this.companyService.findByCode('COMP0001'));
    this.logoUrl.set(company.logoUrl);
    this.companyName.set(company.name);
  }
}
