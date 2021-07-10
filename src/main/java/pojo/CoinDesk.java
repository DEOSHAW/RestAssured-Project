package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinDesk {
	@JsonProperty
	private time time;
	@JsonProperty
	private String disclaimer;
	@JsonProperty
	private String chartName;
	@JsonProperty
	private bpi bpi;
	public bpi getBpi() {
		return bpi;
	}
	public void setBpi(bpi bpi) {
		this.bpi = bpi;
	}
	public time getTime() {
		return time;
	}
	public void setTime(time time) {
		this.time = time;
	}
	
	
	
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	
	
	
	
	

}
