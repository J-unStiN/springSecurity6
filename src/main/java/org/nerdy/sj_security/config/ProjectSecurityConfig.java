package org.nerdy.sj_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated() // 해당 경로는 보호
                        .requestMatchers("/notices", "/contact").permitAll()
                        .and().formLogin()
                        .and().httpBasic();
        return http.build();

        /*모든 요청 거부*/
//        http.authorizeHttpRequests().anyRequest().denyAll()
//                        .and().formLogin()
//                        .and().httpBasic();
//        return http.build();


        /*모든 요청 허용*/
//        http.authorizeHttpRequests().anyRequest().permitAll()
//                        .and().formLogin()
//                        .and().httpBasic();
//        return http.build();


    }


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
