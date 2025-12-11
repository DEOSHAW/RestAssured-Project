package testscripts;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Cart;
import pojo.CartProduct;

public class FakeStore_AddANewCartAPITest 
{
	@Test
	void testAddANewCartAPI()
	{
		CartProduct cp=new CartProduct();
		cp.setId(23);
		cp.setDescription("This is latest product");
		cp.setCategory("Electronics");
		cp.setPrice(22.5);
		cp.setTitle("Speaker");
		cp.setImage("http://example.com/");
		List<CartProduct> cpList=new ArrayList<CartProduct>();
		cpList.add(cp);
		Cart ct=new Cart();
		ct.setId(23);
		ct.setUserId(33);
		ct.setProducts(cpList);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com")
		.addHeader("Content-Type", "application/json")
		.setBody(ct)
		.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201)
				.expectBody("products[0].category",Matchers.equalTo("Electronics"))
				.build();
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/carts")
		.then().assertThat().spec(resSpec);
		
		
	}

}
