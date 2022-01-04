package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoRest;

public class GoRest_NegativeScenario {
	
	
	
	@Test
	void testNegativeScenarioForPostComment() throws IOException
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
		.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"GoRest.json"))))
		.addHeader("Authorization", "Bearer "+"c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4").build();
		
		
		Response res=RestAssured.given().spec(req).when().post("/public/v1/comments");
		
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(422).build();
		GoRest ob=res.then().spec(resp).extract().response().as(GoRest.class);
		
		System.out.println("Value of meta is: "+ob.getMeta());
		System.out.println("Value of message is: "+ob.getData().get(0).getMessage());
		System.out.println("Value of field is: "+ob.getData().get(0).getField());
		
		
		
	}

}
