package formation_sopra.Refuge.rest.response;

import java.util.Base64;

import org.springframework.beans.BeanUtils;

import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

public class UtilisateurResponse {
	private Integer id;
	private String login;
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private UtilisateurType utilisateurType;
	private String imageBase64;
	private String tag;
	
	public UtilisateurResponse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UtilisateurType getUtilisateurType() {
		return utilisateurType;
	}

	public void setUtilisateurType(UtilisateurType utilisateurType) {
		this.utilisateurType = utilisateurType;
	}
	
	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public static UtilisateurResponse convert(Utilisateur utilisateur) {
		UtilisateurResponse utilisateurResponse = new UtilisateurResponse();
		BeanUtils.copyProperties(utilisateur, utilisateurResponse);
		
		if(utilisateur instanceof Worker) {
			utilisateurResponse.setUtilisateurType(UtilisateurType.WORKER);
			
		} else if(utilisateur instanceof Client) {
			utilisateurResponse.setUtilisateurType(UtilisateurType.CLIENT);
			
		} else if(utilisateur instanceof Admin) {
			utilisateurResponse.setUtilisateurType(UtilisateurType.ADMIN);
		}
		
		if (utilisateur.getTag() != null) {
			utilisateurResponse.setTag(utilisateur.getTag().toString());
		}
		
	    if (utilisateur.getImage() != null) {
	        byte[] imageBytes = utilisateur.getImage();  // Récupérer l'image en tant que tableau de bytes
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes); // Convertir en base64
	        utilisateurResponse.setImageBase64(base64Image); // Ajouter l'image encodée au DTO
	    }
		return utilisateurResponse; 
	}
	
	public enum UtilisateurType {
		ADMIN, WORKER, CLIENT;
	}
}
