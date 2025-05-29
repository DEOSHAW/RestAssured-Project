package testscripts;

import org.testng.Assert;
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

public class RestfulAPI_CreateObject 
{
	@Test
	void testAddObjectAPI()
	{
		Objects object=new Objects();
		object.setName("Apple MacBook Pro 16");
		DataObject data=new DataObject();
		data.setYear(2019);
		data.setPrice(1900.05);
		data.setCpuModel("Intel Core i9");
		data.setHardDiskSize("1 TB");
		object.setData(data);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
		.addHeader("Content-Type", "application/json")
		.setBody(object).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/objects")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getDouble("data.price"), 1900.05);
		
		
	}

}
