package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class Coop_UnlockBarn {
	
	@Test
	void testUnlockBarnAPI()
	{
		
	//Snippet to get access token
		RequestSpecification req;
		//RestAssured.baseURI="http://coop.apps.symfonycasts.com"
	req=new RequestSpecBuilder().setBaseUri("http://coop.apps.symfonycasts.com").addFormParam("client_id", "MyAppDeoShaw")
				.addFormParam("client_secret", "7c55a7f84beea87e46ea0ee1407a32e7").addFormParam("grant_type","client_credentials").build();
	req=RestAssured.given().spec(req);
	
	Response resp=req.when().post("/token");
	
	ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
	String respBody=resp.then().spec(res).extract().response().asString();
	
	JsonPath js=new JsonPath(respBody);
	String accessToken=js.getString("access_token");
	//Snippet to call unlock the barn API
	
	RestAssured.given().log().all().auth().oauth2(accessToken).pathParam("USER_ID", "2182")
	.when().post("http://coop.apps.symfonycasts.com/api/{USER_ID}/toiletseat-down").then().log().all()
	.statusCode(200).assertThat().body("action", equalTo("toiletseat-down"));
	
	
	
	
		
		
	}

}
