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

public class Library_GetBookAPI 
{
	@Test
	void getBookAPITest()
	{
		RequestSpecification rqSpec=new RequestSpecBuilder().setBaseUri("http://216.10.245.166/")
		.addQueryParam("ID","3389").build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(404)
				.expectBody("msg",Matchers.equalTo("The book by requested bookid / author name does not exists!"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(rqSpec)
		.when().get("/Library/GetBook.php")
    	.then().assertThat().spec(resSpec);
	}

}
