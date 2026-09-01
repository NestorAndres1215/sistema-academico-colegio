import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../core/modules/user/services/user.service';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormValidationService } from '../../../core/services/form-validation.service';
import { AlertService } from '../../../core/services/alert.service';
import { HttpErrorService } from '../../../core/services/http-error.service';
import { firstValueFrom } from 'rxjs';
import { CompanyService } from '../../../core/modules/company/services/company.service';
import { FileService } from '../../../core/services/file.service';
import { PasswordRequest, UpdatePasswordRequest } from '../../../core/modules/user/models/password-request';
import { Button } from "../../../shared/ui/button/button";
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';

@Component({
  imports: [    
    MatIconModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    ],
  selector: 'app-change-password',
  styleUrl: './change-password.css',
  templateUrl: './change-password.html',
})
export class ChangePassword {

  private readonly router = inject(Router);
  private readonly formBuilder = inject(NonNullableFormBuilder);
  private readonly userService = inject(UserService);
  private readonly fileService = inject(FileService);
  private readonly alertService = inject(AlertService);
  private readonly companyService = inject(CompanyService);
  private readonly formValidationService = inject(FormValidationService);
  private readonly httpErrorService = inject(HttpErrorService);
  readonly currentUserId = signal<number>(0);
  readonly logoUrl = signal<string | null>(null);
  readonly companyName = signal<string>('');
  readonly correo = signal<string>('');

  readonly showNueva = signal(false);
  readonly showConfirmar = signal(false);
  readonly showActual = signal(false);

  readonly passwordForm = this.formBuilder.group({
    newPassword: ['', Validators.required],
    confirmNewPassword: ['', Validators.required],
  });

  async ngOnInit(): Promise<void> {
    await this.getUsername();
    await this.getCompanyLogo();
  }

  async getUsername(): Promise<void> {

    const email = localStorage.getItem('recoveryEmail') ?? '';

    this.correo.set(email);

    const usuario = await firstValueFrom(this.userService.findByEmail(email));
    this.currentUserId.set(usuario.id);
  }
  get inicial(): string {
    return this.companyName()?.charAt(0).toUpperCase() ?? '';
  }
  async getCompanyLogo(): Promise<void> {

    const company = await firstValueFrom(
      this.companyService.findByCode('COMSANANDRES')
    );

    const logoUrl = this.fileService.getFileUrl(company.logoUrl);

    this.logoUrl.set(logoUrl);
    this.companyName.set(company.name);
  }

  async operar(): Promise<void> {

    if (!this.formValidationService.validate(this.passwordForm)) {
      return;
    }

    const confirmado = await this.alertService.confirm(
      '¿Cambiar contraseña?',
      'Se actualizará la contraseña del usuario.',
      'Sí, cambiar',
      'Cancelar',
    );

    if (!confirmado) {
      return;
    }

    const passwordRequest: UpdatePasswordRequest =
      this.passwordForm.getRawValue();

    try {

      await firstValueFrom(
        this.userService.updatechangePassword(
          this.currentUserId(),
          passwordRequest
        )
      );

      this.passwordForm.reset();

    } catch (error: unknown) {

      this.alertService.error(
        this.httpErrorService.getMessage(error)
      );
    }
  }
}