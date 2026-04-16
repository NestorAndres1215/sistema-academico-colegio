import { Component } from '@angular/core';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { DetailView } from "../../../../../shared/ui/detail-view/detail-view";
import { ActivatedRoute } from '@angular/router';
import { TeacherService } from '../../../../../core/services/teacher.service';

@Component({
  selector: 'app-teacher-detail',
  imports: [PageTitle, DetailView],
  templateUrl: './teacher-detail.html',
  styleUrl: './teacher-detail.css',
})
export class TeacherDetail {

  code: string = "";

  constructor(
    private route: ActivatedRoute,
    private teacherService: TeacherService
  ) { }

  user: any;
  userData: any[] = [];

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('id') ?? "";
    this.loadUserDetail(this.code);
  }

  loadUserDetail(code: string): void {
    this.teacherService.getById(code).subscribe({
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
      {
        label: 'Nombre completo',
        value: `${data.firstName} ${data.middleName ?? ''} ${data.paternalLastName} ${data.maternalLastName}`.trim()
      },
      { label: 'DNI', value: data.dni },
      { label: 'Edad', value: data.age ?? 'No disponible' },
      { label: 'Fecha de nacimiento', value: data.birthDate ?? 'No disponible' },
      { label: 'Teléfono', value: data.phone },
      { label: 'Correo', value: data.user?.email ?? data.email ?? 'No disponible' },
      { label: 'Especialización', value: data.specialization ?? 'No disponible' },
      { label: 'Fecha de contratación', value: data.hireDate ?? 'No disponible' },
      { label: 'Género', value: data.gender ?? 'No disponible' },
      { label: 'Nacionalidad', value: data.nationality ?? 'No disponible' },


      {
        label: 'Estado',
        value: data.status ?? 'No disponible'
      }
    ];
  }
}
