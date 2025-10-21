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

public class PostmanEchoTest 
{
	@Test
	void testPostmanEchoAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://postman-echo.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("headers.host",Matchers.equalTo("postman-echo.com"))
				.build();
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/get")
		.then().assertThat().spec(resSpec);
		
	}

}
