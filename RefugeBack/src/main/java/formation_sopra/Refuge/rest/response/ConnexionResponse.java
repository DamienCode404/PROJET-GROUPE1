package formation_sopra.Refuge.rest.response;

public class ConnexionResponse {
	private boolean success;
	private String token;// ← Ajout de la propriété token
	private Integer idUser;
	private String roleUser;

	public ConnexionResponse() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}
	
	
	
}