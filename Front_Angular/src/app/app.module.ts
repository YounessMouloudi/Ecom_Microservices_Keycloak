import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsComponent } from './ui/products/products.component';
import { CustomersComponent } from './ui/customers/customers.component';
import { HTTP_INTERCEPTORS, HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { OrdersComponent } from './ui/orders/orders.component';
import { OrderDetailsComponent } from './ui/order-details/order-details.component';
import { KeycloakInterceptor } from './keycloak.interceptor';


function initializeKeycloak(keycloak: KeycloakService) {
  
  return async () => {    
    try {
      await keycloak.init({
        config: {
          url: 'http://localhost:8080',
          realm: 'products_microser_keyck',
          clientId: 'front_client_ang',
        },
        initOptions: {
          // onLoad: 'login-required', // hadi ladrtiha ghaydik direct login
          onLoad: 'check-sso', // had sso tat3ni "single sign-on" çad authentifier une seul fois pour accé a l'app
          silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
          // silentCheckSsoRedirectUri: window.location.origin,
          // silentCheckSsoFallback : true,
        },
      });      
    } catch (error) {
      console.error('Keycloak initialization failed :', error);
    }

  }   
}

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CustomersComponent,
    OrdersComponent,
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    // HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    /* hna hado homa li wlaw blasst Httpclientmodule car si deprecated ima ndiro provide bohdo aw ndiroh m3a
       interceptors w had les interceptors tay3awnona f les reque et les repons */
    // provideHttpClient(),
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: KeycloakInterceptor, multi: true },
    { provide : APP_INITIALIZER, useFactory : initializeKeycloak, multi : true, deps : [KeycloakService]}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
