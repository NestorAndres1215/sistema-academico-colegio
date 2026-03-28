import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon } from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  private baseConfig = {
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33'
  };

  private show(icon: SweetAlertIcon, title: string, text?: string) {
    return Swal.fire({
      icon,
      title,
      text,
      confirmButtonColor: this.baseConfig.confirmButtonColor
    });
  }

  success(title: string, text?: string) {
    return this.show('success', title, text);
  }

  error(title: string, text?: string) {
    return this.show('error', title, text);
  }

  warning(title: string, text?: string) {
    return this.show('warning', title, text);
  }

  info(title: string, text?: string) {
    return this.show('info', title, text);
  }

  confirm(title: string, text: string): Promise<boolean> {
    return Swal.fire({
      title,
      text,
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
      confirmButtonColor: this.baseConfig.confirmButtonColor,
      cancelButtonColor: this.baseConfig.cancelButtonColor
    }).then(result => result.isConfirmed);
  }
}