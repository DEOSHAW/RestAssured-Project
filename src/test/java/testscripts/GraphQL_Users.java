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
				.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZTU3Njc0YzcxOWIxMDA2OWE1NGRhYyJ9LCJuaWNrbmFtZSI6ImRlb3NoYXczLml0IiwibmFtZSI6ImRlb3NoYXczLml0QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82NjU4YWVkNDU2MmZmZDFkNWRiYzkwNWFiMTdmOTk4Nj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRlLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAxLTI0VDA0OjExOjUzLjc4MloiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MWU1NzY3NGM3MTliMTAwNjlhNTRkYWMiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0Mjk5NzUxNSwiZXhwIjoxNjQzMDMzNTE1LCJhdF9oYXNoIjoiQ0k0ZVBsQjZ6SkNPZUFHOEhkbW1aZyIsIm5vbmNlIjoiVHNSa1JQU05BOWMuSDBkZGZKZ0s4RmJpLWc5UTRzSU8ifQ.MVp8pBq5lWJNL5CeK6t6lG-PYRj_72yMKE7ADIa-LaSQiN7zqfvI7bmizaho9G9QIdYWh19PL4s3zZ5f1pT7gmRPnYYoYbMgpSNCWG5KbfLDhyNO7-K7Rf8cpxtAfsM217hVs8qTpfXb_UVzkdQMHJqOKz_TnphoHZhxw8awJKMoPQJsRU72gTG2KhWRyUY81gP0lMQBc_zNlRFvadN-oJrYTVD_0ldivfYaJMvfWoYv2fZRMP3L-g2yuCFjaEqKTCtYCc_-INjWVBudLbDx1aHPtPjV0ifcA6hHrl9CzD5LMTueQB3hc42Qt8bhMKXmIdBIrqiPFT7ceadK8pSLPQ").build();
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
