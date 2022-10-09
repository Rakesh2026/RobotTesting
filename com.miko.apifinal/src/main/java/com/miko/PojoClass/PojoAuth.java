package com.miko.PojoClass;

public class PojoAuth {
	String order_id;
	String auth_type;
	public PojoAuth(String order_id, String auth_type) {
		this.order_id = order_id;
		this.auth_type = auth_type;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}	
}
