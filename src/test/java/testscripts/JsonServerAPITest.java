package testscripts;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonServerAPITest {
	
	@Test
	void testPostsAPI()
	{
		Map<String,String> params=new HashMap<String,String>();
		params.put("title", "json-server");
		params.put("author", "typicode");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:3000").addQueryParams(params).addHeader("Content-Type", "application/json").build();
		Response respBody=RestAssured.given().spec(req).log().all().when().get("/posts");
		respBody.then().log().all().assertThat().statusCode(200);
		
		String resp=respBody.asPrettyString();
		JsonPath js=new JsonPath(resp);
		System.out.println("Title is: "+js.getString("title[0]"));
		System.out.println("Number of Objects: "+js.getList("id").size());
		System.out.println("IDs: "+js.getList("id"));
		
		
	}

}
