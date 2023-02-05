package testscripts;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GoRest_ChainingAPITests1 {
	
	@Test
	void testCreateUser(ITestContext context)
	{
		String accessToken="c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4";
		Random random=new Random();
		int randomNumber=random.nextInt(5000);
		JSONObject data=new JSONObject();
		data.put("name", "Test"+randomNumber);
		data.put("gender", "Male");
		data.put("email", "xyztest"+randomNumber+"@gmail.com");
		data.put("status", "active");
		
		RestAssured.baseURI="https://gorest.co.in";
		
		int id=RestAssured.given().contentType("application/json").log().all()
		.auth().oauth2(accessToken).body(data)
		.when().post("/public/v2/users")
		.then().assertThat().statusCode(201).log().all().extract().response().jsonPath().getInt("id");
		//Below statements will make token and id available across all tests within suite
		context.getSuite().setAttribute("token", accessToken);
		context.getSuite().setAttribute("user_id", id);
		System.out.println("ID in Create User method is: "+id);
		
	}
	
	

}
