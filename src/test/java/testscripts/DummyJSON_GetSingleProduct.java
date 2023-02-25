package testscripts;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DummyJSON_GetSingleProduct
{
	
	@Test
	void testGetSingleProductAPI()
	{
		HashMap<String,Object> pathParameters=new HashMap();
		pathParameters.put("id", 1);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyjson.com/")
		.addPathParams(pathParameters).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/products/{id}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		
		System.out.println("Title is: "+js.getString("title"));
		System.out.println("Second image is: "+js.getString("images[1]"));
		
		
		
	}

}
