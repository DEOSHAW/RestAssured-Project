package testscripts;

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
import pojo.AddBook;

public class LibraryAddBookApiTest
{
	@Test
	void testAddBookApi()
	{
		AddBook ob=new AddBook();
		ob.setAisle("333");
		ob.setAuthor("Test Author");
		ob.setIsbn("bcd");
		ob.setName("Automation-Selenium");
		
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("http://216.10.245.166")
		.setBody(ob).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("Msg",Matchers.equalTo("Book Already Exists"))
				.expectBody("ID",Matchers.equalTo("bcd333"))
				.build();
		
		JsonPath js=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
		.when().post("Library/Addbook.php")
		.then().assertThat().spec(resSpec).extract().response().jsonPath();
		
		Assert.assertEquals(js.getString("Msg"), "Book Already Exists");
		Assert.assertEquals(js.getString("ID"), "bcd333");
		
	}

}
