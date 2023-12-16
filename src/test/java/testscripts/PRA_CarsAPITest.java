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

public class PRA_CarsAPITest 
{
	@Test
	void testCarsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://vpic.nhtsa.dot.gov/")
		.addQueryParam("format", "json").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("Results.Make_Name",Matchers.hasItem("17 CREEK ENTERPRISES")).build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/vehicles/getallmakes")
		.then().assertThat().spec(resSpec);
	}

}
