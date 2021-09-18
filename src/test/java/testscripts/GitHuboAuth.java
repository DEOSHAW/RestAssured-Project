package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHuboAuth {
	
	@Test
	void testgitHubAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.github.com/").addPathParam("owner", "DEOSHAW").build();
		req=RestAssured.given().log().all().spec(req).auth().oauth2(" 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");
		Response resp=req.when().get("/users/{owner}/repos");
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=resp.then().spec(res).extract().response().asString();
		JsonPath js=new JsonPath(respBody);
		System.out.println("Third repo is: "+js.get("name[2]"));
		System.out.println("Number of repositories is: "+js.getList("name").size());
	}

}
