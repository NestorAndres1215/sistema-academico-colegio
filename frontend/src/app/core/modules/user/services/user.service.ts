import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { UserResponse } from '../models/user-response';
import { CreateUserRequest } from '../models/create-user-request';
import { UpdateUserRequest } from '../models/update-user-request';
import { PasswordRequest } from '../models/password-request';
import { PageResponse } from '../../../models/page-response';






@Service()
export class UserService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  getByStatus(
    status: string,
    page: number = 0,
    size: number = 10,
    search?: string,
  ): Observable<PageResponse<UserResponse>> {
    let params = new HttpParams().set('status', status).set('page', page).set('size', size);

    if (search) {
      params = params.set('search', search);
    }

    return this.http.get<PageResponse<UserResponse>>(`${this.backendUrl}/users`, { params });
  }

  search(search?: string): Observable<UserResponse[]> {
    let params = new HttpParams();

    if (search) {
      params = params.set('search', search);
    }

    return this.http.get<UserResponse[]>(`${this.backendUrl}/users/search`, { params });
  }

  create(request: CreateUserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.backendUrl}/users`, request);
  }

  update(id: number, request: UpdateUserRequest): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.backendUrl}/users/${id}`, request);
  }

  findById(id: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.backendUrl}/users/${id}`);
  }

  changePassword(id: number, request: PasswordRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.backendUrl}/users/${id}/change-password`, request);
  }

  activate(id: number): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.backendUrl}/users/activate/${id}`, {});
  }

  deactivate(id: number): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.backendUrl}/users/deactivate/${id}`, {});
  }
}
