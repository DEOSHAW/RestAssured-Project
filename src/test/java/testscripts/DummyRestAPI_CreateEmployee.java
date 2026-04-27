package testscripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class DummyRestAPI_CreateEmployee 
{
	@Test
	void validateCreateEmployeeAPITest() throws IOException
	{
		//Create Employee Record
		 RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
		.addHeader("Content-Type", "application/json")
		.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"Employee.json"))))
		.build();
		 
		 ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				 .expectBody("data.name",Matchers.equalTo("test"))
				 .build();
		 
		 JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		 .when().post("/api/v1/create")
		 .then().assertThat().spec(resSpec).extract().response().jsonPath();
		 
		 Assert.assertEquals(js.getString("message"), "Successfully! Record has been added.");
	}
}