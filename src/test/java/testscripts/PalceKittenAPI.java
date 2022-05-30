package testscripts;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PalceKittenAPI {
	
	
	
	@Test
	void testPlaceKittenAPI()
	{
		Map<String,String> pathParams=new HashMap<String,String>();
		pathParams.put("ID1", "200");
		pathParams.put("ID2", "300");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://placekitten.com").addPathParams(pathParams).build();
		Response response=RestAssured.given().log().all().spec(req)
		.when().get("/{ID1}/{ID2}");
		response.then().log().all().assertThat().statusCode(200);
		System.out.println("Status line is: ");
		System.out.println(response.getStatusLine());
		
	}

}
