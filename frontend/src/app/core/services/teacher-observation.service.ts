import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TeacherObservationService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAll(id: string, status: string, page: number, size: number): Observable<any> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('teacherId', id)
      .set('status', status.toString());

    return this.http.get<any>(`${this.backendUrl}/teacher-observations`, { params });
  }

  getById(id: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/teacher-observations/${id}`);
  }

  deactivate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/teacher-observations/${id}/deactivate`, {});
  }

  activate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/teacher-observations/${id}/activate`, {});
  }

}
