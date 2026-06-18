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

public class JsonPlaceHolder_GetSpecificComment
{
	@Test
	void ValidateGetSpecificCommentAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/")
		.addPathParam("postId", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectBody("email",Matchers.equalTo("Eliseo@gardner.biz"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/comments/{postId}")
		.then().assertThat().spec(resSpec);
		
	}

}
