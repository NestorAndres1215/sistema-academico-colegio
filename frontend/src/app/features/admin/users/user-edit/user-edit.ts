import { Component, inject, signal } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PageHeader } from '../../../../shared/ui/page-header/page-header';
import { BreadCrumb } from '../../../../shared/ui/bread-crumb/bread-crumb';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { Button } from '../../../../shared/ui/button/button';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { firstValueFrom } from 'rxjs';
import { UpdateUserRequest } from '../../../../core/modules/user/models/update-user-request';
import { ROLES } from '../../../../core/auth/constants/roles';
import { ActivatedRoute, Router } from '@angular/router';
import { FormValidationService } from '../../../../core/services/form-validation.service';
import { AlertService } from '../../../../core/services/alert.service';
import { UserService } from '../../../../core/modules/user/services/user.service';
import { BreadcrumbItem } from '../../../../shared/models/breadcrumb.model';
import { HttpErrorService } from '../../../../core/services/http-error.service';

@Component({
  imports: [
    BreadCrumb,
    PageHeader,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    Button,
  ],
  selector: 'app-user-edit',
  styleUrl: './user-edit.css',
  templateUrl: './user-edit.html',
})
export class UserEdit {

  readonly breadcrumbs = signal<BreadcrumbItem[]>([]);
  readonly user = signal<UpdateUserRequest | null>(null);
  readonly icon = 'edit';
  readonly title = 'Editar usuario';
  readonly subtitle = 'Modifica la información personal del usuario.';
  private readonly route = inject(ActivatedRoute);
  private readonly adminService = inject(UserService);
  private readonly formValidationService = inject(FormValidationService);
  private readonly alertService = inject(AlertService);
  private readonly httpErrorService = inject(HttpErrorService);
  private readonly router = inject(Router);
  private readonly fb = inject(FormBuilder)

  userId!: number;

  async ngOnInit(): Promise<void> {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
    await this.initUser();
    await this.loadUser();
  }

  private async initUser(): Promise<void> {
    this.breadcrumbs.set([
      { label: 'Inicio', href: '/admin' },
      { label: 'Usuarios' },
      { label: 'Listado de Usuarios', href: '/admin/usuarios/listado-usuario' },
      { label: 'Editar Usuario' },
    ]);
  }

  readonly editForm: FormGroup = this.fb.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
  });


  private async loadUser(): Promise<void> {
    const usuario = await firstValueFrom(this.adminService.findById(this.userId));
    this.editForm.patchValue(usuario);
    this.user.set(usuario);
  }

  cancelar(): void {
    this.router.navigate(['/admin/usuarios/listado-usuario']);
  }

  async operar(): Promise<void> {
    if (!this.formValidationService.validate(this.editForm)) {
      return;
    }

    const payload: UpdateUserRequest = {
      email: this.editForm.get('email')?.value,
      username: this.editForm.get('username')?.value,
      role: ROLES.ROLE_ADMIN,
    };

    try {
      await firstValueFrom(
        this.adminService.update(this.userId, payload)
      );

      this.alertService.success(
        'Administrador actualizado',
        'Los datos del administrador se actualizaron correctamente.',
      );

      this.router.navigate(['/admin/usuarios/listado-usuario']);

    } catch (error: unknown) {

      this.alertService.error(
        this.httpErrorService.getMessage(error)
      );
    }
  }

}
