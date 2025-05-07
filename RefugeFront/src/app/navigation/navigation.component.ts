import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigation',
  standalone: false,
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {

  //Dans la page nav le role sera "ADMIN", "WORKER" ou "CLIENT" si connecté et null sinon
  private _role : string | null = null;

  constructor(private authService : AuthService) 
  {
    if (this.authService.user) {this._role = this.authService.user.roleUser}
  }


  public get role()
{
    return this.authService.user?.roleUser;
}

public set role(value : string | null)
{
    this._role = value;
}

}
