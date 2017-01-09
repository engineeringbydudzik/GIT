package com.google.engineering.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

//this class is entity, it can be cached
@Entity
@Cache
public class Profile {
	String displayName;
	String mainEmail;
	String phoneNumber;
	UserType userType;

	// indicate that the userId is to be used in the entity's key
	@Id
	String userId;
	// key list of services that are ordered
	private List<String> serviceKeysBooked = new ArrayList<>(0);

	// making default constructor private
	private Profile() {
	}

	public Profile(String displayName, String mainEmail, String userId,
			String phoneNumber, UserType userType) {
		this.displayName = displayName;
		this.mainEmail = mainEmail;
		this.userId = userId;
		this.userType = userType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getMainEmail() {
		return mainEmail;
	}

	public String getUserId() {
		return userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public List<String> getServiceKeysBooked() {
		return ImmutableList.copyOf(serviceKeysBooked);
	}

	public void addToServiceKeysBooked(String serviceKey) {
		serviceKeysBooked.add(serviceKey);
	}

	public void removeFromServiceKeysBooked(String serviceKey) {
		if (serviceKeysBooked.contains(serviceKey)) {
			serviceKeysBooked.remove(serviceKey);
		} else {
			throw new IllegalArgumentException("Invalid service key: "
					+ serviceKey);
		}
	}

	public void setServiceKeysBooked(List<String> serviceKeysBooked) {
		this.serviceKeysBooked = serviceKeysBooked;
	}

	public void update(String displayName, String phoneNumber) {
		if (displayName != null) {
			this.displayName = displayName;
		}
		if (phoneNumber != null) {
			this.phoneNumber = phoneNumber;
		}
	}

}
