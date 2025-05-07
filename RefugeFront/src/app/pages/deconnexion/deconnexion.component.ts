import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-deconnexion',
  standalone: false,
  templateUrl: './deconnexion.component.html',
  styleUrl: './deconnexion.component.css'
})
export class DeconnexionComponent implements OnInit {
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.logout();
  }
}