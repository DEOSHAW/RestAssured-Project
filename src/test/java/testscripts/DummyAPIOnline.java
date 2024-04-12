package testscripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class DummyAPIOnline
{
	
	
	@Test
	void testGetMoviesAPI() throws IOException
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyapi.online/").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Response response=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/movies")
		.then().assertThat().spec(resSpec).extract().response();
		
		String respBody=response.asString();
		
		JsonPath js=new JsonPath(respBody);
		String movieName=js.get("movie[1]");
		System.out.println(movieName);
		Assert.assertEquals(movieName, "The Godfather");
		
		Headers HDs=response.getHeaders();
		
		Iterator<Header> itr=HDs.iterator();
		for(Header h:HDs)
		{
			System.out.println(h.getName()+"==>"+h.getValue());
		}
		
		
		File file=new File(System.getProperty("user.dir")+File.separator+"MoviesData.json");
		FileWriter writer=new FileWriter(file);
		BufferedWriter buffWriter=new BufferedWriter(writer);
		buffWriter.write(respBody);
		buffWriter.close();
		writer.close();
		
	}

}
