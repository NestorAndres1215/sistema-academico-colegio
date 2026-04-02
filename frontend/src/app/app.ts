import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CompanyService } from './core/services/company.service';
import { combineAll } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  constructor(
    private titleService: Title,
    private configService: CompanyService
  ) { }

  ngOnInit() {
    this.configService.getAll().subscribe(config => {
      const company = config[0];

      this.titleService.setTitle(company.name);
      const link: HTMLLinkElement | null = document.getElementById('appFavicon') as HTMLLinkElement;
      const newHref = company.logo.startsWith('http')
        ? company.logo
        : window.location.origin + '/' + company.logo;

      link.href = newHref + '?v=' + new Date().getTime();

    });
  }
}
