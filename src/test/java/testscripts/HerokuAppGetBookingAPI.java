package testscripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class HerokuAppGetBookingAPI {
	
	
	
	
	@Test
	void testGetBookingAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
				.addPathParam("id", 1).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("firstname",Matchers.equalTo("Mary")).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/booking/{id}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		boolean depositPaid=js.getBoolean("depositpaid");
		Assert.assertEquals(depositPaid, false);
		
		
		
	}

}
