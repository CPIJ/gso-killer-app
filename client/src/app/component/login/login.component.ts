import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: String;
  password: String;

  constructor() { }

  ngOnInit() {
  }

  login() {
    console.log(this.email, this.password);
  }

  forgotPassword() {
    alert("Hier is je wachtwoord: ")
  }
}
