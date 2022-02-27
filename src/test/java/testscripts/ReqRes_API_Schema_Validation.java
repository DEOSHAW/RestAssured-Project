package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ReqRes_API_Schema_Validation {
	
	
	
	@Test
	void validateSchemaOfCreateUserAPI()
	{
		JSONObject user=new JSONObject();
		user.put("name", "Deo Prasad Shaw");
		user.put("job", "Software Engineer");
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://reqres.in/").setBody(user).build();
		Response resp=RestAssured.given().log().all().spec(req).when().post("/api/users");
		ResponseSpecification respSpec=new ResponseSpecBuilder().expectStatusCode(201).build();
		resp.then().log().all().spec(respSpec).assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ReqResUserAPISchema.json"));
		
	}

}
