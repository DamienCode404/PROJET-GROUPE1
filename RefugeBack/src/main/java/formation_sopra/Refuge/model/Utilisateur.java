package formation_sopra.Refuge.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Or JOINED, TABLE_PER_CLASS
@DiscriminatorColumn(name = "user_type") // Only needed for SINGLE_TABLE strategy
@Table(name="utilisateur")
public abstract class Utilisateur{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBasic.class)
	protected Integer id;
	@JsonView(Views.ViewBasic.class)
	protected String login;
	@JsonView(Views.ViewBasic.class)
	protected String password;
	@JsonView(Views.ViewBasic.class)
	protected String lastName;
	@JsonView(Views.ViewBasic.class)
	protected String firstName;
	@JsonView(Views.ViewBasic.class)
	protected String email;
	@JsonView(Views.ViewBasic.class)
	protected String phoneNumber;
	@Lob
	@JsonView(Views.ViewBasic.class)
	private byte[] image;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBasic.class)
	private Tag tag;
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Utilisateur() {}
	
	public Utilisateur(String login, String password, String lastName, String firstName, String email, String phoneNumber) {
		this.login = login;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", login=" + login + ", password=" + password + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", image="
				+ Arrays.toString(image) + ", tag=" + tag + "]";
	}
	
}
