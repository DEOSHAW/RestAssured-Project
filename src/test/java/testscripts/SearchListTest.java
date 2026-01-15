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

public class SearchListTest
{
	@Test
	void testSearchListAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://openlibrary.org")
				.addQueryParam("q", "book")
				.addQueryParam("limit", 20)
				.addQueryParam("offset", 0)
				.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/search/lists.json")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		Assert.assertTrue(js.getList("docs.url").get(3).toString().contains("/people"));	
	}
}
