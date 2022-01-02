import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public router: Router) {}
  canActivate(): boolean {
    if (!localStorage.getItem('connected')) { // If user is not logged in then he can't access this route
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
