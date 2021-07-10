package testscripts;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.CoinDesk;


public class CoinDeskTest {
	
	
	@Test
	void testCoinDeskAPI()
	{
		RestAssured.baseURI="https://api.coindesk.com";
		ResponseSpecification req=given().log().all().expect().defaultParser(Parser.JSON);
		Response res=req.when().get("/v1/bpi/currentprice.json");
		CoinDesk response=res.then().log().all().spec(new ResponseSpecBuilder().expectStatusCode(200).build()).extract().response().as(CoinDesk.class);
		
		System.out.println(response);
		Assert.assertEquals(response.getBpi().getUSD().getCode(),"USD");
		System.out.println(response.getDisclaimer());
		
		
	}

}
