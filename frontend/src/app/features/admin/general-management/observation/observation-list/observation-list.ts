import { Component } from '@angular/core';
import { PageTitle } from "../../../../../shared/ui/page-title/page-title";
import { DataCard } from "../../../../../shared/ui/data-card/data-card";
import { FilterSearch } from "../../../../../shared/ui/filter-search/filter-search";
import { Router } from '@angular/router';
import { Subject, debounceTime } from 'rxjs';
import { TeacherService } from '../../../../../core/services/teacher.service';

@Component({
  selector: 'app-observation-list',
  imports: [PageTitle],
  templateUrl: './observation-list.html',
  styleUrl: './observation-list.css',
})
export class ObservationList {

}
