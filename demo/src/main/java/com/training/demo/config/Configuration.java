package com.training.demo.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Import(KeycloakSpringBootConfigResolver.class)
public class Configuration extends KeycloakWebSecurityConfigurerAdapter {
	 private static final String[] AUTH_WHITELIST = {
             // -- Swagger UI v2
             "/v2/api-docs",
             "/swagger-resources",
             "/swagger-resources/**",
             "/configuration/ui",
             "/configuration/security",
             "/swagger-ui.html",
             "/webjars/**",
             // -- Swagger UI v3 (OpenAPI)
             "/v3/api-docs/**",
             "/swagger-ui/**"
             // other public endpoints of your API may be appended to this array
     };
 
 /**
  * Registers the KeycloakAuthenticationProvider with the authentication manager.
  */
 @Autowired
 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     KeycloakAuthenticationProvider authenticationProvider=new KeycloakAuthenticationProvider();
     authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
     auth.authenticationProvider(authenticationProvider);
 }



/**
  * Defines the session authentication strategy.
  */
 @Bean
 @Override
 protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
     return new RegisterSessionAuthenticationStrategy(SessionRegistryImpl());
 }
 



@Bean
 protected SessionRegistry SessionRegistryImpl() {
     return new SessionRegistryImpl();
 }



  private JwtAuthenticationConverter jwtAuthenticationConverter() {
         JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
         jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
         jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
         JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
         jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
         
         
         return jwtAuthenticationConverter;
     }



  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
  
      super.configure(http);
      http
      
              
              .authorizeRequests()
               .antMatchers(AUTH_WHITELIST).permitAll()
              
              .anyRequest().authenticated()
             // .permitAll()
            
              .and()
              .oauth2ResourceServer().jwt()
              .jwtAuthenticationConverter(jwtAuthenticationConverter());
  }

}
