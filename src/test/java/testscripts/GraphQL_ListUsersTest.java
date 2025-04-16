package testscripts;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GraphQL_ListUsersTest
{
	@Test
	void testGraphQlListUsersAPI()
	{
		HashMap<String,String> headers=new HashMap<>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer d11ecb06e00554e9b1a03b1e0e537a44a9d5cf01964946a43740344b033fa2a4");
		RequestSpecification reqSpec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
		.addHeaders(headers).setBody("{\"query\":\"query{users {pageInfo {endCursor startCursor hasNextPage hasPreviousPage} totalCount nodes {id name email gender status}}}\"}")
		.build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				 .expectBody("data.users.nodes.name",Matchers.hasItem("Aanandinii Guha"))
				 .build();
		
		RestAssured.given().filters(new RequestLoggingFilter(),new ResponseLoggingFilter())
		.given().spec(reqSpec)
		.when().post("/public/v2/graphql")
		.then().assertThat().spec(resSpec);
		
	}

}
