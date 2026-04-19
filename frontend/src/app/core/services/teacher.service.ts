import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TeacherService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/teacher`)
  }

  getByStatus(status: string, page: number, size: number, search: string = ''): Observable<any> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('status', status.toString());

    if (search.trim() !== '') {
      params = params.set('search', search.trim());
    }

    return this.http.get<any>(`${this.backendUrl}/teacher`, { params });
  }

  getById(id: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/teacher/${id}`);
  }

  create(data: FormData) {
    return this.http.post(`${this.backendUrl}/teacher`, data);
  }

  update(id: string, data: FormData) {
    return this.http.put(`${this.backendUrl}/teacher/${id}`, data);
  }

  deactivate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/teacher/deactivate/${id}`, {});
  }

  activate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/teacher/activate/${id}`, {});
  }

}
