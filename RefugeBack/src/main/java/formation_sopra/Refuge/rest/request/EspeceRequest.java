package formation_sopra.Refuge.rest.request;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import formation_sopra.Refuge.model.Espece;
import formation_sopra.Refuge.model.Views;

public class EspeceRequest {
	@JsonView(Views.ViewBasic.class)
	private Integer id;
	@JsonView(Views.ViewBasic.class)
	private String libelle;

	public EspeceRequest() {
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

	public static Espece convert(EspeceRequest especeRequest) {
		Espece espece = new Espece();
		BeanUtils.copyProperties(especeRequest, espece);
		
		return espece;
	}
	

}
