package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Ipify;

public class OpenFDAAPITest {
	
	
	
	@Test
	void testOpenFDA()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.fda.gov")
		.addQueryParam("limit", "10").build();
		Response resp=RestAssured.given().log().all().spec(req).when().get("/food/enforcement.json");
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200)
		.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("OpenFDASchema.json"))
		.expectBody("meta.license",Matchers.equalTo("https://open.fda.gov/license/")).build();
		resp.then().log().all().spec(response);
		
	}
	
	
	@Test
	void testIPifyAPI()
	{
		
		RestAssured.baseURI="https://api.ipify.org";
		
		Ipify ob=RestAssured.given().log().all().queryParam("format", "json")
		.when().get().then().log().all().statusCode(200).extract().response().as(Ipify.class);
		
		System.out.println("IP is: "+ob.getIp());
		
	}
	
	@Test
	void testReqResPut() throws IOException
	{
        RestAssured.baseURI="https://reqres.in/";
		RestAssured.given().log().all().header("Content-Type","application/json").body(new String(
		Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"src/test/resources/ReqResUser.json"))))
		.when().put("/api/users/2").then().log().all().statusCode(200);
		
		
		
	}
	
	
	

}
