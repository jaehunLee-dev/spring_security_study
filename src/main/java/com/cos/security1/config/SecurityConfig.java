package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //spring security filter 가 spring filter chain 에 등록됩니다.
@EnableMethodSecurity(securedEnabled = true)    // secured 어노테이션 활성화
public class SecurityConfig {

    //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public  BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")  // /login 이라는 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행.
                .defaultSuccessUrl("/");
        return http.build();
    }
}
