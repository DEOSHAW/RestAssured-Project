package stepdefinition;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHubAPIStepDefinition {
	
	static RequestSpecification req;
	static ResponseSpecification res;
	Response response;
	
	@Given("Get request is made to Github API over http")
	public void get_request_is_made_to_github_api_over_http() {
	    req=new RequestSpecBuilder().setBaseUri("https://api.github.com").build();
	    req=given().log().all().spec(req);
	}
	@When("the list of events is extracted")
	public void the_list_of_events_is_extracted() {
	   response=req.when().get("/events");
	}
	@Then("the response body for {string} contains {string}")
	public void the_response_body_for_contains(String key, String value) {
	   res=new ResponseSpecBuilder().expectStatusCode(200).build();
	   String responseBody=response.then().log().all().spec(res).extract().response().asString();
	   JsonPath js=new JsonPath(responseBody);
	   String actualValue=js.getString("type[0]");
	   System.out.println("Size of the list is: "+js.getList("type").size());
	   //Assert.assertEquals(actualValue, value,"Value did not match");
	   System.out.println(actualValue);
	   System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	   String id=js.getString("actor.id[0]");
	   System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	   System.out.println(id);
	   
	}


}
