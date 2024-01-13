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

public class SampleDataUsersAPI
{
	@Test
	void getAllUsers()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.slingacademy.com").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
		.expectBody("message",Matchers.equalTo("Sample data for testing and learning purposes"))
		.expectBody("users.job",Matchers.hasItem("Technical author"))
		.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/v1/sample-data/users")
		.then().assertThat().spec(resSpec);
		
	}

}
