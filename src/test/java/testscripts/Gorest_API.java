package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class Gorest_API {
	
	
	
	@Test
	void testGoRestAPI()
	{
		
		
		//Delete a user
		 RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public").addHeader("Authorization", "Bearer "+"c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4")
		  .build();
		 
		 RestAssured.given().spec(req).when().delete("/v1/users/27");
		 //Getting the list of users
		RestAssured.baseURI="https://gorest.co.in";
	  String respBody= RestAssured.given().log().all()
	   .when().get("/public/v1/users")
	   .then().log().all().body("meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1")).extract().response().asString();
	  
	  
	  JsonPath js=new JsonPath(respBody);
	  for(int i=0;i<js.getList("data").size();i++)
	  {
		 System.out.println(js.getString("data["+i+"].name")); 
	  }
	 
	  
	  
	
		
	}
	
	
	 

}
