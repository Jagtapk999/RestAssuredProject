package restAPI;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_POSTRequest {
	@Test
	void registrationSucess() throws InterruptedException {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject reqPara=new JSONObject();
		reqPara.put("name","Ranav");
		reqPara.put("salary","70000");
		reqPara.put("age", "15");
	//	reqPara.put("", "");
		httpRequest.header("Content-Type","application/json");
		 httpRequest.body(reqPara.toString());
		 Response response=httpRequest.request(Method.POST,"/create");
		 
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		//SoftAssert soft=new SoftAssert();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Status code= "+statusCode);
		Thread.sleep(2000);
		String status = response.jsonPath().get("status");
		Assert.assertEquals(status, "success");
		System.out.println("Status= "+status);
		Thread.sleep(2000);
		boolean name=responseBody.contains("Ranav");
		Assert.assertEquals(name,true);
		System.out.println("Name= "+name);
		Thread.sleep(2000);
		boolean salary = responseBody.contains("70000");
		Assert.assertEquals(salary, true);
		System.out.println("Salary= "+salary);
		Thread.sleep(2000);
		boolean age=responseBody.contains("15");
		Assert.assertEquals(age, true);
		System.out.println("age= "+age);
		
		JsonPath path=response.jsonPath();
		System.out.println(path.get("name"));
		
		
	}

}
