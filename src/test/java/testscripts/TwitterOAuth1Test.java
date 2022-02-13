package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;



import io.restassured.RestAssured;

public class TwitterOAuth1Test {
	
	@Test
	void testTwitterAPI()
	{
		JSONObject body=new JSONObject();
		body.put("text", "This is a test tweet");
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.given().log().all().auth().oauth("FtQbjYsi46Hl4hdP4kNQ7Sgn3", "78kppV0ESf2gKbfVeveQWxeBlVtmjis3LoEH28xFqS1QZBqSlP", "3703934658-sFGhDt6GFD8yUiDgfUI8Q529lteqCZs9KMOUalR", "EK2K7RWTLhMuP0jvifgIk7K3Cb7wSDDR7PmQiRLZdYzqu")
		//RestAssured.given().log().all().auth().oauth2("Bearer AAAAAAAAAAAAAAAAAAAAAIvBZAEAAAAAV2v5fl9URlhB4lFGXKHiP2%2BWsAk%3DQqDB7EAlTNy5Y3dyS93nqEEy0AGP4aNK7Al56Gifhnnib19Pjd")
	    .header("content-type","application/json")
		.body(body).when().post("/2/tweets").then().log().all().statusCode(201);
		
	}

}
