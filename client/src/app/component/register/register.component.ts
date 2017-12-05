import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user = new User();

  constructor() { }

  ngOnInit() {

  }

  register() {
    console.log(this.user);
  }
}
