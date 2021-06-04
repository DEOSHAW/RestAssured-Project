package testscripts;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class ReqResAPITest_withSpecBuilder {
	
	@Test
	void testReqResAPIs() throws IOException
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
		RequestSpecification req1=given().log().all().spec(req);
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(201).build();
		
		
		//Create User
		String response=req1.body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"src\\test\\resources\\CreateUserData.json"))))
		.when().post("/api/users")
		.then().spec(resp).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String createdAT=js.getString("createdAt");
		System.out.println(createdAT);
		
		LocalDate today=LocalDate.now();
		System.out.println(today);
		if(createdAT.contains(today.toString()))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		
		
		//Get all the users
		String response1=when().get("https://reqres.in/api/unknown").asString();
		JsonPath  js1=new JsonPath(response1);
		int size=js1.getInt("data.size()");
		System.out.println("Size is: "+size);
		
		
	}

}
