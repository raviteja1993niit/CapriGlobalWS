package com.cgcl.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.cgcl.beans.PsxRequestBean;

public class PsxRequestRowMapper implements RowMapper<PsxRequestBean> {

	private static Logger logger = Logger.getLogger(PsxRequestRowMapper.class);
	
	@Override
	public PsxRequestBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PsxRequestBean psxRequestBean=new PsxRequestBean();
		psxRequestBean.setCustunqid(rs.getString("CUST_UNQ_ID"));
		psxRequestBean.setEnginepickuptime(rs.getTimestamp("ENGINE_PICKUP_TIME"));
		psxRequestBean.setEngineprocessedtime(rs.getTimestamp("ENGINE_PROCESSED_TIME"));
		psxRequestBean.setInserttime(rs.getTimestamp("INSERT_TIME"));
		psxRequestBean.setLchgtime(rs.getTimestamp("LCHGTIME"));
		psxRequestBean.setMatchcount(rs.getInt("MATCH_COUNT"));
		psxRequestBean.setOfflinematchcount(rs.getInt("OFFLINE_MATCH_COUNT"));
		psxRequestBean.setOnlinematchcount(rs.getInt("ONLINE_MATCH_COUNT"));
		psxRequestBean.setProcessdurationmillis(rs.getLong("PROCESS_DURATION_MILLIS")+"");
		psxRequestBean.setProfileid(rs.getInt("PROFILE_ID")+"");
		psxRequestBean.setPsxbatchid(rs.getString("PSX_BATCH_ID"));
		psxRequestBean.setQueuewaitingtimemillis(rs.getLong("QUEUE_WAITING_TIME_MILLIS")+"");
		psxRequestBean.setRequestId(rs.getString("REQUEST_ID"));
		psxRequestBean.setRequeststatus(rs.getString("REQUEST_STATUS"));
		psxRequestBean.setUserid(rs.getString("USERID"));
		psxRequestBean.setNegativeMatchCount(rs.getInt("NEGATIVE_MATCH_COUNT"));
		psxRequestBean.setNegativeOfflineMatchCount(rs.getInt("NEGATIVE_OFFLINE_MATCH_COUNT"));
		psxRequestBean.setAssignedUcicNo(rs.getString("ASSIGNED_UCIC_NO"));
		logger.info("Request Information :: "+psxRequestBean);
		return psxRequestBean;
	}

}
