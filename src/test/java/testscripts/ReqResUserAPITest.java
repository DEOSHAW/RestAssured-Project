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

public class ReqResUserAPITest 
{
	@Test
	void validateUserAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		int dataListSize=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/users")
		.then().assertThat().spec(resSpec).extract().response().jsonPath().getList("data").size();
		
		Assert.assertEquals(dataListSize, 6);
	}
}
