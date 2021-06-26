package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import pojo.AddBook;
import pojo.AddBookResponse;
import utilities.Utilities;

public class LibraryAPITestWithPOJO extends Utilities {
	
	@Test(dataProvider="DataSupplierWithPoi")
	void testLibraryAPI(String name,String isbn,String aisle,String author)
	{
		AddBook book=new AddBook();
		book.setName(name);
		book.setIsbn(isbn);
		book.setAisle(aisle);
		book.setAuthor(author);
		
		RequestSpecification req=given().log().all().header("Content-Type","application/json").spec(new RequestSpecBuilder().setBaseUri("http://216.10.245.166").setBody(book).build());
		AddBookResponse addedBookDetails=req.when().post("/Library/Addbook.php").then().log().all().extract().response().as(AddBookResponse.class);
		System.out.println("Response Object is: "+addedBookDetails);
		String msg=addedBookDetails.getMsg();
		System.out.println("Message is: "+msg);
		Assert.assertEquals(msg, "successfully added");
		String id=addedBookDetails.getID();
		System.out.println("ID is: "+id);
		  
		
		
		
		
		
	}

}
