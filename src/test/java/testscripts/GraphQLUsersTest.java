package testscripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GraphQLUsers;

public class GraphQLUsersTest {
	
	
	
	@Test
	void testGraphQLUsersAPI()
	{
		
		GraphQLUsers ob=new GraphQLUsers();
		ob.setQuery("{\\n  online_users(limit: 10) {\\n    id\\n  }\\n}\\n");
		ob.setVariables(null);
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://hasura.io").setBody(ob).addHeader("Content-Type","application/json").addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxZTU3Njc0YzcxOWIxMDA2OWE1NGRhYyJ9LCJuaWNrbmFtZSI6ImRlb3NoYXczLml0IiwibmFtZSI6ImRlb3NoYXczLml0QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82NjU4YWVkNDU2MmZmZDFkNWRiYzkwNWFiMTdmOTk4Nj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRlLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAxLTMxVDExOjEyOjU4Ljg0OFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MWU1NzY3NGM3MTliMTAwNjlhNTRkYWMiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0MzYyNzU4MCwiZXhwIjoxNjQzNjYzNTgwLCJhdF9oYXNoIjoiMGRBb1VTYU9PTXVCTTdXYmd5Y21KUSIsIm5vbmNlIjoiRkdCUFBra3RJaVpNN2ViSXJfR3VSd35lNGlUSEcwR2MifQ.LYvOOsCn5kegw77Tr9v_pf7Mv6QRMbsVNKfgC_iY_aQ-7A1N-vqjDa414sRS_BE_h5UAImKX-mNLZFG5ZT--mahbgSpPbHKbC5dlMW0EADev7FIVNZrNR4txuMIQzNZ-LkwEcwDTTr4FTGsbDxCF1u5obu9YZGyDCLSoh7TXLapOgZUGWiTxeDluDaImG7gKszl1pOt5n1qR4KzVEld0m7RnHF4fuE2OfVL88HQNg_TZDWMLleFuQ556WxDq2N-HfvLFEVst2Pw4W1oaiLta0TTcNpHTZ_tbKRA_plRSBsib8BeYIzGvyWLMjcuJ_kN97L6DOTxiA5Ohdi7vVcAeSQ").build();
		Response response=RestAssured.given().log().all().spec(req)
		.when().post("/learn/graphql/graphiql");
		
		ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=response.then().spec(resp).extract().response().asString();
		System.out.println("Response Body is:   ");
		System.out.println(respBody);
		
		XmlPath xml=new XmlPath(CompatibilityMode.HTML,respBody);
		String Title=xml.get("html.head.title");
		System.out.println("Title is: "+Title);
		
	}

}
