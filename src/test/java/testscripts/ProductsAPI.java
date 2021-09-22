package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProductsAPI {
	
	@Test
	void testProductAPI()
	{
		
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:8081").build();
		req=RestAssured.given().spec(req).log().all();
		Response response=req.when().get("/productsearch");
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=response.then().log().all().spec(resp).extract().response().asString();
		JsonPath js=new JsonPath(respBody);
		System.out.println("Number of items is: "+js.getList("ean").size());
		System.out.println("Second item is: "+js.getString("ean[1]")+" "+js.getString("description[1]"));
		
		
	}

}
