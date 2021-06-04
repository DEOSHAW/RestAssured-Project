package testscripts;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;
import pojo.AddPlaceResponse;
import pojo.Location;
import static org.hamcrest.Matchers.*;

public class AddPlaceAPITestWithSerialization {
	
	@Test
	void testAddPlace()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//form body
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("English-EN");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		ArrayList<String> typeList=new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		p.setTypes(typeList);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
	             RequestSpecification req=given().log().all().queryParam("key", "qaclick123").body(p);
				AddPlaceResponse addPlaceResponse=req.when().post("/maps/api/place/add/json")
				.as(AddPlaceResponse.class);
				
				String scope=addPlaceResponse.getScope();
				Assert.assertEquals(scope, "APP");
		
		
		
	}

}
