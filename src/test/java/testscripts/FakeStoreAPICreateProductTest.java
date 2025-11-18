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
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FakeStoreAPICreateProductTest
{
	@Test
	void testCreateProductAPI() throws IOException
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com")
		.addHeader("Content-Type", "application/json")
		.setBody(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+File.separator+"Product.json"))))
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201)
				.expectBody("title",Matchers.equalTo("string"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("/products")
		.then().assertThat().spec(resSpec);
	}

}
