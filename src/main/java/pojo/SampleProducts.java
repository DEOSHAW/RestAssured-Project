package pojo;

import java.util.List;
import pojo.Product;

public class SampleProducts
{
	private boolean success;
	private int total_products;
	private String message;
	private int offset;
	private int limit;
	private List<Product> products;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getTotal_products() {
		return total_products;
	}
	public void setTotal_products(int total_products) {
		this.total_products = total_products;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
