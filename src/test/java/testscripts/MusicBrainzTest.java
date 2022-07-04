package testscripts;

import static io.restassured.RestAssured.given;

import java.util.Iterator;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MusicBrainzTest {
	
	
	
	@Test
	void testMusicBrainzAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://musicbrainz.org")
		.addPathParam("ID", "5b11f4ce-a62d-471e-81fc-a69a8278c7da")
		.addQueryParam("fmt", "json").build();
		
		Response response=given().spec(req).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/ws/2/artist/{ID}")
		.then().assertThat().statusCode(200).body("life-span.begin", Matchers.equalTo("1988-01")).extract().response();
		
		Headers h=response.getHeaders();
		Iterator<Header> itr=h.iterator();
		while(itr.hasNext())
		{
			Header head=itr.next();
			System.out.println(head.getName()+"=>"+head.getValue());
		}
		
		
	}

}
