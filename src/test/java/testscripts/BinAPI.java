package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BinAPI {
	
	
	
	@Test
	void testBinAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org").build();
		Response resp=RestAssured.given().spec(req).log().all()
		.when().get("/get");
		
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		Response response=resp.then().log().all().spec(res).extract().response();
				
	  Headers headers=response.getHeaders();
		
		for(Header h:headers)
		{
			System.out.println(h.getName()+"==>"+h.getValue());
		}
		
		System.out.println("Value for Content-Type header is: "+response.getHeader("Content-Type"));
		
		
	}

}
