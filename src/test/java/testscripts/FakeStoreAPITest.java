package testscripts;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FakeStoreAPITest
{
	@Test
	void testGetProductAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://fakestoreapi.com/")
		.addPathParam("ID", 1).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectBody("description", Matchers.equalTo("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"))
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/products/{ID}")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getString("title"), "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
	}
}
