package com.projects.order_service.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
              .authorizeHttpRequests(ar -> ar.requestMatchers("/h2-console/**", "/swagger-ui.html",
                      "/swagger-ui/**","/v3/**").permitAll())
//              .authorizeHttpRequests(ar -> ar.requestMatchers("/api/orders/**").hasAuthority("ADMIN"))
              .authorizeHttpRequests(ar -> ar.anyRequest().authenticated())
              .csrf(csrf -> csrf.disable())
              .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .headers(h -> h.frameOptions(fo -> fo.disable()))
              // hadi drnaha 3la wed h2-console hit il utilise des frames pour sécuriser l'app
              .cors(Customizer.withDefaults())
              // hadi pour autoriser cors mais darori khass nconfiguriw aussi lfilter CorsConfigurationSource
              .oauth2ResourceServer(o2 -> o2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
              /* hadi pour indiquer a oauth2 que on va récuperé le public key depuis keycloak qui est dans certs
              alors on ajoute 2 properties pour configurer oauthserver avec keycloak aprés que le service il
              reçoit la requette il va chercher le jwt et aprés jwt il va vérifier la signature en utilisant
              le public key */
              .build();
    }

    /* mnin tankhadmo b spring sécurity darori tandiro had l filter pour autoriser le cors hit wakha kona
       darna f les controllers hadi "@CrossOrigin("http://localhost:4200/")" mnin tandiro spring sécurity
       matatbe9ach khdama dik l'annotation darori khass had corsConfigurationSource bach t autoriser l'Origin,
       Methodes, Headers, et ExposedHeaders.
       hna drna fihom kamlin ghir "*" mais t9dar t7dad nta chno t allowé
    */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setExposedHeaders(Arrays.asList("*"));
        // hadi pour autoriser le client ou javascript lire les headers de l'app

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
}
