package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Employee;

public class RestAPIEmployeeExample {
	
	
	@Test
	void testGetEmployeeAPI()
	{
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Employee emp=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/api/v1/employees")
		.then().assertThat().spec(resSpec).extract().response().as(Employee.class);
		
		
		System.out.println("Total number of Employees is: "+emp.getEmpData().size());
		System.out.println("First employee is: "+emp.getEmpData().get(0).getEmployee_name());
		System.out.println("Message is: "+emp.getMessage());
		
		
		
	}

}
