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

public class Swagger_GetInventory
{
	@Test
	void testGetInventoryAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("SwaggerInventorySchema.json"))
				.expectBody("Available",Matchers.equalTo(2))
				.build();
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/v2/store/inventory")
		.then().assertThat().spec(resSpec);
		
	}

}
