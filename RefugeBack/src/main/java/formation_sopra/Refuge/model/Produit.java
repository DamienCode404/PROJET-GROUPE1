package formation_sopra.Refuge.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="produit")
public class Produit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Lob
	@JsonView(Views.ViewBasic.class)
	private byte[] image;
	
	public Produit() {}
	
	public Produit(String libelle, String description, Double prix, Integer stock) {
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.stock = stock;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", libelle=" + libelle + ", description=" + description + ", prix=" + prix
				+ ", stock=" + stock + ", image=" + Arrays.toString(image) + "]";
	}
}
