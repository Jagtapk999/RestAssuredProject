package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Response {
	
	@Test
	void getWeatherDetails() {
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1/employee";
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET,"/1");
		
		//print response body
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:"+responseBody);
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		String headerName = response.getHeader("header");
		Assert.assertEquals(headerName, null);
		JsonPath jsonpath=response.jsonPath();
		System.out.println(jsonpath.get("employee_name"));
		
	}

}
