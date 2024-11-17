import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit {
  
  title = 'Front_Angular';
  isAuthenticated: boolean = false;
  public profile!: KeycloakProfile;

  constructor(public keycloakService : KeycloakService) {
  }

  async ngOnInit() {

    this.isAuthenticated = this.keycloakService.isLoggedIn();
    
    console.log(this.isAuthenticated);
    
    if (this.isAuthenticated) {
      this.keycloakService.loadUserProfile().then( profile => this.profile = profile);
    } 
  }


  async handleLogin() {
    await this.keycloakService.login({
      redirectUri : window.location.origin,
    });
  }

  handleLogout() {
    this.keycloakService.logout(window.location.origin);
  }

}
