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

public class GeoCodeAPITest
{
   @Test
   void testGeoCodeAPI()
   {
	   RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://geocode.xyz").addQueryParam("json", 1).build();
	   ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
			   .expectBody("standard.city",Matchers.equalTo("Lng"))
			   .build();
	   
	   RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter()).spec(reqSpec)
	   .when().get("/LAT,LNG")
	   .then().assertThat().spec(resSpec);
	   
   }

}
