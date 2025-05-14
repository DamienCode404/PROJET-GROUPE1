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

		Animal a2 = new Animal(
		    "Misty", "Chartreux", LocalDate.of(2019, 3, 20),
		    "Chat calme, idéal pour appartement", null,
		    Statut.Reserve,
		    Tag.Calme
		);
		daoAnimal.save(a2);

		Animal a3 = new Animal(
		    "Speedy", "Lévrier", LocalDate.of(2021, 7, 2),
		    "Très énergique, aime courir dans les grands espaces", null,
		    Statut.Disponible,
		    Tag.Sportif
		);
		daoAnimal.save(a3);

		Animal a4 = new Animal(
		    "Tango", "Cockatiel", LocalDate.of(2022, 2, 11),
		    "Oiseau sociable qui aime chanter", null,
		    Statut.Adopte,
		    Tag.Sociable
		);
		daoAnimal.save(a4);

		Animal a5 = new Animal(
		    "Nougat", "Lapin Nain", LocalDate.of(2023, 1, 5),
		    "Petit rongeur doux et affectueux", null,
		    Statut.Disponible,
		    Tag.Câlin
		);
		daoAnimal.save(a5);

		Animal a6 = new Animal(
		    "Shadow", "Maine Coon", LocalDate.of(2018, 10, 23),
		    "Chat indépendant mais très intelligent", null,
		    Statut.Disponible,
		    Tag.Intelligent
		);
		daoAnimal.save(a6);

		Animal a7 = new Animal(
		    "Ziggy", "Border Collie", LocalDate.of(2020, 8, 30),
		    "Chien obéissant et vif, idéal pour l’agility", null,
		    Statut.Disponible,
		    Tag.Obéissant
		);
		daoAnimal.save(a7);

		Animal a8 = new Animal(
		    "Luna", "Persan", LocalDate.of(2017, 6, 12),
		    "Très affectueuse, adore les câlins", null,
		    Statut.Adopte,
		    Tag.Affectueux
		);
		daoAnimal.save(a8);

		Animal a9 = new Animal(
		    "Rocky", "Rottweiler", LocalDate.of(2019, 11, 18),
		    "Protecteur et fidèle", null,
		    Statut.Disponible,
		    Tag.Protecteur
		);
		daoAnimal.save(a9);

		Animal a10 = new Animal(
		    "Bulle", "Poisson Rouge", LocalDate.of(2023, 4, 1),
		    "Petit poisson curieux et vif", null,
		    Statut.Disponible,
		    Tag.Curieux
		);
		daoAnimal.save(a10);

		Animal a11 = new Animal(
		    "Bella", "Golden Retriever", LocalDate.of(2020, 9, 14),
		    "Parfait avec les enfants et très docile", null,
		    Statut.Reserve,
		    Tag.AdapteAuxEnfants
		);
		daoAnimal.save(a11);

		Animal a12 = new Animal(
		    "Whiskers", "Siamois", LocalDate.of(2022, 12, 9),
		    "Chat timide mais très doux une fois en confiance", null,
		    Statut.Disponible,
		    Tag.Timide
		);
		daoAnimal.save(a12);
		
		Animal a13 = new Animal(
			    "Chips", "Octodon", LocalDate.of(2022, 5, 3),
			    "Petit rongeur curieux et sociable, aime les roues et les tunnels", null,
			    Statut.Disponible,
			    Tag.Curieux
			);
			daoAnimal.save(a13);

		Animal a14 = new Animal(
		    "Zelda", "Serpent des blés", LocalDate.of(2021, 8, 20),
		    "Reptile calme et facile à manipuler pour les amateurs de reptiles", null,
		    Statut.Reserve,
		    Tag.Calme
		);
		daoAnimal.save(a14);

		Animal a15 = new Animal(
		    "Bambou", "Chèvre naine", LocalDate.of(2020, 4, 12),
		    "Adorable petite chèvre, très gourmande et sociable", null,
		    Statut.Disponible,
		    Tag.Gourmand
		);
		daoAnimal.save(a15);

		Animal a16 = new Animal(
		    "Kiki", "Furet", LocalDate.of(2023, 3, 15),
		    "Furet joueur et vif, a besoin d’espace pour explorer", null,
		    Statut.Disponible,
		    Tag.BesoinEspace
		);
		daoAnimal.save(a16);

		Animal a17 = new Animal(
		    "Néo", "Caméléon panthère", LocalDate.of(2022, 10, 7),
		    "Reptile calme, sensible à l’environnement, change de couleur", null,
		    Statut.Disponible,
		    Tag.Indépendant
		);
		daoAnimal.save(a17);

		Animal a18 = new Animal(
		    "Mango", "Cochon d’Inde", LocalDate.of(2023, 6, 22),
		    "Très affectueux, aime être pris dans les bras", null,
		    Statut.Reserve,
		    Tag.Câlin
		);
		daoAnimal.save(a18);

		Animal a19 = new Animal(
		    "Cactus", "Iguane vert", LocalDate.of(2021, 1, 25),
		    "Reptile imposant mais docile, demande un habitat adapté", null,
		    Statut.Disponible,
		    Tag.Docile
		);
		daoAnimal.save(a19);

		Animal a20 = new Animal(
		    "Litchi", "Axolotl", LocalDate.of(2023, 9, 3),
		    "Amphibien aquatique fascinant, demande peu d’interaction", null,
		    Statut.Disponible,
		    Tag.Calme
		);
		daoAnimal.save(a20);

		Animal a21 = new Animal(
		    "Snow", "Rat domestique", LocalDate.of(2022, 11, 11),
		    "Intelligent, joueur et très sociable avec l'humain", null,
		    Statut.Adopte,
		    Tag.Intelligent
		);
		daoAnimal.save(a21);

		Animal a22 = new Animal(
		    "Twix", "Chinchilla", LocalDate.of(2021, 12, 19),
		    "Affectueux et sensible, a besoin d’un habitat bien sec", null,
		    Statut.Disponible,
		    Tag.Affectueux
		);
		daoAnimal.save(a22);


        System.out.println("Animaux insérés avec succès !");
    }
    
    @Test
    void testInsertEspece() {
    	System.out.println("Début de l'insertion des especes.");
    	
    	Espece e1 = new Espece("Canin");
    	Espece e2 = new Espece("Félin");
    	Espece e3 = new Espece("Rongeur");
    	Espece e4 = new Espece("Oiseau");
    	Espece e5 = new Espece("Reptile");
    	Espece e6 = new Espece("Poisson");
    	Espece e7 = new Espece("Amphibien");
    	Espece e8 = new Espece("Équidé");
    	Espece e9 = new Espece("Bovin");

    	daoEspece.save(e1);
    	daoEspece.save(e2);
    	daoEspece.save(e3);
    	daoEspece.save(e4);
    	daoEspece.save(e5);
    	daoEspece.save(e6);
    	daoEspece.save(e7);
    	daoEspece.save(e8);
    	daoEspece.save(e9);
    	
        System.out.println("Especes insérés avec succès !");
    }
    
    @Test
    void testInsertProduit() {
    	System.out.println("Début de l'insertion des produits.");
    	
    	Produit p1 = new Produit("Croquettes Chat", "Croquettes riches en protéines", 14.99, 200);
    	Produit p2 = new Produit("Panier Douillet", "Panier pour chien taille M", 39.90, 50);
    	Produit p3 = new Produit("Foin Bio", "Foin naturel pour rongeurs et lapins", 8.50, 120);
    	Produit p4 = new Produit("Lampe UVB", "Éclairage UVB pour reptiles tropicaux", 29.99, 30);
    	Produit p5 = new Produit("Roue Silencieuse", "Roue d'exercice pour petits rongeurs", 15.75, 80);
    	Produit p6 = new Produit("Gamelle Inox", "Gamelle antidérapante pour animaux", 9.90, 100);
    	Produit p7 = new Produit("Terrarium 60x40", "Terrarium en verre pour reptiles", 89.00, 15);
    	Produit p8 = new Produit("Peluches pour chiens", "Jouets résistants pour chiens joueurs", 12.90, 70);
    	Produit p9 = new Produit("Litière Végétale", "Litière biodégradable pour NAC", 11.20, 90);
    	Produit p10 = new Produit("Tunnel en tissu", "Tunnel de jeu pour furets et petits animaux", 17.50, 40);
    	Produit p11 = new Produit("Bâtonnets à grignoter", "Snacks naturels pour rongeurs", 6.90, 100);
    	Produit p12 = new Produit("Brosse Douce", "Brosse pour poils longs (chinchilla, chat...)", 13.60, 45);
    	Produit p13 = new Produit("Chauffe-eau aquarium", "Maintien de température pour axolotl", 21.80, 25);
    	Produit p14 = new Produit("Croquettes LOF", "Croquettes premium pour chiens de race", 24.99, 60);
    	Produit p15 = new Produit("Vitamine Reptile", "Complément alimentaire pour reptiles", 10.50, 35);

    	daoProduit.save(p1);
    	daoProduit.save(p2);
    	daoProduit.save(p3);
    	daoProduit.save(p4);
    	daoProduit.save(p5);
    	daoProduit.save(p6);
    	daoProduit.save(p7);
    	daoProduit.save(p8);
    	daoProduit.save(p9);
    	daoProduit.save(p10);
    	daoProduit.save(p11);
    	daoProduit.save(p12);
    	daoProduit.save(p13);
    	daoProduit.save(p14);
    	daoProduit.save(p15);


        System.out.println("Produits insérés avec succès !");
    }
    
    @Test
    void testInsertUtilisateur() {
    	System.out.println("Début de l'insertion des utilisateurs.");
    	
    	Utilisateur c1 = new Client(
		    "alice34", "Motdepasse1", "Martin", "Alice", "alice.martin@mail.com", "0611223344", Tag.Câlin
		);
		Utilisateur c2 = new Client(
		    "runner75", "Motdepasse2", "Lemoine", "Julien", "julien.lemoine@mail.com", "0622334455", Tag.Sportif
		);
		Utilisateur c3 = new Client(
		    "familleDupont", "Motdepasse3", "Dupont", "Sophie", "sophie.dupont@mail.com", "0633445566", Tag.AdapteAuxEnfants
		);
		Utilisateur c4 = new Client(
		    "chatslover", "Motdepasse4", "Durand", "Clara", "clara.durand@mail.com", "0644556677", Tag.AdapteAuxChats
		);
		Utilisateur c5 = new Client(
		    "nacfan", "Motdepasse5", "Moreau", "Luc", "luc.moreau@mail.com", "0655667788", Tag.Curieux
		);
		Utilisateur c6 = new Client(
		    "reptilex", "Motdepasse6", "Garnier", "Eva", "eva.garnier@mail.com", "0666778899", Tag.Indépendant
		);
		Utilisateur c7 = new Client(
		    "furetpassion", "Motdepasse7", "Marchand", "Axel", "axel.marchand@mail.com", "0677889900", Tag.Joueur
		);
		Utilisateur c8 = new Client(
		    "zenhome", "Motdepasse8", "Bertrand", "Julie", "julie.bertrand@mail.com", "0688990011", Tag.Calme
		);
		Utilisateur c9 = new Client(
		    "petitappart", "Motdepasse9", "Faure", "Lucie", "lucie.faure@mail.com", "0699001122", Tag.CompatibleAppartement
		);
		Utilisateur c10 = new Client(
		    "protectdog", "Motdepasse10", "Petit", "Romain", "romain.petit@mail.com", "0612345678", Tag.Protecteur
		);

		// Admins
		Utilisateur ad1 = new Admin(
		    "admin1", "SecureAdmin1", "Lopez", "Marie", "marie.lopez@admin.fr", "0701010101", Tag.Intelligent
		);
		Utilisateur ad2 = new Admin(
		    "admin2", "SecureAdmin2", "Morel", "Thomas", "thomas.morel@admin.fr", "0702020202", Tag.Obéissant
		);

		// Workers
		Utilisateur w1 = new Worker(
		    "worker1", "WorkerPass1", "Fontaine", "Camille", "camille.fontaine@refuge.fr", "0713131313", Tag.Docile
		);
		Utilisateur w2 = new Worker(
		    "worker2", "WorkerPass2", "Noël", "Sébastien", "seb.noel@refuge.fr", "0714141414", Tag.Peureux
		);
		Utilisateur w3 = new Worker(
		    "worker3", "WorkerPass3", "Colin", "Mathilde", "mathilde.colin@refuge.fr", "0715151515", Tag.Sociable
		);

		// Sauvegarde
		daoUtilisateur.save(c1);
		daoUtilisateur.save(c2);
		daoUtilisateur.save(c3);
		daoUtilisateur.save(c4);
		daoUtilisateur.save(c5);
		daoUtilisateur.save(c6);
		daoUtilisateur.save(c7);
		daoUtilisateur.save(c8);
		daoUtilisateur.save(c9);
		daoUtilisateur.save(c10);

		daoUtilisateur.save(ad1);
		daoUtilisateur.save(ad2);

		daoUtilisateur.save(w1);
		daoUtilisateur.save(w2);
		daoUtilisateur.save(w3);

        
        System.out.println("Utilisateurs insérés avec succès !");
    }
}
