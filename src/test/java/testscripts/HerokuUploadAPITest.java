package testscripts;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class HerokuUploadAPITest {
	
	
	@Test
	void testUploadAPI() throws IOException
	{
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://the-internet.herokuapp.com").addMultiPart("file",new File("C:\\Users\\deosh\\Desktop\\Appium.txt")).build();
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).build();
		String respBody=RestAssured.given().log().all().spec(req)
		.when().post("/upload").then().log().all().spec(res).extract().response().asString();
		
		XmlPath xml=new XmlPath(CompatibilityMode.HTML,respBody);
		System.out.println("Uploaded file name is: "+xml.getString("html.html.body.div[1].div.div.div"));
		
		
		//Test to write response in a json file
		Response resp=RestAssured.given().log().all()
		.when().get("https://reqres.in/api/users");
		byte[] body=resp.getBody().asByteArray();
		
		Files.write(body,new File(System.getProperty("user.dir")+File.separator+"reqRes.json"));
		
		
		
		
		
	}

}
