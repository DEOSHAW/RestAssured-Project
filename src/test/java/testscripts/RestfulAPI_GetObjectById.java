package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestfulAPI_GetObjectById
{
	@Test
	void testGetObjectById()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
				.addQueryParam("id", 3)
				.addQueryParam("id", 5)
				.addQueryParam("id", 10)
				.addHeader("Content-Type", "application/json")
				.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("name[2]",Matchers.equalTo("Apple iPad Mini 5th Gen"))
				.build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/objects")
		.then().assertThat().spec(resSpec);
		
	}

}
