package testscripts;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import pojo.Num;

public class NumbersAPI {
	
	@Test
	void testNumbersAPI()
	{
		
		RestAssured.baseURI="http://numbersapi.com";
		Num num=RestAssured.given().log().all().header("Content-Type","application/json").when().get("/random/math")
		.then().log().all().assertThat().statusCode(200).body("type", Matchers.equalTo("math")).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("MathAPISchema.json")).extract().response().as(Num.class);
		System.out.println(num.getText());
		Assert.assertTrue(num.getText().contains(String.valueOf(num.getNumber())));
		
		
		
	}

}
