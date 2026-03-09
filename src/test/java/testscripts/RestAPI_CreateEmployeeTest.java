package testscripts;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestAPI_CreateEmployeeTest 
{
	@Test
	void testCreateEmployeeAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/")
		.addHeader("Content-Type", "application/json")
		.setBody(new String(System.getProperty("user.dir")+File.separator+"Employee.json")).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("message",Matchers.equalTo("Successfully! Record has been added."))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/api/v1/create")
		.then().assertThat().spec(resSpec);
		
	}

}
