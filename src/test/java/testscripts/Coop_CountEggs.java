package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;

import pojo.EggsCount;

import static org.hamcrest.Matchers.*;

public class Coop_CountEggs {
	
	
	
	@Test
	void testCoopAPI_GetNumberOfEggs()
	{
		RestAssured.baseURI="http://coop.apps.symfonycasts.com";
		
		String respBody=given().formParam("client_id", "MyAppDeoShaw")
		.formParam("grant_type", "client_credentials").formParam("redirect_uri", "localhost:8080")
		.formParam("client_secret", "7c55a7f84beea87e46ea0ee1407a32e7")
		.when().post("/token").then().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		String accessToken=js.getString("access_token");
		System.out.println(accessToken);
		
		EggsCount EC=given().log().all().auth().oauth2(accessToken)
		.pathParam("USER_ID", "2182")		
		.when().post("/api/{USER_ID}/eggs-count")
		.then().log().all().statusCode(200).assertThat().body("data",equalTo(2)).extract().as(EggsCount.class);
		Assert.assertEquals(EC.getMessage(),"You have collected a total of "+EC.getData()+" eggs today");	
		
		
		
		
	}

}
