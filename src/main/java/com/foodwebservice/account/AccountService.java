package com.foodwebservice.account;

import com.foodwebservice.account.form.SignUpForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.type.LocalAccount;
import com.foodwebservice.account.type.OAuth2Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void makeAccount(SignUpForm signUpForm, AccountType accountType){
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Account account = modelMapper.map(signUpForm, Account.class);
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountType(accountType);
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email)
                .filter(entity -> entity.getAccountType().equals(AccountType.LOCAL))
                .orElseThrow(() -> new UsernameNotFoundException("email is not exist"));
        return new LocalAccount(account);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String nameAttributeKey = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        Account account = saveOrUpdate(registrationId, attributes);
        OAuthAttributes authAttributes = OAuthAttributes.of(registrationId, attributes, nameAttributeKey);

        return new OAuth2Account(account, authAttributes);
    }

    private Account saveOrUpdate(String registrationId, Map<String, Object> attributes){
        Account account = accountRepository.findByEmail((String)attributes.get("email"))
                .map(entity -> entity.update((String)attributes.get("name")))
                .orElseGet(() -> makeOAuthAccount(registrationId, attributes));
        return accountRepository.save(account);
    }

    private Account makeOAuthAccount(String registrationId, Map<String, Object> attributes){
        AccountType accountType = null;
        Account account = null;
        if(registrationId.equals("google")){
            account = Account.builder()
                    .email((String)attributes.get("email"))
                    .name((String)attributes.get("name"))
                    .accountType(AccountType.GOOGLE)
                    .createdAt(LocalDateTime.now())
                    .build();
        } else if(registrationId.equals("naver")){
            Map<String, Object> response = (Map<String, Object>)attributes.get("response");
            account = Account.builder()
                    .email((String)response.get("email"))
                    .name((String)response.get("name"))
                    .accountType(AccountType.NAVER)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
        else
            throw new RuntimeException();

        return accountRepository.save(account);
    }

}
