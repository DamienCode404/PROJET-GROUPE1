package formation_sopra.Refuge.model;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="animal")
public class Animal{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBasic.class)
	private Integer id;
	@JsonView(Views.ViewBasic.class)
	private String nom;
	@JsonView(Views.ViewBasic.class)
	private String race;
	@JsonView(Views.ViewBasic.class)
	private LocalDate naissance;
	@JsonView(Views.ViewBasic.class)
	private String description;
	@JsonView(Views.ViewBasic.class)
	@Column(name="id_worker")
	private Integer idWorker;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBasic.class)
	private Statut statut;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBasic.class)
	private Tag tag;
	
	@Lob
	@JsonView(Views.ViewBasic.class)
	private byte[] image;
	
	public Animal() {}
	
	public Animal(String nom, String race, LocalDate naissance, String description, Integer idWorker) {
		this.nom = nom;
		this.race = race;
		this.naissance = naissance;
		this.description = description;
		this.idWorker = idWorker;
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
	
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(Integer idWorker) {
		this.idWorker = idWorker;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nom=" + nom + ", race=" + race + ", naissance=" + naissance + ", description="
				+ description + ", idWorker=" + idWorker + ", statut=" + statut + ", tag=" + tag + ", image="
				+ Arrays.toString(image) + "]";
	}
}
