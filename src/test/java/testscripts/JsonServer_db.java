package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.DBAPI;

public class JsonServer_db {
	
	
	@Test
	void testDbAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:3000").addHeader("Content-Type", "application/json").build();
		req=RestAssured.given().log().all().spec(req);
		Response res=req.when().get("/db");
		
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		DBAPI ob=res.then().log().all().spec(response).extract().response().as(DBAPI.class);
		String body=ob.getComments().get(1).getBody();
		System.out.println("******Body is: "+body+" *******");
		
		String respBody=res.getBody().asString();
		JsonPath js=new JsonPath(respBody);
		
		String postId=js.getString("comments.postId[0]");
		System.out.println("Post ID is: "+postId);
		
		
		
		
		
		
		
	}

}
