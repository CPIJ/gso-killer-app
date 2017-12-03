import { Component, OnInit } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import * as $ from 'jquery';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInUser: string;
  projectName: string;
  previousProjectName: string;
  users = ['Henk de Vries', 'Freek Janssen', 'Bert Vissers'];

  constructor() { }

  ngOnInit() {
    this.loggedInUser = 'Casper Pijnenburg';
  }

  shortenName(name: string) {
    return name.substring(0,2).toUpperCase();
  }

  calcColor(name: string) {
    const colors = ['#539f02', '#02a199', '#9f2702'];

    const n = name
      .split('')
      .map(char => char.charCodeAt(0))
      .reduce((accumulared, current) => accumulared + current);

    return colors[n % colors.length];
  }

  onChange(test) {
    $(test).html($(test).html().replace('<div><br></div>', ''));

    if (this.projectName === undefined) {
      console.warn('save to db');
    }

    if ($(test).html() !== this.projectName) {
      this.projectName = $(test).html();
    }
  }
}
