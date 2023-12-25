package testscripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SampleProducts;

public class SampleProductsAPI 
{
	
	@Test
	void getSampleProducts()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://api.slingacademy.com")
		.addQueryParam("offset", 5).addQueryParam("limit", 10).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("message",Matchers.equalTo("Successfully fetched 10 of 1000 products")).build();
		
		SampleProducts product=RestAssured.given().spec(reqSpec).filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.when().get("/v1/sample-data/products")
		.then().assertThat().spec(resSpec).extract().response().as(SampleProducts.class);
		
		SoftAssert sAssert=new SoftAssert();
		
		sAssert.assertEquals(product.getProducts().size(), 10);
		sAssert.assertEquals(product.getTotal_products(), 1000);
		sAssert.assertAll();
		System.out.println("Name of second product is: "+product.getProducts().get(1).getName());
		
		
		
	}

}
