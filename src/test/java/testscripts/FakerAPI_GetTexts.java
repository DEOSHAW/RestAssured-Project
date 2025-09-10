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

public class FakerAPI_GetTexts 
{
	@Test
	void testGetTextsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it")
		.addQueryParam("_quantity", 1)
		.addQueryParam("_gender", "male").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectBody("status",Matchers.equalTo("OK"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/users")
		.then().assertThat().spec(resSpec);		
	}
}
