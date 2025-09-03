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

public class FakerAPI_GetProducts
{
	@Test
	void getProductsAPITest()
	{
		HashMap<String,Object> queryParams=new HashMap<>();
		queryParams.put("_quantity",1);
		queryParams.put("_taxes", 12);
		queryParams.put("_categories_type", "uuid");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it").addQueryParams(queryParams).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/api/v2/products")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getList("data").size(), 1);
	}

}
