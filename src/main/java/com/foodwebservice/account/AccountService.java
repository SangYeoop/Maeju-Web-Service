package com.foodwebservice.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodwebservice.account.exception.NotMakeAccountException;
import com.foodwebservice.account.form.ProfileUpdateForm;
import com.foodwebservice.account.form.SignUpForm;
import com.foodwebservice.account.type.AccountType;
import com.foodwebservice.account.type.GenderType;
import com.foodwebservice.account.type.LocalAccount;
import com.foodwebservice.account.type.OAuth2Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean isEqualPassword(Account account, String password){
        return passwordEncoder.matches(password, account.getPassword());
    }

    @Transactional
    public Account updateProfile(Account account, ProfileUpdateForm profileUpdateForm){
        account.update(profileUpdateForm);
        return accountRepository.save(account);
    }

    @Transactional
    public Account updatePassword(Account account, String password) {
        account.updatePassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    @Transactional
    public void makeAccount(SignUpForm signUpForm, AccountType accountType){
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Account account = modelMapper.map(signUpForm, Account.class);
        account.setCreatedAt(LocalDateTime.now());
        account.setGenderType(GenderType.NONE);
        account.setAccountType(accountType);
        login(accountRepository.save(account));
    }

    public void delete(Account account) {
        logout(account);
        accountRepository.delete(account);
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

        Account account;
        if(registrationId.equals("google")) {
            account = saveOrUpdate(registrationId, attributes);
        }else {
            account = saveOrUpdate(registrationId, (Map<String, Object>)attributes.get("response"));
        }
        OAuthAttributes authAttributes = OAuthAttributes.of(registrationId, attributes, nameAttributeKey);

        return new OAuth2Account(account, authAttributes);
    }

    private void login(Account account){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        new LocalAccount(account), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void logout(Account account){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private Account saveOrUpdate(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationException{
        Account account = accountRepository.findByEmail((String)attributes.get("email")).orElseGet(() -> makeOAuthAccount(registrationId, attributes));
        if(account.getAccountType() == AccountType.LOCAL){
            throw new OAuth2AuthenticationException(new OAuth2Error("400", "로컬 계정으로 존재하는 아이디 입니다.", "/login"));
        }
        account.update(new ProfileUpdateForm((String)attributes.get("name"), account.getEmail(), account.getGenderType()));
        return accountRepository.save(account);
    }

    private Account makeOAuthAccount(String registrationId, Map<String, Object> attributes){
        Account account;

        if(registrationId.equals("google")){
            account = Account.builder()
                    .email((String)attributes.get("email"))
                    .name((String)attributes.get("name"))
                    .accountType(AccountType.GOOGLE)
                    .createdAt(LocalDateTime.now())
                    .genderType(GenderType.NONE)
                    .build();
        } else if(registrationId.equals("naver")){
            account = Account.builder()
                    .email((String)attributes.get("email"))
                    .name((String)attributes.get("name"))
                    .accountType(AccountType.NAVER)
                    .genderType(GenderType.NONE)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
        else
            throw new RuntimeException();

        return accountRepository.save(account);
    }

}
