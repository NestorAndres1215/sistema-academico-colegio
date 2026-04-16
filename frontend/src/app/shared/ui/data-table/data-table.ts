import { Component, Input } from '@angular/core';
import { Button } from "../button/button";
import { CommonModule } from '@angular/common';
import { EmptyState } from "../empty-state/empty-state";

@Component({
  selector: 'app-data-table',
  imports: [Button, CommonModule, EmptyState],
  templateUrl: './data-table.html',
  styleUrl: './data-table.css',
})
export class DataTable {

  @Input() columns: { etiqueta: string; clave: string }[] = [];
  @Input() buttonsConfig: any = {};
  @Input() data: any[] = [];
  @Input() onDetail!: (fila: any) => void;
  @Input() onUpdate!: (fila: any) => void;
  @Input() onDeactivate!: (fila: any) => void;
  @Input() onDownloadPdf!: (fila: any) => void;
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
