package authentication;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.miko.genericUtils.BaseApiClass;
import com.miko.genericUtils.EndPoints;
import com.miko.genericUtils.ListnersClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

/**
 * This class contains all the TC of get All config feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)
public class Register extends BaseApiClass {
	
	@Test
	public void  mIKO_AUTH_028_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_028", "Register using phone no");

		
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the mobile no in response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.mobile"),pUtil.getPropertiesData("mobileno1"),"Response showing different mobile no");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_029_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_029", "Register using email");

		
		HashMap map =new HashMap();
		map.put("email", pUtil.getPropertiesData("emailId1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the email address in response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),pUtil.getPropertiesData("emailId1"),"Response showing different email address");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_030_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_030", "Register using phone no & email id");

		
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));
		map.put("email", pUtil.getPropertiesData("emailId1"));
		
		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the mobile no & email address in response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.mobile"),pUtil.getPropertiesData("mobileno1"),"Response showing different phone no .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),pUtil.getPropertiesData("emailId1"),"Response showing different email address");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_031_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_031", "Register using email ,phone , phone , first name & last name .");

		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));
		map.put("email", pUtil.getPropertiesData("emailId1"));
		map.put("first_name", pUtil.getPropertiesData("firstName1"));
		map.put("last_name", pUtil.getPropertiesData("lastName1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the mobile no , email,First Name & Last Name address in response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.mobile"),pUtil.getPropertiesData("mobileno1"),"Response showing different phone no .");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),pUtil.getPropertiesData("emailId1"),"Response showing different email address");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.first_name"),pUtil.getPropertiesData("firstName1"),"Response showing First name");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.last_name"),pUtil.getPropertiesData("lastName1"),"Response showing different Last name");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_032_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_032", "Register using email ,phone , phone , first name & last name of different user .");

		HashMap map =new HashMap();
		map.put("phone", excelUtil.getExcelData("Sheet1", 5, 2));
		map.put("email", excelUtil.getExcelData("Sheet1", 5, 1));
		map.put("first_name", excelUtil.getExcelData("Sheet1", 5, 3));
		map.put("last_name", excelUtil.getExcelData("Sheet1", 5, 4));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",10,1),"Response showing different message");
		softAssert.assertAll();
	}

	@Test
	public void  mIKO_AUTH_033_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_033", "Register using phone no & email of different user");
		HashMap map =new HashMap();
		map.put("phone", excelUtil.getExcelData("Sheet1", 5, 2));
		map.put("email", excelUtil.getExcelData("Sheet1", 5, 1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",10,1),"Response showing different message");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_034_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_034", "Register using Invalid HTTP method");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.Register);
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
	public void  mIKO_AUTH_035_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_035", "Register using Invalid end point");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different content type");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_036_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_036", "Register using content type as text");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.TEXT)
				.when()
				.post(EndPoints.Register);
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
	public void  mIKO_AUTH_037_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_037", "Register using content type as JavaScript");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType("application/javaScript")
				.when()
				.post(EndPoints.Register);
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
	public void  mIKO_AUTH_038_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_038", "Register using content type as HTML");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.HTML)
				.when()
				.post(EndPoints.Register);
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
	public void  mIKO_AUTH_039_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_039", "Register using content type as XML");
		HashMap map =new HashMap();
		map.put("phone", pUtil.getPropertiesData("mobileno1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.XML)
				.when()
				.post(EndPoints.Register);
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
	public void  mIKO_AUTH_040_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_040", "Register using less than 10 digit phone no");
		HashMap map =new HashMap();
		map.put("phone", excelUtil.getExcelData("Sheet1", 6,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.phone[0]"),excelUtil.getExcelData("Message",11,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_041_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_041", "Register using more than 10 digit phone no .");
		HashMap map =new HashMap();
		map.put("phone", excelUtil.getExcelData("Sheet1", 7,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.phone[0]"),excelUtil.getExcelData("Message",11,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_042_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_042", "Register using alphanumeric data in phone no .");
		HashMap map =new HashMap();
		map.put("phone", excelUtil.getExcelData("Sheet1", 8,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.phone[0]"),excelUtil.getExcelData("Message",11,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_043_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_043", "Register using phone no by not using any data(Blank String)");
		HashMap map =new HashMap();
		map.put("phone", "");

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.phone[0]"),excelUtil.getExcelData("Message",11,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_044_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_044", "Register using invalid email id.");
		HashMap map =new HashMap();
		map.put("email", excelUtil.getExcelData("Sheet1", 9,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.email[0]"),excelUtil.getExcelData("Message",12,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_045_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_045", "Register using alphanumeric data in email id");
		HashMap map =new HashMap();
		map.put("email", excelUtil.getExcelData("Sheet1", 10,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different error");
		ListnersClass.test.log(Status.INFO, "Verifying the email in the response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),excelUtil.getExcelData("Sheet1",10,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_046_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_046", "Register using special character data in email id .");
		HashMap map =new HashMap();
		map.put("email", excelUtil.getExcelData("Sheet1", 11,1));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"),excelUtil.getExcelData("Message",9,1),"Response showing different error");
		ListnersClass.test.log(Status.INFO, "Verifying the email in the response");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.email"),excelUtil.getExcelData("Sheet1",11,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_047_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_047", "Register using email by  using null data(Blank String)");
		HashMap map =new HashMap();
		map.put("email","");

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the error");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.email[0]"),excelUtil.getExcelData("Message",13,1),"Response showing different error");
		softAssert.assertAll();
	}
	@Test
	public void  mIKO_AUTH_048_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_048", "Register by providing valid data in email , phone no & lastname .");
		HashMap map =new HashMap();
		map.put("email",pUtil.getPropertiesData("emailId1"));
		map.put("phone",pUtil.getPropertiesData("mobileno1"));
		map.put("first_name","");
		map.put("last_name",excelUtil.getExcelData("Sheet1",5,4));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the error");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.first_name[0]"),excelUtil.getExcelData("Message",13,1),"Response showing different error");
		softAssert.assertAll();
	}
	
	@Test
	public void  mIKO_AUTH_049_RegisterTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_049", "Register by providing valid data in email , phone no & firstname .");
		HashMap map =new HashMap();
		map.put("email",pUtil.getPropertiesData("emailId1"));
		map.put("phone",pUtil.getPropertiesData("mobileno1"));
		map.put("first_name",excelUtil.getExcelData("Sheet1",5,3));
		map.put("last_name","");

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.Register);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Response showing different status code.");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Response showing different status message.");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different content type.");
		ListnersClass.test.log(Status.INFO, "Verifying the error");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.last_name[0]"),excelUtil.getExcelData("Message",13,1),"Response showing different error ." );
		softAssert.assertAll();
	}
	
}
