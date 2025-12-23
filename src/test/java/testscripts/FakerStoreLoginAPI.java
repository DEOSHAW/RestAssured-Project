package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class FakerStoreLoginAPI 
{
	@Test
	void testLoginAPI()
	{
		String payLoad="{\"username\": \"john_doe\", \"password\": \"pass123\"}";
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com")
		.addHeader("Content-Type", "application/json")
		.setBody(payLoad).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(401).build();
		XmlPath xPath=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().post("/auth/login")
		.then().spec(resSpec).extract().response().xmlPath(CompatibilityMode.HTML);
		String expectedError=xPath.getString("html.body");
		System.out.println(expectedError);
		Assert.assertEquals(expectedError, "username or password is incorrect");
		
	}
	

}
