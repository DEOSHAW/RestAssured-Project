package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SamplePhoto
{
	private boolean success;
	@JsonIgnoreProperties
	private String message;
	private Photo photo;
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	

}
