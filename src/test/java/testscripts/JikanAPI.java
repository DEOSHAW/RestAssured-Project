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

public class JikanAPI {
	
	
	//https://api.jikan.moe/v3/search/anime?q=naruto
	
	@Test
	void testJikanAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder()
		.setBaseUri("https://api.jikan.moe").addQueryParam("q", "naruto").build();
		
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectBody("results.mal_id",Matchers.hasItem(28755)).build();
		//ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		RestAssured.given().relaxedHTTPSValidation().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(req)
		.when().get("/v3/search/anime")
		.then().spec(resp);
		
		
	}

}
