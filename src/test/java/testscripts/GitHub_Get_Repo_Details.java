package testscripts;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GitHub_Get_Repo_Details {
	
	
	
	@Test
	void getRepositoryNames()
	{
		
		RestAssured.baseURI="https://api.github.com";
		String respBody=RestAssured.given().log().all().auth().oauth2("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")
		.when().get("/user/repos")
		.then().statusCode(200).assertThat().extract().response().asString();
		//.body("id[0]", equalTo("<409239133>"))
		
		JsonPath js=new JsonPath(respBody);
		List<Object> repoNames=js.getList("name");
		System.out.println("Number of repositories is: "+repoNames.size());
		for(Object repoName:repoNames)
		{
			System.out.println(repoName);
		}
		
		int firstRepoID=js.getInt("id[0]");
		Assert.assertEquals(firstRepoID, 409239133);
		
	}

}
