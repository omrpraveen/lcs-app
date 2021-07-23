package com.lcs.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LCSResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LCSResponseDto() {

	}

	public Set<LCSValue> getLcs() {
		return lcs;
	}

	public void setLcs(Set<LCSValue> lcs) {
		this.lcs = lcs;
	}

	@JsonProperty("lcs")
	private Set<LCSValue> lcs;

}
