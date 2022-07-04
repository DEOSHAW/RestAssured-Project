package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class MusicBrainzTest {
	
	
	
	@Test
	void testMusicBrainzAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://musicbrainz.org")
		.addPathParam("ID", "5b11f4ce-a62d-471e-81fc-a69a8278c7da")
		.addQueryParam("fmt", "json").build();
		
		given().spec(req).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/ws/2/artist/{ID}")
		.then().assertThat().statusCode(200).body("life-span.begin", Matchers.equalTo("1988-01"));
		
		
	}

}
