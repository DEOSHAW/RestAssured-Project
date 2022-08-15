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
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ImageChartAPI {
	
	
	
	@Test
	void testImageChartAPI()
	{
		
		HashMap<String,Object> qParams=new HashMap();
		qParams.put("cht", "p3");
		qParams.put("chs", "700x100");
		qParams.put("chd", "t:60,40");
		qParams.put("chl","Hello|World");
		qParams.put("chf", "ps0-0,lg,45,ffeb3b,0.2,f44336,1|ps0-1,lg,45,8bc34a,0.2,009688,1");
		
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://image-charts.com")
		.addQueryParams(qParams).build();
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		ValidatableResponse valRes=RestAssured.given().spec(req).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/chart").then().spec(res);
		
		Headers allHeaders=valRes.extract().response().getHeaders();
		for(Header h:allHeaders)
		{
			System.out.println(h.getName()+"======"+h.getValue());
		}
		
	}

}
