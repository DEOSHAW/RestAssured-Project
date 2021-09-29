package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CookiesBasedAuthenticationTest {
	
	
	@Test
	void testCookiesAuthentication()
	{
		
		//Getting JSESSION cookie
		JSONObject json=new JSONObject();
		json.put("username"	,"deoshaw3.it");
		json.put("password"	,"Dd$03091991");
		
		RestAssured.baseURI="http://localhost:8080";
		Response resp=RestAssured.given().log().all().header("Content-Type","application/json").body(json)
		.when().post("/rest/auth/1/session");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getBody().jsonPath().prettyPrint());
		String sessionID=resp.getCookie("JSESSIONID");
		System.out.println();
		
		//Creating an issue in JIRA
		RestAssured.given().log().all().header("Content-Type","application/json").cookie("JSESSIONID",sessionID).body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RES\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Test Bug\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("http://localhost:8080/rest/api/2/issue/").then().log().all().statusCode(201);
		
		
		
		
	}

}
