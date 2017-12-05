import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';
import Context from '../../utillity/Context';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {

  email: String;
  password: String;

  constructor(private authService: AuthenticationService) { }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.email, this.password).subscribe((response) => {
      if (response.hasError) {
        alert(response.message)
      } else {
        Context.currentUser = response.user;
      }
    })
  }

  forgotPassword() {
    alert("Hier is je wachtwoord: ")
  }
}
