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

public class FakeRestAPI_PostActivities
{
	
	@Test
	void testPostActivitiesAPI()
	{
		
		// /api/v1/Activities
		
		String payload="{\r\n"
				+ "  \"id\": 1,\r\n"
				+ "  \"title\": \"string\",\r\n"
				+ "  \"dueDate\": \"2024-02-11T14:32:47.446Z\",\r\n"
				+ "  \"completed\": true\r\n"
				+ "}";
		byte[] payLoad=payload.getBytes();
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net")
				.addHeader("Content-Type", "application/json")
		.setBody(payLoad).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("completed",Matchers.equalTo(true))
				.build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/api/v1/Activities")
		.then().assertThat().spec(resSpec);
	}

}
