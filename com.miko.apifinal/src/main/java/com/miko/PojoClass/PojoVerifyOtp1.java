package com.miko.PojoClass;

public class PojoVerifyOtp1 {
	String phone;
	String code;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public PojoVerifyOtp1(String phone, String code) {
		this.phone = phone;
		this.code = code;
	}

}
