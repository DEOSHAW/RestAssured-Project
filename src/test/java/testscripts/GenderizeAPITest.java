package testscripts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import pojo.Gender;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class GenderizeAPITest {
	
	@Test
	void testGenderizeAPI()
	{
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.genderize.io").addQueryParam("name", "luc").build();
		req=given().log().all().spec(req);
		Gender ob=req.when().get().then().log().all().assertThat().statusCode(200).extract().response().as(Gender.class);
		
		System.out.println("*****Extracted Data are*****");
		System.out.println(ob.getName()+" "+ob.getGender());
	}

}
