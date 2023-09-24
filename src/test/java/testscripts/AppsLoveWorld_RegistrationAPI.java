package testscripts;

import java.util.Random;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AppsLoveWorld_RegistrationAPI 
{
	
	
	@Test
	public void testRegistrationAPI()
	{
		
		JSONObject user=new JSONObject();
		user.put("name", "Developer");
		Random random=new Random();
		int num=random.nextInt(5000);
		user.put("email", "bechan"+num+"@gmail.com");
		user.put("password", 123456);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com").addHeader("Content-Type", "application/json")
		.setBody(user).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("data.Name",Matchers.equalTo("Developer")).build();
		
		RestAssured.given().spec(reqSpec).relaxedHTTPSValidation().log().all()
		.when().post("/api/authaccount/registration")
		.then().spec(resSpec).log().all();
		
		
	}

}
