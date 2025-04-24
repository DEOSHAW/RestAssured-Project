package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GoRest_GetPosts
{
	@Test
	void testGetPostsAPI()
	{
		RestAssured.baseURI="https://gorest.co.in/";
		RestAssured.given().log().all()
		.when().get("/public/v2/posts")
		.then().log().all().statusCode(200).body("title", Matchers.hasItem("Quod cunctatio thema cursim tui."));
		
	}

}
