import { Component, input, output } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { Button } from '../button/button';
import { SearchResultAction } from './search-result.types';
import { SearchResultItem } from '../../models/search-result-model';

@Component({
  imports: [MatIconModule, MatButtonModule, Button],
  selector: 'app-search-result',
  styleUrl: './search-result.css',
  templateUrl: './search-result.html',
})
export class SearchResult {

  readonly items = input<SearchResultItem[]>([]);
  readonly loading = input<boolean>(false);
  readonly emptyMessage = input<string>('No se encontraron resultados');

  readonly actions = input<SearchResultAction[]>([
    'message',
    'viewProfile',
    'viewContract',
    'closeSession'
  ]);

  readonly downloadTooltip = input('Descargar');
  readonly messageTooltip = input('Enviar mensaje');
  readonly viewProfileTooltip = input('Ver Perfil');
  readonly viewContractTooltip = input('Ver contrato');
  readonly activateTooltip = input('Activar');
  readonly deactivateTooltip = input('Desactivar');
  readonly blockedTooltip = input('Bloquear');
  readonly closeSessionTooltip = input('Cerrar sesión');

  readonly actionDisabled = input<
    (action: SearchResultAction, item: SearchResultItem) => boolean
  >(() => false);

  readonly download = output<SearchResultItem>();
  readonly message = output<SearchResultItem>();
  readonly viewProfile = output<SearchResultItem>();
  readonly viewContract = output<SearchResultItem>();
  readonly activate = output<SearchResultItem>();
  readonly deactivate = output<SearchResultItem>();
  readonly blocked = output<SearchResultItem>();
  readonly closeSession = output<SearchResultItem>();

  hasAction(action: SearchResultAction): boolean {
    return this.actions().includes(action);
  }

  isActionDisabled(
    action: SearchResultAction,
    item: SearchResultItem
  ): boolean {
    return this.actionDisabled()(action, item);
  }

  getInitial(name: string): string {
    return name.charAt(0).toUpperCase();
  }

  onDownload(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('download', item)) return;

    this.download.emit(item);
  }

  onMessage(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('message', item)) return;

    this.message.emit(item);
  }

  onViewProfile(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('viewProfile', item)) return;

    this.viewProfile.emit(item);
  }

  onViewContract(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('viewContract', item)) return;

    this.viewContract.emit(item);
  }

  onActivate(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('activate', item)) return;

    this.activate.emit(item);
  }

  onDeactivate(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('deactivate', item)) return;

    this.deactivate.emit(item);
  }

  onBlocked(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('blocked', item)) return;

    this.blocked.emit(item);
  }

  onCloseSession(item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled('closeSession', item)) return;

    this.closeSession.emit(item);
  }

  trackByFn(index: number, item: SearchResultItem): number | string {
    return item.id ?? index;
  }
}