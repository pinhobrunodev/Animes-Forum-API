package com.pinhobrunodev.animesforum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {


    @Value("${jwt.secret}")
    private String jwtSecret;

    // Componente gerenciado pelo Spring Boot
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    /**
     * Sao objetos que vao ser capazes de acessar (Ler,Decodificar,criar um token codificando ele)  o TOKEN JWT
     *
     *
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        // Instanciei o objeto
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // Registramos nossa assinatura do TOKEN
        tokenConverter.setSigningKey(jwtSecret);
        // Retornamos ele
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        // Esse tokenStore e o TokenConverter vamos injetar no nosso AuthorizationServer
        return new JwtTokenStore(accessTokenConverter());
    }


}
