package com.cgcl.beans;

import java.util.Date;

import lombok.Data;

@Data
public class PsxProfileBean {
	private Integer profileId;
	private String profileDescription;
	private String profileName;
	private String active;
	private String matchingRuleCSV;
	private String weightagesCSV;
	private String residualsCSV;
	private String scaleStringentCSV;
	private String maker;
	private String checker;
	private Date approvalTs;
	private String approved;
	private String tenantId;
	private String rankingCSV;
}
