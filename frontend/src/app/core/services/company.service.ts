import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  private config = {
    title: "Sistema Escolar",
    icon: "assets/logo.png"
  };

  loadConfig() {
    return this.config;
  }

  getConfig() {
    return this.config;
  }

  getAll(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/company`);
  }

}
