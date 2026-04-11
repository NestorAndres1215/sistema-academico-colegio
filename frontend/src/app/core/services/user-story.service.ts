import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserStoryService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getWithFilters(email: string, status: boolean, action: string, page: number, size: number) {
    let params = new HttpParams()
      .set('email', email)
      .set('status', status)
      .set('action', action)
      .set('page', page)
      .set('size', size);

    return this.http.get<any>(`${this.backendUrl}/user-story`, { params });
  }


  create(request: any): Observable<any> {
    return this.http.post(`${this.backendUrl}/user-story`, request);
  }

  activate(id: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/user-story/${id}/activate`, {});
  }

  deactivate(id: string): Observable<any> {
  return this.http.put(`${this.backendUrl}/user-story/${id}/deactivate`, {});
}

}
