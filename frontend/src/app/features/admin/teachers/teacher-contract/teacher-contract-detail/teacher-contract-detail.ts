import { Component, inject, signal } from '@angular/core';
import { PageHeader } from '../../../../../shared/ui/page-header/page-header';
import { TeacherContractService } from '../../../../../core/modules/teacher/services/teacher-contract.service';
import { ActivatedRoute, Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { TeacherContractResponse } from '../../../../../core/modules/teacher/models/teacher-contract-response';
import { MatIconModule } from '@angular/material/icon';

@Component({
  imports: [PageHeader,MatIconModule],
  selector: 'app-teacher-contract-detail',
  styleUrl: './teacher-contract-detail.css',
  templateUrl: './teacher-contract-detail.html',
})
export class TeacherContractDetail {
  readonly icon = 'description';
  readonly title = 'Detalle del contrato';
  readonly subtitle = 'Consulta y revisa la información del contrato del profesor.';
  private readonly teacherContractService = inject(TeacherContractService);
  private readonly router = inject(Router);
  private readonly route = inject(ActivatedRoute);
  readonly teacherContract = signal<TeacherContractResponse | null>(null);
  readonly id = signal<number>(0);

  async ngOnInit(): Promise<void> {
    this.id.set(Number(this.route.snapshot.paramMap.get('id')));
    await this.loadTeacherContract()
  }

  async loadTeacherContract(): Promise<void> {
    const admin = await firstValueFrom(this.teacherContractService.findById(this.id()));
    this.teacherContract.set(admin);
  }
}
