import { Service } from '@angular/core';
import { environment } from '../../../environments/environment';

@Service()
export class FileService {

  private readonly baseUrl = environment.apiUrl;

  getFileUrl(fileUrl: string | null | undefined): string | null {
    if (!fileUrl) {
      return null;
    }

    if (fileUrl.startsWith('http://') || fileUrl.startsWith('https://')) {
      return fileUrl;
    }

    return `${this.baseUrl}${fileUrl}`;
  }
}