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

public class DummyRestAPI_GetEmployeeById 
{
	@Test
	void validateGetEmployeeByIdAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
		.addPathParam("id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("message",Matchers.equalTo("Successfully! Record has been fetched."))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v1/employee/{id}")
		.then().assertThat().spec(resSpec);
		
	}

}
