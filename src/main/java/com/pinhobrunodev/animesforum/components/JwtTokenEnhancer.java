package com.pinhobrunodev.animesforum.components;


import com.pinhobrunodev.animesforum.entities.User;
import com.pinhobrunodev.animesforum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository repository;

    /*
        Recebe esses 2 objetos e entra no ciclo de vida do token e na hr de gerar o token vai
        acrescentar os parametros que eu desejo
     */

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // o email ja vai estar instanciado dentro do authentication
        User user =  repository.findByEmail(authentication.getName());

        Map<String,Object> map = new HashMap<>();
        map.put("userId",user.getId());
        map.put("firstName",user.getFirstName());
        map.put("nickname",user.getNickname());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);

        return token;
    }
}