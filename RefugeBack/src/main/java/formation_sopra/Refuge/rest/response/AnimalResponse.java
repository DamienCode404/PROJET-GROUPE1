package formation_sopra.Refuge.rest.response;

import java.time.LocalDate;
import java.util.Base64;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation_sopra.Refuge.model.Animal;
import formation_sopra.Refuge.model.Views;


public class AnimalResponse {
	@JsonView(Views.ViewAnimal.class)
	private Integer id;
	@JsonView(Views.ViewAnimal.class)
	private String nom;
	@JsonView(Views.ViewAnimal.class)
	private String race;
	@JsonView(Views.ViewAnimal.class)
	private LocalDate naissance;
	@JsonView(Views.ViewAnimal.class)
	private String description;
	@JsonView(Views.ViewAnimal.class)
	private Integer idWorker;
	@JsonView(Views.ViewAnimal.class)
	private String imageBase64;
	@JsonView(Views.ViewAnimal.class)
	private String statut;
	@JsonView(Views.ViewAnimal.class)
	private String tag;
	
	public AnimalResponse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public LocalDate getNaissance() {
		return naissance;
	}

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}

	public String getDescription() {
		return description;
	}

	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(Integer idWorker) {
		this.idWorker = idWorker;
	}
	
	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	public static AnimalResponse convert(Animal animal) {
		AnimalResponse animalResponse = new AnimalResponse();
		BeanUtils.copyProperties(animal, animalResponse);
		
		if (animal.getStatut() != null) {
	        animalResponse.setStatut(animal.getStatut().toString());
	    }
		
		if (animal.getTag() != null) {
			animalResponse.setTag(animal.getTag().toString());
		}
		
	    if (animal.getImage() != null) {
	        byte[] imageBytes = animal.getImage();  // Récupérer l'image en tant que tableau de bytes
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes); // Convertir en base64
	        animalResponse.setImageBase64(base64Image); // Ajouter l'image encodée au DTO
	    }
		return animalResponse; 
	}
}
