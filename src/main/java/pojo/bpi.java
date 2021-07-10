package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class bpi {
	@JsonProperty
	USD USD;
	public USD getUSD() {
		return USD;
	}
	public void setUSD(USD uSD) {
		USD = uSD;
	}
	public GBP getGBP() {
		return GBP;
	}
	public void setGBP(GBP gBP) {
		GBP = gBP;
	}
	public EUR getEUR() {
		return EUR;
	}
	public void setEUR(EUR eUR) {
		EUR = eUR;
	}
	@JsonProperty
	GBP GBP;
	@JsonProperty
	EUR EUR;
	

}
