import 'zone.js';

import { bootstrapApplication } from '@angular/platform-browser';

import { appConfig } from './app/app.config';
import { App } from './app/app';
import { environment } from './environments/environment';

const frontendUrl = 'http://localhost:4200';
const backendUrl = environment.apiUrl;


console.log('==================================================');
console.log('       FRONTEND INICIADO CORRECTAMENTE');
console.log('==================================================');
console.log(`Frontend : ${frontendUrl}`);
console.log(`Backend  : ${backendUrl}`);
console.log('==================================================');


bootstrapApplication(App, appConfig)
  .catch((error) => {
    console.error('Error al iniciar Angular:', error);
  });