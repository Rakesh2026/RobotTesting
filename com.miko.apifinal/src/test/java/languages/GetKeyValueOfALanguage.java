package languages;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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
 * This class contains all the TC to check all the key value of all string .
 * @author Rakesh
 * author
 *
 */

@Listeners(com.miko.genericUtils.ListnersClass.class)

public class GetKeyValueOfALanguage extends BaseApiClass{
	List idList = new ArrayList<String>();
	List LanguageList = new ArrayList<String>();
	@BeforeClass
	public void bcConfiguration() {
		Response resp0 = given()
				.when()
				.get(EndPoints.GetListOfLanguages);
		
		idList=resp0.jsonPath().getList("data.id");
		
		LanguageList=resp0.jsonPath().getList("data.title");
		
	}
	@Test
	public void   mIKO_LANG_004_GetKeyValueOfALanguageTests() {
		ListnersClass.test = ListnersClass.reports.createTest(" MIKO_LANG_004", "Get all key value pairs of all string for all language .");

		
		for(int i=0;i<idList.size();i++) {
		Response resp = given()
				.pathParam("language_id",idList.get(i))
				.when()
				.get(EndPoints.GetKeyValuesOfaLanguages);
		
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");

		softAssert.assertAll();
		}
	}
	@Test
	public void   mIKO_LANG_005_GetKeyValueOfALanguageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_005", "Get all key value pairs of a language with invalid HTTP method");

		Response resp = given()
				.pathParam("language_id",idList.get(1))
				.when()
				.post(EndPoints.GetKeyValuesOfaLanguages);
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
	public void    mIKO_LANG_006_GetKeyValueOfALanguageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_006", "Get all key value pairs of a language with invalid end point");

		Response resp = given()
				.pathParam("language_id",idList.get(1))
				.when()
				.get(EndPoints.GetKeyValuesOfaLanguages_Invalid);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_LANG_007_GetKeyValueOfALanguageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("mIKO_LANG_007", "Get all key value pairs of a language with invalid language Id");

		Response resp = given()
				.pathParam("language_id",idList.get(1)+""+jUtil.getRandomNumber(100))
				.when()
				.get(EndPoints.GetKeyValuesOfaLanguages);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		softAssert.assertNull(rUtil.getJsonData(resp, "data.code"), "title is displaying with invalid Id");
		softAssert.assertNull(rUtil.getJsonData(resp, "data.values"), "subtitle is displaying with invalid Id");
		softAssert.assertAll();
	}
	@Test
	public void    mIKO_LANG_008_GetKeyValueOfALanguageTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_008", "Get all key value pairs of a language by providing null in language id(with out providing any data )");

		Response resp = given()
				.pathParam("language_id","")
				.when()
				.get(EndPoints.GetKeyValuesOfaLanguages);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	 
	
}