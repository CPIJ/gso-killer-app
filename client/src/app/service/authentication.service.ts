import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import { User } from '../model/user';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { LoginResponse } from '../model/login-response';

@Injectable()
export class AuthenticationService {

  constructor(private http: Http) { }

  login(email: String, password: String): Observable<LoginResponse> {
    return this.http.post("http://localhost:2000/authentication/", { username: email, password: password })
      .map(u => u.json())
      .catch(err => {
        alert(err.message)
        return null;
      })
  }

  isAuthorized(user: User): Observable<boolean> {
    return this.http.post("http://localhost:2000/authentication/isAuthorized/", { username: user.username, email: user.email, password: user.password })
      .map(response => response.json())
      .catch(err => Observable.throw(err));
  }

  forgotPassword(email: string): Observable<string> {
    return this.http.get("http://localhost:2000/authentication/forgotPassword?email=" + email)
      .map(r => r.text())
      .catch(err => Observable.throw(err))
  }
}
