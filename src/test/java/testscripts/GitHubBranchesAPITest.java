package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.GitHubBranch;

public class GitHubBranchesAPITest 
{
	@Test
	void validateGitHubBranchesAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.github.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		GitHubBranch[] ob=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/repos/octocat/Hello-World/branches")
		.then().assertThat().spec(resSpec).extract().response().as(GitHubBranch[].class);
		Assert.assertEquals(ob[0].getName(), "master");
		for(GitHubBranch branch:ob)
		{
			System.out.println(branch.getCommit().getUrl());
		}
		
	}

}
