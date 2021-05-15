package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GitHubAPITest {
	
	@Test
	void testGitHubAPIs()
	{
		//Test to get Repo names
		RestAssured.baseURI="https://api.github.com";
		String response=given().pathParam("owner", "DEOSHAW").header("Authorization","Bearer "+"7da2848bf30028bff9871263aeb383c51e0fdeff")
		.when().get("/users/{owner}/repos")
		.then().assertThat().statusCode(200).body("name[0]",equalTo("CucumberFramework")).extract().response().asString();
		JsonPath js=new JsonPath(response);
		int count=js.getList("name").size();
		
		
		for(int i=0;i<count;i++)
		{
			System.out.println(js.getString("name["+i+"]"));
		}
		
		//Test to Add,Get and Delete emails
		String email="deoshaw3.it@gmail.com";
		String bodyContent="\r\n"
				+ "{\"emails\" :\r\n"
				+ "[\r\n"
				+ "\""+email+"\"\r\n"
				+ "]\r\n"
				+ "}";
		
		//To Add email
		given().header("Authorization","Bearer "+"7da2848bf30028bff9871263aeb383c51e0fdeff").body(bodyContent)
		.when().post("/user/emails")
		.then().assertThat().statusCode(201).body("email[2]", equalTo("deoshaw3.it@gmail.com"));
		
		//To get list of emails
		given().header("Authorization","Bearer "+"7da2848bf30028bff9871263aeb383c51e0fdeff")
		.when().get("/user/emails")
		.then().assertThat().statusCode(200).body("email[2]", equalTo("deoshaw3.it@gmail.com"));
		
		//To delete email
		given().header("Authorization","Bearer "+"7da2848bf30028bff9871263aeb383c51e0fdeff").body(bodyContent)
		.when().delete("/user/emails").
		then().assertThat().statusCode(204);
		
		//To get list of emails
		try
		{
				given().header("Authorization","Bearer "+"7da2848bf30028bff9871263aeb383c51e0fdeff")
				.when().get("/user/emails")
				.then().assertThat().statusCode(200).body("email[2]", equalTo("deoshaw3.it@gmail.com"));
		}
		catch(AssertionError e)
		{
			System.out.println("Email not found as it has been deleted");
		}
		
		
	}


}
