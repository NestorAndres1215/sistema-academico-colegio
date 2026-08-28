import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ThemeOption } from './core/models/theme.model';
import { firstValueFrom } from 'rxjs';
import { CompanyService } from './core/company/services/company.service';
import { ThemeService } from './core/services/theme.service';
import { Title } from '@angular/platform-browser';

@Component({
  imports: [RouterOutlet],
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
  private readonly themeService = inject(ThemeService);
  private readonly titleService = inject(Title);
  private readonly configService = inject(CompanyService);

  async ngOnInit(): Promise<void> {
    this.themesSystem();
    await this.getCompany();
  }

  async getCompany(): Promise<void> {
    const company = await firstValueFrom(this.configService.findByCode('COMSANANDRES'));

    this.titleService.setTitle(company.name);

    const link = document.getElementById('appFavicon') as HTMLLinkElement | null;

    if (!link || !company.logoUrl) {
      return;
    }

    const newHref = company.logoUrl.startsWith('http')
      ? company.logoUrl
      : `${window.location.origin}/${company.logoUrl}`;

    link.href = `${newHref}?v=${Date.now()}`;
  }

  themesSystem(): void {
    const THEMES: ThemeOption[] = [
      {
        key: 'default',
        name: 'Institucional',
        colorPrincipal: '#1A3A6B',
        colorSecundario: '#F4F6FA',
        colorTercero: '#F5A623',
        colorPaginaPrincipal: '#F4F6FA',
        colorTextoPrimario: '#1A3A6B',
        colorTextoSecundario: '#5A6D8C',
        colorBorder: '#D0D7E8',
        colorShadow: 'rgba(26, 44, 91, 0.15)',
        colorDanger: '#D32F2F',
        colorHover: 'rgba(17, 24, 39, 0.04)',
        colorGrafico1: '#1A3A6B',
        colorGrafico2: '#F5A623',
        colorGrafico3: '#3B6EA5',
        colorGrafico4: '#5A6D8C',
        colorGrafico5: '#8FA8C9',
      },
      {
        key: 'dark',
        name: 'Modo Oscuro',
        colorPrincipal: '#1E293B',
        colorSecundario: '#F4F6FA',
        colorTercero: '#FBBF24',
        colorPaginaPrincipal: '#0B1220',
        colorTextoPrimario: '#F8FAFC',
        colorTextoSecundario: '#94A3B8',
        colorBorder: '#334155',
        colorShadow: 'rgba(0, 0, 0, 0.45)',
        colorDanger: '#EF4444',
        colorHover: 'rgba(255, 255, 255, 0.06)',
        colorGrafico1: '#60A5FA',
        colorGrafico2: '#FBBF24',
        colorGrafico3: '#2DD4BF',
        colorGrafico4: '#A78BFA',
        colorGrafico5: '#FB923C',
      },
      {
        key: 'white-premium',
        name: 'Blanco Premium',
        colorPrincipal: '#111827',
        colorSecundario: '#FFFFFF',
        colorTercero: '#F3F4F6',
        colorPaginaPrincipal: '#FFFFFF',
        colorTextoPrimario: '#111827',
        colorTextoSecundario: '#6B7280',
        colorBorder: '#E5E7EB',
        colorShadow: 'rgba(0, 0, 0, 0.06)',
        colorDanger: '#EF4444',
        colorHover: 'rgba(17, 24, 39, 0.04)',
        colorGrafico1: '#0F172A',
        colorGrafico2: '#B45309',
        colorGrafico3: '#0E7490',
        colorGrafico4: '#6D28D9',
        colorGrafico5: '#9CA3AF',
      },
    ];

    this.themeService.init(THEMES);
  }
}
