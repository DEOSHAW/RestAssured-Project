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
import junit.framework.Assert;

public class FakerAPI_GetPlaces
{
	@Test
	void testGetPlacesAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it/")
		.addQueryParam("_quantity", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("status",Matchers.equalTo("OK")).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/addresses")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		Assert.assertEquals(js.getList("data").size(), 1);
		
	}

}
