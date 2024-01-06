package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo 
{
	private String url;
	private String title;
	@JsonProperty("user")
	private int userId;
	private String description;
	private int id;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUser() {
		return userId;
	}
	public void setUser(int user) {
		this.userId = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
