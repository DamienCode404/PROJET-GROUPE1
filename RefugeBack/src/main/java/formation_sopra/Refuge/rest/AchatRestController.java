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

import com.fasterxml.jackson.annotation.JsonView;

import formation_sopra.Refuge.dao.IDAOAchat;
import formation_sopra.Refuge.model.Achat;
import formation_sopra.Refuge.model.Views;

@RestController
@RequestMapping("/achat")
public class AchatRestController {

	private IDAOAchat daoAchat;
	
	public AchatRestController(IDAOAchat daoAchat) {
		super();
		this.daoAchat = daoAchat;
	}
	
	@GetMapping("")
	@JsonView(Views.ViewAchat.class)
	public List<Achat> getAll() {
		return this.daoAchat.findAll();
	}	
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewAchat.class)
	public Achat getById(@PathVariable Integer id) {
		return this.daoAchat.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Achat non trouvé"));
	}
	
	@PostMapping("")
	@JsonView(Views.ViewAchat.class)
	public Achat create(@RequestBody Achat achat) {
		return this.daoAchat.save(achat);
	}
	

	@PutMapping("/{id}")
	@JsonView(Views.ViewAchat.class)
	public Achat update(@RequestBody Achat achat, @PathVariable Integer id) {
		if (id != achat.getId() || !daoAchat.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}
		return this.daoAchat.save(achat);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!daoAchat.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Achat non trouvé");
		}
		this.daoAchat.deleteById(id);
	
}
}
	
	
