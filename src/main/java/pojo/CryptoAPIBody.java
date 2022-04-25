package pojo;

import java.util.List;

public class CryptoAPIBody {
	private List<Markets> markets;
	private String next;
	
	
	public List<Markets> getMarkets() {
		return markets;
	}
	public void setMarkets(List<Markets> markets) {
		this.markets = markets;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	

}
