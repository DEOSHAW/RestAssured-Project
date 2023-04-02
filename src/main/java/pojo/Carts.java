package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Carts {
	
	private int id;
	List<Products> Products;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Products> getProducts() {
		return Products;
	}
	public void setProducts(List<Products> products) {
		Products = products;
	}


}
