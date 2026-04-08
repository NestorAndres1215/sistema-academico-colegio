import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PositionService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAll(page: number, size: number, search: string = ''): Observable<any> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size);

    if (search.trim() !== '') {
      params = params.set('search', search.trim());
    }

    return this.http.get<any>(`${this.backendUrl}/position`, { params });
  }

  getById(id: string) {
    return this.http.get<any>(`${this.backendUrl}/position/${id}`);
  }


  create(data: any) {
    return this.http.post<any>(`${this.backendUrl}/position`, data);
  }


  update(id: string, data: any) {
    return this.http.put<AnimationPlaybackEvent>(`${this.backendUrl}/position/${id}`, data);
  }

}
