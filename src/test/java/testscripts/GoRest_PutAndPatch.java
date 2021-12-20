package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class GoRest_PutAndPatch {
	
	@Test
	void testPutAndPatchAPI() throws IOException
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/").addHeader("Content-Type", "application/json").addHeader("Authorization", "Bearer "+"c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4")
		.build();
		
		req=RestAssured.given().spec(req);
		
		//API Request to get the user detail before patch operation
		req.when().get("/public/v1/users/71").then().log().all().statusCode(200);
		
		//API request to partially update(patch) the details of a user
		req.body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"src\\test\\resources\\DataToPatch.json")))).when().patch("/public/v1/users/71").then().log().all().statusCode(200);
		
		//API Request to get the user detail after patch operation
				req.when().get("/public/v1/users/71").then().log().all().statusCode(200);
		
	}

}
