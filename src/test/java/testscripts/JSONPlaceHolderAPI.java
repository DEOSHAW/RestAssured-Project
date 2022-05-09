package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.JSONPlaceHolder;

public class JSONPlaceHolderAPI {
	
	@Test
	void test_API()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").addPathParam("id", 1).build();
		Response resp=RestAssured.given().log().all().spec(req).when().get("/posts/{id}");
		
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		Response response=resp.then().log().all().spec(res).extract().response();
				String respBody=response.asString();
		JsonPath js=new JsonPath(respBody);
		
		System.out.println("****"+js.getString("title")+"##"+js.getInt("userId")+"****");
		
		JSONPlaceHolder ob=response.as(JSONPlaceHolder.class);
		
		System.out.println("^^^^^"+ob.getBody()+"##"+ob.getId()+"^^^^^");
		

		
	}

}
