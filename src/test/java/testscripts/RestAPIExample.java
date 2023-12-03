package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAPIExample 
{
	
	
	@Test
	void testPostAPI() throws IOException
	{
		byte[] payload="{\"name\":\"Test\",\"salary\":\"1234\",\"age\":\"30\"}".getBytes();
		//Request
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
				.setBody(payload).build();
		//Expected Response
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("message",Matchers.equalTo("Successfully! Record has been added.")).build();
		
		//Post API request
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/api/v1/create")
		.then().assertThat().spec(resSpec);
		
	}

}
