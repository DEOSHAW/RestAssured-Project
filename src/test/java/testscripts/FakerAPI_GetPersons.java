package testscripts;

import java.util.LinkedHashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class FakerAPI_GetPersons
{
	@Test
	void getPersonsAPITest()
	{
		LinkedHashMap<String,Object> parameterMaps=new LinkedHashMap<>();
		parameterMaps.put("_quantity", 1);
		parameterMaps.put("_gender", "female");
		parameterMaps.put("_birthday_start", "2005-01-01");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakerapi.it")
		.addQueryParams(parameterMaps).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("data.image",Matchers.hasItem("http://placeimg.com/640/480/people"))
				.build();
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/persons")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		System.out.println("Number of persons is: "+js.getList("data").size());
		Assert.assertTrue(js.getString("data[0].image").contains("http://placeimg.com"));
		
		
	}

}
