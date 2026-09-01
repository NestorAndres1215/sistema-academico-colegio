import { inject, Service } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Service()
export class VerificationCodeService {

    private readonly http = inject(HttpClient);
    private readonly backendUrl = environment.apiUrl;

    verifyEmail(username: string) {
        return this.http.post<any>(`${this.backendUrl}/verification-code/verify/email/${username}`, {});
    }

    verifyCode(code: string) {
        return this.http.post<any>(`${this.backendUrl}/verification-code/verify/code/${code}`, {});
    }

}
