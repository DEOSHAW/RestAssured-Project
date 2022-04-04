package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.UserCreated;

public class ReqResCreateUser_Map_Example {
	
	
	
	@Test
	void testReQResCreateUser()
	{
		HashMap<String,String> user=new HashMap();
		user.put("name", "Deo Prasad Shaw");
		user.put("job", "leader");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/").addHeader("Content-Type", "application/json").setBody(user).build();
		Response response=RestAssured.given().log().all().spec(req).when().post("/api/users");
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(201).expectBody("name",Matchers.equalTo("Deo Prasad Shaw"))
				.expectBody("job",Matchers.equalTo("leader")).build();
		UserCreated ob=response.then().log().body().spec(res).extract().response().as(UserCreated.class);
		
		System.out.println("ID of the created user is: "+ob.getId());
		
		
	}

}
