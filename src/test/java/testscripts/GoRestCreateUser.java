package testscripts;

import java.io.IOException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class GoRestCreateUser {
	
	
	@Test
	void testCreateUserAPI() throws IOException
	{
		
		String accessToken="d11ecb06e00554e9b1a03b1e0e537a44a9d5cf01964946a43740344b033fa2a4";
		Random random=new Random();
		int uniqueId=random.nextInt(500);
		JSONObject payload=new JSONObject();
		payload.put("name", "Deb Gupta");
		payload.put("gender", "male");
		payload.put("email", "xyz"+uniqueId+"@gmail.com");
		payload.put("status", "active");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/")
		//.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"GoRestUser.json"))))
				.setBody(payload)
		.addHeader("Authorization", "Bearer "+accessToken).setContentType("application/json")
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201).expectBody("status",Matchers.equalTo("active")).build();
		
		Response resp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/public/v2/users")
		.then().assertThat().spec(resSpec).extract().response();
		
		Assert.assertEquals(resp.getHeader("x-content-type-options"), "nosniff");
		
	}

}
