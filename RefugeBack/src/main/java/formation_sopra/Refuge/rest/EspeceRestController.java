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

import formation_sopra.Refuge.dao.IDAOEspece;
import formation_sopra.Refuge.model.Espece;
import formation_sopra.Refuge.model.Views;

@RestController
@RequestMapping("/espece")
public class EspeceRestController {

	private IDAOEspece daoEspece;

	public EspeceRestController(IDAOEspece daoEspece) {
		super();
		this.daoEspece = daoEspece;
	}

	@GetMapping("")
	@JsonView(Views.ViewEspece.class)
	public List<Espece> getAll() {
		return this.daoEspece.findAll();
	}
	@GetMapping("/{id}")
	@JsonView(Views.ViewEspece.class)
	public Espece getById(@PathVariable Integer id) {
		return this.daoEspece.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("")
	@JsonView(Views.ViewEspece.class)
	public Espece create(@RequestBody Espece espece) {
		return this.daoEspece.save(espece);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewEspece.class)
	public Espece update(@RequestBody Espece espece, @PathVariable Integer id) {
		if (id != espece.getId() || !this.daoEspece.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoEspece.save(espece);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoEspece.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoEspece.deleteById(id);
	}
}
