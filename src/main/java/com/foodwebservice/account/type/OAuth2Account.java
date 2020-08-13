package com.foodwebservice.account.type;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.OAuthAttributes;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;

@Getter
public class OAuth2Account extends DefaultOAuth2User {

    private Account account;

    public OAuth2Account(Account account, OAuthAttributes oAuthAttributes) {
        super(List.of(new SimpleGrantedAuthority("ROLE_USER")), oAuthAttributes.getAttributes(), oAuthAttributes.getNameAttributeKey());
        this.account = account;
    }
}
