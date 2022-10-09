package authentication;



import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
 * This class contains all the TC of Login(OrderId) feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class LoginOrderId extends BaseApiClass{
	@Test
	public void mIKO_AUTH_001_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_001", "Login using email OTP with valid order Id");

		//PojoAuth authpojo=new PojoAuth(pUtil.getPropertiesData("OrderId1"), pUtil.getPropertiesData("auth_type1"));
		//PojoAuth authpojo=new PojoAuth("1047", "email_otp");
		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message  ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.message"),excelUtil.getExcelData("Message",1,1),"Verifying the message");
		//softAssert.assertEquals(rUtil.getJsonData1(resp, "success"),true,"Verifying the success");

		//softAssert.assertEquals(rUtil.getJsonData1(resp, "success").toString(),"true" );
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_002_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_002", "Login using mobile OTP with valid order Id");

		//PojoAuth authpojo=new PojoAuth(pUtil.getPropertiesData("OrderId1"), pUtil.getPropertiesData("auth_type1"));
		//PojoAuth authpojo=new PojoAuth("1047", "email_otp");
		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId2"));
		map.put("auth_type", pUtil.getPropertiesData("authType2"));;

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the message ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "data.message"),excelUtil.getExcelData("Message",2,1),"Verifying the message");

		//softAssert.assertEquals(rUtil.getJsonData(resp, "success"),true,"Verifying the success");
		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_003_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_003", "Login using mobile OTP with valid order Id , valid auth type & invalid method");

		//PojoAuth authpojo=new PojoAuth(pUtil.getPropertiesData("OrderId1"), pUtil.getPropertiesData("auth_type1"));
		//PojoAuth authpojo=new PojoAuth("1047", "email_otp");
		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.get(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail ");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",20,1), "Verifying the detail");
		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_004_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_004", "Login using mobile OTP with valid order Id , valid auth type & invalid end point");

		//PojoAuth authpojo=new PojoAuth(pUtil.getPropertiesData("OrderId1"), pUtil.getPropertiesData("auth_type1"));
		//PojoAuth authpojo=new PojoAuth("1047", "email_otp");
		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID_Invalid);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Verifying the Status code");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Verifying the content type");

		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_005_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_005", "Login using mobile OTP with valid order Id , valid auth type & invalid Base URI");

		int num = jUtil.getRandomNumber(100);
		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(num+EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code, Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Verifying the content type");

		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_006_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_006", "Login using email OTP with invalid Order Id");

		int num = jUtil.getRandomNumber(10000);
		HashMap map=new HashMap();
		map.put("order_id", num);
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.order_id[0]"),excelUtil.getExcelData("Message",3,1),"Verifying the error message");
		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_007_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_007", "Login using email OTP with Order Id as alphanumeric");

		String OrderID = excelUtil.getExcelData("Sheet1", 1, 1);
		HashMap map=new HashMap();
		map.put("order_id", OrderID);
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.order_id[0]"),excelUtil.getExcelData("Message",3,1),"Verifying the error message");
		softAssert.assertAll();
	}



	@Test
	public void mIKO_AUTH_008_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_008", "Login using email OTP without Order_Id ");


		HashMap map=new HashMap();
		//map.put("order_id", OrderID);
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.order_id[0]"),excelUtil.getExcelData("Message",4,1),"Verifying the error message");
		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_009_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_009", "Login using email OTP without Order_Id & auth_type");

		Response resp = given()	
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.order_id[0]"),excelUtil.getExcelData("Message",4,1),"Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.auth_type[0]"),excelUtil.getExcelData("Message",4,1),"Verifying the error message");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_010_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_010", "Login using order Id & without using authtype");


		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("orderId1"));
		map.put("auth_type", "");

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.auth_type[0]"),excelUtil.getExcelData("Message",4,1),"Verifying the error message");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_011_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_011", "Login using order Id as null/Blank");


		HashMap map=new HashMap();
		map.put("order_id", "");
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.JSON)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),422,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unprocessable Entity"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the error message");
		softAssert.assertEquals(rUtil.getJsonData(resp, "errors.order_id[0]"),excelUtil.getExcelData("Message",4,1),"Verifying the error message");
		softAssert.assertAll();
	}

	@Test
	public void mIKO_AUTH_012_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_012", "Login using mobile OTP with valid order Id with content type as Text");


		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("authType1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.TEXT)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the details message");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_013_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_013", "Login using mobile OTP with valid order Id with content type as Java Script");


		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("authType1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType("application/javaScript")
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the details message");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_014_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_014", "Login using mobile OTP with valid order Id with content type as HTML");


		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("authType1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.HTML)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the details message");
		softAssert.assertAll();
	}
	@Test
	public void mIKO_AUTH_015_LoginOrderIdTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_AUTH_015", "Login using mobile OTP with valid order Id with content type as XML");


		HashMap map=new HashMap();
		map.put("order_id", pUtil.getPropertiesData("authType1"));
		map.put("auth_type", pUtil.getPropertiesData("authType1"));

		Response resp = given()
				.body(map)
				.contentType(ContentType.XML)
				.when()
				.post(EndPoints.logInOrderID);
		ValidatableResponse response=resp.then();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),415,"Verifying the Status code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unsupported Media Type"),"Verifying the status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Verifying the content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail message");
		softAssert.assertTrue(rUtil.getJsonData(resp, "detail").contains(excelUtil.getExcelData("Message",5,1)),"Verifying the details message");
		softAssert.assertAll();
	}
}