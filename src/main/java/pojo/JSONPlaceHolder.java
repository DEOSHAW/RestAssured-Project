package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONPlaceHolder {
	@JsonProperty
	int userId;
	@JsonProperty
	int id;
	@JsonProperty
	String title;
	@JsonProperty
	String body;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	

}
