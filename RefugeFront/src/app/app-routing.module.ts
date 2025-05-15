import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './pages/accueil/accueil.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { DeconnexionComponent } from './pages/deconnexion/deconnexion.component';

import { AdminAnimauxComponent } from './pages/admin-animaux/admin-animaux.component';
import { AdminBoutiqueComponent } from './pages/admin-boutique/admin-boutique.component';
import { AdminUtilisateursComponent } from './pages/admin-utilisateurs/admin-utilisateurs.component';
import { BenevoleAjoutComponent } from './pages/benevole-ajout/benevole-ajout.component';
import { BenevoleProfileComponent } from './pages/benevole-profile/benevole-profile.component';
import { BoutiqueComponent } from './pages/boutique/boutique.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { PanierComponent } from './pages/panier/panier.component';
import { authGuard } from './auth.guard';
import { BoutiqueDetailComponent } from './pages/boutique-detail/boutique-detail.component';
import { AnimalDetailComponent } from './pages/animal-detail/animal-detail.component';
import { InfosComponent } from './pages/infos/infos.component';
import { AnimalDetailBenevoleComponent } from './pages/animal-detail-benevole/animal-detail-benevole.component';

const routes: Routes = [
  { path: '', component: InfosComponent },
  { path: 'infos', component: InfosComponent },
  { path: 'home', component: AccueilComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'deconnexion', component: DeconnexionComponent },
  { path: 'admin-animaux', component: AdminAnimauxComponent },
  { path: 'admin-boutique', component: AdminBoutiqueComponent },
  { path: 'admin-utilisateurs', component: AdminUtilisateursComponent, canActivate: [ authGuard ] },
  { path: 'benevole-ajout', component: BenevoleAjoutComponent },
  { path: 'benevole-profile', component: BenevoleProfileComponent },
  { path: 'benevole/:id', component: AnimalDetailBenevoleComponent },
  { path: 'boutique', component: BoutiqueComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'panier', component: PanierComponent },
  { path: 'produit/:id', component: BoutiqueDetailComponent },
  { path: 'animal/:id', component: AnimalDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
