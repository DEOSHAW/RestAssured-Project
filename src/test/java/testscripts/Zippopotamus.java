package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
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
		
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		Zippo ob=resp.then().log().all().spec(response).extract().response().as(Zippo.class);
		
		System.out.println("Place is: "+ob.getPlaces().get(0).getPlace_name());
		
		
	}

}
