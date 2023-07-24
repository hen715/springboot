package spring_study.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import spring_study.springboot.domain.user.Role;

@EnableWebSecurity//스프링 시큐리티 설정들을 활성화
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .headers(headers->headers.frameOptions(frameOptions->frameOptions.disable()))//h2-console 화면 사용을 위한 코드
//                .and()
                .authorizeRequests(auth->auth.requestMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll())//URL 별 권한 설정
//                .antMatchers("/", "/css/**", "/images/**",
//                        "/js/**", "/h2-console/**").permitAll()
                .authorizeRequests(auth->auth.requestMatchers("/api/v1/**").hasRole(Role.USER.name()).anyRequest().authenticated())//API는 USER권한이 있어야만 , anyRequest는 설정 이외에 모든 URL,나머지는 로그인 사용자만 가능
//                .antMatchers("/api/v1/**").hasRole(Role.
//                        USER.name())
//                .anyRequest().authenticated()
//                .and()
                .logout(logout->logout.logoutSuccessUrl("/"))//로그아웃시 이동 주소 설정
//                .logoutSuccessUrl("/")
//                .and()
                .oauth2Login(oauth->oauth.userInfoEndpoint(user->user.userService(customOAuth2UserService)));//로그인 후속 조치 서비스 등록
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);

        return http.build();
    }

}
