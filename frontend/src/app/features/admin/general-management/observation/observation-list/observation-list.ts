import { Component, OnInit } from '@angular/core';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { ActivatedRoute } from '@angular/router';
import { TeacherService } from '../../../../../core/services/teacher.service';
import { TeacherObservationService } from '../../../../../core/services/teacher-observation.service';
import { DataCard } from "../../../../../shared/ui/data-card/data-card";
import { Pagination } from "../../../../../shared/ui/pagination/pagination";


@Component({
  selector: 'app-observation-list',
  imports: [PageTitle, DataCard, Pagination],
  templateUrl: './observation-list.html',
  styleUrl: './observation-list.css',
})
export class ObservationList implements OnInit {
viewDetail($event: any) {
throw new Error('Method not implemented.');
}

  constructor(
    private route: ActivatedRoute,
    private teacherObservationService: TeacherObservationService
  ) { }

  code: string = "";
  pageActivos = 1;
  itemsPerPageActivo = 8;
  totalPages = 0;

  teacherPaginated: any[] = [];

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('id') ?? "";
    this.loadTeachers();
  }

  loadTeachers(): void {
 
    this.teacherObservationService.getAll(
     this.code,
      "ACTIVE",
      this.pageActivos - 1,
      this.itemsPerPageActivo,
    ).subscribe({
      next: (res: any) => {
        console.log(res)
         this.teacherPaginated = (res.content || []).map((t: any) => ({
          ...t,
          observation: `${t.observation} `,
          detailField: ` ${new Date(t.createdAt).toLocaleDateString()}`
        }));
        this.totalPages = res.totalPages || 0;

        if (this.totalPages === 0) this.pageActivos = 1;
      },
    });
  }

  
  onPageChange(page: number): void {
    this.pageActivos = page;
    
    this.loadTeachers();
    
  }
}
