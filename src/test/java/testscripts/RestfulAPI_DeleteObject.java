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

public class RestfulAPI_DeleteObject 
{
	@Test
	void deleteObject()
	{
		//Create Object
		Objects object=new Objects();
		object.setName("Apple MacBook Pro 16");
		DataObject data=new DataObject();
		data.setYear(2019);
		data.setPrice(1900.05);
		data.setCpuModel("Intel Core i9");
		data.setHardDiskSize("1 TB");
		object.setData(data);
		
		RequestSpecification reqSpecCreate=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
		.addHeader("Content-Type", "application/json")
		.setBody(object).build();
		
		ResponseSpecification resSpecCreate=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpecCreate)
		.when().post("/objects")
		.then().assertThat().spec(resSpecCreate).extract().response().jsonPath();
		String objectId=js.getString("id");
		
		//Delete Object
		RequestSpecification reqSpecDelete=new RequestSpecBuilder().setBaseUri("https://api.restful-api.dev")
				.addPathParam("Id", objectId)
				.build();
		ResponseSpecification resSpecDelete=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("message",Matchers.equalTo("Object with id = "+objectId+" has been deleted."))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpecDelete)
		.when().delete("/objects/{Id}")
		.then().assertThat().spec(resSpecDelete);
		
	}

}
