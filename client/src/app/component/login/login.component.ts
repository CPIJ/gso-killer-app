import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';
import Context from '../../utillity/Context';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;
  returnUrl: string;

  constructor(private authService: AuthenticationService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => this.returnUrl = params["returnUrl"])
  }

  login() {
    this.authService.login(this.email, this.password).subscribe((response) => {
      if (response.hasError) {
        alert(response.message)
      } else {
        Context.currentUser = response.user;

        if (this.returnUrl) {
          this.router.navigateByUrl(this.returnUrl);  
        } else {
          this.router.navigate(['project-creation']);
        }
      }
    })
  }

  forgotPassword() {
    this.authService.forgotPassword(this.email).subscribe(response => {
      alert(`Dit is je wachtwoord: ${response}`)
    });
  }
}
