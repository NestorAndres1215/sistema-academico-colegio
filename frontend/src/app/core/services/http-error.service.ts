import { HttpErrorResponse } from '@angular/common/http';
import { Service } from '@angular/core';
import { ErrorResponse } from '../models/error-response';

@Service()
export class HttpErrorService {

    getMessage(error: unknown): string {
        if (error instanceof HttpErrorResponse) {
            const errorResponse = error.error as ErrorResponse;

            return errorResponse.message;
        }

        return 'Ocurrió un error inesperado.';
    }
}