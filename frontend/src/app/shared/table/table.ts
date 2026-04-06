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


  @Input() columns: { etiqueta: string; clave: string }[] = [];
  @Input() buttonsConfig: any = {};
  @Input() data: any[] = [];
  @Input() onDetail!: (fila: any) => void;
  @Input() onUpdate!: (fila: any) => void;
  @Input() onDeactivate!: (fila: any) => void;
  @Input() onActivate!: (fila: any) => void;
  @Input() onPrint!: (fila: any) => void;


  hasActionButtons(): boolean {
    return (
      this.buttonsConfig.update ||
      this.buttonsConfig.activate ||
      this.buttonsConfig.deactivate ||
      this.buttonsConfig.print ||
      this.buttonsConfig.detail
    );
  }

  getValue(obj: any, clave: string): any {
    return clave.split('.').reduce((valor, parte) => valor?.[parte], obj);
  }


}
