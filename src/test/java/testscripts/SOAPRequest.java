package testscripts;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import junit.framework.Assert;

public class SOAPRequest {
	
	
	
	@Test
	void Test_TempConversionService()
	{
		String soapBody="<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n"
				+ "\r\n"
				+ "  <soap12:Body>\r\n"
				+ "\r\n"
				+ "    <FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">\r\n"
				+ "\r\n"
				+ "      <Fahrenheit>92</Fahrenheit>\r\n"
				+ "\r\n"
				+ "    </FahrenheitToCelsius>\r\n"
				+ "\r\n"
				+ "  </soap12:Body>\r\n"
				+ "\r\n"
				+ "</soap12:Envelope>";
		
		RestAssured.baseURI="https://www.w3schools.com";
		Map<String,Object> headers=new HashMap<String,Object>();
		headers.put("Content-Type", "text/xml");
		headers.put("Host", "www.w3schools.com");
		
		
		String soapResp=RestAssured.given().log().all().body(soapBody).headers(headers)
		.when().post("/xml/tempconvert.asmx")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		XmlPath xml=new XmlPath(soapResp);
		String temp=xml.getString("FahrenheitToCelsiusResult");
		System.out.println("Converted temperature value is: "+temp);
		Assert.assertEquals("33.3333333333333", temp);
		
	}

}
