package app.inmobiliaria.api.payload.response;

import app.inmobiliaria.api.entity.Privilegios;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private String roles;
	private String token;
	private Privilegios privilegios;

	public UserInfoResponse(Long id, String username, String email, String roles, String token, Privilegios privilegios) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.token = token;
		this.privilegios = privilegios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Privilegios getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(Privilegios privilegios) {
		this.privilegios = privilegios;
	}
	
	
	
	
	
}
