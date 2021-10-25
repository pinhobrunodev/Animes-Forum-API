package com.pinhobrunodev.animesforum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Arrays;

import static com.pinhobrunodev.animesforum.auxiliary.EndpointsConfigurer.PUBLIC;
import static com.pinhobrunodev.animesforum.auxiliary.EndpointsConfigurer.USER_ENDPOINT;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Se estou um rodando profile test , eu quero rodar o banco H2
        if(Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.POST,USER_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.PUT,USER_ENDPOINT).authenticated()
                .antMatchers(HttpMethod.GET,USER_ENDPOINT).authenticated()
                .antMatchers(HttpMethod.DELETE,USER_ENDPOINT).hasAnyRole("ADMIN","BASIC")
                .anyRequest().authenticated();
    }


}
