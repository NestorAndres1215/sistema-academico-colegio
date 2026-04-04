import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator`)
  }

  getById(id: number): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/${id}`);
  }

  getByDni(dni: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/dni/${dni}`);
  }

  getByPhone(phone: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/phone/${phone}`);
  }

  getByGender(gender: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/gender/${gender}`);
  }

  findByFirstName(firstName: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/name?firstName=${firstName}`);
  }

  findByFirstNameAndPaternalLastName(firstName: string, paternalLastName: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/name-lastname?firstName=${firstName}&paternalLastName=${paternalLastName}`);
  }

  findByFullName(firstName: string, paternalLastName: string, maternalLastName: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrator/full-name?firstName=${firstName}&paternalLastName=${paternalLastName}&maternalLastName=${maternalLastName}`);
  }



}
