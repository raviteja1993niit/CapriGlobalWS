package com.cgcl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.cgcl.beans.PsxRequestBean;

public class DBTest {
	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.88:3306/capriglobal?autoReconnect=false&rewriteBatchedStatements=true&useCursorFetch=true", "capriglobal_test","psx123");
			Statement mystatement = con.createStatement();
			ResultSet rs = mystatement.executeQuery("select MATCH_COUNT,OFFLINE_MATCH_COUNT,ONLINE_MATCH_COUNT from psx_request where request_id='720'");
			while (rs.next()) {
				System.out.println(rs.getInt("MATCH_COUNT") + "  " + rs.getInt("OFFLINE_MATCH_COUNT") + "  " + rs.getInt("ONLINE_MATCH_COUNT"));

			}

			
//				return jdbcTemplate.query(sql,new ResultSetExtractor<PsxRequestBean>() {
//					@Override
//					public PsxRequestBean extractData(ResultSet rs)throws SQLException, DataAccessException {
//						while (rs.next()) {
//						
//							psxRequestBean.setCustunqid(rs.getString("CUST_UNQ_ID"));
//							psxRequestBean.setEnginepickuptime(rs.getTimestamp("ENGINE_PICKUP_TIME"));
//							psxRequestBean.setEngineprocessedtime(rs.getTimestamp("ENGINE_PROCESSED_TIME"));
//							psxRequestBean.setInserttime(rs.getTimestamp("INSERT_TIME"));
//							psxRequestBean.setLchgtime(rs.getTimestamp("LCHGTIME"));
//							System.out.println("Match Count :: "+rs.getInt("MATCH_COUNT"));
//							psxRequestBean.setMatchcount(rs.getInt("MATCH_COUNT"));
//							System.out.println("OFFLINE_MATCH_COUNT"+rs.getLong("OFFLINE_MATCH_COUNT"));
//							System.out.println("ONLINE_MATCH_COUNT :: "+rs.getLong("ONLINE_MATCH_COUNT"));
//							psxRequestBean.setOfflinematchcount(rs.getInt("OFFLINE_MATCH_COUNT"));
//							psxRequestBean.setOnlinematchcount(rs.getInt("ONLINE_MATCH_COUNT"));
//							psxRequestBean.setProcessdurationmillis(rs.getInt("PROCESS_DURATION_MILLIS")+"");
//							psxRequestBean.setProfileid(rs.getInt("PROFILE_ID")+"");
//							psxRequestBean.setPsxbatchid(rs.getString("PSX_BATCH_ID"));
//							psxRequestBean.setQueuewaitingtimemillis(rs.getInt("QUEUE_WAITING_TIME_MILLIS")+"");
//							psxRequestBean.setRequestId(rs.getString("REQUEST_ID"));
//							psxRequestBean.setRequeststatus(rs.getString("REQUEST_STATUS"));
//							psxRequestBean.setUserid(rs.getString("USERID"));
//							psxRequestBean.setNegativeMatchCount(rs.getInt("NEGATIVE_MATCH_COUNT"));
//							psxRequestBean.setNegativeOfflineMatchCount(rs.getInt("NEGATIVE_OFFLINE_MATCH_COUNT"));
//						}
//						return psxRequestBean;
//					}},requestId);
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
