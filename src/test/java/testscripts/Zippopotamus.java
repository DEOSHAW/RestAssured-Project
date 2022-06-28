package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Zippo;

public class Zippopotamus {
	
	
	
	@Test
	void test_Zippopotamus_API()
	{
		
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.zippopotam.us").addPathParam("RID", "90210").build();
		Response resp=RestAssured.given().log().all().spec(req)
		.when().get("/us/{RID}");
		
		/*ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		Zippo ob=resp.then().log().all().spec(response).extract().response().as(Zippo.class);*/
		
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=resp.then().log().all().spec(response).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		
		System.out.println("Country is: "+js.getString("'country abbreviation'"));
		
		
	}

}
