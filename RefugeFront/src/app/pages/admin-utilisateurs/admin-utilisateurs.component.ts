import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Utilisateurs } from './utilisateurs';
import { UtilisateursService } from './utilisateurs.service';

@Component({
  selector: 'app-admin-utilisateurs',
  standalone: false,
  templateUrl: './admin-utilisateurs.component.html',
  styleUrl: './admin-utilisateurs.component.css'
})
export class AdminUtilisateursComponent {
  utilisateurs$!: Observable<Utilisateurs[]>;
  subscriptions: any = [];
  
  constructor(private service: UtilisateursService) {}

  ngOnInit(): void {
    this.utilisateurs$ = this.service.findAll();
  }

  ngOnDestroy(): void {
    this.unsub('delete');
  }

  public deleteUtilisateurs(utilisateurs: Utilisateurs) {
    this.unsub('delete');

    this.subscriptions['delete'] = this.service.delete(utilisateurs).subscribe(() => this.service.refresh());
  }

  private unsub(name: string) {
    if (this.subscriptions[name]) {
      this.subscriptions[name].unsubscribe();
      this.subscriptions[name] = null;
    }
  }
}
