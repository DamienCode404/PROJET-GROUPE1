package formation_sopra.Refuge;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import formation_sopra.Refuge.dao.IDAOAnimal;
import formation_sopra.Refuge.dao.IDAOEspece;
import formation_sopra.Refuge.dao.IDAOProduit;
import formation_sopra.Refuge.dao.IDAOUtilisateur;
import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Animal;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Espece;
import formation_sopra.Refuge.model.Produit;
import formation_sopra.Refuge.model.Statut;
import formation_sopra.Refuge.model.Tag;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

@SpringBootTest
class RefugeApplicationTests {

	@Autowired
	private IDAOAnimal daoAnimal;
	@Autowired
	private IDAOEspece daoEspece;
	@Autowired
	private IDAOProduit daoProduit;
	@Autowired
	private IDAOUtilisateur daoUtilisateur;
    
    @Test
    void testInsertAnimal() {
    	System.out.println("Début de l'insertion des animaux.");
    	
        Animal a1 = new Animal(
                "Rex", "Berger Allemand", LocalDate.of(2020, 5, 15), 
                "Chien très joueur et affectueux", null,
                Statut.Disponible,
                Tag.Joueur
            );

            daoAnimal.save(a1);

        System.out.println("Animaux insérés avec succès !");
    }
    
    @Test
    void testInsertEspece() {
    	System.out.println("Début de l'insertion des especes.");
    	
        Espece e1 = new Espece("Canin");
        Espece e2 = new Espece("Félin");

        daoEspece.save(e1);
        daoEspece.save(e2);

        System.out.println("Especes insérés avec succès !");
    }
    
    @Test
    void testInsertProduit() {
    	System.out.println("Début de l'insertion des produits.");
    	
        Produit p1 = new Produit("Croquettes Chat", "Croquettes riches en protéines", 14.99, 200);
        Produit p2 = new Produit("Panier Douillet", "Panier pour chien taille M", 39.90, 50);


        daoProduit.save(p1);
        daoProduit.save(p2);

        System.out.println("Produits insérés avec succès !");
    }
    
    @Test
    void testInsertUtilisateur() {
    	System.out.println("Début de l'insertion des utilisateurs.");
    	
        Utilisateur c1 = new Client(
                "jdoe", "Client", "Doe", "John", "jdoe@example.com", "0601020304", Tag.Sociable
            );
        Utilisateur ad1 = new Admin(
                "jdoe", "Adin", "Doe", "John", "jdoe@example.com", "0601020304", Tag.Sociable
            );
        Utilisateur w1 = new Worker(
                "jdoe", "Worker", "Doe", "John", "jdoe@example.com", "0601020304", Tag.Sociable
            );
        
        daoUtilisateur.save(c1);
        daoUtilisateur.save(ad1);
        daoUtilisateur.save(w1);
        
        System.out.println("Utilisateurs insérés avec succès !");
    }
}
