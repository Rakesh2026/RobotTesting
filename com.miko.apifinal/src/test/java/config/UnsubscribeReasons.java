package config;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.miko.PojoClass.PojoVerifyOtp1;
import com.miko.genericUtils.BaseApiClass;
import com.miko.genericUtils.EndPoints;
import com.miko.genericUtils.IPathConstant;
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
 * This class contains all the TC of Get list of reason to unsubscribe feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class UnsubscribeReasons extends BaseApiClass{
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	
	@Test
	public void   mIKO_CONFIG_025_UnsubscribeReasonsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_025", "Get list of reason to unsubscribe");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.UnsubscribeReason);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		List ReasonActual = new ArrayList<String>();
		ReasonActual=resp.jsonPath().getList("data.reasons.label");
		List ReasonExpected = new ArrayList<String>();
		for(int i=1;i<=6;i++) {
			ReasonExpected.add(excelUtil.getExcelData("Unsubscribe",i,1));
		}
		softAssert.assertTrue(ReasonActual.containsAll(ReasonExpected), "Some different reason is displaying");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_026_UnsubscribeReasonsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_026", "Get list of reason to unsubscribe with invalid HTTP method ");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.UnsubscribeReason);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",21,1), "Response showing diffenent detail");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_027_UnsubscribeReasonsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_027", "Get list of reason to unsubscribe with invalid end point");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.UnsubscribeReason_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_028_UnsubscribeReasonsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_028", "Get list of reason to unsubscribe with invalid authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.header("Authorization",token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.UnsubscribeReason);
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
	@Test
	public void   mIKO_CONFIG_029_UnsubscribeReasonsTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_029", "Get list of reason to unsubscribe with out authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.when()
				.get(EndPoints.UnsubscribeReason);
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

	
	
	 
	
}