package datamodel;

public class GoogleAPIData {
	
	
	public static String addPlaceBody(String placeAddress)
	{
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\":"+"\""+placeAddress+"\""+",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String putPlaceBody(String placeAddress,String placeId)
	{
		
		return "{\r\n"
				+ "\"place_id\":"+"\""+placeId+"\""+",\r\n"
				+ "\"address\":"+"\""+placeAddress+"\""+",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	}
	
	public static String deletePlaceBody(String placeId)
	{
		
		return "{\r\n"
				+ "        \"place_id\":"+"\""+placeId+"\""+"\r\n"
				+ "    }";
	}



}
