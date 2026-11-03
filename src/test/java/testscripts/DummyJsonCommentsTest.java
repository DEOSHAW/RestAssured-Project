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
import pojo.FakeAPICommentObject;

public class DummyJsonCommentsTest
{
	@Test
	void validateCommentSAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyjson.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		FakeAPICommentObject ob=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/comments")
		.then().assertThat().spec(resSpec).extract().as(FakeAPICommentObject.class);
		Assert.assertEquals(ob.getLimit(), 30);
		Assert.assertEquals(ob.getComments().size(), 30);
		System.out.println(ob.getComments().get(3).getUser().getFullName());
	}
}
