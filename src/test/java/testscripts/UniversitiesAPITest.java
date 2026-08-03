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
import junit.framework.Assert;

public class UniversitiesAPITest
{
	@Test
	void validateGetUniversitiesAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://universities.hipolabs.com/")
		.addQueryParam("country", "India").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/search")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getList("name").size(),477);
		Assert.assertEquals(js.get("name[3]"),"University of Petroleum and Energy Studies");
		
	}
}
