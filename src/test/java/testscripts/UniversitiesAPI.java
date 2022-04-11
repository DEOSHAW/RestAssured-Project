package testscripts;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UniversitiesAPI {
	
	
	
	
	@Test
	void getTheListOfUniversities()
	{
		Map<String,String> queryParam=new HashMap<String,String>();
		queryParam.put("country", "United+Kingdom");
		RestAssured.urlEncodingEnabled = false;
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://universities.hipolabs.com").addQueryParams(queryParam).build();
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		String responseBody=given().spec(req).log().all().when().get("/search").then().log().body().spec(res).extract().response().asString();
		JsonPath js=new JsonPath(responseBody);
		int size=js.getList("array").size();
		for(int i=0;i<10;i++)
		{
		System.out.println(js.getList("web_pages["+i+"]").get(0));
		}
		
		
		
		
		
	}

}
