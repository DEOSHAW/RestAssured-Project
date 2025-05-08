package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class PostmanBook_GetBooks
{
	@Test
	void testGetBooksAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://openlibrary.org")
				.addQueryParam("bibkeys", "ISBN:0201558025,LCCN:93005405")
				.addQueryParam("format", "json")
				.build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/books")
		.then().assertThat().spec(resSpec);
		
		
	}

}
