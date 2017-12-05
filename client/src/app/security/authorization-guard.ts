import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from "@angular/router";
import { Observable } from "rxjs/Observable";
import Context from "../utillity/Context";
import { Inject, Injectable } from "@angular/core";
import { AuthenticationService } from "../service/authentication.service";

@Injectable()
export class AuthorizationGuard implements CanActivate {

    constructor(private router: Router, private authService: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        if (Context.currentUser) {
            return this.authService.isAuthorized(Context.currentUser);
        } else {
            this.router.navigate([''], { queryParams: { returnUrl: state.url } });
            return false;
        }
    }
}
