package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CryptoAPIBody;

public class CryptoCurrencyAPI {
	
	
	
	@Test
	void testCryptoCurrencyAPI()
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://www.cryptingup.com").build();
		Response response=RestAssured.given().log().all().spec(req).when().get("/api/markets");
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectBody("markets[0].base_asset",Matchers.equalTo("BTC")).build();
		CryptoAPIBody ob=response.then().log().all().spec(resp).extract().response().as(CryptoAPIBody.class);
		
		System.out.println("Fourth Base asset is: "+ob.getMarkets().get(3).getBase_asset());
		
		
	}

}
