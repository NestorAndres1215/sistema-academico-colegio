import { Component, inject, signal } from '@angular/core';
import { TeacherService } from '../../../../core/modules/teacher/services/teacher.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TeacherListResponse } from '../../../../core/modules/teacher/models/teacher-list-response';
import { firstValueFrom } from 'rxjs';
import { TeacherDetailService } from '../../../../core/modules/teacher/services/teacher-detail.service';
import { MatIconModule } from '@angular/material/icon';
import { PageHeader } from "../../../../shared/ui/page-header/page-header";

@Component({
  imports: [MatIconModule, PageHeader],
  selector: 'app-teacher-detail',
  styleUrl: './teacher-detail.css',
  templateUrl: './teacher-detail.html',
})
export class TeacherDetail {

 readonly icon = 'description';
  readonly title = 'Detalle de profesor';
  readonly subtitle = 'Consulta y revisa la información del profesor.';
  private readonly teacherDetailService = inject(TeacherDetailService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  readonly teacherContract = signal<TeacherListResponse | null>(null);
  readonly id = signal<number>(0);

  async ngOnInit(): Promise<void> {
    this.id.set(Number(this.route.snapshot.paramMap.get('id')));
    await this.loadTeacherContract()
  }

  async loadTeacherContract(): Promise<void> {
    const admin = await firstValueFrom(this.teacherDetailService.findByTeacherId(this.id()));
    this.teacherContract.set(admin);
  }

}
