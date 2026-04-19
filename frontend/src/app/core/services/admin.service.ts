import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }


getAll(status: boolean | null, page: number, size: number, search: string = ''): Observable<any> {

  let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString());

  if (status !== null && status !== undefined) {
    params = params.set('status', status.toString());
  }

  if (search.trim() !== '') {
    params = params.set('search', search.trim());
  }

  return this.http.get<any>(`${this.backendUrl}/administrator`, { params });
}

  getById(id: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/${id}`);
  }

  create(data: FormData) {
    return this.http.post(`${this.backendUrl}/administrator`, data);
  }

  deactivate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrator/deactivate/${id}`, {});
  }

  activate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrator/activate/${id}`, {});
  }

}
