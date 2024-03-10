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

public class FakeRestAPI_PostAuthor 
{
	
	@Test
	void testPostAuthorAPI()
	{
		String payload="{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"idBook\": 0,\r\n"
				+ "  \"firstName\": \"Test\",\r\n"
				+ "  \"lastName\": \"Author\"\r\n"
				+ "}";
		byte[] payLoad=payload.getBytes();
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net")
		.addHeader("Content-Type", "application/json")
		.setBody(payLoad).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("firstName",Matchers.equalTo("Test"))
				.expectBody("lastName",Matchers.equalTo("Author"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/api/v1/Authors")
		.then().assertThat().spec(resSpec);
		
	}

}
