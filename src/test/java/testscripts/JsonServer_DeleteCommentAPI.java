package testscripts;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class JsonServer_DeleteCommentAPI {
	
	@Test
	void testDeleteCommentAPI()
	{
		RequestSpecification req=new RequestSpecBuilder()
		.setBaseUri("http://localhost:3000").addHeader("Content-Type", "application/json").addPathParam("number", "2").build();
		
		Response resp=given().log().all().spec(req).when().delete("/comments/{number}");
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectHeader("X-Content-Type-Options", "nosniff")
		.expectHeader("Content-Length", "2").build();
		
		resp.then().log().all().spec(res);
	}

}
