package testscripts;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.specification.ResponseSpecification;

public class InstagramAPITest {
	
	@Test
	void testInstaAPI()
	{
		//Get a list of what media is most popular at the moment.
		RestAssured.baseURI="https://api.instagram.com";
		ResponseSpecification rs=new ResponseSpecBuilder().expectStatusCode(200).build();
		String response=when().get("/v1/media/popular").then().log().all().spec(rs).extract().response().asString();
		
		//System.out.println(response);
		//XmlPath js=new XmlPath(response);
		XmlPath doc = new XmlPath(CompatibilityMode.HTML,response);
		
		System.out.println(doc.getString("html.head.title"));
		
	}

}
