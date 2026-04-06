import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RoleService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }


  getAll(page: number, size: number, search: string = ''): Observable<any> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size);

    if (search.trim() !== '') {
      params = params.set('search', search.trim());
    }

    return this.http.get<any>(`${this.backendUrl}/role`, { params });
  }


  getById(id: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/role/${id}`);
  }

  getByName(name: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/role/${name}`);
  }

}
