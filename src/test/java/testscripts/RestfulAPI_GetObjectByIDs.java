package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestfulAPI_GetObjectByIDs
{
	@Test
	void testGetObjectByIDsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev/")
		.addQueryParam("id", 3).addQueryParam("id", 5).addQueryParam("id", 10)
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("Objects.json"))
				.build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/objects")
		.then().assertThat().spec(resSpec);
	
		
	}

}
