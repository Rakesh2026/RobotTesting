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
 * This class contains all the TC of Load carousel on max page feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class MaxPlanHomePageBanner extends BaseApiClass{
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	
	@Test
	public void   mIKO_CONFIG_030_MaxPlanHomePageBannerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_030", "Load max page after subscription");

		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.MaxPlanHomePageBanner);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		//List ReasonActual = new ArrayList<String>();
		List BannerList=resp.jsonPath().getList("data.image");
		softAssert.assertTrue(BannerList.size()>0, "Max page banner is not displaying");
		
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_031_MaxPlanHomePageBannerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_031", "Load max page after subscription with invalid HTTP method");
		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.MaxPlanHomePageBanner);
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
	public void   mIKO_CONFIG_032_MaxPlanHomePageBannerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_032", "Load max page after subscription with invalid end point");

		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.MaxPlanHomePageBanner_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	
	@Test
	public void   mIKO_CONFIG_029_MaxPlanHomePageBannerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_033", "Load max page after subscription without authorization");

		Response resp = given()
				.when()
				.get(EndPoints.MaxPlanHomePageBanner);
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
	@Test
	public void   mIKO_CONFIG_034_MaxPlanHomePageBannerTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_034", "Load max page after subscription with invalid authorization");

		Response resp = given()
				.header("Authorization",token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.MaxPlanHomePageBanner);
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

	
	
	 
	
}