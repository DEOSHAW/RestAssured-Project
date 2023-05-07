package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AdequateShopGetTravellerWithPagingAPI {
	
	
	@Test
	void testGetTravellerAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://restapi.adequateshop.com/")
				.addQueryParam("page", "1")
				.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("TravelerinformationResponse.travelers.Travelerinformation[2].name",Matchers.equalTo("vano"))
				.build();
		
		XmlPath xml=RestAssured.given().spec(reqSpec).log().all()
		.when().get("/api/Traveler")
		.then().assertThat().spec(resSpec).log().all().extract().response().xmlPath();
		String name=xml.getString("TravelerinformationResponse.travelers.Travelerinformation[2].name");
		System.out.println("Name is: "+name);
		
	}

}
