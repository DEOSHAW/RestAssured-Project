package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class Restful_RegisterUserAPITest 
{
	@Test
	void validateRegisterAPIWithoutAPIKey()
	{
		String payload="{\r\n"
				+ "  \"email\": \"antonio@example.com\",\r\n"
				+ "  \"password\": \"securePassword123\",\r\n"
				+ "  \"name\": \"Antonio\"\r\n"
				+ "}";
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
				.addHeader("Content-Type", "application/json")
				.setBody(payload)
				.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(403).build();
		
		String respBody=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/register")
		.then().assertThat().spec(resSpec).extract().response().asString();
		
		System.out.println("Response body is: ");
		System.out.println(respBody);
		Assert.assertEquals(respBody.contains("API key is missing"), true);
	}
}
