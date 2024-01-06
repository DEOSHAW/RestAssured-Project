package testscripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SamplePhoto;

public class SamplePhotosAPI 
{
	@Test
	void getSamplePhotoByID()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.slingacademy.com/").addPathParam("ID", 1).build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("success",Matchers.equalTo(true))
				.build();
		
		SamplePhoto p1=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/v1/sample-data/photos/{ID}")
		.then().assertThat().spec(resSpec).extract().response().as(SamplePhoto.class);
		
		System.out.println("User ID: "+p1.getPhoto().getUser());
		System.out.println("Description: "+p1.getPhoto().getDescription());
		Assert.assertEquals(p1.getPhoto().getId(), 1);
		Assert.assertEquals(p1.getPhoto().getUser(), 28);
	}

}
