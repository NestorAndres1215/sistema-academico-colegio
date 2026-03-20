import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class EmpresaService {
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
}
