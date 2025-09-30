package testscripts;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestfulAPI_GetObjectById
{
	@Test
	void testGetObjectByIdAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev/")
		.addQueryParam("id", 3).addQueryParam("id", 5).addQueryParam("id", 10).build();
		
		ArrayList<Integer> IDs=new ArrayList<Integer>();
		IDs.add(3);
		IDs.add(5);
		IDs.add(10);
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("id",Matchers.containsInAnyOrder("3","10","5"))
				.expectBody("id",Matchers.hasItems("3","5","10"))
				.build();
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/objects")
		.then().assertThat().spec(resSpec);
		
	}

}
