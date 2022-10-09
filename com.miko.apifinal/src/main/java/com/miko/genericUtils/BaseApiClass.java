package com.miko.genericUtils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
/**
 * This class contains pre and post conditions for all API
 * @author Rakesh
 *
 */
public class BaseApiClass {


	public PropertyFileUtil pUtil=new PropertyFileUtil();
	public JavaUtil jUtil=new JavaUtil();
	public RestAssuredUtil rUtil=new RestAssuredUtil();
	public SoftAssert softAssert=new SoftAssert();
	public ExcelFileUtil excelUtil=new ExcelFileUtil();
	public static ReusableMethod reusableMethod=new ReusableMethod();
	public String token;
	//public String mobile;
	//public String OPT;
	
	
	
	
	
	@BeforeSuite
	public void bSConfiguration() {
		
		
		baseURI=pUtil.getPropertiesData("BaseURI");
		
	}
	@BeforeClass
	public void bcConfiguration() {
		
		//token=reusableMethod.getToken(pUtil.getPropertiesData("mobileno1"),pUtil.getPropertiesData("otp"));
		//token=reusableMethod.getToken(mobile,OPT);
	}
	 
    @AfterSuite
    public void aSConfiguration() {
    }
}
