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
import pojo.Comment;


public class JsonPlaceHolderGetCommentsTest
{
	@Test
	void testGetCommentsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("name[3]",Matchers.equalTo("alias odio sit"))
				.build();
		
		Comment[] comments=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/comments")
		.then().assertThat().spec(resSpec).extract().response().as(Comment[].class);
		
		System.out.println("Third name is: "+comments[2].getName());
		
		
		
	}

}
