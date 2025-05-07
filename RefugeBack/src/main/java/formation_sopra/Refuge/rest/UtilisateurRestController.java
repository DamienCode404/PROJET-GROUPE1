package formation_sopra.Refuge.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.rest.request.UtilisateurRequest;
import formation_sopra.Refuge.rest.response.UtilisateurResponse;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurRestController {

	private IDAOUtilisateur daoUtilisateur;

	public UtilisateurRestController(IDAOUtilisateur daoUtilisateur) {
		super();
		this.daoUtilisateur = daoUtilisateur;
	}

	@GetMapping("")
	public List<UtilisateurResponse> getAll() {
		List<Utilisateur> utilisateurs = this.daoUtilisateur.findAll();

//		return utilisateurs.stream().map(u -> UtilisateurResponse.convert(u)).toList();
		return utilisateurs.stream().map(UtilisateurResponse::convert).toList();
	}

	@GetMapping("/{id}")
	public UtilisateurResponse getById(@PathVariable Integer id) {
		return this.daoUtilisateur.findById(id).map(UtilisateurResponse::convert)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	public Utilisateur create(@RequestBody UtilisateurRequest utilisateurRequest) {
		Utilisateur utilisateur = UtilisateurRequest.convert(utilisateurRequest);

		return daoUtilisateur.save(utilisateur);
	}

	@PutMapping("/{id}")
	public Utilisateur update(@RequestBody UtilisateurRequest utilisateurRequest, @PathVariable Integer id) {
		if (id != utilisateurRequest.getId() || !this.daoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		Utilisateur utilisateur = UtilisateurRequest.convert(utilisateurRequest);

		return daoUtilisateur.save(utilisateur);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoUtilisateur.deleteById(id);
	}
	

}
