package com.foodwebservice.account;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes, String nameAttributeKey){
        return ofGoogle(attributes, nameAttributeKey);
    }

    private static OAuthAttributes ofGoogle(Map<String, Object> attributes, String nameAttributeKey){
        return OAuthAttributes.builder()
                .nameAttributeKey(nameAttributeKey)
                .attributes(attributes)
                .build();
    }

}
