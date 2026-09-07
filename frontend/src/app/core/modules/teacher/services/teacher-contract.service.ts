import { inject, Service } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageResponse } from '../../../models/page-response';
import { TeacherContractResponse } from '../models/teacher-contract-response';
import { TeacherContractFilter } from '../models/teacher-contract-filter';
import { toApiDate } from '../../../utils/date.util';

@Service()
export class TeacherContractService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;
  findWithFilters(
    filter: TeacherContractFilter,
  ): Observable<PageResponse<TeacherContractResponse>> {
    let params = new HttpParams()
      .set('page', filter.page ?? 0)
      .set('size', filter.size ?? 10)
      .set('sort', filter.sort ?? 'desc');

    const startDate = toApiDate(filter.startDate);
    const endDate = toApiDate(filter.endDate);

    if (startDate) {
      params = params.set('startDate', startDate);
    }

    if (endDate) {
      params = params.set('endDate', endDate);
    }

    return this.http.get<PageResponse<TeacherContractResponse>>(
      `${this.backendUrl}/teacher-contract/${filter.teacherCode}`,
      { params },
    );
  }
}
