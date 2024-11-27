package com.example.exambyte.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
        chainBuilder.authorizeHttpRequests(
                c -> c.requestMatchers("/","/error", "/css/*", "/img/**").permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2Login(
                        config -> config.userInfoEndpoint(
                                info ->  info.userService(new AppUserService())
                        )

                );
        return chainBuilder.build();
    }

}
