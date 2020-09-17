//package com.cgcl.entities;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Entity
//@Table(name = "psx_profiles")
//@Data
//public class Profile implements Serializable {
//
//	private static final long serialVersionUID = 2466793342603533308L;
//
//	@Id
//	@Column(name = "PROFILE_ID")
//	private Integer profileId;
//	@Column(name = "PROFILE_DESC")
//	private String profileDescription;
//	@Column(name = "PROFILE_NAME")
//	private String profileName;
//	@Column(name = "ACTIVE")
//	private String active;
//	@Column(name = "MATCHING_RULE_CSV", length = 4000)
//	private String matchingRuleCSV;
//	@Column(name = "WEIGHTAGES_CSV", length = 4000)
//	private String weightagesCSV;
//	@Column(name = "RESIDUAL_PARAMETERS", length = 4000)
//	private String residualsCSV;
//	@Column(name = "SCALE_STRINGENT_CSV", length = 4000)
//	private String scaleStringentCSV;
//	@Column(name = "MAKER")
//	private String maker;
//	@Column(name = "CHECKER")
//	private String checker;
//	@Column(name = "APPROVAL_TS")
//	private Date approvalTs;
//	@Column(name = "APPROVED")
//	private String approved;
//	@Column(name = "TENANT_ID")
//	private String tenantId;
//	@Column(name = "RANKING_CSV", length = 400)
//	private String rankingCSV;
//
//}
