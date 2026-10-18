package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class DummyJsonRecipesApiTest 
{
	@Test
	void validateRecipesApiTest()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummyjson.com").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Response resp=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/recipes")
		.then().assertThat().spec(resSpec).extract().response();
		
		JsonPath js=resp.jsonPath();
		
		Assert.assertEquals(js.getInt("total"), 50);
       System.out.println(js.getString("recipes[0].name"));
       
       System.out.println("Headers are: ");
       
       for(Header hd:resp.getHeaders())
       {
    	   System.out.println(hd.getName() +" "+hd.getValue());
       }
	}
}
