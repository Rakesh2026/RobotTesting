package miko;



import org.testng.annotations.AfterClass;
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

public class SwitchBot extends BaseApiClass{
	List idList = new ArrayList<String>();

	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@BeforeClass
	public void bcConfiguration1() {
		
		given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.SwitchBot);
		given()
		.pathParam("bot_code",pUtil.getPropertiesData("botCodeNotPaired"))
		.header("Authorization","Bearer "+token)
		.when()
		.post(EndPoints.PairWithBot);
	}
	
		
		
	@Test
	public void  mIKO_MIKO_024_SwitchBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_024", "Change current active bot .");
		
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer "+token)
				.when()
				.put(EndPoints.SwitchBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.bot.bot_id"),pUtil.getPropertiesData("BotId1"), "Response showing different Bot id .");
		
		
		softAssert.assertAll();
	}

	@Test
	public void   mIKO_MIKO_025_SwitchBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_025", "Change current active bot with invalid BOT id .");
		HashMap map=new HashMap();
		map.put("bot_id", excelUtil.getExcelData("Sheet1",14,1));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer "+token)
				.when()
				.put(EndPoints.SwitchBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",17,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_MIKO_026_SwitchBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_026", "Change current active bot with out any BOT id .");
		HashMap map=new HashMap();
		map.put("bot_id", "");
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer "+token)
				.when()
				.put(EndPoints.SwitchBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_id[0]"), excelUtil.getExcelData("Message",13,1), "Response showing diffenent error .");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_MIKO_027_SwitchBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_027", "Change current active bot with invalid HTTP method .");
		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.SwitchBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowe"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",21,1), "Response showing diffenent detail.");
		softAssert.assertAll();
	}
	
	@Test
	public void    mIKO_MIKO_028_SwitchBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_028", "Change current active bot with invalid end point.");

		HashMap map=new HashMap();
		map.put("bot_id", pUtil.getPropertiesData("BotId1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer "+token)
				.when()
				.put(EndPoints.SwitchBot_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}

		@Test
		public void    mIKO_MIKO_029_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_029", "Change current active bot with out any authorization");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.body(map)
					.contentType(ContentType.JSON)
					.when()
					.put(EndPoints.SwitchBot);
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the Message .");
			softAssert.assertTrue(rUtil.getJsonData(resp, "message").contains(excelUtil.getExcelData("Message",7,1)), "Response showing different message .");
			softAssert.assertAll();
		}
		@Test
		public void    mIKO_MIKO_030_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_030", "Change current active bot with invalid authorization.");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.body(map)
					.contentType(ContentType.JSON)
					.when()
					.put(EndPoints.SwitchBot);;
			ValidatableResponse response=resp.then();
			response.log().all();
			ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
			softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
			softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
			softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
			ListnersClass.test.log(Status.INFO, "Verifying the detail");
			softAssert.assertTrue(rUtil.getJsonData(resp, "message").contains(excelUtil.getExcelData("Message",6,1)), "Response showing different message .");
			softAssert.assertAll();
		}
		@Test
		public void    mIKO_MIKO_031_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_031", "Change current active bot with content type as Text.");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.body(map)
					.contentType(ContentType.TEXT)
					.when()
					.put(EndPoints.SwitchBot);;
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
		public void    mIKO_MIKO_032_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_032", "Change current active bot with content type as Java Script.");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.body(map)
					.contentType("application/javascript")
					.when()
					.put(EndPoints.SwitchBot);;
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
		public void    mIKO_MIKO_033_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_033", "Change current active bot with content type as HTML.");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.body(map)
					.contentType(ContentType.HTML)
					.when()
					.put(EndPoints.SwitchBot);;
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
		public void    mIKO_MIKO_034_SwitchBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_034", "Change current active bot with content type as XML .");
			
			HashMap map=new HashMap();
			map.put("bot_id", pUtil.getPropertiesData("BotId1"));
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.body(map)
					.contentType(ContentType.HTML)
					.when()
					.put(EndPoints.SwitchBot);;
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
		@AfterClass
		public void acConfiguration1() {
			//Unlink 2nd Bot
			given()
					.header("Authorization","Bearer "+token)
					.when()
					.delete(EndPoints.UnlinkBot);
			
		}
		
	 
	
}