package com.google.engineering.form;

public class ProfileForm {
	private String displayName;
	private String phoneNumber;

	private ProfileForm() {
	}

	//only for unit test
	public ProfileForm(String displayName, String phoneNumber) {
		this.displayName = displayName;
		this.phoneNumber = phoneNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}
