package testscripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class LibraryAPITest {
	
	
	@Test(dataProvider = "authorData")
	void testLibraryAPI(String authorName)
	{
		int statusCode=0;
		if(authorName.equalsIgnoreCase("Chetan Bhagat"))
		{
			statusCode=200;
		}
		else
		{
			statusCode=500;
		}
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().queryParam("AuthorName", authorName).log().all()
		.when().get("Library/GetBook.php")
		.then().assertThat().statusCode(statusCode).log().all().extract().response().asString();
		
		
		JsonPath js=new JsonPath(response);
        
		if(authorName.equalsIgnoreCase("Chetan Bhagat"))
		{
			int count= js.getList("book_name").size();
	        System.out.println("Count is: "+count);
		for(int i=0;i<count;i++)
		{
		System.out.println(js.getString("book_name["+i+"]"));
		}
		}
		else
		{
			System.out.println("No book available by author John Foe");
		}
		
	}
	
	@DataProvider(name="authorData")
	Object[][] provideAuthorName()
	{
		
		Object[][] authorArray=new Object[2][1];
		authorArray[0][0]="Chetan Bhagat";
		authorArray[1][0]="John Foe";
		return authorArray;
		
		
		
	}

}
