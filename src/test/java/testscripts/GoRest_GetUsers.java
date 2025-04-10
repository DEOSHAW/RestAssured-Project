package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoRestUser;

public class GoRest_GetUsers 
{
	@Test
	void testGetUsersAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
		.addHeader("Content-Type", "application/json")
		.addHeader("Authorization", "Bearer bf533a5bafb638aad68d137f2981133fb57f764511d17d124dba32faf7a8ea43").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		GoRestUser[] users=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/public/v2/users")
		.then().assertThat().spec(resSpec).extract().response().as(GoRestUser[].class);
		
		System.out.println("Fourth user is: "+users[3].getName());
		
	}

}
