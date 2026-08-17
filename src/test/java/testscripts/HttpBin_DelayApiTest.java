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

public class HttpBin_DelayApiTest 
{
	@Test
	void validateHttpBinDelayApiTest()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://httpbin.org/")
		.addPathParam("Delay", 3).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/delay/{Delay}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		Assert.assertEquals(js.getString("url"), "https://httpbin.org/delay/3");
	}
}
