package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleMapAPITest {
	
	@Test
	void testGoogleMAPAddPlaceAPI()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Ghusuri\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"1 Girish Ghosh Lane, Howrah\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"English\"\r\n"
				+ "}\r\n"
				+ "").when().post("maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		
		System.out.println("Place ID is: "+placeId);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().queryParam("key", "qaclick123").queryParam("place_id", placeId).when().get("maps/api/place/get/json").then().assertThat()
		.statusCode(200).body("name", equalTo("Ghusuri"));
		
		
	}
	

}
