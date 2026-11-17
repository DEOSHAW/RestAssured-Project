package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class GoRest_TaskAPITest 
{
	@Test
	void validateGetTasksAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
		.addQueryParam("page[size]", 10).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/public/v3/tasks")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getList("data").size(), 10);
		Assert.assertEquals(js.getBoolean("meta.has_more"), true);
	}
}
