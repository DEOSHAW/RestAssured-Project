package testscripts;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PokeApiTest
{
	@Test
	void validatePokeApiTest()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://pokeapi.co/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("abilities.ability.url",Matchers.hasItem("https://pokeapi.co/api/v2/ability/7/"))
				.build();
		
		RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/api/v2/pokemon/ditto")
		.then().assertThat().spec(resSpec);
	}
}
