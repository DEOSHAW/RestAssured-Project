package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DummyJsonSearchProductAPI 
{
	
	
	
	@Test
	void testSearchProductAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyjson.com/")
		.addQueryParam("q", "Laptop").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("products.title",Matchers.hasItem("Microsoft Surface Laptop 4")).build();
		
		Headers hds=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/products/search")
		.then().assertThat().spec(resSpec).extract().response().headers();
		
		for(Header h:hds)
		{
			System.out.println(h.getName()+"---"+h.getValue());
		}
		
	}

}
