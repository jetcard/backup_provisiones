import { Injectable, inject } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Subject  } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UtilService {
  isLoading = new Subject<boolean>();

  constructor(private keycloakService: KeycloakService) { }

  getRoles(){
    return this.keycloakService.getUserRoles();
  }

  isAdmin(){
    let roles = this.keycloakService.getUserRoles().filter( role => role == "admin");

    if (roles.length > 0) 
      return true;
    else 
      return false;
  }
  foto(){
    return this.keycloakService.getKeycloakInstance().clientId;
  }
  nombre(){
    return this.keycloakService.getUsername().substring(0,10);
  }

  show() {
    this.isLoading.next(true);
  }

  hide() {
    this.isLoading.next(false);
  }
}