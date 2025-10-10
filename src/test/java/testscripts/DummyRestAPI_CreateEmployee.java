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

public class DummyRestAPI_CreateEmployee 
{
	@Test
	void testCreateEmployeeAPI() throws IOException
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
		.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"Employee.json")))).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("status",Matchers.equalTo("success"))
				.build();
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/api/v1/create")
		.then().assertThat().spec(resSpec);
		
	}

}
