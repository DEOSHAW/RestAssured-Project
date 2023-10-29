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

public class PRA_DogsAPI
{
	
	@Test
	void testDogsBySubBreedAPI()
	{
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dog.ceo").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("message",Matchers.hasItem("english")).build();
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/breed/hound/list")
		.then().assertThat().spec(resSpec);
		
	}

}
