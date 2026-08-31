import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { BreadCrumb } from '../../../../shared/ui/bread-crumb/bread-crumb';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { Button } from '../../../../shared/ui/button/button';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';
import { UserService } from '../../../../core/modules/user/services/user.service';
import { FormValidationService } from '../../../../core/services/form-validation.service';
import { AlertService } from '../../../../core/services/alert.service';
import { firstValueFrom } from 'rxjs';
import { CreateUserRequest } from '../../../../core/modules/user/models/create-user-request';
import { ROLES } from '../../../../core/auth/constants/roles';
import { Router } from '@angular/router';
import { HttpErrorService } from '../../../../core/services/http-error.service';

@Component({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatDatepickerModule,
    PageHeader,
    BreadCrumb,
    Button,
  ],
  selector: 'app-user-create',
  styleUrl: './user-create.css',
  templateUrl: './user-create.html',
})
export class UserCreate {
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  private readonly fb = inject(FormBuilder);
  private readonly userService = inject(UserService);
  private readonly alertService = inject(AlertService);
  private readonly formValidationService = inject(FormValidationService);
  private readonly httpErrorService = inject(HttpErrorService);
  private readonly router = inject(Router);
  readonly icon = 'person_add';
  readonly title = 'Registrar usuario';
  readonly subtitle = 'Ingrese los datos requeridos para crear una nueva cuenta de usuario.';
  readonly hidePassword = signal(true);
  readonly hideConfirmPassword = signal(true);

  async ngOnInit(): Promise<void> {
    await this.initUser();
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Usuarios' },
      { label: 'Listado de Usuarios' },
    ]);
  }

  readonly registerForm: FormGroup = this.fb.group(
    {
      email: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirmPassword: ['', Validators.required],
    },
  );

  togglePassword(): void {
    this.hidePassword.set(!this.hidePassword());
  }

  toggleConfirmPassword(): void {
    this.hideConfirmPassword.set(!this.hideConfirmPassword());
  }

  async operar(): Promise<void> {
    if (!this.formValidationService.validate(this.registerForm)) return;

    const raw = this.registerForm.getRawValue();

    const payload: CreateUserRequest = {
      email: raw.email,
      username: raw.username,
      password: raw.password,
      role: ROLES.ROLE_ADMIN,
    };

    try {
      await firstValueFrom(this.userService.create(payload));
      this.alertService.success('Administrador Registro');

      this.router.navigate(['/admin/usuarios/listado-usuario']);
    } catch (error: unknown) {

      this.alertService.error(this.httpErrorService.getMessage(error));
    }
  }

  cancelar(): void {
    this.registerForm.reset();
  }
}
