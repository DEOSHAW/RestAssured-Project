package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class GoRest_GetPosts
{
	@Test
	void testGetPostsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("Posts.json"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/public/v2/posts")
		.then().assertThat().spec(resSpec);
		
	}

}
