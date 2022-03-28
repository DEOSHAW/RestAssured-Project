package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import pojo.CatFact;

public class CatFactAPI {
	
	
	@BeforeMethod
	void preReq()
	{
		RestAssured.baseURI="https://catfact.ninja";
	}
	
	
	@Test
	void testGetBreedsAPI()
	{
		
		
		RestAssured.given().log().all()
		.when().get("/breeds")
		.then().log().all().statusCode(200).body("data[1].country", Matchers.equalTo("Greece"));
		
		
	}
	
	@Test
	void testGetFactsAPI()
	{
		
		
		CatFact ob=RestAssured.given().log().all()
		.when().get("/fact")
		.then().log().all().statusCode(200).extract().response().as(CatFact.class);
		
		System.out.println("Fact is: "+ob.getFact());
		System.out.println("Length of the fact is: "+ob.getLength());
		
		
	}
	
	
	@Test
	void validateCatFactAPISchema()
	{
		RestAssured.given().log().all()
		.when().get("/fact")
		.then().log().all().statusCode(200).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CatFact.json"));
		
		
	}

}
