package testscripts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CoinDeskTest {
	
	
	@Test
	void testCoinDeskAPI()
	{
		
		RequestSpecification req=given().log().all().spec(new RequestSpecBuilder().setBaseUri("https://api.coindesk.com").build());
		Response res=req.when().get("/v1/bpi/currentprice.json");
		String response=res.then().log().all().spec(new ResponseSpecBuilder().expectStatusCode(200).build()).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		Assert.assertEquals(js.getString("bpi.USD.code"),"USD");
		System.out.println(js.getString("disclaimer"));
		
		
	}

}
