package testscripts;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import pojo.Nationalize;
import pojo.country;

import static io.restassured.RestAssured.*;

public class NationalizeAPITest {
	
	
	@Test
	void testNationalizeAPI()
	{
		
		RequestSpecification req=given().log().all().spec(new RequestSpecBuilder()
				.setBaseUri("https://api.nationalize.io").addQueryParam("name", "nathaniel").build());
		Nationalize ob=req.when().get("").then().log().all().extract().response().as(Nationalize.class);
		
		System.out.println("List of countries: ");
		for(country cont:ob.getCountry())
		{
			System.out.println(cont.getCountry_id());
		}
		
	}

}
