package testscripts;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class MyDummyAPITest
{
	@Test
	void validateGetParticularAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.mydummyapi.com/")
		.addPathParam("Id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("id", Matchers.equalTo(1))
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/comments/{Id}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println(js.getString("email"));
	}
}
