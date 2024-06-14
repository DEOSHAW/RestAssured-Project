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


public class RestfulAPI_GetAllObjects 
{
	@Test
	void testGetAllObjectsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/objects")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println("Second device name is: "+js.getString("name[2]")+" "+js.getDouble("data[3].price"));
		Assert.assertEquals(js.getString("name[3]"), "Apple iPhone 11, 64GB");
		Assert.assertEquals(js.getString("data[3].color"), "Purple");
		Assert.assertEquals(js.getDouble("data[3].price"), 389.99);
		
	}

}
