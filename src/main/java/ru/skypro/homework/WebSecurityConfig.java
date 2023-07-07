package ru.skypro.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

  private static final String[] AUTH_WHITELIST = {
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/v3/api-docs",
    "/webjars/**",
    "/login",
    "/register"
  };

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user =
        User.builder()
            .username("user@gmail.com")
            .password("password")
            .passwordEncoder((plainText) -> passwordEncoder().encode(plainText))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeHttpRequests(
            (authorization) ->
                authorization
                    .mvcMatchers(AUTH_WHITELIST)
                    .permitAll()
                    .mvcMatchers("/ads/**", "/users/**")
                    .authenticated())
        .cors()
        .disable()
        .httpBasic(withDefaults());
    return http.build();
//    return http
//            .anonymous(AbstractHttpConfigurer::disable)         // AnonymousAuthenticationFilter
//            .csrf(AbstractHttpConfigurer::disable)              // CsrfFilter
//            .sessionManagement(AbstractHttpConfigurer::disable) // DisableEncodeUrlFilter, SessionManagementFilter
//            .exceptionHandling(AbstractHttpConfigurer::disable) // ExceptionTranslationFilter
//            .headers(AbstractHttpConfigurer::disable)           // HeaderWriterFilter
//            .logout(AbstractHttpConfigurer::disable)            // LogoutFilter
//            .requestCache(AbstractHttpConfigurer::disable)      // RequestCacheAwareFilter
//            .servletApi(AbstractHttpConfigurer::disable)        // SecurityContextHolderAwareRequestFilter
//            .securityContext(AbstractHttpConfigurer::disable)   // SecurityContextPersistenceFilter
//            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
