package testscripts;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.User;
import pojo.UserResponse;

public class POJOTest {
	
	
	
	@Test
	void testLoginAPI()
	{
		//RestAssured.baseURI="https://reqres.in/";
		
		User user1=new User();
		user1.setName("Deo Shaw");
		user1.setJob("Senior QA Engineer");
		UserResponse UR1=given().log().all().body(user1).expect().defaultParser(Parser.JSON).
		when().post("https://reqres.in/api/users").as(UserResponse.class);
		//.then().log().all().assertThat().statusCode(201).extract().response().as(UserResponse.class);
		System.out.println("Extracted response is: ");
		System.out.println(UR1.getCreatedAt()+"  "+UR1.getId());
		
		
		
	}

}
