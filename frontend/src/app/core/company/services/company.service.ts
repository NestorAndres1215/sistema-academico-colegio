import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Service } from '@angular/core';

import { Observable } from 'rxjs';

import { CompanyRequest } from '../models/company-request';
import { environment } from '../../../../environments/environment';
import { CompanyModel } from '../models/company.model';

@Service()
export class CompanyService {
  private readonly http = inject(HttpClient);
  private readonly backendUrl = environment.apiUrl;

  findPage(page = 0, size = 10, search?: string, status?: string): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);

    if (search) {
      params = params.set('search', search);
    }

    if (status) {
      params = params.set('status', status);
    }

    return this.http.get<any>(`${this.backendUrl}/companies`, { params });
  }

  findByCode(code: string): Observable<CompanyModel> {
    return this.http.get<CompanyModel>(`${this.backendUrl}/companies/code/${code}`);
  }

  findById(id: number): Observable<CompanyModel> {
    return this.http.get<CompanyModel>(`${this.backendUrl}/companies/${id}`);
  }

  create(request: CompanyRequest, logo?: File): Observable<CompanyModel> {
    const formData = this.toFormData(request, logo);

    return this.http.post<CompanyModel>(`${this.backendUrl}/companies`, formData);
  }

  update(id: number, request: CompanyRequest, logo?: File): Observable<CompanyModel> {
    const formData = this.toFormData(request, logo);

    return this.http.put<CompanyModel>(`${this.backendUrl}/companies/${id}`, formData);
  }

  private toFormData(request: CompanyRequest, logo?: File): FormData {
    const formData = new FormData();

    formData.append('company', new Blob([JSON.stringify(request)], { type: 'application/json' }));

    if (logo) {
      formData.append('file', logo);
    }

    return formData;
  }
}
