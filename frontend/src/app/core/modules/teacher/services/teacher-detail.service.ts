import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { TeacherListResponse } from '../models/teacher-list-response';

@Service()
export class TeacherDetailService {

    private readonly http = inject(HttpClient);
    private readonly backendUrl = environment.apiUrl;

    downloadCurriculum(teacherId: number): Observable<Blob> {
        return this.http.get(`${this.backendUrl}/teacher-details/${teacherId}/curriculum/download`, {
            responseType: 'blob',
        });
    }

    findByTeacherId(teacherId: number): Observable<TeacherListResponse> {
        return this.http.get<TeacherListResponse>(
            `${this.backendUrl}/teacher-details/teacher/${teacherId}`
        );
    }

}
