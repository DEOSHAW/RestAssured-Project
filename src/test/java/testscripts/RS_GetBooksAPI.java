package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo.RSBooks;

public class RS_GetBooksAPI {
	
	
	
	@Test
	void testGetBooksAPI()
	{
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		RSBooks[] rsBooks=RestAssured.given().log().all().queryParam("AuthorName", "shetty")
		.when().get("/Library/GetBook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().as(RSBooks[].class);
		
		System.out.println(rsBooks[5].getBook_name());
		
		System.out.println(rsBooks[5].getIsbn());
		
		System.out.println(rsBooks[5].getAisle());
		
		
	}

}
