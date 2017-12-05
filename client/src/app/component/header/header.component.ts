import { Component, OnInit, Input } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import TextUtillity from '../../utillity/text.utillity';
import * as $ from 'jquery';
import Event from '../../utillity/Event';
import Context from '../../utillity/Context'
import { User } from '../../model/user';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() isInProject: boolean;

  loggedInUser: User;
  projectName: string;
  previousProjectName: string;
  users: String[];

  constructor() { }

  ngOnInit() {
    Event.listen("projectNameChanged", name => this.projectName = name);
    Event.listen("connectedClientsChanged", clients => this.users = clients.filter(c => c.username !== this.loggedInUser).map(c => c.username));
    this.isInProject = true;
    this.loggedInUser = Context.currentUser;
    Event.emit("headerReady");
  }

  shortenName(name: string) {
    if (name !== undefined) {
      return TextUtillity.shortenName(name);
    }
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

  compile() {
    Event.emit('compile')
  }
}
