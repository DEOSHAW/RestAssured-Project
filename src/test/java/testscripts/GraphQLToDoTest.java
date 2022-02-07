package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pojo1.GraphQLToDoOutput;

public class GraphQLToDoTest {
	
	@Test
	void testToDOsAPI()
	{
		JSONObject jsonBody=new JSONObject();
		jsonBody.put("query", "{\r\n"
				+ "  todos(where: {title: {_eq: \"Deo Shaw\"}}) {\r\n"
				+ "    title\r\n"
				+ "  }\r\n"
				+ "}");
		jsonBody.put("variables", null);
		RestAssured.baseURI="https://hasura.io";
		GraphQLToDoOutput ob=RestAssured.given().log().all().body(jsonBody).header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZTU3Njc0YzcxOWIxMDA2OWE1NGRhYyJ9LCJuaWNrbmFtZSI6ImRlb3NoYXczLml0IiwibmFtZSI6ImRlb3NoYXczLml0QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82NjU4YWVkNDU2MmZmZDFkNWRiYzkwNWFiMTdmOTk4Nj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRlLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAyLTA3VDEyOjM4OjA4LjczMVoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MWU1NzY3NGM3MTliMTAwNjlhNTRkYWMiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NDIzNzQ5MSwiZXhwIjoxNjQ0MjczNDkxLCJhdF9oYXNoIjoiaTJ4YXZjR3ZPZlh6eVBwMnZhRXdfQSIsIm5vbmNlIjoiN3ozY0x-OXhOMGh3OVpWYkJNfnlTejJRckRBUy0zMU0ifQ.lVL_0DHy2LdHc7a7FwbcgI2Z6ln3NJkBKlcWxASq1iiuvX36oGsF4PEGUVQGS4y8cpPGAaol_52xGcqKhNPqmipPS1Vuf6ALSUaTmGrBbSjRfZd66FVlCXuFMfry-jnrF1YVVK_ny6-8t7C7CRYcIasMaEZloxqoTK10G9V4KyzKy_ZEVvt8DCEwb-L80sDZGS3qUPI0hKrcxr-fpcnhJQJCo-LbYBJi7Te73Jv84cSYnQMpjIoUxYLPIis-62Ix3HciIjNFvlEene8w9scrQD-FmEt1DCH16Um4RCLi3kZ-04LqyOpwRPRgOP_W8Y1yOBM_BiwnudtW0zAcJEk1Ig")
		.when().post("/learn/graphql")
		.then().log().all().statusCode(200).extract().response().as(GraphQLToDoOutput.class);
		
		String Title=ob.getData().getTodos().get(0).getTitle();
		System.out.println("Title of the ToDo is: "+Title);
		
	
		
		
	}

}
