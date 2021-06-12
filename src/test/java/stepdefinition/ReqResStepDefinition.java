package stepdefinition;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.User;
import pojo.UserResponse;

public class ReqResStepDefinition {
	
	static RequestSpecification req;
	static ResponseSpecification res;
	static Response response;
	static Response patchResponse;
	static UserResponse UR;
	static String responseBody;
	
	@Given("request is made to ReqRes API over http")
	public void request_is_made_to_req_res_api_over_http() {
		
		User user=new User();
		user.setName("Deo Prasad");
		user.setJob("QA Engineer");
	    req=new RequestSpecBuilder().setBaseUri("https://reqres.in/").setBody(user).build();
	    req=given().spec(req);
	}
	@When("the request is successful")
	public void the_request_is_successful() {
		
		res=new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();
	    UR=req.when().post("/api/users").then().log().all().spec(res).extract().as(UserResponse.class);
	}
	@Then("user id is stored and displayed")
	public void user_id_is_stored_and_displayed() {
		System.out.println("Response is: ");
	   System.out.println(UR.getCreatedAt()); 
	   System.out.println(UR.getId()); 
	   
	}
	
	@Given("request is made to ReqRes Patch API over http")
	public void request_is_made_to_req_res_patch_api_over_http() {
		patchResponse=req.when().patch("/api/users/2");
	}
	@When("the patch request is successful")
	public void the_patch_request_is_successful() {
		responseBody=patchResponse.then().log().all().assertThat().statusCode(200).extract().response().asString();
	}
	@Then("created date is displayed")
	public void created_date_is_displayed() {
		JsonPath js=new JsonPath(responseBody);
		System.out.println("Response is: ");
		   System.out.println(js.getString("updatedAt")); 
		    
	}

}
