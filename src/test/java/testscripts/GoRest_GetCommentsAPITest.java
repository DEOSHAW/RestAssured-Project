package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class GoRest_GetCommentsAPITest 
{
	@Test
	void validateGetCommentsAPITest()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Response resp=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/public/v2/comments")
		.then().assertThat().spec(resSpec).extract().response();
		
		System.out.println("Below are the response headers: ");
		
		for(Header h:resp.getHeaders())
		{
			System.out.println(h.getName()+"====>"+h.getValue());
		}
		
		Assert.assertEquals(resp.jsonPath().getList("name").size(), 10);
	}
}
