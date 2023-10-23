package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Activity;

public class FakeRestAPI_GetActivities 
{
	
	
	@Test
	void testGetActivitiesAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Activity[] ob=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/v1/Activities")
		.then().assertThat().spec(resSpec).extract().response().as(Activity[].class);
		
		System.out.println("Title of third activity is: "+ob[2].getTitle());
		
	}

}
