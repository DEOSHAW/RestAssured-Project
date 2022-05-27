package testscripts;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.ToolsQA;

public class ToolsQAAPITest {
	
	
	@BeforeClass
	void beforeClass()
	{
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Test
	void testToolsQAAPI()
	{
		
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		ToolsQA respBody=RestAssured.given().log().all().auth().preemptive().basic("TOOLSQA-Test", "Test@@123")
		.when().get("/BookStore/v1/Books").then().log().all().statusCode(200).extract().response().as(ToolsQA.class);
		
		
		System.out.println("Title of second book is: "+respBody.getBooks().get(1).getTitle());
		
		
	}
	
	@Test
	void testToolsQAAPI_BasicAuth()
	{
		
		RestAssured.baseURI="https://bookstore.toolsqa.com";
		String cred="TOOLSQA-Test:Test@@123";
		String stringCred=Base64.encodeBase64(cred.getBytes()).toString();
		ToolsQA respBody=RestAssured.given().log().all().header("Authorization","Basic "+stringCred)
		.when().get("/BookStore/v1/Books").then().log().all().statusCode(200).extract().response().as(ToolsQA.class);
		
		
		System.out.println("Title of third book is: "+respBody.getBooks().get(2).getTitle());
		
		
	}

}
