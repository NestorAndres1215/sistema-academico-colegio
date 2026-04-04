import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { Button } from '../button/button';

@Component({
  selector: 'app-table',
imports: [CommonModule, Button],
  templateUrl: './table.html',
  styleUrl: './table.css',
})
export class Table {
 @Input() icono: string = '';
  @Input() titulo: string = '';
  @Input() columnas: { etiqueta: string; clave: string }[] = [];

  @Input() onRegistrar!: () => void;
  @Input() botonesConfig: any = {};
  @Input() datos: any[] = [];

  paginaActual: number = 1;
  itemsPorPagina: number = 10;
  datos_vacio: string = '  No hay datos para mostrar.';
  columnaOrden: string = '';

  @Input() onVer!: (fila: any) => void;
  @Input() onActualizar!: (fila: any) => void;
  @Input() onDesactivar!: (fila: any) => void;
  @Input() onActivar!: (fila: any) => void;
  @Input() onSuspender!: (fila: any) => void;
  @Input() onInhabilitar!: (fila: any) => void;
  @Input() onBloquear!: (fila: any) => void;
  @Input() onImprimir!: (fila: any) => void;
  @Input() onCancelar!: (fila: any) => void;

  hasActionButtons(): boolean {
    return (
      this.botonesConfig.actualizar ||
      this.botonesConfig.activar ||
      this.botonesConfig.suspender ||
      this.botonesConfig.desactivar ||
      this.botonesConfig.inhabilitar ||
      this.botonesConfig.bloquear ||
      this.botonesConfig.imprimir ||
      this.botonesConfig.ver ||
      this.botonesConfig.cancelar
    );
  }

  obtenerValor(obj: any, clave: string): any {
    return clave.split('.').reduce((valor, parte) => valor?.[parte], obj);
  }

 
}
