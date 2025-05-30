package formation_sopra.Refuge.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderAuthorizationFilter extends OncePerRequestFilter {
	@Autowired
	private IDAOUtilisateur daoUtilisateur;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		//pour eviter l'erreur du token vide lors de la deconnexion, pour ne pas avoir de casse serveur
	    // pour permettre d'aller a l'accueil d'un visiteur non connecté, pas de verification de token nécessaire
	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String token = authHeader.substring(7);
	    
		Optional<String> optUsername = JwtUtil.getUsername(token);

		// Si on a le nom d'utilisateur, le jeton est valide
		if (optUsername.isPresent()) {
			String username = optUsername.get();
			Optional<Utilisateur> optUtilisateur = this.daoUtilisateur.findByLogin(username);

			// Si on a l'utilisateur, on peut l'authentifier
			if (optUtilisateur.isPresent()) {
				Utilisateur utilisateur = optUtilisateur.get();

				// Simuler la connexion grâce au jeton

				// Créer la liste des rôles
				List<GrantedAuthority> authorities = new ArrayList<>();

				// Le préfix "ROLE_" permet d'indiquer à SPRING SECURITY qu'il s'agit d'un rôle
				if(utilisateur instanceof Admin) {
					authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				} else if(utilisateur instanceof Worker) {
					authorities.add(new SimpleGrantedAuthority("ROLE_WORKER"));
				} else if(utilisateur instanceof Client) {
					authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
				} 
				
				// Recréer un nouvel Authentication
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

				// Injecter cet Authentication dans le contexte de sécurité
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		// Important, pour passer à la suite
		filterChain.doFilter(request, response);
	}
}
