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

public class Restful_GetAllObjects 
{
	@Test
	void testGetAllObjectsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/objects")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getList("id").size(), 13);
		Assert.assertEquals(js.getString("id[3]"), "4");
	}
}
