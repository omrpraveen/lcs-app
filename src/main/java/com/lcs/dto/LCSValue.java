package com.lcs.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("setOfStrings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LCSValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LCSValue() {
	}
	
	public LCSValue(String value) {
		this.value = value;
	}

	@JsonProperty("value")
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
