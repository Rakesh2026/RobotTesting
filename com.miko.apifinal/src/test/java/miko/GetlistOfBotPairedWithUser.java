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

public class GetlistOfBotPairedWithUser extends BaseApiClass{
	List idList = new ArrayList<String>();

	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@BeforeClass
	public void bcConfiguration1() {
		
		given()
				.pathParam("bot_code",pUtil.getPropertiesData("botCodeNonMax"))
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.PairWithBot);
		given()
		.pathParam("bot_code",pUtil.getPropertiesData("botCodeNotPaired"))
		.header("Authorization","Bearer "+token)
		.when()
		.post(EndPoints.PairWithBot);
	}
	
		
		
	@Test
	public void  mIKO_MIKO_019_GetlistOfBotPairedWithUserTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_019", "Get list of bot paired with user .");

		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.get(EndPoints.GetListOfBotPairedWithUser);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertNotNull(rUtil.getJsonData(resp, "data.bot_id[0]"), "Response showing null botid .");
		softAssert.assertNotNull(rUtil.getJsonData(resp, "data.bot_id[1]"), "Response showing null botid .");
		
		softAssert.assertAll();
	}

	@Test
	public void   mIKO_MIKO_020_GetlistOfBotPairedWithUserTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_020", "Get list of bot paired with user with invalid HTTP method");
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetListOfBotPairedWithUser);
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
	public void    mIKO_MIKO_021_GetlistOfBotPairedWithUserTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_021", "Get list of bot paired with user using invalid end point");

		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetListOfBotPairedWithUser_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}

		@Test
		public void    mIKO_MIKO_022_GetlistOfBotPairedWithUserTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_022", "Get list of bot paired with out authorization");
			
			Response resp = given()
					.when()
					.get(EndPoints.GetListOfBotPairedWithUser);
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
		public void    mIKO_MIKO_023_GetlistOfBotPairedWithUserTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MIKO_023", "Get list of bot paired with invalid authorization .");
			
			Response resp = given()
					.header("Authorization","Bearer "+token +jUtil.getRandomNumber(1000))
					.when()
					.get(EndPoints.GetListOfBotPairedWithUser);;
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
		@AfterClass
		public void acConfiguration1() {
			//Unlink 2nd Bot
			given()
					.header("Authorization","Bearer "+token)
					.when()
					.delete(EndPoints.UnlinkBot);
			
		}
		
	 
	
}