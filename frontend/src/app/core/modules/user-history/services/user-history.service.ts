import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { UserHistoryResponse } from '../models/user-history-response';
import { UserHistoryFilter } from '../models/user-history-request';
import { PageResponse } from '../../../models/page-response';

@Service()
export class UserHistoryService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  findWithFilters(
    userHistoryFilter: UserHistoryFilter,
  ): Observable<PageResponse<UserHistoryResponse>> {
    let params = new HttpParams()
      .set('email', userHistoryFilter.email ?? '')
      .set('page', userHistoryFilter.page ?? 0)
      .set('size', userHistoryFilter.size ?? 10)
      .set('sort', userHistoryFilter.sort ?? 'desc');

    if (userHistoryFilter.status) {
      params = params.set('status', userHistoryFilter.status);
    }

    if (userHistoryFilter.action) {
      params = params.set('action', userHistoryFilter.action);
    }

    if (userHistoryFilter.dateFrom) {
      params = params.set('dateFrom', this.formatDate(userHistoryFilter.dateFrom));
    }

    if (userHistoryFilter.dateTo) {
      params = params.set('dateTo', this.formatDate(userHistoryFilter.dateTo));
    }

    return this.http.get<PageResponse<UserHistoryResponse>>(`${this.backendUrl}/user-history`, {
      params,
    });
  }

  private formatDate(date: Date): string {
    const d = new Date(date);

    const pad = (n: number) => n.toString().padStart(2, '0');

    return (
      d.getFullYear() +
      '-' +
      pad(d.getMonth() + 1) +
      '-' +
      pad(d.getDate()) +
      'T' +
      pad(d.getHours()) +
      ':' +
      pad(d.getMinutes()) +
      ':' +
      pad(d.getSeconds())
    );
  }
}
