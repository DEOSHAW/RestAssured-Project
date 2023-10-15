package testscripts;

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
import pojo.AppsLoveUser;

public class AppsLoveGetUsersAPI {
	
	@Test
	void testGetUsersAPI()
	{
		
		JSONObject user=new JSONObject();
		Random random=new Random();
		//int num=random.nextInt();
		user.put("email", "bechan682@gmail.com");
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
		
		//Making request to Get Users API
		RequestSpecification reqSpecification=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com")
				.addQueryParam("page", 1).build();
		ResponseSpecification resSpecification=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		AppsLoveUser ob=RestAssured.given().spec(reqSpecification).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.auth().oauth2(token)
		.when().get("/api/users")
		.then().assertThat().spec(resSpecification).extract().as(AppsLoveUser.class);
		
		System.out.println("Third user's name is: "+ob.getAppsLoveData().get(2).getName());
	}

}
