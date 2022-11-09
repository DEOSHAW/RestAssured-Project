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

public class GetSpecificComment {
	
	
	@Test
	void getCommentCorrespondingToPostID()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/")
		.addQueryParam("postId", 1).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("email",Matchers.hasItem("Nikita@garfield.biz")).build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/comments").then().assertThat().spec(resSpec);
		
		
	}

}
