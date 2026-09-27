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

public class GithubRepoCommitsAPI
{
	@Test
	void validateGithubRepoCommitsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.github.com/repos/DEOSHAW/RestAssured-Project")
		.addHeader("Authorization", "Bearer "+"{Bearer Token}")
		.addQueryParam("per_page", 1)
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("commit.author.name",Matchers.hasItem("Deo Prasad Shaw"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/commits")
		.then().assertThat().spec(resSpec);
		
	}

}
