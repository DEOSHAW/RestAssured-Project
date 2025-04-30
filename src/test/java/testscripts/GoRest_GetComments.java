package testscripts;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GoRest_GetComments
{
	@Test
	void getCommentsAPITest()
	{
		
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/public/v2/comments")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();		
		List<Object> IDs=js.getList("id");
		System.out.println(IDs);
		System.out.println("Total number of records: "+IDs.size());
	}

}
