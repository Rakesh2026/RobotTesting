package config;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.miko.PojoClass.PojoVerifyOtp1;
import com.miko.genericUtils.BaseApiClass;
import com.miko.genericUtils.EndPoints;
import com.miko.genericUtils.ListnersClass;
import com.miko.genericUtils.ReusableMethod;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;


import java.util.HashMap;
import java.util.List;

/**
 * This class contains all the TC of Load carousel of dashboard feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class DashboardHome extends BaseApiClass{
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	
	@Test
	public void   mIKO_CONFIG_015_DashboardHomeTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_015", "Load carousel of dashboard");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.DashboardHome);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		//assertTrue(rUtil.getJsonData(resp, "data.dashboardHome[:].title").length());
		//response.jsonPath().get(path)
		List<String> dashBoard=resp.jsonPath().getList("data.dashboardHome.title");
		softAssert.assertTrue(dashBoard.size()>1, "Dashboard showing blank list");

		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_016_DashboardHomeTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_016", "Load carousel of dashboard with different HTTP method");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.DashboardHome);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",21,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	
	@Test
	public void   mIKO_CONFIG_017_DashboardHomeTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_017", "Load carousel of dashboard with different end point .");
		//sreusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.DashboardHome_Invalid);
		ValidatableResponse response=resp.then();
		//response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_018_DashboardHomeTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_018", "Load carousel of dashboard without authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.when()
				.get(EndPoints.DashboardHome);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",7,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_019_DashboardHomeTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_019", "Load carousel of dashboard with invalid authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.DashboardHome);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",6,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	 
	
}