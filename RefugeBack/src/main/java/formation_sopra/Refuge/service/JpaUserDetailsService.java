package formation_sopra.Refuge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = this.daoUtilisateur.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas."));
		
		// Si l'utilisateur n'a pas été trouvé, l'exception sera jetée, et on s'arrêtera là

		User.UserBuilder userBuilder = User.withUsername(username).password(passwordEncoder.encode(utilisateur.getPassword()));
		
		if(utilisateur instanceof Admin) {
			userBuilder.roles("ADMIN");
		} else if(utilisateur instanceof Client) {
			userBuilder.roles("CLIENT");
		} else if(utilisateur instanceof Worker) {
			userBuilder.roles("WORKER");
		}
		
		return userBuilder.build();
	}

}
