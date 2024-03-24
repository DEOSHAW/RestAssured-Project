package pojo;

import java.util.List;

public class EmailResponse 
{
	private String message;
	private List<Email> emails;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Email> getEmails() {
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	

}
