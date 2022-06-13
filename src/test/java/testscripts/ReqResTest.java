package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResTest {
	
	
	
	
	@Test
	void testDeleteAPI()
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
		Response resp=RestAssured.given().spec(req).when().delete("/api/users/2");
		
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(204).build();
		Headers head=resp.then().spec(response).extract().response().getHeaders();
		for(Header h:head)
		{
			System.out.println(h.getName()+" "+h.getValue());
		}
		
	}

}
