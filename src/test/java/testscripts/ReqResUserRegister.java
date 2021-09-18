package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.User;
import pojo.UserBody;
import pojo.UserRegistrationResponse;
import pojo.UserResponse;

import static io.restassured.RestAssured.*;

public class ReqResUserRegister {
	
	@Test
	void testRegisterUserAPI()
	{
		
		RestAssured.baseURI="https://reqres.in";
		UserBody UB1=new UserBody();
		UB1.setEmail("eve.holt@reqres.in");
		UB1.setPassword("pistol");
		//User user=new User();
		//user.setName("Deo Prasad");
		//user.setJob("QA Engineer");
		UserRegistrationResponse UR1=given().log().all().body(UB1).accept(ContentType.JSON)
		.when().post("/api/register").then().log().all().statusCode(201).extract().response().as(UserRegistrationResponse.class);
		
		System.out.println("ID of the registered user is: "+UR1.getId());
		
		
		
	}

}
