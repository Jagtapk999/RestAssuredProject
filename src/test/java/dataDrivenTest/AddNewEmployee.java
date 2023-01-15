package dataDrivenTest;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddNewEmployee {
	
	@Test(dataProvider="EmpData")
	void CreateNewEmp(String ename,String esal,String eage) {
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		RequestSpecification httpReq=RestAssured.given();
		JSONObject reqPara=new JSONObject();
		reqPara.put("name", ename);
		reqPara.put("salary", esal);
		reqPara.put("age", eage);
		httpReq.header("Content-Type","application/json");
		httpReq.body(reqPara.toString());
		Response response=httpReq.request(Method.POST,"/create");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}
	
	
	
	@DataProvider(name="EmpData")
	String[][] getData(){
		String[][] empData= {
							{"Caa","70000","28"},
							{"BCbb","60000","24"},
							{"CcBc","75000","38"}
							};
		return empData;
	}

}
