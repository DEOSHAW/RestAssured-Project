package testscripts;

import java.util.List;

import org.hamcrest.Matchers;
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

public class GitHubTest
{
	@Test
	void validateGetRepoAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.github.com")
		.addHeader("Authorization", "Bearer "+"<Your PAT>")
		.addHeader("Accept", "application/vnd.github+json").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("name",Matchers.hasItem("CucumberFramework"))
				.expectBody("name[4]",Matchers.equalTo("RestAssured-Project"))
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/user/repos")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		List<String> repoName=js.getList("name");
		System.out.println("Below are your repositories: ");
		System.out.println(repoName);
		Assert.assertEquals(repoName.size(), 7);
		Assert.assertEquals(repoName.get(4), "RestAssured-Project");
		
	}

}
