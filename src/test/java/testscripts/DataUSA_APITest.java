package testscripts;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DataUSA_APITest {
	
	@Test
	void testDataUsa()
	{
		
		Map<String,String> qParams=new HashMap<String,String>();
		qParams.put("drilldowns", "Nation");
		qParams.put("measures", "Population");
		RestAssured.baseURI="https://datausa.io";
		String responseBody=given().log().all().queryParams(qParams).urlEncodingEnabled(false)
		.when().get("/api/data")
		.then().log().all().statusCode(200).extract().response().asString();
		
		
		JsonPath js=new JsonPath(responseBody);
		int Size=js.getList("data.Year").size();
		List<Object> data=js.getList("data");
		Object data_1=data.get(0);
		System.out.println(data_1.toString().split(",")[0].split("=")[1]);
		System.out.println("Size of the array is: "+Size);
		System.out.println("2nd year is: "+js.getString("data[1].Year"));
		
	}

}
