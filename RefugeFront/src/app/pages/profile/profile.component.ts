import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth.service'; // corriger le chemin ici
import { UtilisateursService } from '../admin-utilisateurs/utilisateurs.service';
import { Utilisateurs } from '../admin-utilisateurs/utilisateurs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  standalone: false 
})
export class ProfileComponent implements OnInit {
  user!: Utilisateurs;
  
  constructor(private authService: AuthService, private service: UtilisateursService, private router: Router) {}
  
  ngOnInit(): void {
    const id = this.authService.user.idUser;
    this.service.findById(id).subscribe({
      next: user => this.user = user,
      error: () => this.user = new Utilisateurs(0, "", "", "", "", "", "", "", "", "")
    });
  }
}
