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
import pojo.BookerUser;
import pojo.BookingDates;

public class HerokuAppCreateBookingWithPojo 
{
	
	
	
	@Test
	void testCreateBookingAPI()
	{
		
		BookerUser user=new BookerUser();
		user.setFirstname("John");
		user.setLastname("Tim");
		user.setTotalprice(200);
		user.setDepositpaid(true);
		user.setAdditionalneeds("Lunch");
		
		BookingDates dates=new BookingDates();
		dates.setCheckin("2023-05-20");
		dates.setCheckout("2023-05-25");
		user.setBookingdates(dates);
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
				.addHeader("Content-Type", "application/json").setAccept("application/json").setBody(user).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("booking.lastname",Matchers.equalTo("Tim")).build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/booking")
		.then().assertThat().spec(resSpec);
		
	}

}
