package com.google.engineering.domain;

import static com.google.engineering.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.api.search.query.ExpressionParser.condExpr_return;
import com.google.common.collect.ImmutableList;
import com.google.engineering.form.ServiceForm;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.condition.IfNotDefault;

//this class is entity, it can be cached
@Entity
@Cache
public class Service {

	private static final String DEFAULT_DESCRIPTION = "Repair";

	private static final List<String> DEFAULT_THINGS_TO_DO = ImmutableList.of(
			"Default", "Overview");

	private List<String> workerKeysToExecute = new ArrayList<>(0);

	// id for the datastore, using automatic id assignment for entites of
	// Service class
	@Id
	private long id;

	private String description; // short description of service
	private String additionalInfo; // addidtional info from reporter/client

	// holds profile key as the parent
	@Parent
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<Profile> profileKey;

	// the userId of the customer which orders service
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private String customerUserId;

	// things that's need to be done
	@Index
	private List<String> thingsToDo;
	private List<String> thingsDone;

	// dates of service
	private Date startDate;
	private Date endDate;

	// month for queris
	@Index
	private int month;

	private Service() {
	}

	public Service(final long id, final String customerUserId) {// please add a
																// service form
		this.id = id;
		this.profileKey = Key.create(Profile.class, customerUserId);
		this.customerUserId = customerUserId;
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Profile> getProfileKey() {
		return profileKey;
	}

	// get a String version of the key
	public String getWebsafeKey() {
		return Key.create(profileKey, Service.class, id).getString();
	}

	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public String getCustomerUserId() {
		return customerUserId;
	}

	// returns customer display name, if there is no Profile, return his userId
	public String getCustomerDisplayName() {
		Profile customer = ofy().load().key(getProfileKey()).now();
		if (customer == null) {
			return customerUserId;
		} else {
			return customer.getDisplayName();
		}
	}

	public String getCustomerGplusId() {
		return customerUserId;
	}

	// return defensive copy of thingsToDo if not null
	public List<String> getThingsToDo() {
		return thingsToDo == null ? null : ImmutableList.copyOf(thingsToDo);
	}

	public void setThingsToDo(List<String> thingsToDo) {
		this.thingsToDo = thingsToDo;
	}

	public List<String> getThingsDone() {
		return thingsDone;
	}

	public void setThingsDone(List<String> thingsDone) {
		this.thingsDone = thingsDone;
	}

	public List<String> getWorkerKeysToExecute() {
		return ImmutableList.copyOf(workerKeysToExecute);
	}

	public void setWorkerKeysToExecute(List<String> workerKeysToExecute) {
		this.workerKeysToExecute = workerKeysToExecute;
	}

	public void addToWorkerKeysToExecute(String workerKey) {
		workerKeysToExecute.add(workerKey);
	}

	public void removeFromWorkerKeysToExecute(String workerKey) {
		if (workerKeysToExecute.contains(workerKey)) {
			workerKeysToExecute.remove(workerKey);
		} else {
			throw new IllegalArgumentException("Invalid worker user key: "
					+ workerKey);
		}
	}

	public String getDescription() {
		return description;
	}

	// returns a defensive copy of startDate if not null
	public Date getStartDate() {
		return startDate == null ? null : new Date(startDate.getTime());
	}

	// returns a defensive copy of endDate if not null
	public Date getEndDate() {
		return endDate == null ? null : new Date(endDate.getTime());
	}

	public int getMonth() {
		return month;
	}

	// please add void method 'updateWithServiceForm(ServiceForm serviceForm)

	public void updateWithServiceForm(ServiceForm serviceForm) {
		this.description = serviceForm.getDescription() == null ? DEFAULT_DESCRIPTION
				: serviceForm.getDescription();
		this.additionalInfo = serviceForm.getAdditionalInfo();
		List<String> thingsToDo = serviceForm.getThingsToDo();
		this.thingsToDo = thingsToDo == null ? null : DEFAULT_THINGS_TO_DO;
		Date startDate = serviceForm.getStartDate();
		this.startDate = serviceForm.getStartDate() == null ? null : new Date(startDate.getTime());
		Date endDate = serviceForm.getEndDate();
		this.endDate = serviceForm.getEndDate() == null ? null : new Date(endDate.getTime());
//		if () 
	}
	// please add toString method with basic info about service

}
