package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import datamodel.DataObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import pojo.Objects;

public class Restful_CreateObject
{
	@Test
	void testCreateObjectAPI()
	{
		Objects object=new Objects();
		DataObject db=new DataObject();
		db.setCpuModel("Intel Core i9");
		db.setHardDiskSize("1 TB");
		db.setPrice(1849.99);
		db.setYear(2019);
		object.setName("Apple MacBook Pro 16");
		object.setData(db);
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
				.addHeader("Content-Type", "application/json")
		.setBody(object).build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/objects")
		.then().assertThat().statusCode(200).body("data.'CPU model'", Matchers.equalTo("Intel Core i9"));	
	}

}
