package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHub_getUserProfile {
	
	
	
	@Test
	void getUserProfile()
	{
		
	  RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.github.com")
			  .addHeader("Authorization", "Bearer "+"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").build();
	  Response resp= RestAssured.given().log().all().spec(req).when().log().all().get("/user");
	  
	  ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
	  String responseBody= resp.then().log().all().spec(response).extract().response().asString();
	  
	  JsonPath js=new JsonPath(responseBody);
	  String planName=js.get("plan.name");
	  String ownerName=js.get("name");
	  
	  System.out.println("Owner name: "+ownerName+" "+"\nPlan name: "+planName);
	 
	 
		
	}

}
