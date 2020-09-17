//package com.cgcl.service.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import com.cgcl.entities.PSX_REQUEST;
//
//public class RequestRowMapper implements RowMapper<PSX_REQUEST> {
//
//	@Override
//	public PSX_REQUEST mapRow(ResultSet rs, int rowNum) throws SQLException {
//		PSX_REQUEST request=new PSX_REQUEST();
//		request.setCustunqid(rs.getString("CUST_UNQ_ID"));
//		request.setMatchcount(rs.getInt("MATCH_COUNT"));
//		request.setRequestId(rs.getInt("REQUEST_ID"));
//		request.setRequeststatus(rs.getString("REQUEST_STATUS"));
//		return null;
//	}
//
//}
