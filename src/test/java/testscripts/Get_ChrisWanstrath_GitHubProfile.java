package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Get_ChrisWanstrath_GitHubProfile {
	
	
	
	@Test
	void getProfileInformation()
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.github.com").build();
		Response response=RestAssured.given().log().all().spec(req).when().get("/users/defunkt");
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectHeader("Content-Length", "484").build();
		String respBody=response.then().log().all().spec(resp).body("name", Matchers.equalTo("Chris Wanstrath")).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		
		System.out.println("Blog name is: "+js.getString("blog"));
		System.out.println("Site admin is: "+js.getBoolean("site_admin"));
		//System.out.println("Thread id is: "+Thread.currentThread().getId());
		
	}
	

}
