package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Zippo;

import static io.restassured.RestAssured.*;

public class ZippoAPITest {
	
	@Test
	void testZippoAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.zippopotam.us").setUrlEncodingEnabled(false).build();
		Response res=given().log().all().spec(req).when().get("/us/90210");
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).build();
		Zippo ob=res.then().log().all().spec(resp).extract().response().as(Zippo.class);
		//JsonPath js=new JsonPath(respBody);
		//Assert.assertEquals(js.getString("'country abbreviation'"), "US");
		//System.out.println("Country abbreviation is: "+js.getString("'country abbreviation'"));
		System.out.println(ob.getPlaces().get(0).getPlace_name());
		
		
		
	}

}
