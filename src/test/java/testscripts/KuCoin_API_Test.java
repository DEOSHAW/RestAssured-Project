package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class KuCoin_API_Test {
	
	
	
	@Test
	void test_KuCoin_API()
	{
		
		RestAssured.baseURI="https://api.kucoin.com";
		Response response=RestAssured.given().log().all().queryParam("symbol", "BTC-USDT")
		.when().get("/api/v1/market/stats")
		.then().log().all().assertThat().statusCode(200).body("data.symbol", Matchers.equalTo("BTC-USDT")).extract().response();
		String respBody=response.asString();
		JsonPath js=new JsonPath(respBody);
		System.out.println("The code is: "+js.getString("code"));
		
		//Get headers
		Headers headers=response.getHeaders();
		for(Header h:headers)
		{
			System.out.println(h.getName()+"==>"+h.getValue());
		}
		
		
	}

}
