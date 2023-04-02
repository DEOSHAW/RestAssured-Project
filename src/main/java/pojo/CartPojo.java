package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CartPojo {
	
	private List<Carts> Carts;

	public List<Carts> getCarts() {
		return Carts;
	}

	public void setCarts(List<Carts> carts) {
		Carts = carts;
	} 

}
