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
import pojo.TypicodePost;

public class Typicode_GetPosts {
	
	@Test
	void testGetPostsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("title",Matchers.hasItem("eum et est occaecati")).build();
		
		
		TypicodePost[] post=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/posts")
		.then().spec(resSpec).extract().response().as(TypicodePost[].class);
		
		System.out.println(post[1].getTitle());
		Assert.assertEquals(post[1].getTitle(), "qui est esse");
		
		
		
		
	}

}
