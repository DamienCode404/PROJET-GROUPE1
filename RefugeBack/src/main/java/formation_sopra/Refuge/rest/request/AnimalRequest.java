package formation_sopra.Refuge.rest.request;

import java.time.LocalDate;
import java.util.Base64;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import formation_sopra.Refuge.model.Animal;
import formation_sopra.Refuge.model.Statut;
import formation_sopra.Refuge.model.Tag;

public class AnimalRequest {
	private Integer id;
	private String nom;
	private String race;
	private LocalDate naissance;
	private String description;
	private String statut;
	private String tag;
	private Integer idWorker;
	private String imageBase64;

	public AnimalRequest() {
		super();
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Integer getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(Integer idWorker) {
		this.idWorker = idWorker;
	}

	public static Animal convert(AnimalRequest animalRequest) {
		Animal animal = new Animal();
		BeanUtils.copyProperties(animalRequest, animal);

		if (animalRequest.getStatut() != null) {
			animal.setStatut(Statut.valueOf(animalRequest.getStatut()));
		}

		if (animalRequest.getTag() != null) {
			animal.setTag(Tag.valueOf(animalRequest.getTag()));
		}
		
		// Base64 vers byte
		if (animalRequest.getImageBase64() != null) {
			var b64 = animalRequest.getImageBase64();
			var split = Stream.of(b64.split(",")).toList().getLast();
			byte[] decode = Base64.getDecoder().decode(split);
			animal.setImage(decode);
		}

		return animal;
	}
}
