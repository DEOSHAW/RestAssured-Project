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

public class DummyAPIGetBlogPost {
	
	
	@Test
	void testGetBlogPostAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyapi.online").addPathParam("number", "1").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("title",Matchers.equalTo("How to Build a Successful Blog"))
				.build();
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/blogposts/{number}")
		.then().assertThat().spec(resSpec);
	}

}
