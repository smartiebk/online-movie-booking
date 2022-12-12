package com.ombp.cloud.model.usermanagement;

import java.io.File;
import java.util.Date;

import com.ombp.cloud.model.BaseEntity;

public class AppUserDetails extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2076804826514254705L;

	private String firstName;
	private String lastName;
	private String city;
	private String address;
	private String state;
	private String altMobile;
	private String altEmail;
	private String imagePath;
	private File profilePic;
	private String fullNameOfUser;
	
	private Date dateOfBirth;
	
	private Integer appUserId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAltMobile() {
		return altMobile;
	}
	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}
	public String getAltEmail() {
		return altEmail;
	}
	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getFullNameOfUser(){
		return fullNameOfUser;
	}
	public void setFullNameOfUser(String fullNameOfUser) {
		this.fullNameOfUser = fullNameOfUser;
	}
	public File getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(File profilePic) {
		this.profilePic = profilePic;
	}
	
}
