package com.cgcl.beans;

import lombok.Data;

@Data
public class MatchedAddressStrengths {

	private String matchedId;
	
	private String permanentMaxStrength;
	
	private String currentMaxStrength;
	
	private String residentMaxStrength;
	
	private String employeeMaxStrength;
	
	private String recordType;
}
