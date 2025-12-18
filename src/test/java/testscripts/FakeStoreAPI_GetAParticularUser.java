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

public class FakeStoreAPI_GetAParticularUser 
{
	@Test
	void testGetSingleUserAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com/")
		.addPathParam("Id", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("id",Matchers.equalTo(1))
				.build();
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().get("/users/{Id}")
		.then().assertThat().spec(resSpec);
		
		
	}

}
