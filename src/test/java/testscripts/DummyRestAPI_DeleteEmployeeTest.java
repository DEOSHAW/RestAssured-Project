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

public class DummyRestAPI_DeleteEmployeeTest 
{
	
	@Test
	void validateCreateEmployeeAPITest() throws IOException
	{
		 RequestSpecification reqSpecDel = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
		 .build();
		 
		 ResponseSpecification resSpecDel = new ResponseSpecBuilder().expectStatusCode(200)
				 .expectBody("message",Matchers.equalTo("Successfully! Record has been deleted"))
				 .build();
		 
		 RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpecDel)
		 .when().delete("/api/v1/delete/2")
		 .then().assertThat().spec(resSpecDel);
	}

}
