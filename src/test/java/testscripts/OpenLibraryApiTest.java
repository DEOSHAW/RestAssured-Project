package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OpenLibraryApiTest
{
	@Test
	void testSearchBookAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://openlibrary.org")
		.addQueryParam("q", "harry+potter").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/search.json")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getString("q"), "harry+potter");
		
	}

}
