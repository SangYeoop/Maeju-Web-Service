package com.foodwebservice.account;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes, String nameAttributeKey){
        if(registrationId.equals("naver")) {
            return ofNaver(attributes, "id");
        }
        return ofGoogle(attributes, nameAttributeKey);
    }

    private static OAuthAttributes ofGoogle(Map<String, Object> attributes, String nameAttributeKey){
        return OAuthAttributes.builder()
                .nameAttributeKey(nameAttributeKey)
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofNaver(Map<String, Object> attributes, String nameAttributeKey){
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .nameAttributeKey(nameAttributeKey)
                .attributes(response)
                .build();
    }

}
