package com.trumpia.shortcodetester.model;

import javax.json.Json;
import javax.json.JsonObject;


public class APIMessagePayload {
	private String mobileNumber;
	private final String message = "testing code [$code] %s";
	private String code;
	private long uniqueID;

	public APIMessagePayload() {
		mobileNumber = "4043243857";
		code = "7008";
	}

	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber=mobileNumber;
	}
	public String getMessage() {
		return message;
	}
	public long getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(long uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String toString() {
		JsonObject value = Json.createObjectBuilder()
				.add("mobile_number", mobileNumber)
				.add("message", String.format(message,uniqueID))
				.add("code", code)
				.build();
		System.out.println(value.toString());
		return value.toString();
	}
}
