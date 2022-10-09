package miko;



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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains all the TC of Get all the available languages feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class GetBotDetail extends BaseApiClass{
	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void  mIKO_MIKO_001_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_001", "Get bot details using bot code .");
		HashMap map =new HashMap();
		map.put("code", pUtil.getPropertiesData("botCodeNonMax"));
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.post(EndPoints.GetBotDetails);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.user.mobile"),pUtil.getPropertiesData("mobileno1") , "Response showing different mobile no.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.bot_code"),pUtil.getPropertiesData("botCodeNonMax"), "Response showing different bot code.");
		softAssert.assertAll();
	}

	@Test
	public void   mIKO_MIKO_002_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_002", "Get bot details using bot code using invalid HTTP method");
		
		HashMap map =new HashMap();
		map.put("code", pUtil.getPropertiesData("botCodeNonMax"));

		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.get(EndPoints.GetBotDetails);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",20,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	
	@Test
	public void    mIKO_MIKO_003_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_003", "Get bot details using bot code using invalid end point");
		HashMap map =new HashMap();
		map.put("code", pUtil.getPropertiesData("botCodeNonMax"));

		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.post(EndPoints.GetBotDetails_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_MIKO_004_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_004", "Get bot details by passing invalid BOT code");
		HashMap map =new HashMap();
		map.put("code", jUtil.getRandomNumber(100000));
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.post(EndPoints.GetBotDetails);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.code[0]"), excelUtil.getExcelData("Message",15,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_MIKO_005_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_005", "Get bot details using bot code using special caracter in BOT code .");
		HashMap map =new HashMap();
		map.put("code", excelUtil.getExcelData("Sheet1",12,1));
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.post(EndPoints.GetBotDetails);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.code[0]"), excelUtil.getExcelData("Message",15,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_MIKO_006_GetBotDetailTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_006", "Get bot details using bot code field as blank .");
		HashMap map =new HashMap();
		map.put("code","");
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				.post(EndPoints.GetBotDetails);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.code[0]"), excelUtil.getExcelData("Message",13,1), "Response showing different error .");
		softAssert.assertAll();
	}
		@Test
		public void    mIKO_MIKO_007_GetBotDetailTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_007", "Get bot details using bot code with content type as Text .");
			HashMap map =new HashMap();
			map.put("code",pUtil.getPropertiesData("botCodeNonMax"));
			
			Response resp = given()
					.body(map)
					.contentType(ContentType.TEXT)
					.when()
					.post(EndPoints.GetBotDetails);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail");
			softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)), "Response showing different detail .");
			softAssert.assertAll();
		}
		@Test
		public void    mIKO_MIKO_008_GetBotDetailTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_008", "Get bot details using bot code with content type as Text .");
			HashMap map =new HashMap();
			map.put("code",pUtil.getPropertiesData("botCodeNonMax"));
			
			Response resp = given()
					.body(map)
					.contentType("application/javascript")
					.when()
					.post(EndPoints.GetBotDetails);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail");
			softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)), "Response showing different detail .");
			softAssert.assertAll();
		}
		
		@Test
		public void    mIKO_MIKO_009_GetBotDetailTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_009", "Get bot details using bot code with content type as HTML .");
			HashMap map =new HashMap();
			map.put("code",pUtil.getPropertiesData("botCodeNonMax"));
			
			Response resp = given()
					.body(map)
					.contentType(ContentType.HTML)
					.when()
					.post(EndPoints.GetBotDetails);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail");
			softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)), "Response showing different detail .");
			softAssert.assertAll();
		}
		@Test
		public void    mIKO_MIKO_010_GetBotDetailTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_010", "Get bot details using bot code with content type as XML .");
			HashMap map =new HashMap();
			map.put("code",pUtil.getPropertiesData("botCodeNonMax"));
			
			Response resp = given()
					.body(map)
					.contentType(ContentType.XML)
					.when()
					.post(EndPoints.GetBotDetails);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail");
			softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)), "Response showing different detail .");
			softAssert.assertAll();
		}
	 
	 
	
}