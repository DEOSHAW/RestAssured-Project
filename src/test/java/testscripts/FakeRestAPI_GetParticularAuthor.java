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

public class FakeRestAPI_GetParticularAuthor
{
	@Test
	void testGetParticularActivityAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net/")
		.addPathParam("id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("firstName",Matchers.equalTo("First Name 1")).build();
		
		String respBody=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v1/Authors/{id}")
		.then().assertThat().spec(resSpec).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		String firstName=js.get("firstName");
		String lastName=js.get("lastName");
		String fullName=firstName.concat(" "+lastName);
		System.out.println("Last name is: "+fullName);
		
	}

}
