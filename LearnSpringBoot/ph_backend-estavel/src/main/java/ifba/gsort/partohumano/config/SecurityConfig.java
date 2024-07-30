package ifba.gsort.partohumano.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ifba.gsort.partohumano.security.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;
    private final static String[] cargosAdministrativos = { "Admin", "Sesab", "Fesf" };
    private final static String[] todosOsCargos = { "Admin", "Sesab", "Fesf", "Ssm", "Enfermeira" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (authorize) -> authorize.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/cadastrar").hasAnyRole(cargosAdministrativos)
                                .requestMatchers(HttpMethod.PATCH, "/gpq/aprovar/**").hasAnyRole(todosOsCargos)
                                .requestMatchers(HttpMethod.GET, "/gpq").hasAnyRole(todosOsCargos)
                                .requestMatchers("/gpq").hasAnyRole("Enfermeira")
                                .requestMatchers("/gpq/respostas/**").hasAnyRole("Enfermeira")
                                .requestMatchers("/users").hasAnyRole(cargosAdministrativos)
                                .requestMatchers("/enfermagem").hasAnyRole(cargosAdministrativos)
                                .requestMatchers("/gpq/perguntas").hasRole("Admin")
                                .requestMatchers("/historico/minhasacoes/**").permitAll()
                                .requestMatchers("/historico/**").hasAnyRole(cargosAdministrativos)
                                .requestMatchers("/**").hasRole("Admin")
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationConfiguration(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
