package com.cgcl.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cgcl.beans.PsxProfileBean;

public class ProfileRowMapper implements RowMapper<PsxProfileBean> {

	@Override
	public PsxProfileBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PsxProfileBean profileBean =new PsxProfileBean();
		profileBean.setActive(rs.getString("ACTIVE"));
		profileBean.setApprovalTs(rs.getDate("APPROVAL_TS"));
		profileBean.setApproved(rs.getString("APPROVED"));
		profileBean.setChecker(rs.getString("CHECKER"));
		profileBean.setMaker(rs.getString("MAKER"));
		profileBean.setMatchingRuleCSV(rs.getString("MATCHING_RULE_CSV"));
		profileBean.setProfileDescription(rs.getString("PROFILE_DESC"));
		profileBean.setProfileId(rs.getInt("PROFILE_ID"));
		profileBean.setProfileName(rs.getString("PROFILE_NAME"));
		profileBean.setRankingCSV(rs.getString("RANKING_CSV"));
		profileBean.setResidualsCSV(rs.getString("RESIDUAL_PARAMETERS"));
		profileBean.setScaleStringentCSV(rs.getString("SCALE_STRINGENT_CSV"));
		profileBean.setTenantId(rs.getString("TENANT_ID"));
		profileBean.setWeightagesCSV(rs.getString("WEIGHTAGES_CSV"));
		return profileBean;
	}

}
