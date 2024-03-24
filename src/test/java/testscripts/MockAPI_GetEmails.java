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
import pojo.EmailResponse;

public class MockAPI_GetEmails 
{
	
	@Test
	void testGetEmailsAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://mocki.io").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("emails[1].email",Matchers.equalTo("michael.jon@reqres.in")).build();
		
		EmailResponse ob=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/v1/b64b7a4b-2a20-4988-aa18-8f0d60d19066")
		.then().assertThat().spec(resSpec).extract().as(EmailResponse.class);
		
		String email=ob.getEmails().get(0).getEmail();
		Assert.assertEquals(email, "michael.lawson@reqres.in");
		System.out.println(email);
		
	}

}
