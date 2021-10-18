package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JIRAGetMetaData {
	
	
	@Test
	void testCreateSubTaskAPI() throws IOException
	{
		//Get Cookie Value
				
		JSONObject json=new JSONObject();
		json.put("username"	,"deoshaw3.it");
		json.put("password"	,"Dd$03091991");
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:8080/").setBody(json).addHeader("Content-Type","application/json").build();
		req=RestAssured.given().log().all().spec(req);
		Response resp=req.when().post("/rest/auth/1/session");
		String respBody=resp.getBody().asPrettyString();
		//String cookie=resp.getBody().jsonPath().getString("session.value");
		
		System.out.println("Body is "+respBody);
		
		JsonPath js=new JsonPath(respBody);
		String cookie=js.getString("session.value");
		
		//Call JIRA API to create sub task
		RestAssured.baseURI="http://localhost:8080/";
		String responseBody=RestAssured.given().log().all().header("Content-Type","application/json").cookie("JSESSIONID",cookie).queryParams("projectKeys", "RES", "issuetypeNames","Epic"
				,"expand","projects.issuetypes.fields")
		.when().get("/rest/api/2/issue/createmeta")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//JsonPath  js1=new JsonPath(responseBody);
		//System.out.println("ID of the sub task is: "+js1.getString("id"));
		
	}

}
