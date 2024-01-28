package testscripts;

import java.io.File;
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

public class SwaggerAPITest
{
	
	@Test
	void testAddAPetAPI() throws Exception
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2")
				.addHeader("Content-Type", "application/json")
				.addHeader("api-key", "special-key")
		.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"Pet.json")))).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectBody("name",Matchers.equalTo("Zebra")).build();
		
		String respBody=RestAssured.given(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("pet")
		.then().assertThat().spec(resSpec).extract().response().asString();
		
		JsonPath js=new JsonPath(respBody);
		System.out.println("ID is: "+js.getLong("id"));
		
	}

}
