package com.miko.PojoClass;

/**
 * This class contains all the constructor of POJO ,
 * which can be used for providing the data to the body .
 * @author Rakesh
 *
 */
public class PojoLib {
	String FirstName;
	String LastName;
	String EmailAddress;
	int PhoneNo;
	public PojoLib(String FirstName, String LastName, String EmailAddress, int PhoneNo) {
	
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.EmailAddress = EmailAddress;
		this.PhoneNo = PhoneNo;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public int getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		PhoneNo = phoneNo;
	}
	
}
