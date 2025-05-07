package formation_sopra.Refuge.rest.request;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation_sopra.Refuge.model.Achat;
import formation_sopra.Refuge.model.Views;

public class AchatRequest {
	@JsonView(Views.ViewBasic.class)
	private Integer id;
	@JsonView(Views.ViewBasic.class)
	private Integer qte;
	@JsonView(Views.ViewBasic.class)
	private Double prix;
	@JsonView(Views.ViewBasic.class)
	private LocalDate date;

	public AchatRequest() {
		super();
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

	public static Achat convert(AchatRequest achatRequest) {
		Achat achat = new Achat();
		BeanUtils.copyProperties(achatRequest, achat);
		
		return achat;
	}
	

}
