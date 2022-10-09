package max;



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
 * This class contains all the TC of Get Max Home Page feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class GetMaxHomePage extends BaseApiClass{
	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void mIKO_MAX_001_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_001", "Fetch data for max home page.");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		List ImageList = new ArrayList<String>();
		ImageList=resp.jsonPath().getList("data.max_home_brand");
		
		softAssert.assertTrue(ImageList.size()>0, "Max image is not loading .");
		softAssert.assertAll();
	}
	
	@Test
	public void mIKO_MAX_002_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_002", "Fetch data for max home page with invalid HTTP method .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",20,1), "Response showing diffenent detail .");
		softAssert.assertAll();
	}
	
	@Test
	public void mIKO_MAX_003_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_MAX_003", "Fetch data for max home page with invalid end point .");

		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_004_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_004", "Fetch data for max home page without authorization.");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",7,1), "Response showing different detail");
		softAssert.assertAll();
	}
	
	@Test
	public void mIKO_MAX_005_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_MAX_005", "Fetch data for max home page without request body .");
		
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_id[0]"), excelUtil.getExcelData("Message",4,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_006_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_MAX_006", "Fetch data for max home page with invalid authorization");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token+jUtil.getRandomNumber(100))
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",6,1), "Response showing different message .");
		softAssert.assertAll();
	}
	 
	//@Test
	public void mIKO_MAX_007_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_MAX_007", "Fetch data for max home page with invalid authorization");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token+jUtil.getRandomNumber(100))
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",14,1), "Response showing different message .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_008_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_008", "Fetch data for max home page with null bot Id .");
		HashMap map=new HashMap();
		map.put("bot_id", "");
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_id[0]"), excelUtil.getExcelData("Message",13,1), "Response showing different error .");
		softAssert.assertAll();
	}
	//@Test
	public void mIKO_MAX_009_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_009", "Fetch data for max home page with null bot Id .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_id[0]"), excelUtil.getExcelData("Message",13,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_010_GetMaxHomePageTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_010", "Fetch data for max home page with content type as text.");
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId2"));
			
			
			Response resp = given()
					.header("Authorization","Bearer "+token)
					.body(map)
					.contentType(ContentType.TEXT)
					.when()
					.post(EndPoints.GetMaxHomePage);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail .");
			softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the detail .");
			softAssert.assertAll();
		}
	@Test
	public void mIKO_MAX_011_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_011", "Fetch data for max home page with content type as Java Script .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType("application/javaScript")
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail detail .");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_012_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_012", "Fetch data for max home page with content type as HTML .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.HTML)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the details .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_013_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_013", "Fetch data for max home page with content type as XML .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.XML)
				.when()
				.post(EndPoints.GetMaxHomePage);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail .");
		softAssert.assertAll();
	}
}
