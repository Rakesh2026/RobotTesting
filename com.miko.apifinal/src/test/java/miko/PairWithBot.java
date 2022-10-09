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

public class PairWithBot extends BaseApiClass{
	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void  mIKO_MIKO_011_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_011", "Pair bot with parent .");

		
		Response resp = given()
				.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.bot_code"),pUtil.getPropertiesData("botCodeNonMax") , "Response showing different bot code .");
		
		softAssert.assertAll();
	}

	@Test
	public void   mIKO_MIKO_012_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_012", "Pair bot with parent with different HTTP method .");
		
		Response resp = given()
				.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
				.header("Authorization","Bearer "+token)
				.when()
				.get(EndPoints.PairWithBot);
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
	public void    mIKO_MIKO_013_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_013", "Pair bot with parent with invalid end point .");

		Response resp = given()
				.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_MIKO_014_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_014", "Pair bot with parent with paired BOT code .");
		
		
		Response resp = given()
				.pathParam("bot_code",pUtil.getPropertiesData("AlreadyPairedbotCode"))
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",16,1), "Response showing different Message .");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_MIKO_015_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_015", "Pair bot with parent with Invalid BOT code .");
		
		Response resp = given()
				.pathParam("bot_code",excelUtil.getExcelData("Sheet1",13,1))
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",15,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_MIKO_016_PairWithBotTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_016", "Pair bot with parent without BOT code .");
		
		Response resp = given()
				.pathParam("bot_code","")
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
		@Test
		public void    mIKO_MIKO_017_PairWithBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_017", "Pair bot with parent without authorization .");
			
			Response resp = given()
					.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
					.when()
					.post(EndPoints.PairWithBot);
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
		public void    mIKO_MIKO_018_PairWithBotTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_018", "Pair bot with parent with invalid authorization .");
			
			Response resp = given()
					.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.when()
					.post(EndPoints.PairWithBot);
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
		
		
	 
	
}