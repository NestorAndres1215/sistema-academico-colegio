import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  // Estado del formulario
  rol      = 'alumno';
  usuario  = '';
  password = '';
  remember = false;
  showPass = false;
  loading  = false;
  submitted = false;
  error    = '';
  success  = '';
 
  setRol(nuevoRol: string): void {
    this.rol = nuevoRol;
    this.usuario = '';
    this.error = '';
    this.success = '';
    this.submitted = false;
  }
 
  togglePass(): void {
    this.showPass = !this.showPass;
  }
 
  onSubmit(): void {
    this.submitted = true;
    this.error = '';
    this.success = '';
 
    if (!this.usuario || !this.password) return;
 
    this.loading = true;
 
    // Simulación de llamada al backend (reemplaza con tu servicio real)
    setTimeout(() => {
      this.loading = false;
 
      // Demo: credenciales de prueba
      if (this.usuario === 'demo' && this.password === '1234') {
        this.success = `¡Bienvenido! Sesión iniciada como ${this.rol}.`;
        // Aquí navegarías al dashboard: this.router.navigate(['/dashboard']);
      } else {
        this.error = 'Usuario o contraseña incorrectos. Verifica tus datos.';
      }
    }, 1800);
  }
}
