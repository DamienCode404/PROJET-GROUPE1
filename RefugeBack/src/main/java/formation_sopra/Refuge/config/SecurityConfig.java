package formation_sopra.Refuge.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
		// Méthode d'authentification par HTTP Basic
		http.httpBasic(Customizer.withDefaults());

		// Autorisations sur URLs
		http.authorizeHttpRequests(auth -> {
		    auth.requestMatchers("/api/animal/**").permitAll(); // Public : pas besoin de token

		    auth.requestMatchers("/api/connexion").authenticated(); // Token obligatoire
		    auth.requestMatchers("/api/inscription").authenticated(); // Token obligatoire

		    auth.requestMatchers("/api/utilisateur/**").hasRole("ADMIN"); // Admin obligatoire
		    auth.requestMatchers("/api/admin/**").hasRole("ADMIN"); // Admin obligatoire
		    auth.requestMatchers("/api/**").authenticated(); // tout le reste : connecté
		    auth.requestMatchers("/**").permitAll(); // le reste du site (non /api) public
		});

		http.csrf(c -> c.ignoringRequestMatchers("/**"));

		// Configurer les CORS (Cross-Origine Resources Sharing)
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();

				// On autorise tout le monde
				config.setAllowedOrigins(List.of("*"));

				// On autorise toutes les commandes HTTP (GET, POST, PUT, ...)
				config.setAllowedMethods(List.of("*"));

				// On autorise toutes les en-têtes HTTP
				config.setAllowedHeaders(List.of("*"));

				return config;
			};

			c.configurationSource(source);
		});

		// Positionner le filtre JWT AVANT le filter
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// Grace à ce Bean, on pourra injecter un AuthenticationManager directement
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

//	@Bean
//	public UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//		manager.createUser(
//				User.withUsername("eric").password(passwordEncoder.encode("123456")).roles("FORMATEUR").build());
//		manager.createUser(
//				User.withUsername("damien").password(passwordEncoder.encode("123456")).roles("STAGIAIRE").build());
//		manager.createUser(
//				User.withUsername("marina").password(passwordEncoder.encode("123456")).roles("STAGIAIRE").build());
//		manager.createUser(
//				User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("ADMIN").build());
//
//		return manager;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Pas d'encadage sur les mots de passe - PAS BIEN
//		return NoOpPasswordEncoder.getInstance();

		return new BCryptPasswordEncoder();
	}
}
