package formation_sopra.Refuge.model;

import jakarta.persistence.Entity;

@Entity
public class Worker extends Utilisateur {
	
	public Worker() {}

	public Worker(String login, String password, String lastName, String firstName, String email,
			String phoneNumber) {
		super(login, password, lastName, firstName, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}

}