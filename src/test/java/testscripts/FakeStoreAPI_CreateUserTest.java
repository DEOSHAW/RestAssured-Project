package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FakeStoreAPI_CreateUserTest 
{
	@Test
	void testCreateUserAPI()
	{
		String payload="{\"username\": \"john_doe\", \"email\": \"john@example.com\", \"password\": \"pass123\"}";
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com")
				.addHeader("Content-Type", "application/json")
				.setBody(payload)
				.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().post("/users")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println("Id is: "+js.getInt("id"));
		
	}

}
