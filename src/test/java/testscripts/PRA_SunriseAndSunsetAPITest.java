package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SunAPI;

public class PRA_SunriseAndSunsetAPITest
{
	
	
	@Test
	void testSunriseAndSunsetAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.sunrise-sunset.org/")
		.addQueryParam("lat", "36.7201600").addQueryParam("lng", "-4.4203400").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		SunAPI ob=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/json")
		.then().assertThat().spec(resSpec).extract().as(SunAPI.class);
		
		System.out.println("Solar noon: "+ob.getResults().getSolar_noon());
		System.out.println("Status: "+ob.getStatus());
		
	}

}
