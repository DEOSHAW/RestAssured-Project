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

public class FakeRestAPI_GetParticularActivity 
{
	
	
	@Test
	void testGetParticularActivityAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net/")
		.addPathParam("ID", 2).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("title",Matchers.containsString("2")).build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/v1/Activities/{ID}")
		.then().assertThat().spec(resSpec);
		
	}

}
