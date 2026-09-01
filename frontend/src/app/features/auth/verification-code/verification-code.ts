import { Component, inject, signal, OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { VerificationCodeService } from '../../../core/auth/service/verification-code.service';
import { AlertService } from '../../../core/services/alert.service';
import { Router } from '@angular/router';
import { Button } from '../../../shared/ui/button/button';
import { HttpErrorService } from '../../../core/services/http-error.service';
import { CompanyService } from '../../../core/modules/company/services/company.service';
import { FileService } from '../../../core/services/file.service';
import { FormValidationService } from '../../../core/services/form-validation.service';

@Component({
  imports: [Button, ReactiveFormsModule, MatFormFieldModule, MatInputModule],
  selector: 'app-verification-code',
  styleUrl: './verification-code.css',
  templateUrl: './verification-code.html',
})
export class VerificationCode implements OnInit {
  private readonly router = inject(Router);
  private readonly alertService = inject(AlertService);
  private readonly verificationCodeService = inject(VerificationCodeService);
  private readonly httpErrorService = inject(HttpErrorService);
  private readonly fileService = inject(FileService);
  private readonly companyService = inject(CompanyService);
  private readonly fb = inject(FormBuilder);
  private readonly formValidationService = inject(FormValidationService);
  readonly logoUrl = signal<string | null>(null);
  readonly companyName = signal<string>('');
  email = signal('');

  readonly digitPattern = /^[0-9]$/;

  readonly form: FormGroup = this.fb.group({
    digit1: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
    digit2: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
    digit3: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
    digit4: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
    digit5: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
    digit6: ['', [Validators.required, Validators.pattern(this.digitPattern)]],
  });

  async ngOnInit(): Promise<void> {
    await this.getCompanyLogo();
  }

  async send() {
    this.email.set(localStorage.getItem('recoveryEmail') ?? '');
    await firstValueFrom(this.verificationCodeService.verifyEmail(this.email()));
  }

  autoFocusNext(event: any, nextInput: HTMLInputElement) {
    if (event.target.value.length === 1 && nextInput) {
      nextInput.focus();
    }
  }

  async enviarCodigo(): Promise<void> {

    if (!this.formValidationService.validate(this.form)) {
      return;
    }

    const { digit1, digit2, digit3, digit4, digit5, digit6 } = this.form.value;
    const codigo = `${digit1}${digit2}${digit3}${digit4}${digit5}${digit6}`;

    try {

      await firstValueFrom(this.verificationCodeService.verifyCode(codigo));

      await this.router.navigate(['/auth/cambiar-contrasenia']);
    } catch (error: unknown) {
      this.alertService.error(this.httpErrorService.getMessage(error));
    }
  }

  get inicial(): string {
    return this.companyName()?.charAt(0).toUpperCase() ?? '';
  }

  async getCompanyLogo(): Promise<void> {

    const company = await firstValueFrom(this.companyService.findByCode('COMSANANDRES'));

    const logoUrl = this.fileService.getFileUrl(company.logoUrl);

    this.logoUrl.set(logoUrl);

    this.companyName.set(company.name);

  }
}