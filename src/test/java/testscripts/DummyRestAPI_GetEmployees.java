package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.Employee;

public class DummyRestAPI_GetEmployees 
{
	@Test
	void testGetEmployeesAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Employee emp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/v1/employees")
		.then().assertThat().spec(resSpec).extract().as(Employee.class);
		Assert.assertEquals(emp.getStatus(), "success");
		Assert.assertEquals(emp.getMessage(), "Successfully! All records has been fetched.");
		
	}

}
