package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AlphaVantage_IntraDayTest
{
	@Test
	void testIntraDayAPI()
	{
		HashMap<String,String> qParams=new HashMap<String,String>();
		qParams.put("function", "TIME_SERIES_INTRADAY");
		qParams.put("symbol", "IBM");
		qParams.put("interval", "5min");
		qParams.put("apikey","demo");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://www.alphavantage.co/")
		.addQueryParams(qParams).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("Information",Matchers.equalTo("The **demo** API key is for demo purposes only. Please claim your free API key at (https://www.alphavantage.co/support/#api-key) to explore our full API offerings. It takes fewer than 20 seconds."))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/query")
		.then().assertThat().spec(resSpec);
		
	}

}
