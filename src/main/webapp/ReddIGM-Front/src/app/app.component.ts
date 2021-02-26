import { Component, EventEmitter, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ReddIGM-Front';
  isModalActive: boolean = false;

  ngOnInit(){
  }

  openModalCreatePost() {
	this.isModalActive = !this.isModalActive;
  }
//   public openModalEvent: EventEmitter<number> = new EventEmitter<number>();

//   openModalInMainComponent(id: number | undefined): void {
// 	this.openModalEvent.emit(id);
//  }

}
