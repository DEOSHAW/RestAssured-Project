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

public class MockoonPostsAPITest
{
	@Test
	void validateGetPostsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://localhost:3000/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/posts")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		
		Assert.assertEquals(js.getList("title").size(), 100);
	}
}
