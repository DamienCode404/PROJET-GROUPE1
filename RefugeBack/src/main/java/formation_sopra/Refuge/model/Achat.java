package formation_sopra.Refuge.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="achat")
public class Achat{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBasic.class)
	private Integer id;
	@JsonView(Views.ViewBasic.class)
	private Integer qte;
	@JsonView(Views.ViewBasic.class)
	private Double prix;
	@JsonView(Views.ViewBasic.class)
	private LocalDate date;
	
	public Achat() {}
	
	public Achat(Integer qte, Double prix, LocalDate date) {
		this.qte = qte;
		this.prix = prix;
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Achat [id=" + id + ", qte=" + qte + ", prix=" + prix + ", date=" + date + "]";
	}
	
}
