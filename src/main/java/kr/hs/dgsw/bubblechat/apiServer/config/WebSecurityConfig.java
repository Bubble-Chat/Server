package kr.hs.dgsw.bubblechat.apiServer.config;

import kr.hs.dgsw.bubblechat.apiServer.security.BubbleChatOAuth2UserService;
import kr.hs.dgsw.bubblechat.apiServer.security.JwtAuthFilter;
import kr.hs.dgsw.bubblechat.apiServer.security.JwtTokenProvider;
import kr.hs.dgsw.bubblechat.apiServer.security.OAuth2AuthenticationSuccessHandler;
import kr.hs.dgsw.bubblechat.apiServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final BubbleChatOAuth2UserService oauth2UserService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests((authorize) -> authorize
                .anyRequest().permitAll());
//                .requestMatchers("/api/v1/user/**", "/lab/index", "/login/**").permitAll()
//                .anyRequest().authenticated());

        http.oauth2Login()
                .userInfoEndpoint()
                .userService(oauth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtTokenProvider, userService), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
