package testscripts;

import java.util.HashMap;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GoRestAPI_CreateUser
{
	@Test
	void testCreateUserAPI()
	{
		Random random=new Random();
		int id=random.nextInt(1,500);
	    HashMap<String, Object> user=new HashMap<String, Object>();
	    user.put("name", "Tenali Ramakrishna");
	    user.put("gender", "male");
	    user.put("email", "tenali.ramakrishna@"+id+"ce.com");
	    user.put("status", "active");
		
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
		.addHeader("Content-Type", "application/json")
		.addHeader("Accept", "application/json")
		.addHeader("Authorization", "Bearer bf533a5bafb638aad68d137f2981133fb57f764511d17d124dba32faf7a8ea43")
		.setBody(user).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(201)
				.expectBody("status",Matchers.equalTo("active"))
				 .build();
		
		Response response=RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.spec(reqSpec)
		.when().post("/public/v2/users")
		.then().assertThat().spec(resSpec).extract().response();
		
		
		for(Header hd:response.getHeaders())
		{
			System.out.println(hd.getName()+"===>"+hd.getValue());
		}
		
	}

}
