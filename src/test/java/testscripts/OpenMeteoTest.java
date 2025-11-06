package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OpenMeteoTest 
{
	@Test
	void testOpenMeteoAPI()
	{
		HashMap<String,Object> queryParams=new HashMap<String, Object>();
		queryParams.put("latitude", 19.07);
		queryParams.put("longitude", 72.87);
		queryParams.put("current_weather", true);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.open-meteo.com/")
		.addQueryParams(queryParams).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("timezone",Matchers.equalTo("GMT"))
				.build();
		
		Headers HDs=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/v1/forecast")
		.then().assertThat().spec(resSpec).extract().response().getHeaders();
		System.out.println("Response Headers are: ");
		for(Header hd:HDs)
		{
			System.out.println(hd.getName()+"==>"+hd.getValue());
		}
		
	}

}
