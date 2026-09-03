import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AlertService } from '../../../core/services/alert.service';
import { VerificationCodeService } from '../../../core/auth/service/verification-code.service';
import { firstValueFrom } from 'rxjs';
import { Button } from '../../../shared/ui/button/button';
import { CompanyService } from '../../../core/modules/company/services/company.service';
import { FileService } from '../../../core/services/file.service';
import { HttpErrorService } from '../../../core/services/http-error.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormValidationService } from '../../../core/services/form-validation.service';

@Component({
  imports: [Button, ReactiveFormsModule, MatFormFieldModule, MatInputModule],
  selector: 'app-forgot-password',
  styleUrl: './forgot-password.css',
  templateUrl: './forgot-password.html',
})
export class ForgotPassword {
  private readonly formBuilder = inject(FormBuilder);
  private readonly router = inject(Router);
  private readonly fileService = inject(FileService);
  private readonly alertService = inject(AlertService);
  private readonly companyService = inject(CompanyService);
  private readonly verificationCodeService = inject(VerificationCodeService);
  private readonly formValidationService = inject(FormValidationService);
  readonly logoUrl = signal<string | null>(null);
  readonly companyName = signal<string>('');
  readonly recoveryEmail = signal<string>('');
  private readonly httpErrorService = inject(HttpErrorService);

  readonly forgotForm = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
  });

  get inicial(): string {
    return this.companyName()?.charAt(0).toUpperCase() ?? '';
  }

  async forgotPassword(): Promise<void> {
    if (!this.formValidationService.validate(this.forgotForm)) {
      return;
    }
    const email = this.forgotForm.value.email;

    if (!email) {
      return;
    }

    try {
      await firstValueFrom(this.verificationCodeService.verifyEmail(email));

      localStorage.setItem('recoveryEmail', email);

      this.recoveryEmail.set(email);

      this.router.navigate(['/auth/codigo-verificacion']);
    } catch (error: unknown) {
      this.alertService.error(this.httpErrorService.getMessage(error));
    }
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  async ngOnInit(): Promise<void> {
    await this.getCompanyLogo();
  }

  async getCompanyLogo(): Promise<void> {
    const company = await firstValueFrom(this.companyService.findByCode('COMSANANDRES'));

    const logoUrl = this.fileService.getFileUrl(company.logoUrl);

    this.logoUrl.set(logoUrl);

    this.companyName.set(company.name);
  }
}
