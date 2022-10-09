package com.miko.genericUtils;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ReusableMethod {
	public RestAssuredUtil rUtil=new RestAssuredUtil();
	public void registerUsingPhone(String phoneNo) {
		HashMap map =new HashMap();
		map.put("phone", phoneNo);
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				  .post(EndPoints.Register);
	}
	
	public void registerUsingEmail(String EmailId) {
		HashMap map =new HashMap();
		map.put("email", EmailId);
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				  .post(EndPoints.Register);
	}
	public String getToken(String phoneNo,String Code) {
		HashMap map =new HashMap();
		map.put("phone", phoneNo);
		
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(map)
				.when()
				  .post(EndPoints.Register);	
		HashMap map2 =new HashMap();
		map2.put("phone", phoneNo);
		map2.put("code", Code);
		Response resp3 = given()
				.contentType(ContentType.JSON)
				.body(map2)
				.when()
				  .post(EndPoints.VerifyOTP);
			
		String token=rUtil.getJsonData(resp3, "data.token");
		return token;
	}


}
