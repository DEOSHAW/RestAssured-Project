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
import pojo.Booking;

public class RestfulBooker_GetBookingIDs
{
	
	@Test
	void getBookingIDs()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Booking[] booking=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/booking")
		.then().assertThat().spec(resSpec).extract().response().as(Booking[].class);
		
		System.out.println("Total Booking IDs: "+booking.length);
		
		System.out.println("Third Booking ID is: "+booking[2].getBookingid());
		
	}

}
