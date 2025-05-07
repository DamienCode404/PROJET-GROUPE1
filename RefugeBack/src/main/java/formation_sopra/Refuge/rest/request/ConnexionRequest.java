package formation_sopra.Refuge.rest.request;

public class ConnexionRequest {
	private String login;
	private String password;

	public ConnexionRequest() {
		super();
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

}
