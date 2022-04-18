package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Num {
	@JsonProperty
	private String text;
	@JsonProperty
	private int Number;
	@JsonProperty
	private boolean found;
	@JsonProperty
	private String type;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int Number) {
		this.Number = Number;
	}
	public boolean isFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}
	
}
