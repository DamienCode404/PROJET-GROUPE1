package formation_sopra.Refuge.model;

public interface Views {
	
	public static interface ViewBasic {
	}

	// Achat
	public static interface ViewAchat extends ViewBasic {
	}
	public static interface ViewAchatDetail extends ViewAchat {
	}

	// Animal
	public static interface ViewAnimal extends ViewBasic {
	}
	public static interface ViewAnimalDetail extends ViewAnimal {
	}

	// Espece
	public static interface ViewEspece extends ViewBasic {
	}
	public static interface ViewEspeceDetail extends ViewEspece {
	}

	// Produit
	public static interface ViewProduit extends ViewBasic {
	}
	public static interface ViewProduitDetail extends ViewProduit {
	}
}