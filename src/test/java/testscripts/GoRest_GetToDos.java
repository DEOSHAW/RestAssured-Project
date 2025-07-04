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

public class GoRest_GetToDos
{
	@Test
	void testGoToDOs()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/public/v2/todos")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println("Number of records is: "+js.getList("id").size());
	}

}
