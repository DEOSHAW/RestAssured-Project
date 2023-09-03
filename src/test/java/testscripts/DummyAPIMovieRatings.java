package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DummyAPIMovieRatings {
	
	
	@Test
	void testMovieRatingsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyapi.online/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/movies")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println("Rating of third movie "+js.getString("movie[2]")+" is: "+js.getInt("rating[2]"));
		System.out.println("Total number of movies: "+js.getList("movie").size());
	}

}
