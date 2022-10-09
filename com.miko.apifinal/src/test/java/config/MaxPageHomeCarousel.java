package config;



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
import java.util.List;

/**
 * This class contains all the TC of Load carousel on max page feature
 * @author Rakesh
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class MaxPageHomeCarousel extends BaseApiClass{
	@BeforeClass
	public void bcConfiguration() {
		
		token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
	}
	
	@Test
	public void   mIKO_CONFIG_020_MaxPageHomeCarouselTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_020", "Load carousel on max page");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.get(EndPoints.MaxPageHomecarousel);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		List<String> Carousel=resp.jsonPath().getList("data.title");
		softAssert.assertTrue(Carousel.size()>1, "Carousel showing blank list");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_021_MaxPageHomeCarouselTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_021", "Load carousel on max page with invalid HTTP method");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.MaxPageHomecarousel);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),405,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Method Not Allowed"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "detail"), excelUtil.getExcelData("Message",21,1), "Response showing different detail");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_022_MaxPageHomeCarouselTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_022", "Load carousel on max page with invalid end point");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token)
				.when()
				.post(EndPoints.MaxPageHomecarousel_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_023_MaxPageHomeCarouselTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_023", "Load carousel on max page with invalid authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()
				.header("Authorization",token+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.MaxPageHomecarousel);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",6,1), "Response showing diffenent detail");
		softAssert.assertAll();
	}
	@Test
	public void   mIKO_CONFIG_024_MaxPageHomeCarouselTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_CONFIG_024", "Load carousel on max page without authorization");
		//reusableMethod.registerUsingPhone(pUtil.getPropertiesData("mobileno1"));
		
		
		Response resp = given()

				.when()
				.get(EndPoints.MaxPageHomecarousel);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),401,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Unauthorized"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		ListnersClass.test.log(Status.INFO, "Verifying the detail");
		softAssert.assertEquals(rUtil.getJsonData(resp, "message"), excelUtil.getExcelData("Message",7,1), "Response showing diffenent detail");
		softAssert.assertAll();
	}
	
	
	
	 
	
}