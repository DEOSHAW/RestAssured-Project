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
import pojo.Holiday;

public class HolidayApiTest
{
	
	@Test
	void testGetHolidayAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://holidays.abstractapi.com/")
		.addQueryParam("api_key", "b2d0e8438cee4b159279a0fdbd399d2b")
		.addQueryParam("country", "US")
		.addQueryParam("year", "2025")
		.addQueryParam("month", "12")
		.addQueryParam("day", "25").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("type[0]",Matchers.equalTo("National"))
				.expectBody("name[0]",Matchers.equalTo("Christmas Day"))
				.build();
		
		Holiday[] holidays=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/v1/")
		.then().assertThat().spec(resSpec).extract().response().as(Holiday[].class);
		System.out.println("Holiday name is: "+holidays[0].getName());
		
	}

}
