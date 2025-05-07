package formation_sopra.Refuge.rest.response;

import java.util.Base64;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation_sopra.Refuge.model.Produit;
import formation_sopra.Refuge.model.Views;

public class ProduitResponse {
	@JsonView(Views.ViewProduit.class)
	private Integer id;
	@JsonView(Views.ViewProduit.class)
	private String libelle;
	@JsonView(Views.ViewProduit.class)
	private String description;
	@JsonView(Views.ViewProduit.class)
	private Double prix;
	@JsonView(Views.ViewProduit.class)
	private Integer stock;

	@JsonView(Views.ViewProduit.class)
	private String imageBase64; 
	 
	public ProduitResponse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public static ProduitResponse convert(Produit produit) {
		ProduitResponse produitResponse = new ProduitResponse();
		BeanUtils.copyProperties(produit, produitResponse);
		
		
		   
	    if (produit.getImage() != null) {
	        byte[] imageBytes = produit.getImage();  // Récupérer l'image en tant que tableau de bytes
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes); // Convertir en base64
	        produitResponse.setImageBase64(base64Image); // Ajouter l'image encodée au DTO
	    }
		return produitResponse;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
}
