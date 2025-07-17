package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Address;

public class FakerAPI_GetAddresses
{
	@Test
	void testGetAddressesAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it").addQueryParam("_quantity", 1).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody(JsonSchemaValidator.matchesJsonSchemaInClasspath("Address.json"))
				.build();
		
		Address ob=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/addresses")
		.then().assertThat().spec(resSpec).extract().response().as(Address.class);
		
		System.out.println("Country is: "+ob.getData().get(0).getCountry());
		
	}

}
