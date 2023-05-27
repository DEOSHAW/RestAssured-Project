package testscripts;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Booker_GenerateToken {
	
	
	
	@Test
	void testAuthAPI()
	{
		
	
		JSONObject ob=new JSONObject();
		ob.put("username", "admin");
		ob.put("password", "password123");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").setBody(ob)
		.addHeader("Content-Type", "application/json").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/auth")
		.then().spec(resSpec).extract().response().jsonPath();
		
		
		String token=js.getString("token");
		
		System.out.println("Generated token is: "+token);
		
		
		
		
	}

}
