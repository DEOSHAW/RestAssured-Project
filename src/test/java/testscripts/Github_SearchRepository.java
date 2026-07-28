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

public class Github_SearchRepository 
{
	@Test
	void validateSearchRepositoryApi()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.github.com/")
		.addQueryParam("q", "selenium").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("items[0].full_name",Matchers.equalTo("SeleniumHQ/selenium"))
				.expectBody("items[0].owner	.login",Matchers.equalTo("SeleniumHQ"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/search/repositories")
		.then().assertThat().spec(resSpec);
	}

}
