package formation_sopra.Refuge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import formation_sopra.Refuge.config.JwtUtil;
import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;
import formation_sopra.Refuge.rest.request.ConnexionRequest;
import formation_sopra.Refuge.rest.response.ConnexionResponse;

@RestController
public class CommonRestController {

	@Autowired
	private IDAOUtilisateur daoUtilisateur;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/connexion")
	public ConnexionResponse create(@RequestBody ConnexionRequest connexionRequest) {

		Authentication authentication = new UsernamePasswordAuthenticationToken(connexionRequest.getLogin(),
				connexionRequest.getPassword());

		this.authenticationManager.authenticate(authentication);

		ConnexionResponse connexionResponse = new ConnexionResponse();

		String token = JwtUtil.generate(authentication);
		connexionResponse.setSuccess(true);
		connexionResponse.setToken(token);

		Utilisateur utilisateur = daoUtilisateur.findByLoginAndPassword(connexionRequest.getLogin(),
				connexionRequest.getPassword());
		
		connexionResponse.setIdUser(utilisateur.getId());
		if (utilisateur instanceof Admin) {connexionResponse.setRoleUser("ADMIN");}
		if (utilisateur instanceof Worker) {connexionResponse.setRoleUser("WORKER");}
		if (utilisateur instanceof Client) {connexionResponse.setRoleUser("CLIENT");}

		

		return connexionResponse;
	}
}
