import { Component, input, output } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { Button } from '../button/button';
import { SearchResultAction, SearchResultActionConfig } from './search-result.types';

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
    'viewObservation',
    'viewContract',
    'closeSession',
  ]);

  readonly downloadTooltip = input('Descargar');
  readonly messageTooltip = input('Enviar mensaje');
  readonly viewProfileTooltip = input('Ver Perfil');
  readonly viewObservationTooltip = input('Ver observaciones');
  readonly viewContractTooltip = input('Ver contrato');
  readonly activateTooltip = input('Activar');
  readonly deactivateTooltip = input('Desactivar');
  readonly blockedTooltip = input('Bloquear');
  readonly closeSessionTooltip = input('Cerrar sesión');

  readonly actionDisabled = input<(action: SearchResultAction, item: SearchResultItem) => boolean>(
    () => false,
  );

  readonly download = output<SearchResultItem>();
  readonly message = output<SearchResultItem>();
  readonly viewProfile = output<SearchResultItem>();
  readonly viewObservation = output<SearchResultItem>();
  readonly viewContract = output<SearchResultItem>();
  readonly activate = output<SearchResultItem>();
  readonly deactivate = output<SearchResultItem>();
  readonly blocked = output<SearchResultItem>();
  readonly closeSession = output<SearchResultItem>();

  private readonly actionConfigs: Record<SearchResultAction, SearchResultActionConfig> = {
    download: {
      icon: 'download',
      variant: 'secondary',
    },

    message: {
      icon: 'chat_bubble_outline',
      variant: 'secondary',
    },

    viewProfile: {
      icon: 'person',
      variant: 'primary',
    },

    viewObservation: {
      icon: 'visibility',
      variant: 'secondary',
    },

    viewContract: {
      icon: 'description',
      variant: 'secondary',
    },

    activate: {
      icon: 'check_circle',
      variant: 'success',
    },

    deactivate: {
      icon: 'block',
      variant: 'danger',
    },

    blocked: {
      icon: 'lock_person',
      variant: 'danger',
    },

    closeSession: {
      icon: 'logout',
      variant: 'danger',
    },
  };

  hasAction(action: SearchResultAction): boolean {
    return this.actions().includes(action);
  }

  getActionConfig(action: SearchResultAction): SearchResultActionConfig {
    return this.actionConfigs[action];
  }

  getActionLabel(action: SearchResultAction): string {
    switch (action) {
      case 'download':
        return this.downloadTooltip();

      case 'message':
        return this.messageTooltip();

      case 'viewProfile':
        return this.viewProfileTooltip();

      case 'viewObservation':
        return this.viewObservationTooltip();

      case 'viewContract':
        return this.viewContractTooltip();

      case 'activate':
        return this.activateTooltip();

      case 'deactivate':
        return this.deactivateTooltip();

      case 'blocked':
        return this.blockedTooltip();

      case 'closeSession':
        return this.closeSessionTooltip();
    }
  }

  isActionDisabled(action: SearchResultAction, item: SearchResultItem): boolean {
    return this.actionDisabled()(action, item);
  }

  getInitial(name: string): string {
    return name.charAt(0).toUpperCase();
  }

  onAction(action: SearchResultAction, item: SearchResultItem, event: Event): void {
    event.stopPropagation();

    if (this.isActionDisabled(action, item)) {
      return;
    }

    switch (action) {
      case 'download':
        this.download.emit(item);
        break;

      case 'message':
        this.message.emit(item);
        break;

      case 'viewProfile':
        this.viewProfile.emit(item);
        break;

      case 'viewObservation':
        this.viewObservation.emit(item);
        break;

      case 'viewContract':
        this.viewContract.emit(item);
        break;

      case 'activate':
        this.activate.emit(item);
        break;

      case 'deactivate':
        this.deactivate.emit(item);
        break;

      case 'blocked':
        this.blocked.emit(item);
        break;

      case 'closeSession':
        this.closeSession.emit(item);
        break;
    }
  }

  trackByFn(index: number, item: SearchResultItem): number | string {
    return item.id ?? index;
  }
}
