package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CoversAPITest 
{
	@Test
	void testGetCoversAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://covers.openlibrary.org").addPathParam("Id", 12547191)
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("author",Matchers.equalTo("/people/ScarTissue"))
				.build();
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("https://covers.openlibrary.org/b/id/{Id}.json")
		.then().assertThat().spec(resSpec);
		
	}

}
