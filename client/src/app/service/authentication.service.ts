import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from "rxjs/Observable";
import { User } from '../model/user';
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch';
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

  isAuthorized(user: User): boolean {
    return true;
  }
}
