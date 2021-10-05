package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddAssignee;
import pojo.assignee;
import pojo.fields;

public class JIRA_UpdatingAnIssue {
	
	
	@Test
	void updateJIRAIssue()
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
		System.out.println("Cookies is: "+cookie);
		assignee ob=new assignee();
		ob.setName("deoshaw3.it");
		fields ob1=new fields();
		ob1.setAssignee(ob);
		AddAssignee ob2=new AddAssignee();
		ob2.setFields(ob1);
		//Call API to modify issue
		RestAssured.baseURI="http://localhost:8080/";
		RestAssured.given().header("Content-Type","application/json").cookie("JSESSIONID",cookie).body(ob2)
		.when().put("/rest/api/2/issue/RES-2").then().log().all().statusCode(204);
		
	}

}
