import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { authInterceptor } from './core/auth/interceptors/auth.interceptor';
import { DATE_PROVIDERS } from './core/config/date.config';


export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({
      eventCoalescing: true,
    }),
    provideRouter(routes),

    provideHttpClient(
      withInterceptors([
        authInterceptor,
      ]),
    ),

    ...DATE_PROVIDERS,
  ],
};