package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GraphQL_Users {
	
	
	@Test
	void testgraphQLUsers()
	{
		String query="{\r\n"
				+ "  \"query\": \"{\\n  online_users(limit: 3) {\\n    id\\n    \\n  }\\n}\\n\",\r\n"
				+ "  \"variables\": null\r\n"
				+ "}";
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://hasura.io").setBody(query)
				.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZTU3Njc0YzcxOWIxMDA2OWE1NGRhYyJ9LCJuaWNrbmFtZSI6ImRlb3NoYXczLml0IiwibmFtZSI6ImRlb3NoYXczLml0QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82NjU4YWVkNDU2MmZmZDFkNWRiYzkwNWFiMTdmOTk4Nj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRlLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAxLTE4VDA5OjIyOjU0LjM3NloiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MWU1NzY3NGM3MTliMTAwNjlhNTRkYWMiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0MjQ5Nzc3NiwiZXhwIjoxNjQyNTMzNzc2LCJhdF9oYXNoIjoiNmxXSjg0QlhqS1NrR2lRWUJoM19aUSIsIm5vbmNlIjoiS2hUTX5JRThYYjRySWlBdnhVZ3N2QnAxWWxRSnhSNUoifQ.kIIqV-HOej4aYUOuxyIAKuD8faKj2wU-No0hZ4n7etiGyBJwAOBTvy33usWOG6g4kendkPND_CEiaVgXubVyZCjbl-6Bz6EaWr5cz2_DQZS7cws6OyDyVz5StPDGxTt5yGPbrSufQFBZy3QHXjSYvR4fKelgjhzAZSPjqKFjdfUJkj0F5DXveoSE3SmlQ9ZjFA9CPy-zdF4eVS4AXBa3RcPT-7qVOTjMtqJ36He_Z83yNTmo6XVcxQiGEMh9gDWIR1EE-0kxNKfe6LrPp3jk7pJ2IlDst0q6-czX_OVe5C6anZCfWbPdRSMrG8y-2MtjQPw0AgRpcWQxKDq0Mr0w8Q").build();
		Response resp=RestAssured.given().spec(req)
		.when().post("/learn/graphql");
		
		ResponseSpecification respSpec=new ResponseSpecBuilder().expectStatusCode(200).build();
		String response=resp.then().log().all().spec(respSpec).extract().response().asString();
		
		
		//System.out.println("**********");
		//System.out.println(response);
		JsonPath js=new JsonPath(response);
		String id=js.getString("data.online_users[2].id");
		System.out.println(id);
		
	}

}
