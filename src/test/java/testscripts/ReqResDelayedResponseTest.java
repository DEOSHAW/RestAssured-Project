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

public class ReqResDelayedResponseTest {
	
	
	@Test
	void testDelayedAPI()
	{
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://reqres.in/").addQueryParam("delay", 3).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("page",Matchers.equalTo(1)).build();
		
		RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/users")
		.then().assertThat().spec(resSpec);
		
	}

}
