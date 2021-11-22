package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CoopToken;

public class CoopAPI_PutTheToiletSitDown {
	
	
	@Test
	void testPutTheToiletSitDown()
	{
		
		//API call to get access token
		RestAssured.baseURI="http://coop.apps.symfonycasts.com";
		
		CoopToken ob=RestAssured.given().log().all().formParam("client_id", "MyAppDeo").formParam("client_secret", "0b2ddd6055d18351fd24981f845434c5").formParam("grant_type", "client_credentials")
		.when().post("/token")
		.then().log().all().statusCode(200).extract().response().as(CoopToken.class);
		
		String token=ob.getAccess_token();
		System.out.println("Access token is: "+token);
		
		//API call to PutTheToiletSitDown API
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://coop.apps.symfonycasts.com").addHeader("Authorization", "Bearer "+token).addPathParam("userid", "2182").build();
		req=RestAssured.given().log().all().spec(req);
		Response res=req.when().post("/api/{userid}/toiletseat-down");
		System.out.println("Response body is: ");
		System.out.println(res.getBody().asPrettyString());
		
		
		
		
	}

}
