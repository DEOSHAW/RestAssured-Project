package testscripts;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GoRest_ChainingAPITests2 {
	
	
	@Test
	void testGetUser(ITestContext context)
	{
		
		
		String token=(String) context.getSuite().getAttribute("token");
		int id=(Integer) context.getSuite().getAttribute("user_id");
		System.out.println("ID in Get User method is: "+id);
		RestAssured.baseURI="https://gorest.co.in";
		
		RestAssured.given().contentType("application/json").log().all().auth().oauth2(token)
		.pathParam("id", id)
		.when().get("/public/v2/users/{id}")
		.then().assertThat().statusCode(200).log().all();
	}
	
	

}
