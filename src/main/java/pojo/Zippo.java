package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Zippo {
	@JsonProperty("post code")
	private String post_code;
	private String country;
	@JsonProperty("country abbreviation")
	private String country_abbreviation;
	List<places> places;
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_abbreviation() {
		return country_abbreviation;
	}
	public void setCountry_abbreviation(String country_abbreviation) {
		this.country_abbreviation = country_abbreviation;
	}
	public List<places> getPlaces() {
		return places;
	}
	public void setPlaces(List<places> places) {
		this.places = places;
	}
	

}
