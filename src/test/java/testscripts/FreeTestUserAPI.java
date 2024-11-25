package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FreeTestUserAPI
{
	
	@Test
	void testGetStudentsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder()
				.setBaseUri("https://www.freetestapi.com")
				.setRelaxedHTTPSValidation()
				.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchema.json"))
				.expectBody("name",Matchers.hasItem("Michael Brown"))
				.build();
		
		RestAssured.given(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/v1/users")
		.then().assertThat().spec(resSpec);
		
	}

}
