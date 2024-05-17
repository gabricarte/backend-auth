package projetoPessoal.usuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.List;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Usuario")
                .password("Usuario@123.")
                .roles("USER")
                .build();
        UserDetails loja = User.withDefaultPasswordEncoder()
                .username("Usuario")
                .password("Usuario@123.")
                .roles("LOJA")
                .build();
        UserDetails fornecedor = User.withDefaultPasswordEncoder()
                .username("Usuario")
                .password("Usuario@123.")
                .roles("FORNECEDOR")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Admin")
                .password("Admin@1.")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r
                        .requestMatchers(new OrRequestMatcher(List.of(
                                new AntPathRequestMatcher("/swagger-ui"),
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/h2-console/**")
                        ))).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET)).hasAnyRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.POST)).hasAnyRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT)).hasAnyRole("USER")
                        .requestMatchers(antMatcher(HttpMethod.DELETE)).hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}