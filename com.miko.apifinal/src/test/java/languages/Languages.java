package languages;



import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.miko.PojoClass.PojoVerifyOtp1;
import com.miko.genericUtils.BaseApiClass;
import com.miko.genericUtils.EndPoints;
import com.miko.genericUtils.ListnersClass;
import com.miko.genericUtils.ReusableMethod;

import io.qameta.allure.Description;
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

public class Languages extends BaseApiClass{
	
	@Test(description="Testing")

  // @Description("Some detailed test description")
	public void  mIKO_LANG_001_LanguagesTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_001", "Get all the available languages");

		Response resp = given()
				.when()
				.get(EndPoints.GetListOfLanguages);
		ValidatableResponse response=resp.then();
		response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),200,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("OK"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.JSON.toString(),"Response showing different Content type");
		List<String> dashBoard=resp.jsonPath().getList("data.id");
		softAssert.assertTrue(dashBoard.size()>1, "Language list is not displaying .");
		List LanguageActual = new ArrayList<String>();
		LanguageActual=resp.jsonPath().getList("data.title");
		List LanguageExpected = new ArrayList<String>();
		for(int i=1;i<=6;i++) {
			LanguageExpected.add(excelUtil.getExcelData("Language",i,1));
		}
		softAssert.assertTrue(LanguageActual.containsAll(LanguageExpected), "Some different reason is displaying");

		softAssert.assertAll();
	}
	@Test
	public void   mIKO_LANG_002_LanguagesTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_002", "Get all the available languages with invalid HTTP method");

		Response resp = given()
				.when()
				.post(EndPoints.GetListOfLanguages);
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
	public void    mIKO_LANG_003_LanguagesTests() {
		ListnersClass.test = ListnersClass.reports.createTest("MIKO_LANG_003", "Get all the available languages with invalid end point");

		Response resp = given()
				.when()
				.get(EndPoints.GetListOfLanguages_Invalid);
		ValidatableResponse response=resp.then();
		//response.log().all();
		ListnersClass.test.log(Status.INFO, "Verified the status code , Status message & content type");
		softAssert.assertEquals(response.extract().statusCode(),404,"Response showing different Status Code");
		softAssert.assertTrue(response.extract().statusLine().contains("Not Found"),"Response showing different Status message");
		softAssert.assertEquals(response.extract().contentType().toString(), ContentType.HTML.toString(),"Response showing different Content type");
		softAssert.assertAll();
	}
	 
	
}