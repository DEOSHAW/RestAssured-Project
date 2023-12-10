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

public class PRA_GetNews
{
	
	
	@Test
	void testNewsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://chroniclingamerica.loc.gov/")
		.addQueryParam("term", "oakland")
		.addQueryParam("format", "json")
		.addQueryParam("page", "5").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("items.place_of_publication",Matchers.hasItem("West Jefferson, N.C."))
				.build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
		.when().get("/search/titles/results/")
		.then().assertThat().spec(resSpec);
		
	}

}
