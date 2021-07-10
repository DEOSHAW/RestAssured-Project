package testscripts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetDelayedReqRes {
	
	@Test
	void testAPIGetDelayedResponse()
	{
		
		RestAssured.baseURI="https://reqres.in";
		String response=given().log().all().queryParam("page", 2).when().get("/api/users").then().log().all().assertThat().body("total_pages",equalTo(2)).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		System.out.println(js.getString("data[0].first_name"));
	}

}
