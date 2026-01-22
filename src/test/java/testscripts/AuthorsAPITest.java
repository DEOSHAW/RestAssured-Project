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

public class AuthorsAPITest 
{
	@Test
	void testAuthorsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://openlibrary.org/")
				.addQueryParam("q", "j%20k%20rowling")
				.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("numFoundExact", Matchers.equalTo(true))
		        .build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/search/authors.json")
		.then().assertThat().spec(resSpec);
	}
}
