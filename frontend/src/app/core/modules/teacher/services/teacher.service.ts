import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { TeacherRequest } from '../models/teacher-request';
import { Observable } from 'rxjs';

@Service()
export class TeacherService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  create(request: TeacherRequest, foto: File | null, cv: File | null): Observable<any> {
    const formData = this.toFormData(request, foto, cv);

    return this.http.post<any>(`${this.backendUrl}/teachers`, formData);
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
