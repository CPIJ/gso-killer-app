import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ConfigService {

  private location = "../../assets/config.json";

  constructor(private http: Http) { }

  public get(): Observable<any> {
    return this.http.get(this.location)
  }
}
