package testscripts;

import java.util.HashMap;

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

public class FakerAPI_GetImages 
{
	@Test
	void getImagesAPITest()
	{
		HashMap<String,Object> params=new HashMap<>();
		params.put("_quantity", 1);
		params.put("_type", "any");
		params.put("_height", 300);
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it/").addQueryParams(params).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/images")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		Assert.assertEquals(js.get("locale"), "en_US");	
	}

}
