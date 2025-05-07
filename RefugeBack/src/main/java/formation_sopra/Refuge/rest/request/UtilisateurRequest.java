package formation_sopra.Refuge.rest.request;

import java.util.Base64;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import formation_sopra.Refuge.model.Admin;
import formation_sopra.Refuge.model.Client;
import formation_sopra.Refuge.model.Tag;
import formation_sopra.Refuge.model.Utilisateur;
import formation_sopra.Refuge.model.Worker;

public class UtilisateurRequest {
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
	
	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	public UtilisateurRequest() {
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

	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public static Utilisateur convert(UtilisateurRequest utilisateurRequest) {
		Utilisateur utilisateur = null;
		if(utilisateurRequest.getUtilisateurType() == UtilisateurType.ADMIN) {
			utilisateur = new Admin();
		} else if(utilisateurRequest.getUtilisateurType() == UtilisateurType.WORKER) {
			utilisateur = new Worker();
		} else if(utilisateurRequest.getUtilisateurType() == UtilisateurType.CLIENT) {
			utilisateur = new Client();
		}
		
		BeanUtils.copyProperties(utilisateurRequest, utilisateur);
		
		if (utilisateurRequest.getTag() != null) {
			utilisateur.setTag(Tag.valueOf(utilisateurRequest.getTag()));
		}
		
		//Base64 vers byte 
		if (utilisateurRequest.getImageBase64() != null) {
			var b64 = utilisateurRequest.getImageBase64();
			var split = Stream.of(b64.split(",")).toList().getLast();
			byte[] decode = Base64.getDecoder().decode(split);
			utilisateur.setImage(decode);
		}
		
		return utilisateur;
	}
	
	public enum UtilisateurType {
		ADMIN, WORKER, CLIENT;
	}
}
