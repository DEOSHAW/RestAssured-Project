package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SubjectsAPITest
{
	@Test
	void testGetDetailsOfASubjectAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://openlibrary.org/")
		.addQueryParam("details", true).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/subjects/love.json")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		String name=js.getString("name");
		System.out.println("Name is: "+name);
		Assert.assertEquals(name, "Love");
		
	}

}
