package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CartPojo;

public class DummyJsonCartsAPI
{
	
	
	@Test
	void testGetCartsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyjson.com/").addHeader("ContentType", "application/json").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		
		Response resp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/carts")
		.then().assertThat().spec(resSpec).extract().response();
		
		JsonPath js=resp.jsonPath();
		
		String title=js.getString("carts.products[0].title[2]");
		
		System.out.println("Title of 3rd product is: "+title);
		
		CartPojo ob=resp.as(CartPojo.class);
		System.out.println(ob.getCarts().get(1).getProducts().get(2).getTitle());
		
	}

}
