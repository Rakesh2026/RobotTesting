package authentication;



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

/**
 * This class contains all the TC of Verify OTP feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class VerifyOTP extends BaseApiClass{

	@BeforeClass
	public void bcConfiguration() {
		
		reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
	}
	@Test
	public void  mIKO_AUTH_016_VerifyOTPTests() {
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_016", "Verify OTP using mobile OTP");
		
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"), pUtil.getPropertiesData("otp"));
		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message  ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.mobile"),pUtil.getPropertiesData("mobileno1"),"Response showing different mobile no");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_AUTH_017_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest(" MIKO_AUTH_017", "Verify OTP using email OTP");
		reusableMethod.registerUsingEmail(pUtil.getPropertiesData("emailId1"));

		HashMap map=new HashMap();
		map.put("email", pUtil.getPropertiesData("emailId1"));
		map.put("code", pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message  ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),pUtil.getPropertiesData("emailId1"),"Response showing different mobile no");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_018_VerifyOTPTests() {
		
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_018", "Verify OTP using different HTTP method");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"), pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",20,1), "Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_019_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_019", "Verify OTP using Invalid end point");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"), pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different content type");
		softAssert.assertAll();
	}
	
	@Test
	public void  mIKO_AUTH_020_VerifyOTPTests() {
	
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_020", "Verify OTP using invalid code");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),jUtil.getRandomNumber(1000000)+"" );

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",8,1), "Response showing different message");
		softAssert.assertAll();
	}
	
	@Test
	public void  mIKO_AUTH_021_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_021", "Verify OTP using code more than 6 digit");
		String OTP=excelUtil.getExcelData("Sheet1", 2, 1);
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),OTP );

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",8,1), "Response showing different message");
		softAssert.assertTrue(OTP.length()>6,"The length of the OTP was less than 6 digit .");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_022_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_022", "Verify OTP using code less than 6 digit");
		String OTP=excelUtil.getExcelData("Sheet1", 3, 1);
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),OTP );

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",8,1), "Response showing different message");
		softAssert.assertTrue(OTP.length()<6,"The length of the OTP was greater than 6 digit .");
		softAssert.assertAll();
	}
	//@Test
	public void  mIKO_AUTH_023_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_023", "Verify OTP using invalid mobile no");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(excelUtil.getExcelData("Sheet1", 4, 1),pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),400,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Bad Request"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",8,1), "Response showing diffenent message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_024_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_024", "Verify OTP using content Type as Text");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.TEXT)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_025_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_025", "Verify OTP using content Type as JavaScript");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType("application/javaScript")
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_026_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_026", "Verify OTP using content Type as HTML");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.HTML)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_027_VerifyOTPTests() {

		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_027", "Verify OTP using content Type as XML");
		PojoVerifyOtp1 pojoVerifyOtp1=new PojoVerifyOtp1(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));

		Response resp = given()
				.body(pojoVerifyOtp1)
				.contentType(ContentType.XML)
				.when()
				.post(EndPoints.VerifyOTP);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Response showing different detail");
		softAssert.assertAll();
	}
}