package com.projects.order_service.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {

    /* hna mnin tandémariw les services ay inventory et order aprés on authentifié avec keyck alors hna 3ndna jwt
    ay access token mais c'est juste pour products hit ila dkhalna orders maghadich ya3tina hit orders tansta3mlo
    fiha inventoryRest alors hna khass nsefto jwt l inventory-service bach ya3tina list d products alors hadchi
    3lach drna had l'interceptor openFeign ay anana ghadi nakhdo dak jwt mn authentication w ghadi nseftoh f
    headers d request l inventory aw ayi request openFeign */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
//      System.out.println("************");
//      System.out.println(authentication.getClass().getName()); // hadi bach jbadna class JwtAuthenticationToken
//      System.out.println("************");
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        if(jwtAuthenticationToken != null) {
            String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
//            System.out.println("Access Token => "+jwtAccessToken);
            requestTemplate.header("Authorization","Bearer "+jwtAccessToken);
        }
    }
}
