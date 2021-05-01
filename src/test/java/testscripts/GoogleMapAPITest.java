package testscripts;

import org.testng.annotations.Test;

import datamodel.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleMapAPITest {
	
	@Test
	void testGoogleMAPAddPlaceAPI()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Post Place API
		String placeAddress="29, side layout, cohen 09";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(GoogleAPIData.addPlaceBody(placeAddress)).
				when().post("maps/api/place/add/json").
				then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.getString("place_id");
		
		//Put Place API
		String placeAddress1="1 Girish Ghosh Lane, Howrah";
		given().log().all().queryParam("place_id", placeId).queryParam("key", "qaclick123").header("Content-Type","application/json").body(GoogleAPIData.putPlaceBody(placeAddress1,placeId))
		.when().put("maps/api/place/update/json")
		.then().log().all().statusCode(200);
		
		
		//Get Place API
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).
		when().get("maps/api/place/get/json").
		then().log().all().assertThat()
		.statusCode(200).body("address", equalTo("1 Girish Ghosh Lane, Howrah"));
		
		
		//Delete place API
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").log().all().body(GoogleAPIData.deletePlaceBody(placeId))
		.when().delete("/maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
		
		//Get Place API
				given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId).
				when().get("maps/api/place/get/json").
				then().log().all().assertThat()
				.statusCode(404).body("msg", equalTo("Get operation failed, looks like place_id  doesn't exists"));
		
		
	}
	

}
