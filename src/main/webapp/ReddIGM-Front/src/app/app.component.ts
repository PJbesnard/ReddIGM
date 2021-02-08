import { Component } from '@angular/core';

@Component({
  // nom du composant html appelé pour l'injection
  selector: 'app-root',

  // chemin jusqu'au code à injecter
  templateUrl: './app.component.html',

  // chemin de son CSS 
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ReddIGM-Front';
}
