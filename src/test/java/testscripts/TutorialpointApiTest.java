package testscripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TutorialpointApiTest
{
	@Test
	void testTutorialPointAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://d1zndhgj5c3q5g.cloudfront.net/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("client[3].bidderCode",Matchers.equalTo("ix"))
				.expectBody("client.bidderCode",Matchers.hasItem("triplelift"))
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/tutorialspoint.com.json")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getList("client").size(), 4);
		Assert.assertEquals(js.getList("server").size(), 4);
	}
}
