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

public class GetListOfMaxSubscriptions extends BaseApiClass{
	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void mIKO_MAX_014_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_014", "Fetch available max plan for the Bot .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		List priceList = new ArrayList<String>();
		priceList=resp.jsonPath().getList("data.subscription_list.price");
		
		softAssert.assertTrue(priceList.size()>3, "Max plan is not displaying .");
		softAssert.assertAll();
	}
	
	@Test
	public void mIKO_MAX_015_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_015", "Fetch available max plan for the Bot  with invalid HTTP method .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.GetListOfMaxSubscriptions);
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
	public void mIKO_MAX_016_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_016", "Fetch available max plan for the Bot with invalid end point .");

		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_017_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_017", "Fetch available max plan for the Bot without request body .");
		//HashMap map=new HashMap();
		//map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error.");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_code[0]"), excelUtil.getExcelData("Message",4,1), "Response showing different detail");
		softAssert.assertAll();
	}
	
	//@Test
	public void mIKO_MAX_018_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_018", "Fetch available max plan for the Bot with invalid bot_code .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
	//@Test
	public void mIKO_MAX_019_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_020", "Fetch available max plan for the Bot with bot_code as null .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token+jUtil.getRandomNumber(100))
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
	 
	@Test
	public void mIKO_MAX_020_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_MAX_020", "Fetch available max plan for the Bot with bot_code as null .");
		HashMap map=new HashMap();
		map.put("bot_code", "");
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.bot_code[0]"), excelUtil.getExcelData("Message",13,1), "Response showing different error .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_021_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_021", "Fetch available max plan for the Bot without authorization .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the Message .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",7,1), "Response showing different message .");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_MAX_022_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_022", "Fetch available max plan for the Bot with invalid authorization .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token+jUtil.getRandomNumber(100))
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
	@Test
	public void mIKO_MAX_023_GetMaxHomePageTests() {
			ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_023", "Fetch available max plan for the Bot with content type as text .");
			HashMap map=new HashMap();
			map.put("bot_code", pUtil.getPropertiesData("BotId2"));
			
			
			Response resp = given()
					.header("Authorization","Bearer "+token)
					.body(map)
					.contentType(ContentType.TEXT)
					.when()
					.post(EndPoints.GetListOfMaxSubscriptions);
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
	public void mIKO_MAX_024_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_024", "Fetch available max plan for the Bot with content type as Java Script .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType("application/javaScript")
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
	public void mIKO_MAX_025_GetListOfMaxSubscriptionsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_025", "Fetch available max plan for the Bot with content type as HTML .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.HTML)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
	public void mIKO_MAX_026_GetMaxHomePageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_MAX_026", "Fetch available max plan for the Bot with content type as XML .");
		HashMap map=new HashMap();
		map.put("bot_code", pUtil.getPropertiesData("BotId2"));
		
		
		Response resp = given()
				.header("Authorization","Bearer "+token)
				.body(map)
				.contentType(ContentType.XML)
				.when()
				.post(EndPoints.GetListOfMaxSubscriptions);
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
