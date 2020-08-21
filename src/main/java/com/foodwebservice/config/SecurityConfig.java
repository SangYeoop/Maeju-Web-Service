package com.foodwebservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/diet/example", "/search/**").permitAll()
                .mvcMatchers("/food/type").authenticated()
                .mvcMatchers(HttpMethod.GET, "/food/{foodId}").permitAll()
                .anyRequest().authenticated()
                .and().
                    logout().logoutSuccessUrl("/")
                .and().
                    formLogin().loginPage("/login").permitAll()
                .and().
                    oauth2Login(oauth2 -> oauth2.loginPage("/login")
                            .authorizationEndpoint(authorization -> authorization.baseUri("/login/oauth2/authorization"))
                            .userInfoEndpoint().userService(oAuth2UserService));


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


}
