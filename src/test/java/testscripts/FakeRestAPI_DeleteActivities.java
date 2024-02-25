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

public class FakeRestAPI_DeleteActivities
{
	
	@Test
	void testDeleteActivities()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerestapi.azurewebsites.net")
		.addPathParam("id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Response resp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().delete("/api/v1/Activities/{id}")
		.then().assertThat().spec(resSpec).extract().response();
		System.out.println("Response body is: "+resp.asPrettyString());
		Headers HDs=resp.getHeaders();
		Iterator<Header> itr=HDs.iterator();
		Header hd = null;
		System.out.println("Response Headers are: ");
		while(itr.hasNext())
		{
			hd=itr.next();
		}
			System.out.println(hd.getName()+"====>"+hd.getValue());
		}
		
	

}
