package formation_sopra.Refuge.model;

public class AffectationProduitEspece{
	private Integer id;
	
	public AffectationProduitEspece(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + "]";
	}
}
