package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.CommentBody;

public class JIRA_AddingACommentInIssue {
	
	@Test
	void addACommentToIssue()
	{
		
		//Getting Jsession ID
		JSONObject json=new JSONObject();
		json.put("username"	,"deoshaw3.it");
		json.put("password"	,"Dd$03091991");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:8080").setBody(json).addHeader("Content-Type","application/json").build();
		req=RestAssured.given().log().all().spec(req);
		String resp=req.when().post("/rest/auth/1/session").asString();
		JsonPath js=new JsonPath(resp);
		String jsessionID=js.getString("session.value");
		
		//Adding comment to issue
		CommentBody comment=new CommentBody();
		comment.setBody("First comment");
		RestAssured.baseURI="http://localhost:8080";
		String responseBody=RestAssured.given().log().all().body(comment).cookie("JSESSIONID",jsessionID).header("Content-Type", "application/json")
		.when().post("/rest/api/2/issue/RES-2/comment").then().log().all().statusCode(201).extract().response().asString();
		System.out.println("Response body is: "+responseBody);
	
		
		
	}

}
