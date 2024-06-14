package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import datamodel.DataObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Objects;

public class RestfulAPI_CreateAnObject
{
	@Test
	void testCreateObjectsAPI()
	{
		//https://api.restful-api.dev/objects
		DataObject dataOb=new DataObject();
		dataOb.setYear(2019);
		dataOb.setPrice(1848.99);
		dataOb.setCpuModel("Intel Core i9");
		dataOb.setHardDiskSize("1 TB");
		Objects ob=new Objects();
		ob.setName("Apple MacBook Pro 16");
		ob.setData(dataOb);
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
		.addHeader("Content-Type", "application/json")
		.setBody(ob).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("data.year",Matchers.equalTo(2019))
				.expectBody("data.price",Matchers.equalTo(1848.99F))
				.expectBody("data.\"CPU model\"",Matchers.equalTo("Intel Core i9"))
				.build();
		
		JsonPath js=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
		.when().post("/objects")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		System.out.println("Hard Disk Size is: "+js.getString("data.'Hard disk size'"));
		
	
	}

}
