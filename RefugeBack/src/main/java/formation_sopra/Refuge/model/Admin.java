package formation_sopra.Refuge.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Utilisateur {
	
	public Admin() {}

	public Admin(String login, String password, String lastName, String firstName, String email,
			String phoneNumber, Tag tag) {
		super(login, password, lastName, firstName, email, phoneNumber, tag);
		// TODO Auto-generated constructor stub
	}

}