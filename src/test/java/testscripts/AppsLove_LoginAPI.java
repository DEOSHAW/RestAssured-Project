package testscripts;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.hamcrest.Matchers;
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

public class AppsLove_LoginAPI {
	
	
	
	@Test
	void testLoginAPI() throws Exception
	{
		
		JSONObject user=new JSONObject();
		Random random=new Random();
		int num=random.nextInt(100);
		user.put("email", "Developer"+num+"@gmail.com");
		user.put("password", 123456);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com")
				.addHeader("Content-Type", "application/json")
		.setBody(user).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("message",Matchers.equalTo("success")).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/api/authaccount/login")
		.then().spec(resSpec).extract().jsonPath();
		
		String token=js.getString("data.Token");
		System.out.println("Token is: "+token);
		
		
		
	}

}
