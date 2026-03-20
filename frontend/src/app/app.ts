import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { EmpresaService } from './core/services/empresa.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  constructor(
    private titleService: Title,
    private configService: EmpresaService
  ) { }

  ngOnInit() {
    const config = this.configService.getConfig();

    this.titleService.setTitle(config.title);

    const link: HTMLLinkElement =
      document.querySelector("link[rel*='icon']") || document.createElement('link');

    link.type = 'image/png';
    link.rel = 'icon';
    link.href = config.icon;

    document.head.appendChild(link);
  }
}
