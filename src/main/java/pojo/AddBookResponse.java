package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponse {
	@JsonProperty
	private String Msg;
	@JsonProperty
	private String ID;

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	

	

}
