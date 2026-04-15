import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from '../../../../../core/services/admin.service';
import { DetailData } from "../../../../../shared/ui/detail-data/detail-data";
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";

@Component({
  selector: 'app-user-detail',
  imports: [DetailData, PageTitle],
  templateUrl: './user-detail.html',
  styleUrl: './user-detail.css',
})
export class UserDetail {
  code: string = "";

  constructor(
    private route: ActivatedRoute,
    private adminService: AdminService
  ) { }

  user: any;
  userData: any[] = [];

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('id') ?? "";
    this.loadUserDetail(this.code);
  }

  loadUserDetail(code: string): void {
    this.adminService.getById(code).subscribe({
      next: (res: any) => {
        this.user = res;
        console.log(this.user);
        this.userData = this.buildUserData(res);
        console.log(this.userData);
      }
    });
  }

  buildUserData(data: any) {
    return [
      { label: 'Nombre completo', value: `${data.firstName} ${data.middleName ?? ''} ${data.paternalLastName} ${data.maternalLastName}`.trim() },
      { label: 'DNI', value: data.dni },
      { label: 'Edad', value: data.age ?? 'No disponible' },
      { label: 'Fecha de nacimiento', value: data.birthDate },
      { label: 'Teléfono', value: data.phone },
      { label: 'Correo', value: data.user?.email ?? 'No disponible' },
      { label: 'Perfil', value: data.profile },
      { label: 'Género', value: data.gender },
      { label: 'Nacionalidad', value: data.nationality },
      { label: 'Estado', value: data.status ? 'Activo' : 'Inactivo' }
    ];
  }
}