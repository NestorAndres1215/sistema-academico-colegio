import { inject, Service } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AlertService } from './alert.service';

@Service()
export class FormValidationService {
  private readonly alertService = inject(AlertService);

  validate(form: FormGroup): boolean {
    if (form.valid) {
      return true;
    }

    form.markAllAsTouched();

    this.alertService.warning('Campos incompletos', 'Revisa los campos marcados');

    return false;
  }
}
