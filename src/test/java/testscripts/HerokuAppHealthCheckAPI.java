package testscripts;

import java.util.Iterator;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class HerokuAppHealthCheckAPI 
{
	
	@Test
	void testHealthCheckAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201).build();
		
		Response resp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
		.when().get("/ping")
		.then().assertThat().spec(resSpec).extract().response();
		System.out.println("Response body is: "+resp.getBody().asPrettyString());
		System.out.println("Status line is: "+resp.getStatusLine());
		
		Headers hds=resp.getHeaders();
		Header hd=null;
		
		Iterator<Header> itr=hds.iterator();
		
		while(itr.hasNext())
		{
			hd=itr.next();
			System.out.println(hd.getName()+"====>"+hd.getValue());
		}
		
		
	}

}
