package testscripts;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GoRestUser;

public class Gorest_API_CreateUser {
	
	
	
	@Test
	public void testPostAPI()
	{
		//Create a user
		Map<String,String> headerValues=new HashMap<String,String>();
		headerValues.put("Accept", "application/json");
		headerValues.put("Content-Type", "application/json");
		headerValues.put("Authorization", "Bearer "+"c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4");
		GoRestUser ob=new GoRestUser();
		ob.setName("Viraj Gupta");
		ob.setGender("male");
		ob.setEmail("virajdummy123@gmail.com");
		ob.setStatus("active");
		RestAssured.baseURI="https://gorest.co.in/";
		
		String respBody=RestAssured.given().headers(headerValues).log().all().body(ob)
		.when().post("/public/v1/users")
		.then().statusCode(201).log().all().extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		String id=js.getString("data.id");
		System.out.println("*********ID of the new user is: "+id);
		
		//Get the list of all the users
		RestAssured.given().log().all().auth().oauth2("c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4")
		.when().get("/public/v1/users/"+id+"").then().log().all().statusCode(200);
		
		
	}

}
