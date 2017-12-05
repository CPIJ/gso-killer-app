import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { Observable } from "rxjs/Observable";
import Context from "../utillity/Context";
import { Inject, Injectable } from "@angular/core";

@Injectable()
export class AuthorizationGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        debugger;
        if (Context.currentUser) {
            return true;
        } else {
            this.router.navigate([''], { queryParams: { returnUrl: state.url } });
            return false;
        }
    }
}
