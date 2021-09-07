package testscripts;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SwaggerAPI {
	
	@Test
	void testSwaggerAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://generator.swagger.io").build();
		req=RestAssured.given().spec(req);
		Response res=req.when().get("/api/gen/clients");
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		String respBody=res.then().spec(resp).extract().response().asString();
		Assert.assertTrue(respBody.contains("ada"));
		
		
	}

}
