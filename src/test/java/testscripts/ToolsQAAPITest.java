package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.ToolsQA;

public class ToolsQAAPITest {
	
	@Test
	void testToolsQAAPI()
	{
		
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		ToolsQA respBody=RestAssured.given().log().all().auth().preemptive().basic("TOOLSQA-Test", "Test@@123")
		.when().get("/BookStore/v1/Books").then().log().all().statusCode(200).extract().response().as(ToolsQA.class);
		
		
		System.out.println("Title of second book is: "+respBody.getBooks().get(1).getTitle());
		
		
	}

}
