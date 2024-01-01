package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BlogPostByID
{
	@Test
	void testGetBlogPostByIdAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.slingacademy.com")
		.addPathParam("id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/v1/sample-data/blog-posts/{id}")
		.then().assertThat().spec(resSpec).extract().jsonPath();
		
		SoftAssert softAssert=new SoftAssert();
		softAssert.assertEquals(js.getString("blog.description"), "Role set leader structure.");
		softAssert.assertTrue(js.getString("blog.title").contains("travel"));
		softAssert.assertAll();
		
	}

}
