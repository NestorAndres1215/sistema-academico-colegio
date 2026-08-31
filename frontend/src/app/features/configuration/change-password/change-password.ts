import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { firstValueFrom } from 'rxjs';

import { BreadCrumb } from '../../../shared/ui/bread-crumb/bread-crumb';
import { FormValidationService } from '../../../core/services/form-validation.service';
import { AlertService } from '../../../core/services/alert.service';
import { Button } from '../../../shared/ui/button/button';
import { AuthService } from '../../../core/auth/service/auth.service';
import { BreadcrumbItem } from '../../../shared/models/breadcrumb.model';
import { PasswordRequest } from '../../../core/modules/user/models/password-request';
import { UserService } from '../../../core/modules/user/services/user.service';
import { HttpErrorService } from '../../../core/services/http-error.service';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    BreadCrumb,
    Button
  ],
  templateUrl: './change-password.html',
  styleUrl: './change-password.css',
})
export class ChangePassword {
  private readonly userService = inject(UserService);
  private readonly authService = inject(AuthService);
  private readonly fb = inject(FormBuilder);
  private readonly formValidationService = inject(FormValidationService);
  private readonly alertService = inject(AlertService);
  private readonly httpErrorService = inject(HttpErrorService);
  readonly editMode = signal(false);
  readonly username = signal('');
  readonly currentRole = signal('');
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly showNueva = signal(false);
  readonly showConfirmar = signal(false);
  readonly showActual = signal(false);
  private readonly currentUserId = signal(0);

  readonly avatarLetter = computed(() => this.username().charAt(0).toUpperCase() || '?');

  passwordForm = this.fb.nonNullable.group({
    currentPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmNewPassword: ['', Validators.required],
  });

  async ngOnInit(): Promise<void> {
    await this.initUser();
  }

  private async initUser(): Promise<void> {
    const currentUser = await firstValueFrom(this.authService.getCurrentUser());

    if (!currentUser) {
      this.alertService.error('No se pudo obtener la información del usuario.');
      return;
    }

    this.currentUserId.set(currentUser.id);
    this.username.set(currentUser.username);
    this.currentRole.set(currentUser.role);

    this.breadcrumbs.set([
      {
        label: 'Inicio',
        href: this.authService.getHomeByRole(currentUser.role),
      },
      {
        label: 'Cambiar Contraseña',
      },
    ]);
  }

  toggleEdit(): void {
    this.editMode.set(true);
  }

  cancelar(): void {
    this.editMode.set(false);
    this.passwordForm.reset();
  }

  async guardar(): Promise<void> {
    if (!this.formValidationService.validate(this.passwordForm)) return;

    const confirmado = await this.alertService.confirm(
      '¿Cambiar contraseña?',
      'Se actualizará la contraseña del usuario.',
      'Sí, cambiar',
      'Cancelar',
    );

    if (!confirmado) return;
    const payload: PasswordRequest = this.passwordForm.getRawValue();

    try {
      await firstValueFrom(this.userService.changePassword(this.currentUserId(), payload));

      this.editMode.set(false);
      this.passwordForm.reset();
    } catch (error: unknown) {
    
      this.alertService.error(
        this.httpErrorService.getMessage(error)
      );
    }
  }
}
