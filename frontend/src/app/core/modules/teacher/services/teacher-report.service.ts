import { HttpClient } from '@angular/common/http';
import { inject, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';

@Service()
export class TeacherReportService {
    private readonly http = inject(HttpClient);
    private readonly backendUrl = environment.apiUrl;


    downloadContractPdf(contractId: number): Observable<Blob> {
        return this.http.get(`${this.backendUrl}/teacher-report/contract/${contractId}/pdf`, {
            responseType: 'blob',
        });
    }
}
