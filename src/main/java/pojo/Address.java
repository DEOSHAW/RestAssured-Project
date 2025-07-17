package pojo;

import java.util.List;

public class Address 
{
	private String status;
	private int code;
	private String locale;
	private String seed;
	private int total;
	private List<AddressData> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<AddressData> getData() {
		return data;
	}
	public void setData(List<AddressData> data) {
		this.data = data;
	}
}
