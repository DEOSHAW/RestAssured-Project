package testscripts;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class UniversityListAPI {
	
	@Test
	void testUniversityAPI()
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://universities.hipolabs.com").addQueryParam("country", "United+States").setUrlEncodingEnabled(false).build();
		String response=given().spec(req).log().all().when().get("/search").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		int count=js.getList("country").size();
		System.out.println("Number of universities: "+count);
		System.out.println("First university in the list is: "+js.getString("name[0]"));
		
	}

}
