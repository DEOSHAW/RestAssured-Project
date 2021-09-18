package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationResponse {
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String token;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}
