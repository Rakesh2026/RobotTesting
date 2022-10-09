package profile;



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

public class GetProfileForCustomer extends BaseApiClass{
	
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	@Test
	public void  mIKO_PROFILE_001_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_PROFILE_001", "Get all the available languages");

		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.get(EndPoints.GetProfileForCustomer);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.mobile"), pUtil.getPropertiesData("mobileno1"), "Response showing different mobile no .");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_PROFILE_002_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_PROFILE_002", "Get the profile detail of a customer with invalid HTTP method");

		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.post(EndPoints.GetProfileForCustomer);
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
	public void    mIKO_PROFILE_003_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_PROFILE_003", "Get the profile detail of a customer with invalid end Point");

		Response resp = given()
				.header("Authorization","Bearer "+token)
				.when()
				.get(EndPoints.GetProfileForCustomer_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_PROFILE_004_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_PROFILE_004", "Get the profile detail of a customer without authorization .");
		Response resp = given()
				.when()
				.get(EndPoints.GetProfileForCustomer);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",14,1), "Response showing different detail");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_PROFILE_005_GetProfileForCustomerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_PROFILE_005", "Get the profile detail of a customer with invalid authorization .");
		Response resp = given()
				.header("Authorization","Bearer "+token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.GetProfileForCustomer);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",14,1), "Response showing different detail");
		softAssert.assertAll();
	}
	 
	 
	
}