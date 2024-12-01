package com.example.exambyte.adapters.configuration;

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

        String login = oAuth2User.<String>getAttribute("login");
        Integer id = oAuth2User.<Integer>getAttribute("id");
        if(id==null) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST));
        }
        String role = determineRole(id);
        switch (role){
            case "ROLE_ORGANISATOR":
                authorities.add(new SimpleGrantedAuthority("ROLE_ORGANISATOR"));
            case "ROLE_KORREKTOR":
                authorities.add(new SimpleGrantedAuthority("ROLE_KORREKTOR"));
            case "ROLE_STUDENT":
                System.out.printf("granting %s privileges to user: %s%n",role.substring(5), login);
                authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                break;
            default:
                System.out.printf("denying privileges to user: %s%n", login);
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
        }
        System.out.println(authorities);
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "id");
    }

    private String determineRole(Integer id){
        if(organisatoren.contains(id.toString())){
            return "ROLE_ORGANISATOR";
        }
        if(korrektoren.contains(id.toString())) {
            return "ROLE_KORREKTOR";
        }
        if(studenten.contains(id.toString())) {
            return "ROLE_STUDENT";
        }
        return "ROLE_USER";
    }
}
