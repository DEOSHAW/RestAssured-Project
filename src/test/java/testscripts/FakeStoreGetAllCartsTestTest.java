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

public class FakeStoreGetAllCartsTestTest 
{
	@Test
	void testGetAllCartsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/carts")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		Assert.assertEquals(js.getList("id").size(), 7);
		Assert.assertEquals(js.getInt("products[3].quantity[0]"), 4);
		System.out.println("Quantity of third product is: "+js.getInt("products[3].quantity[0]"));
	}
}
