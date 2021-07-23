package com.lcs.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LCSRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LCSRequestDto() {

	}

	@JsonProperty("setOfStrings")
	private List<LCSValue> setOfStrings;

	public List<LCSValue> getSetOfStrings() {
		return setOfStrings;
	}

	public void setSetOfStrings(List<LCSValue> setOfStrings) {
		this.setOfStrings = setOfStrings;
	}

}