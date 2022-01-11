package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class GoRest_ToDoAPI {
	
	
	@Test
	void testToDoAPI()
	{
		RestAssured.baseURI="https://gorest.co.in";
		String respBody=given().log().all().header("Authorization","Bearer c2ced1dd9f668aa80fdc165698b6e8c353f19da055ca6d938b7a47557e9c3ec4")
		.when().get("/public/v1/users/123/todos")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		int sizeOfData=js.getList("data").size();
		System.out.println("Size of data is: "+sizeOfData);
		System.out.println(js.getList("data"));
		System.out.println("Limit is: "+js.getString("meta.pagination.limit"));
		
		
		
		
	}

}
