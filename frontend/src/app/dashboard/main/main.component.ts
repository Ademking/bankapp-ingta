import { Component, OnInit } from '@angular/core';
import { fadeInAnimation } from 'src/app/animations/fadein.animation';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
  animations: [fadeInAnimation],
  host: { '[@fadeInAnimation]': '' }
})




export class MainComponent implements OnInit {

  constructor() { }

  isMobile: boolean = false;
  getIsMobile(): boolean {
    const w = document.documentElement.clientWidth;
    const breakpoint = 992;
    console.log(w);
    if (w < breakpoint) {
      return true;
    } else {
      return false;
    }
  }

  ngOnInit() {
    this.isMobile = this.getIsMobile();
    window.onresize = () => {
      this.isMobile = this.getIsMobile();
    };
  }

}
