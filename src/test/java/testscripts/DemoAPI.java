package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DemoAPI {
	
	
	@Test
	void testAPI()
	{
		//https://abc.com/xyz
		HashMap<Object,Object> body=new HashMap<Object,Object>();
		body.put("mobile", "455666445");
		body.put("Name", "Deo Shaw");
		
		RestAssured.baseURI="https://abc.com";
		RestAssured.given().header("Content-Type","application/json").body(body).auth().oauth2("")
		.when().post("/xyz").then().assertThat().statusCode(201).body("name",Matchers.equalTo("Deo"));
		
		
		
		
	}
	
	


}
