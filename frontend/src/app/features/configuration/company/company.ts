import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BreadCrumb } from '../../../shared/ui/bread-crumb/bread-crumb';
import { FormValidationService } from '../../../core/services/form-validation.service';
import { AlertService } from '../../../core/services/alert.service';
import { Button } from '../../../shared/ui/button/button';

import { AuthService } from '../../../core/auth/service/auth.service';
import { BreadcrumbItem } from '../../../shared/models/breadcrumb.model';

import { PageHeader } from '../../../shared/ui/page-header/page-header';
import { FileService } from '../../../core/services/file.service';
import { CompanyService } from '../../../core/modules/company/services/company.service';
import { CompanyResponse } from '../../../core/modules/company/models/company-response';
import { CompanyRequest } from '../../../core/modules/company/models/company-request';

@Component({
  selector: 'app-company',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatTooltipModule,
    BreadCrumb,
    Button,
    PageHeader,
  ],
  templateUrl: './company.html',
  styleUrl: './company.css',
})
export class Company implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly companyService = inject(CompanyService);
  private readonly authService = inject(AuthService);
  private readonly formValidationService = inject(FormValidationService);
  private readonly alertService = inject(AlertService);
  private readonly fileService = inject(FileService);
  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly editMode = signal(false);
  readonly success = signal(false);
  readonly logoPreview = signal<string | null>(null);
  readonly company = signal<CompanyResponse | null>(null);

  readonly icon = 'business';
  readonly title = 'Mi compañía';
  readonly subtitle = 'Administra la información de tu institución';

  selectedFile: File | null = null;

  readonly companyForm = this.fb.group({
    name: ['', Validators.required],
    businessName: ['', Validators.required],
    taxId: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    phone: ['', Validators.required],
    address: ['', Validators.required],
    city: ['', Validators.required],
    country: ['', Validators.required],
    website: [''],
  });

  readonly inicial = computed(() => this.company()?.name?.charAt(0).toUpperCase() ?? '');

  async ngOnInit(): Promise<void> {
    await this.getCompany();
    await this.initUser();
  }

  private async initUser(): Promise<void> {
    const currentUser = await firstValueFrom(this.authService.getCurrentUser());

    if (!currentUser) {
      return;
    }

    this.breadcrumbs.set([
      {
        label: 'Inicio',
        href: this.authService.getHomeByRole(currentUser.role),
      },
      {
        label: 'Compañía',
      },
    ]);
  }

  async getCompany(): Promise<void> {
    const data = await firstValueFrom(this.companyService.findByCode('COMSANANDRES'));

    this.company.set(data);

    this.logoPreview.set(this.fileService.getFileUrl(data.logoUrl));

    this.patchForm(data);
  }

  private patchForm(c: CompanyResponse): void {
    this.companyForm.patchValue({
      name: c.name,
      businessName: c.businessName,
      taxId: c.taxId,
      email: c.email,
      phone: c.phone,
      address: c.address,
      city: c.city,
      country: c.country,
      website: c.website,
    });
  }

  toggleEdit(): void {
    const c = this.company();

    if (!c) {
      return;
    }

    this.selectedFile = null;

    this.logoPreview.set(this.fileService.getFileUrl(c.logoUrl));

    this.patchForm(c);
    this.editMode.set(true);
  }

  cancelar(): void {
    const c = this.company();

    if (!c) {
      return;
    }

    this.selectedFile = null;

    this.logoPreview.set(this.fileService.getFileUrl(c.logoUrl));

    this.patchForm(c);
    this.editMode.set(false);
  }

  async guardar(): Promise<void> {
    if (!this.formValidationService.validate(this.companyForm)) {
      return;
    }

    const company = this.company();

    if (!company) {
      return;
    }

    const formValue = this.companyForm.getRawValue();

    const request: CompanyRequest = {
      code: company.code,
      name: formValue.name ?? '',
      businessName: formValue.businessName ?? '',
      taxId: formValue.taxId ?? '',
      email: formValue.email ?? '',
      phone: formValue.phone ?? '',
      address: formValue.address ?? '',
      city: formValue.city ?? '',
      country: formValue.country ?? '',
      website: formValue.website ?? '',
    };

    try {
      await firstValueFrom(
        this.companyService.update(company.id, request, this.selectedFile ?? undefined),
      );

      this.editMode.set(false);
      this.success.set(true);
      this.selectedFile = null;

      await this.getCompany();
    } catch (error: any) {
      this.alertService.error(error?.error?.message ?? 'Error al actualizar la compañía');
    }
  }

  onLogoChange(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files?.length) {
      return;
    }

    this.selectedFile = input.files[0];

    const reader = new FileReader();

    reader.onload = () => {
      this.logoPreview.set(reader.result as string);
    };

    reader.readAsDataURL(this.selectedFile);
  }

  triggerLogoInput(): void {
    document.getElementById('logo-input')?.click();
  }
}
