package com.google.engineering.form;

import java.util.Date;
import java.util.List;

import com.google.common.collect.ImmutableList;

//simple pojo class, it represents form sent from the client
public class ServiceForm {
	// fields received from customer
	private String description;
	private String additionalInfo;
	private List<String> thingsToDo;
	private Date startDate;
	private Date endDate;

	private ServiceForm() {
	}

	// public constructor only for Unit Test
	public ServiceForm(String description, String additionalInfo,
			List<String> thingsToDo, Date startDate, Date endDate) {
		this.description = description;
		this.additionalInfo = additionalInfo;
		this.thingsToDo = thingsToDo == null ? null : ImmutableList
				.copyOf(thingsToDo);
		this.startDate = startDate == null ? null : new Date(
				startDate.getTime());
		this.endDate = endDate == null ? null : new Date(endDate.getTime());
	}

	public String getDescription() {
		return description;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public List<String> getThingsToDo() {
		return thingsToDo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

}
