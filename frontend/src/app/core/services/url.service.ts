import { Service } from '@angular/core';
import { environment } from '../../../environments/environment';

@Service()
export class UrlService {
  private readonly apiUrl = environment.apiUrl;

  resolve(path: string | null | undefined): string | null {
    if (!path) {
      return null;
    }

    if (path.startsWith('http://') || path.startsWith('https://')) {
      return path;
    }

    return `${this.apiUrl}${path}`;
  }
}
