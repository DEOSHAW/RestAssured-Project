package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Album;

public class JsonPlaceHolder_AlbumAPITest
{
	@Test
	void testGetAlbumsAPI()
	{
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		
		Album[] albums=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().get("/albums")
		.then().assertThat().spec(resSpec).extract().response().as(Album[].class);
		Assert.assertEquals(albums.length, 100);
	}

}
