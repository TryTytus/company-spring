package org.my.companyspring.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication, use default:(client_id=company,username=adam,password=adam,request body)",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER,
        flows = @OAuthFlows(
                password = @OAuthFlow(
                        tokenUrl = "${openapi.oauth.tokenUrl}",
                        scopes = {@OAuthScope(
                                name = "email"
                        )}
                )
        )
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((auth) ->{
                    auth.requestMatchers("/v3/api-docs/**").anonymous();
                    auth.requestMatchers("/swagger-ui/**").anonymous();
                    auth.requestMatchers("/profile/**").anonymous();
                    auth.anyRequest().authenticated();
                });

        http.oauth2ResourceServer((oauth) ->
                        oauth.jwt(Customizer.withDefaults()));

        http.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
