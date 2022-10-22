package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TypicodeGetPosts {
	
	
	
	@Test
	void testTypicodeGetAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com")
		.addPathParam("id", "1").build();
		
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200)
		.expectBody("title",Matchers.equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")).build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(req)
		.when().get("/posts/{id}")
		.then().spec(res);
		
	
		
	}

}
