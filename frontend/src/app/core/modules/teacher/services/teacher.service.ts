import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { TeacherRequest } from '../models/teacher-request';
import { Observable } from 'rxjs';
import { TeacherResponse } from '../models/teacher-response';
import { PageResponse } from '../../../models/page-response';

@Service()
export class TeacherService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  findByAllStatus(
    page = 0,
    size = 10,
    search?: string,
    status?: string,
  ): Observable<PageResponse<TeacherResponse>> {
    let params = new HttpParams().set('page', page).set('size', size);

    if (search) {
      params = params.set('search', search);
    }

    if (status) {
      params = params.set('status', status);
    }

    return this.http.get<PageResponse<TeacherResponse>>(`${this.backendUrl}/teachers`, { params });
  }

  create(request: TeacherRequest, foto: File | null, cv: File | null): Observable<TeacherResponse> {
    const formData = this.toFormData(request, foto, cv);

    return this.http.post<TeacherResponse>(`${this.backendUrl}/teachers`, formData);
  }

  activate(id: number): Observable<TeacherResponse> {
    return this.http.put<TeacherResponse>(`${this.backendUrl}/users/activate/${id}`, {});
  }

  deactivate(id: number): Observable<TeacherResponse> {
    return this.http.put<TeacherResponse>(`${this.backendUrl}/users/deactivate/${id}`, {});
  }

  private toFormData(request: TeacherRequest, foto: File | null, cv: File | null): FormData {
    const formData = new FormData();

    formData.append(
      'teacher',
      new Blob([JSON.stringify(request)], {
        type: 'application/json',
      }),
    );

    if (foto) {
      formData.append('foto', foto);
    }

    if (cv) {
      formData.append('cv', cv);
    }

    return formData;
  }
}
