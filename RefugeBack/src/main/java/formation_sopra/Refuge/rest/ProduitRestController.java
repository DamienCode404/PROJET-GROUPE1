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

import formation_sopra.Refuge.model.Produit;
import formation_sopra.Refuge.model.Views;
import formation_sopra.Refuge.rest.request.ProduitRequest;
import formation_sopra.Refuge.rest.response.ProduitResponse;
import formation_sopra.Refuge.service.ProduitService;

@RestController
@RequestMapping("/produit")
public class ProduitRestController {

	private ProduitService produitService;

	public ProduitRestController(ProduitService produitService) {
		super();
		this.produitService = produitService;
	}

	@GetMapping("")
	@JsonView(Views.ViewProduit.class)
	public List<ProduitResponse> getAll() {
		List<Produit> produits = this.produitService.findAll();
		
		System.out.println(produits);

		return produits.stream().map(ProduitResponse::convert).toList();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public ProduitResponse getById(@PathVariable Integer id) {
		Produit produit = this.produitService.findById(id);
		
		return ProduitResponse.convert(produit);
	}

	@PostMapping("")
	@JsonView(Views.ViewProduit.class)
	public Produit create(@RequestBody ProduitRequest produitRequest) {
		Produit produit = ProduitRequest.convert(produitRequest);
		return this.produitService.create(produit);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit update(@RequestBody ProduitRequest produitRequest, @PathVariable Integer id) {
		if (id != produitRequest.getId() || !this.produitService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}
		
		Produit produit = ProduitRequest.convert(produitRequest);

		return this.produitService.update(produit);
	}

	@DeleteMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public void delete(@PathVariable Integer id) {
		if (!this.produitService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.produitService.deleteById(id);
	}
}
