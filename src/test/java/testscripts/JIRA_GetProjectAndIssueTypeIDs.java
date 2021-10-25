package testscripts;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class JIRA_GetProjectAndIssueTypeIDs {
	
	
	@Test
	void getProjectAndIssueTypeIDs()
	{
		String bugID = null;
		//Get Cookie Value
		
				JSONObject json=new JSONObject();
				json.put("username"	,"deoshaw3.it");
				json.put("password"	,"Dd$03091991");
				
				RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:8080/").setBody(json).addHeader("Content-Type","application/json").build();
				req=RestAssured.given().log().all().spec(req);
				Response resp=req.when().post("/rest/auth/1/session");
				String respBody=resp.getBody().asPrettyString();
				JsonPath js=new JsonPath(respBody);
				String cookie=js.getString("session.value");
				
	   // Calling JIRA API to get Project ID
				RequestSpecification request=new RequestSpecBuilder().setBaseUri("http://localhost:8080").addHeader("Content-Type", "application/json").addCookie("JSESSIONID",cookie).build();
		        request=RestAssured.given().log().all().spec(request);
		        Response res=request.when().get("/rest/api/2/project");
		        String responseBody=res.getBody().asPrettyString();
		       System.out.println(responseBody); 
		       JsonPath jsPath=new JsonPath(responseBody);
		       String projectID=jsPath.getString("id[0]");
		       System.out.println("Project ID is: "+projectID);
		       Assert.assertEquals(res.getStatusCode(),200);
	  //Calling JIRA API to get issue types for the project
		       RestAssured.baseURI="http://localhost:8080";
		      String issueTypeBody= RestAssured.given().header("Content-Type", ContentType.JSON).cookie("JSESSIONID",cookie).pathParam("projectIdOrKey",projectID)
		       .when().get("/rest/api/2/issue/createmeta/{projectIdOrKey}/issuetypes")
		       .then().log().all().statusCode(200).extract().response().asString();
		      JsonPath jsIssueType=new JsonPath(issueTypeBody);
		      
		      //Get the id of the 'bug' issue type
		      int count=jsIssueType.getList("values.id").size();
		      for(int i=0;i<count;i++)
		      {
		    	  if(jsIssueType.getString("values.name["+i+"]").equalsIgnoreCase("Task"))
		    	  {
		    		  bugID=jsIssueType.getString("values.id["+i+"]");
		    		  break;
		    	  }
		      }
		      
		      
		      System.out.println("ID of Bug issue type is "+bugID);
		       
	}

}
