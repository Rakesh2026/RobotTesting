package config;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
 * This class contains all the TC of Load all API keys & static string feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class KeysConfig extends BaseApiClass{
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void  mIKO_CONFIG_010_KeysConfigTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_010", "Load all API keys & static string");	
		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.KeysConfig);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		//SoftAssert.asser(rUtil.getJsonData(resp, "data.id"))
		softAssert.assertNotNull(rUtil.getJsonData(resp, "data.active_bot.bot_id"));
		softAssert.assertAll();
	}
	 
	@Test
	public void   mIKO_CONFIG_011_KeysConfigTests() {
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_011", "Load all API keys & static string with invalid HTTP method");
		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.KeysConfig);
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
	public void   mIKO_CONFIG_012_KeysConfigTests() {
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_012", "Load all API keys & static string with invalid end point");

		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.KeysConfig_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_013_KeysConfigTests() {
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_013", "Load all API keys & static string with invalid authorization code");

		Response resp = given()
				.header("Authorization",token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.KeysConfig);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorize"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",6,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	
	@Test
	public void   mIKO_CONFIG_014_KeysConfigTests() {
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_014", "Load all API keys & static string without any authorization");

		Response resp = given()
				.when()
				.get(EndPoints.KeysConfig);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorize"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",7,1), "Response showing diffenent message");
		softAssert.assertAll();
	}

}