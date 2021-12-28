package testscripts;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GoRest_CommentsAPI {
	
	
	
	@Test
	void testCommentsAPI()
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://gorest.co.in").addHeader("Content-Type", "application/json").build();
		Response resp=RestAssured.given().log().all().spec(req).when().get("/public/v1/comments");
		
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=resp.then().log().all().spec(response).extract().response().asString();
		JsonPath js=new JsonPath(respBody);
		List<Object> allData=js.getList("data");
		int noOfComments=allData.size();
		System.out.println("Number of comments: "+noOfComments);
		for(int i=0;i<noOfComments;i++)
		{
			System.out.println(js.getString("data["+i+"].name"));
		}
		
	}

}
