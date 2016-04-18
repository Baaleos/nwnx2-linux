package org.baaleos.systems.lang;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslationResponse {
	@SerializedName("responseDetails")
	@Expose
	private Object responseDetails;
	@SerializedName("responseData")
	@Expose
	private ResponseData responseData;
	@SerializedName("responseStatus")
	@Expose
	private Integer responseStatus;

	/**
	* 
	* @return
	* The responseDetails
	*/
	public Object getResponseDetails() {
	return responseDetails;
	}

	/**
	* 
	* @param responseDetails
	* The responseDetails
	*/
	public void setResponseDetails(Object responseDetails) {
	this.responseDetails = responseDetails;
	}

	/**
	* 
	* @return
	* The responseData
	*/
	public ResponseData getResponseData() {
	return responseData;
	}

	/**
	* 
	* @param responseData
	* The responseData
	*/
	public void setResponseData(ResponseData responseData) {
	this.responseData = responseData;
	}

	/**
	* 
	* @return
	* The responseStatus
	*/
	public Integer getResponseStatus() {
	return responseStatus;
	}

	/**
	* 
	* @param responseStatus
	* The responseStatus
	*/
	public void setResponseStatus(Integer responseStatus) {
	this.responseStatus = responseStatus;
	}
	@Generated("org.jsonschema2pojo")
	public class ResponseData {

	@SerializedName("translatedText")
	@Expose
	private String translatedText;

	/**
	* 
	* @return
	* The translatedText
	*/
	public String getTranslatedText() {
	return translatedText;
	}

	/**
	* 
	* @param translatedText
	* The translatedText
	*/
	public void setTranslatedText(String translatedText) {
	this.translatedText = translatedText;
	}

	}
}