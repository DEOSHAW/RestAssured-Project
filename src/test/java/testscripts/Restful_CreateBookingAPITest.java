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

public class Restful_CreateBookingAPITest 
{
	@Test
	void testCreateBookingAPI()
	{
		String payload="{\r\n"
				+ "  \"firstname\": \"John\",\r\n"
				+ "  \"lastname\": \"Smith\",\r\n"
				+ "  \"totalprice\": 150,\r\n"
				+ "  \"depositpaid\": true,\r\n"
				+ "  \"bookingdates\": {\r\n"
				+ "    \"checkin\": \"2026-10-01\",\r\n"
				+ "    \"checkout\": \"2026-10-05\"\r\n"
				+ "  },\r\n"
				+ "  \"additionalneeds\": \"Breakfast\"\r\n"
				+ "}";
		byte[] payloadByte=payload.getBytes();
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
		.addHeader("Content-Type", "application/json")
		.setBody(payloadByte).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("booking.additionalneeds",Matchers.equalTo("Breakfast"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/booking")
		.then().assertThat().spec(resSpec);
		
	}

}
