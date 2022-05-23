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

public class IpifyAPI {
	
	
	
	@Test
	void testIpifyAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.ipify.org").addQueryParam("format", "json").build();
		Response response=RestAssured.given().spec(req)
		.when().get();
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectBody("ip",Matchers.equalTo("110.224.4.154")).build();
		String respBody=response.then().spec(resp).extract().response().asString();
		JsonPath js=new JsonPath(respBody);
		System.out.println("IP address is: "+js.getString("ip"));
		
		
		
		
	}

}
