import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { NavigationComponent } from './navigation/navigation.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { AccueilComponent } from './pages/accueil/accueil.component';
import { AdminUtilisateursComponent } from './pages/admin-utilisateurs/admin-utilisateurs.component';
import { AdminAnimauxComponent } from './pages/admin-animaux/admin-animaux.component';
import { AdminBoutiqueComponent } from './pages/admin-boutique/admin-boutique.component';
import { BoutiqueComponent } from './pages/boutique/boutique.component';
import { PanierComponent } from './pages/panier/panier.component';
import { BenevoleProfileComponent } from './pages/benevole-profile/benevole-profile.component';
import { BenevoleAjoutComponent } from './pages/benevole-ajout/benevole-ajout.component';
import { jwtHeaderInterceptor } from './jwt-header.interceptor';
import { AscTextFieldComponent } from './asc-text-field/asc-text-field.component';

import { SearchBarComponent } from './search-bar/search-bar.component';

import { BoutiqueDetailComponent } from './pages/boutique-detail/boutique-detail.component';
import { AnimalDetailComponent } from './pages/animal-detail/animal-detail.component';
import { DeconnexionComponent } from './pages/deconnexion/deconnexion.component';
import { SearchBarBoutiqueComponent } from './search-bar-boutique/search-bar-boutique.component';
import { InfosComponent } from './pages/infos/infos.component';
import { AnimalDetailBenevoleComponent } from './pages/animal-detail-benevole/animal-detail-benevole.component';
import { AdminAnimauxFormComponent } from './pages/admin-animaux-form/admin-animaux-form.component';


@NgModule({
  declarations: [
    AppComponent,
    ConnexionComponent,
    NavigationComponent,
    InscriptionComponent,
    AccueilComponent,
    AdminUtilisateursComponent,
    AdminAnimauxComponent,
    AdminBoutiqueComponent,
    BoutiqueComponent,
    PanierComponent,
    BenevoleProfileComponent,
    BenevoleAjoutComponent,
    AscTextFieldComponent,

    SearchBarComponent,

    BoutiqueDetailComponent,
      AnimalDetailComponent,
      DeconnexionComponent,
      SearchBarBoutiqueComponent,
      InfosComponent,
      AnimalDetailBenevoleComponent,
      AdminAnimauxFormComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    provideHttpClient(withFetch(), withInterceptors([ jwtHeaderInterceptor ])) // Remplace l'import de HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
