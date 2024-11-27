package com.example.exambyte.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Value("${exambyte.rollen.organisator}")
    private Set<String> organisatoren;

    @Value("${exambyte.rollen.korrektor}")
    private Set<String> korrektoren;

    @Value("${exambyte.rollen.student}")
    private Set<String> studenten;


    private final DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultService.loadUser(userRequest);
        Set<GrantedAuthority> authorities = new HashSet<>(oAuth2User.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        String login = oAuth2User.<String>getAttribute("login");
        Integer id = oAuth2User.<Integer>getAttribute("id");
        if(id==null) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST));
        }
        if(organisatoren.contains(id.toString())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ORGANISATOR"));
        } else if (korrektoren.contains(id.toString())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_KORREKTOR"));
        } else if (studenten.contains(id.toString())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        System.out.printf("GRANTING %s AUTHORITIES: %s \n", login, authorities.toString());

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "id");
    }
}
