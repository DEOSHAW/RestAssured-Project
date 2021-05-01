package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import datamodel.MockAPIResponseData;
import io.restassured.path.json.JsonPath;

public class ValidateMockAPIResponse {
	
	@Test
	void validateMockAPIResponse()
	{
		
		JsonPath js=new JsonPath(MockAPIResponseData.mockAPIResponse());
		//Find Number of courses
		int count=js.getInt("courses.size()");
		System.out.println("Number of courses is: "+count);
		//Print purchase amount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount is: "+purchaseAmount);
		//Print title of the first course
		String firstCourseTitle=js.getString("courses[0].title");
		System.out.println("Title of the first course is: "+firstCourseTitle);
		//Print all course titles and their respective prices
		for(int i=0;i<count;i++)
		{
			String title=js.getString("courses["+i+"].title");
			String price=js.getString("courses["+i+"].price");
			System.out.println(title+" "+price);
		}
		//Print no of copies sold by RPA Course
		for(int i=0;i<count;i++)
		{
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
			{
				System.out.println("Number of copies sold by RPA is: "+js.getString("courses["+i+"].copies"));
				break;
			}
		}
		
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		double Sum=0;
		for(int i=0;i<count;i++)
		{
			Sum=Sum+js.getInt("courses["+i+"].copies")*js.getInt("courses["+i+"].price");
		}
		System.out.println("Sum of prices is: "+Sum);
		Assert.assertEquals(Sum, purchaseAmount);
		
	}

}
