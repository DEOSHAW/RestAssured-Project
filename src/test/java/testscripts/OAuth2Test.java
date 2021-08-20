package testscripts;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;

public class OAuth2Test {
	
	@Test
	void testSymfonycastsAPI()
	{
		
		
		String respBody=given().log().all().formParam("client_id", "MyAppDeoShaw").formParam("grant_type", "client_credentials")
		.formParam("client_secret", "7c55a7f84beea87e46ea0ee1407a32e7").formParam("redirect_uri", "localhost:8080")
		.when().post("http://coop.apps.symfonycasts.com/token").then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		String accessToken=js.getString("access_token");
		System.out.println("Extracted access token is: "+accessToken);
		
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
	/*String Response=RestAssured.given().log().all().auth().oauth2(accessToken)
		.when().post("/api/2182/chickens-feed").then().log().all().statusCode(200).extract().response().asString();*/
	
	String Response=RestAssured.given().log().all().header("Authorization", "Bearer "+accessToken)
			.when().post("/api/2182/chickens-feed").then().log().all().statusCode(200).extract().response().asString();
		
	}

}
