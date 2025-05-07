import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);
  const hasToken = !!authService.token;

  if (!hasToken) {
    router.navigate([ '/connexion' ]);
  }

  return true;
}
