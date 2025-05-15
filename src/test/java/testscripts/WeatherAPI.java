package testscripts;

import java.util.List;

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

public class WeatherAPI 
{
	
	
	@Test
	void testWeatherAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.weather.gov/")
		.addPathParam("latitude", "39.7456")
		.addPathParam("longitude", "-97.0892").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/points/{latitude},{longitude}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		String context=js.getString("@context");
		System.out.println("=========================================");
		System.out.println(context);
		Assert.assertTrue(context.contains("https://api.weather.gov/ontology#"), "URL not found");
		
		
		
	}

}
