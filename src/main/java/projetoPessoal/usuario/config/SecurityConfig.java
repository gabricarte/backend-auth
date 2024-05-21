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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails loja = User.withUsername("Loja")
                .password(passwordEncoder().encode("Loja@123."))
                .roles("LOJA")
                .build();
        UserDetails fornecedor = User.withUsername("Fornecedor")
                .password(passwordEncoder().encode("Fornecedor@123."))
                .roles("FORNECEDOR")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("Admin@123."))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(loja, fornecedor, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new OrRequestMatcher(List.of(
                                new AntPathRequestMatcher("/swagger-ui"),
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/h2-console/**")
                        ))).permitAll()
                        //loja
                        .requestMatchers(HttpMethod.POST, "/usuario/**").hasRole("LOJA") // cadastro de loja
                        .requestMatchers(HttpMethod.POST, "/produtos/**").hasRole("LOJA") // pede produto
                        .requestMatchers(HttpMethod.PUT, "/usuario/**").hasRole("LOJA") // atualiza as próprias informações

                        //admin
                        .requestMatchers(HttpMethod.POST, "/cadastro/fornecedor").hasRole("ADMIN") // deleta fornecedores
                        .requestMatchers(HttpMethod.PUT, "/fornecedor/**").hasRole("ADMIN") // atualiza dados do fornecedor
                        .requestMatchers(HttpMethod.GET, "/fornecedor/**").hasRole("ADMIN") // cadastra novos fornecedores
                        .requestMatchers(HttpMethod.DELETE, "/loja/**").hasRole("ADMIN") // deleta lojas


                        .requestMatchers(HttpMethod.POST, "/login/admin").hasRole("ADMIN") //login de adm

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
