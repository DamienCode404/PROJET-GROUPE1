package formation_sopra.Refuge.rest.request;

import java.util.Base64;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import formation_sopra.Refuge.model.Produit;

public class ProduitRequest {
	private Integer id;
	private String libelle;
	private String description;
	private Double prix;
	private Integer stock;
	private String imageBase64;

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public ProduitRequest() {
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

	public static Produit convert(ProduitRequest produitRequest) {
		Produit produit = new Produit();
		BeanUtils.copyProperties(produitRequest, produit);
		
		//Base64 vers byte 
		var b64 = produitRequest.getImageBase64();
		var split = Stream.of(b64.split(",")).toList().getLast();
		byte[] decode = Base64.getDecoder().decode(split);
		produit.setImage(decode);
		
		return produit;
	}
	

}
