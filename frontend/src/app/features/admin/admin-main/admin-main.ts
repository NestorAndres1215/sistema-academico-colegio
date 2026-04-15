import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageTitle } from "../../../shared/ui/page-title/page-title";

@Component({
  selector: 'app-admin-main',
  imports: [CommonModule, PageTitle],
  templateUrl: './admin-main.html',
  styleUrl: './admin-main.css',
})
export class AdminMain {


  kpis = [
    { label: 'Total Alumnos',    value: '1,200',     trend: '3.2%', trendUp: true,  icon: 'fas fa-user-graduate',       iconBg: 'rgba(22,50,96,.1)',    iconColor: '#163260' },
    { label: 'Docentes',         value: '64',         trend: '1.5%', trendUp: true,  icon: 'fas fa-chalkboard-teacher',  iconBg: 'rgba(200,153,42,.12)', iconColor: '#c8992a' },
    { label: 'Asistencia Hoy',   value: '94%',        trend: '0.8%', trendUp: true,  icon: 'fas fa-calendar-check',      iconBg: 'rgba(16,185,129,.1)',  iconColor: '#059669' },
    { label: 'Pagos Pendientes', value: 'S/ 12,400',  trend: '5.1%', trendUp: false, icon: 'fas fa-credit-card',         iconBg: 'rgba(239,68,68,.1)',   iconColor: '#dc2626' },
  ];

  accesos = [
    { label: 'Alumnos', page: 'alumnos', icon: 'fas fa-user-graduate', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
    { label: 'Docentes', page: 'docentes', icon: 'fas fa-chalkboard-teacher', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
    { label: 'Pagos', page: 'pagos', icon: 'fas fa-credit-card', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
    { label: 'Reportes', page: 'reportes', icon: 'fas fa-chart-bar', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
    { label: 'Cursos', page: 'cursos', icon: 'fas fa-book-open', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
    { label: 'Mensajes', page: 'mensajes', icon: 'fas fa-envelope', bg: 'rgba(200, 153, 42, .12)', color: '#c8992a' },
  ];

  pagoResumen = [
    { label: 'Cobrado', valor: 'S/ 48,200', pct: '75%', color: '#059669' },
    { label: 'Pendiente', valor: 'S/ 12,400', pct: '20%', color: '#d97706' },
    { label: 'Vencido', valor: 'S/ 4,200', pct: '7%', color: '#dc2626' },
  ];

  actividad = [
    { texto: 'Nuevo alumno matriculado: Ana Paredes', tiempo: 'Hace 5 min', icon: 'fas fa-user-plus', bg: 'rgba(22,50,96,.1)', color: '#163260' },
    { texto: 'Pago recibido — S/ 850', tiempo: 'Hace 18 min', icon: 'fas fa-check-circle', bg: 'rgba(16,185,129,.1)', color: '#059669' },
    { texto: 'Docente Ramírez actualizó notas', tiempo: 'Hace 1 hora', icon: 'fas fa-pen', bg: 'rgba(200,153,42,.12)', color: '#c8992a' },
    { texto: 'Reporte mensual generado', tiempo: 'Hace 2 horas', icon: 'fas fa-file-alt', bg: 'rgba(30,77,140,.1)', color: '#1e4d8c' },
    { texto: 'Pago vencido: Lucía Flores', tiempo: 'Hace 3 horas', icon: 'fas fa-exclamation', bg: 'rgba(239,68,68,.1)', color: '#dc2626' },
  ];


}
