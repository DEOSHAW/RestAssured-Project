package testscripts;

import org.testng.annotations.Test;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GraphQLUsers;
import pojo2.GraphQLUsersAPIOutput;

public class GraphQL_UsersAPI {
	
	
	@Test
	void testSubscriptionsAPI()
	{
		String accessToken="Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZTU3Njc0YzcxOWIxMDA2OWE1NGRhYyJ9LCJuaWNrbmFtZSI6ImRlb3NoYXczLml0IiwibmFtZSI6ImRlb3NoYXczLml0QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82NjU4YWVkNDU2MmZmZDFkNWRiYzkwNWFiMTdmOTk4Nj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRlLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAyLTIxVDEyOjA3OjEyLjA0NloiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MWU1NzY3NGM3MTliMTAwNjlhNTRkYWMiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NTQ0NTIzNCwiZXhwIjoxNjQ1NDgxMjM0LCJhdF9oYXNoIjoiVEhxNkozSmtTMXlFekZwOFVCaVlhUSIsIm5vbmNlIjoiSUYyemVPLXJROUpHcjMyd0tpSXN4VHRmY1JIeWM1Q2EifQ.pRq7N6H1ZGL2R_gE7_nTGzdUw82jJ8iHQn59v9jHmr1_pplbiSWdc-uFJLD4-3nHWReHqM2E9xrfAlIoZh4-a-YSEhZ_YOfVNyFLnDv_0EhJt9Wb0uTE6ErOTj7BbNtw7RznSxasC6WiLlv11YfAKoThVn4JSilg-703I5Xv7se-KnvhHfQ-Ogel4o5gC5I5AsUA54Z3oCWi44sHgd3CwLDgG4huJHJFAiNqz-BsPTl_81slzQdWTmY-LmSF8PsRcw2w3NMWNY4aTCZsl3y6uwJdP5fo4twJ5P_nAvLShoX1O4B0Sz-Y0uwmxH9Jd_OwmFJ9uu3-ajcy7ibkt--nQA";
		GraphQLUsers ob=new GraphQLUsers();
		ob.setQuery("{\r\n"
				+ "  online_users(distinct_on: id, limit: 10) {\r\n"
				+ "    user {\r\n"
				+ "      id\r\n"
				+ "      name\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}");
		ob.setVariables(null);
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://hasura.io/").addHeader("Authorization",accessToken).setBody(ob).build();
		ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).build();
		GraphQLUsersAPIOutput resp=RestAssured.given().log().all().spec(req).when().post("/learn/graphql").then().log().all().spec(response)
				.extract().as(GraphQLUsersAPIOutput.class);
		
		System.out.println("Name of the second user is: ");
		
		System.out.println(resp.getData().getOnline_users().get(1).getUser().getName());
		
System.out.println("Id of the third user is: ");
		
		System.out.println(resp.getData().getOnline_users().get(2).getUser().getId());
		
	}

}
