package com.cgcl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cgcl.beans.ACE;
import com.cgcl.beans.CgclAceBlkBean;
import com.cgcl.beans.CgclDgBlkBean;
import com.cgcl.beans.CgclLoanDetailsBean;
import com.cgcl.beans.CgclReportInputOutputBean;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.PsxProfileBean;
import com.cgcl.beans.PsxRequestBean;
import com.cgcl.dao.CapriGlobalJdbcDao;
import com.cgcl.exception.DAOException;
import com.cgcl.service.impl.ProfileRowMapper;
import com.cgcl.service.impl.PsxRequestRowMapper;
import com.cgcl.utils.CapriGlobalHashMapBuilder;

@Repository
@PropertySource("classpath:dbQueries.properties")
public class CapriGlobalJdbcDaoImpl implements CapriGlobalJdbcDao {

	private static Logger logger = Logger.getLogger(CapriGlobalJdbcDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment environment;
	
	@Autowired
	private CapriGlobalHashMapBuilder capriGlobalHashMapBuilder;
	
	@Override
	public void saveDemographicRequest(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId)throws DAOException {
		try {
			Date dobDate = null;
			Date doiDate=null;
			if(customerRequest.getDemographicInformation().getDob() != null && !customerRequest.getDemographicInformation().getDob().isEmpty() && !customerRequest.getDemographicInformation().getDob().trim().equalsIgnoreCase("null"))
		    dobDate=new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDob());  
			if(customerRequest.getDemographicInformation().getDoi() != null && !customerRequest.getDemographicInformation().getDoi().isEmpty() && !customerRequest.getDemographicInformation().getDob().trim().equalsIgnoreCase("null"))
		    doiDate=new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDoi());  	
			String sql = environment.getProperty("insert.dg.blk.request.query");
	     	int i=	jdbcTemplate.update(sql, new Object[] {customerRequest.getDemographicInformation().getAadhar_no(),customerRequest.getDemographicInformation().getAccount_status(),customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname(),
	     	psxBatchId,customerRequest.getDemographicInformation().getCaste(),customerRequest.getDemographicInformation().getCibil_score(),customerRequest.getDemographicInformation().getCno(),customerRequest.getDemographicInformation().getCycNo(),customerRequest.getSourcecustomerid(),custUnqId,customerRequest.getCustomercategory(),customerRequest.getSourcecustomerid(),
			customerRequest.getDemographicInformation().getCustomertypecode(),"",customerRequest.getDemographicInformation().getDinno(),dobDate,doiDate,customerRequest.getDemographicInformation().getDrivinglicense(),"I_OR_U","MATCHING",customerRequest.getDemographicInformation().getFathername(),customerRequest.getDemographicInformation().getFirstname(),customerRequest.getDemographicInformation().getGender(),customerRequest.getDemographicInformation().getGstin(),customerRequest.getDemographicInformation().getHighesteducation(),
			timestamp,customerRequest.getDemographicInformation().getLan(),customerRequest.getDemographicInformation().getLastname(),timestamp,"",customerRequest.getDemographicInformation().getMartialstatus(),customerRequest.getMatchingruleprofile(),customerRequest.getDemographicInformation().getMiddlename(),"",customerRequest.getDemographicInformation().getMothername(),customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname(),
			"",customerRequest.getDemographicInformation().getPanno(),customerRequest.getDemographicInformation().getPassportno(),customerRequest.getDemographicInformation().getPrimaryoccupation(),customerRequest.getDemographicInformation().getProduct(),psxBatchId,customerRequest.getDemographicInformation().getCno(),customerRequest.getDemographicInformation().getReligion(),customerRequest.getRequestId(),
			customerRequest.getDemographicInformation().getResidencestatus(),customerRequest.getSourceapplicationid(),customerRequest.getSourceapplicationno(),customerRequest.getSourceauthenticationtoken(),customerRequest.getSourcecustomerid(),customerRequest.getSourcesystemname(),customerRequest.getDemographicInformation().getSpousename(),customerRequest.getDemographicInformation().getTanno(),customerRequest.getDemographicInformation().getTaxId(),customerRequest.getDemographicInformation().getTitle(),
		    customerRequest.getUcic(),customerRequest.getUcicType(),customerRequest.getDemographicInformation().getUid(),customerRequest.getUidType(),customerRequest.getDemographicInformation().getVoterid(),customerRequest.getDemographicInformation().getFatherFirstName(),customerRequest.getDemographicInformation().getFatherLastName()
			});
			
		 logger.info("Saved Customer DG Request Information ::: "+i+ " for the RequestId :: "+customerRequest.getRequestId());		
		} catch (Exception ex) {
			logger.error("Exception while Saving  Customer DG Request Information : "+ ex.getMessage());
			throw (new DAOException("Exception while Saving  Customer DG Request Information : "));
			//throw (new DAOException(ex.getMessage()));
		}

	}

	@Override
	public void saveAddressRequest(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId)throws DAOException {
		try {
			String sql = environment.getProperty("insert.ace.blk.request.query");
			int i[]={0};
		   List<ACE> aceList=capriGlobalHashMapBuilder.buildAceObject(customerRequest, timestamp, psxBatchId);
		   if(aceList !=null){
		   aceList.stream().forEach(ace->{
		   i[0]=i[0]+jdbcTemplate.update(sql, new Object[] {ace.getAddress(),ace.getAddressid(),ace.getAddresstype(),"",ace.getCity(),customerRequest.getSourcecustomerid(),custUnqId,
				   ace.getContact() ,ace.getContactType(),ace.getLandline(),ace.getLandlineType(),customerRequest.getSourcecustomerid(),"","I_OR_U",ace.getEmail(),ace.getEmailType(),"MATCHING",timestamp,ace.getLandline(),timestamp,"",ace.getContact(),"",ace.getPincode(),psxBatchId,customerRequest.getRequestId(),ace.getState()	
			});});}
		   
//			 if((customerRequest.getAddressInformation().getAddress0() != null && customerRequest.getAddressInformation().getAddress0() != "") || (customerRequest.getContactInformation().getContact0()!=null && customerRequest.getContactInformation().getContact0()!="" )){
//				  i=jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress0(),customerRequest.getAddressInformation().getAddressid0(),customerRequest.getAddressInformation().getAddresstype0(),"",customerRequest.getAddressInformation().getCity0(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+sourceSystemSCode,
//					customerRequest.getContactInformation().getContact0()!=null ? customerRequest.getContactInformation().getContact0():customerRequest.getContactInformation().getContact1() ,customerRequest.getContactInformation().getContactType0()!=null ? customerRequest.getContactInformation().getContactType0():customerRequest.getContactInformation().getContactType1(),customerRequest.getContactInformation().getLandline0()!=null?customerRequest.getContactInformation().getLandline0():customerRequest.getContactInformation().getLandline1(),customerRequest.getContactInformation().getLandlineType0()!=null?customerRequest.getContactInformation().getLandlineType0():customerRequest.getContactInformation().getLandlineType1(),customerRequest.getSourcecustomerid(),"","I_OR_U",customerRequest.getEmailInformation().getEmail0()!=null?customerRequest.getEmailInformation().getEmail0():customerRequest.getEmailInformation().getEmail1(),customerRequest.getEmailInformation().getEmailType0()!=null?customerRequest.getEmailInformation().getEmailType0():customerRequest.getEmailInformation().getEmailType1(),"MATCHING",timestamp,customerRequest.getContactInformation().getLandline0()!=null?customerRequest.getContactInformation().getLandline0():customerRequest.getContactInformation().getLandline1(),timestamp,"",customerRequest.getContactInformation().getContact0()!=null?customerRequest.getContactInformation().getContact0():customerRequest.getContactInformation().getContact1(),"",customerRequest.getAddressInformation().getPincode0(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState0()	
//			});
//			}
//			 if(customerRequest.getAddressInformation().getAddresstype1() != null && customerRequest.getAddressInformation().getAddresstype1() != ""){
//				 i=i+ jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress1(),customerRequest.getAddressInformation().getAddressid1(),customerRequest.getAddressInformation().getAddresstype1(),"",customerRequest.getAddressInformation().getCity1(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+sourceSystemSCode,
//							customerRequest.getContactInformation().getContact1()!=null ? customerRequest.getContactInformation().getContact1():customerRequest.getContactInformation().getContact0() ,customerRequest.getContactInformation().getContactType1()!=null ? customerRequest.getContactInformation().getContactType1():customerRequest.getContactInformation().getContactType0(),customerRequest.getContactInformation().getLandline1()!=null?customerRequest.getContactInformation().getLandline1():customerRequest.getContactInformation().getLandline0(),customerRequest.getContactInformation().getLandlineType1()!=null?customerRequest.getContactInformation().getLandlineType1():customerRequest.getContactInformation().getLandlineType0(),customerRequest.getSourcecustomerid(),"","I_OR_U",customerRequest.getEmailInformation().getEmail1()!=null?customerRequest.getEmailInformation().getEmail1():customerRequest.getEmailInformation().getEmail0(),customerRequest.getEmailInformation().getEmailType1()!=null?customerRequest.getEmailInformation().getEmailType1():customerRequest.getEmailInformation().getEmailType0(),"MATCHING",timestamp,customerRequest.getContactInformation().getLandline1()!=null?customerRequest.getContactInformation().getLandline1():customerRequest.getContactInformation().getLandline0(),timestamp,"",customerRequest.getContactInformation().getContact1()!=null?customerRequest.getContactInformation().getContact1():customerRequest.getContactInformation().getContact0(),"",customerRequest.getAddressInformation().getPincode1(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState1()	
//					});
//			 }
//			 if(customerRequest.getAddressInformation().getAddresstype2() != null && customerRequest.getAddressInformation().getAddresstype2() != ""){
//				 i=i+jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress2(),customerRequest.getAddressInformation().getAddressid2(),customerRequest.getAddressInformation().getAddresstype2(),"",customerRequest.getAddressInformation().getCity2(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+sourceSystemSCode,
//					"","","","",customerRequest.getSourcecustomerid(),"","I_OR_U","","","MATCHING",timestamp,"",timestamp,"","","",customerRequest.getAddressInformation().getPincode2(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState2()	
//			});
//			 }
//			 if(customerRequest.getAddressInformation().getAddresstype3() != null && customerRequest.getAddressInformation().getAddresstype3() != ""){
//				 i=i+jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress3(),customerRequest.getAddressInformation().getAddressid3(),customerRequest.getAddressInformation().getAddresstype3(),"",customerRequest.getAddressInformation().getCity3(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+sourceSystemSCode,
//					"","","","",customerRequest.getSourcecustomerid(),"","I_OR_U","","","MATCHING",timestamp,"",timestamp,"","","",customerRequest.getAddressInformation().getPincode3(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState3()	
//			});
//			 }
			 logger.info("Saved Customer Address Request Information ::: "+i[0]+ " for the RequestId :: "+customerRequest.getRequestId());		

		 //}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception while Saving  Customer Address Request Information  "+ ex.getMessage());
			throw (new DAOException("Exception while Saving  Customer Address Request Information  "));
			//throw (new DAOException(ex.getMessage()));
		}

	}

	@Override
	public void saveDemographicIntraday(CustomerRequestInformation customerRequest,Date date,String psxBatchId,boolean isTempUcic,String custUnqId)throws DAOException {
		try {
			 Timestamp timestamp=new Timestamp(date.getTime());
//			logger.info("While Saving the DG Intraday Info LchgTime "+timestamp);
			Date dobDate = null;
			Date doiDate=null;
//			logger.info("Before Setting >>> "+customerRequest.getDemographicInformation().getDob()+" :::: "+customerRequest.getDemographicInformation().getDoi());
			if(customerRequest.getDemographicInformation().getDob() != null && !customerRequest.getDemographicInformation().getDob().isEmpty() && !customerRequest.getDemographicInformation().getDob().trim().equalsIgnoreCase("null")){
			    String[] str=customerRequest.getDemographicInformation().getDob().trim().split("-");
			    ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
				if(list!=null && list.get(0).length() >2){
				customerRequest.getDemographicInformation().setDob(castDates(customerRequest.getDemographicInformation().getDob()));
			    }else{
			    	customerRequest.getDemographicInformation().setDob(customerRequest.getDemographicInformation().getDob());
			    }
				
//				logger.info("Before Setting DOB >>> "+customerRequest.getDemographicInformation().getDob());
				
				dobDate=new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDob()); 
		    
			}
			if(customerRequest.getDemographicInformation().getDoi() != null && !customerRequest.getDemographicInformation().getDoi().isEmpty() && !customerRequest.getDemographicInformation().getDoi().trim().equalsIgnoreCase("null")){
				 String[] str=customerRequest.getDemographicInformation().getDoi().trim().split("-");	
				 ArrayList<String> list = new ArrayList<>(Arrays.asList(str));
				if(list!=null && list.get(0).length() >2){
					customerRequest.getDemographicInformation().setDoi(castDates(customerRequest.getDemographicInformation().getDoi()));
				    }else{
				    	customerRequest.getDemographicInformation().setDoi(customerRequest.getDemographicInformation().getDoi());
				    }
				
//				logger.info("Before Setting DOI >>> "+customerRequest.getDemographicInformation().getDoi());
				doiDate=new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDoi());  	
			}
//			logger.info("Intraday dob & doi >>> "+dobDate +" ::: "+doiDate+ ":::"+timestamp);
			String sql = environment.getProperty("insert.dg.blk.intraday.query");
	     	int i=	jdbcTemplate.update(sql, new Object[] {customerRequest.getDemographicInformation().getAadhar_no(),customerRequest.getDemographicInformation().getAccount_status(),customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname(),
	     	psxBatchId,customerRequest.getDemographicInformation().getCaste(),customerRequest.getDemographicInformation().getCibil_score(),customerRequest.getDemographicInformation().getCno(),customerRequest.getDemographicInformation().getCycNo(),customerRequest.getSourcecustomerid(),custUnqId,customerRequest.getCustomercategory(),customerRequest.getSourcecustomerid(),
			customerRequest.getDemographicInformation().getCustomertypecode(),"",customerRequest.getDemographicInformation().getDinno(),dobDate,doiDate,customerRequest.getDemographicInformation().getDrivinglicense(),"I_OR_U","INTRADAY",customerRequest.getDemographicInformation().getFathername(),customerRequest.getDemographicInformation().getFirstname(),customerRequest.getDemographicInformation().getGender(),customerRequest.getDemographicInformation().getGstin(),customerRequest.getDemographicInformation().getHighesteducation(),
			timestamp,customerRequest.getDemographicInformation().getLan(),customerRequest.getDemographicInformation().getLastname(),timestamp,"",customerRequest.getDemographicInformation().getMartialstatus(),customerRequest.getMatchingruleprofile(),customerRequest.getDemographicInformation().getMiddlename(),"",customerRequest.getDemographicInformation().getMothername(),customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname(),
			"",customerRequest.getDemographicInformation().getPanno(),customerRequest.getDemographicInformation().getPassportno(),customerRequest.getDemographicInformation().getPrimaryoccupation(),customerRequest.getDemographicInformation().getProduct(),psxBatchId,customerRequest.getDemographicInformation().getCno(),customerRequest.getDemographicInformation().getReligion(),customerRequest.getRequestId(),
			customerRequest.getDemographicInformation().getResidencestatus(),customerRequest.getSourceapplicationid(),customerRequest.getSourceapplicationno(),customerRequest.getSourceauthenticationtoken(),customerRequest.getSourcecustomerid(),customerRequest.getSourcesystemname(),customerRequest.getDemographicInformation().getSpousename(),customerRequest.getDemographicInformation().getTanno(),customerRequest.getDemographicInformation().getTaxId(),customerRequest.getDemographicInformation().getTitle(),
			isTempUcic == true ? "TEMP_"+customerRequest.getUcic():customerRequest.getUcic(),customerRequest.getUcicType(),customerRequest.getDemographicInformation().getUid(),customerRequest.getUidType(),customerRequest.getDemographicInformation().getVoterid(),customerRequest.getDemographicInformation().getFatherFirstName(),customerRequest.getDemographicInformation().getFatherLastName()
			});
			
		 logger.info("Saved Customer DG Intraday Information ::: "+i+ " for the RequestId :: "+customerRequest.getRequestId());	
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception while Saving  Customer DG Information: "+ ex.getMessage());
			throw (new DAOException("Exception while Saving  Customer DG Information: "));
			//	throw (new DAOException(ex.getMessage()));
		}

	}
    public String castDates(String dateStr) throws ParseException{
    //	String dateStr = "Mon Jun 18 00:00:00 IST 2012";
    //	DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = (Date)formatter.parse(dateStr);
    	logger.info(date);        

    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	String formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
    	logger.info("formatedDate : " + formatedDate);
		return formatedDate; 
    }
	@Override
	public void saveAddressIntraday(CustomerRequestInformation customerRequest,Date date,String psxBatchId,String custUnqId)throws DAOException {
		try {
			 Timestamp timestamp=new Timestamp(date.getTime());
			logger.info("While Saving the ACE Intraday Info LchgTime "+timestamp);
			String sql = environment.getProperty("insert.ace.blk.intraday.query");
			int i[]={0};
			   List<ACE> aceList=capriGlobalHashMapBuilder.buildAceObject(customerRequest, timestamp, psxBatchId);
			   if(aceList !=null){
			   aceList.stream().forEach(ace->{
			   i[0]=i[0]+jdbcTemplate.update(sql, new Object[] {ace.getAddress(),ace.getAddressid(),ace.getAddresstype(),"",ace.getCity(),customerRequest.getSourcecustomerid(),custUnqId,
					   ace.getContact() ,ace.getContactType(),ace.getLandline(),ace.getLandlineType(),customerRequest.getSourcecustomerid(),"","I_OR_U",ace.getEmail(),ace.getEmailType(),"INTRADAY",timestamp,ace.getLandline(),timestamp,"",ace.getContact(),"",ace.getPincode(),psxBatchId,customerRequest.getRequestId(),ace.getState()	
				});});}
			   		
			
//			 if(customerRequest.getAddressInformation().getAddresstype0() != null && customerRequest.getAddressInformation().getAddresstype0() != ""){
//					i=jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress0(),customerRequest.getAddressInformation().getAddressid0(),customerRequest.getAddressInformation().getAddresstype0(),"",customerRequest.getAddressInformation().getCity0(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+customerRequest.getSourcesystemname(),
//							customerRequest.getContactInformation().getContact0()!=null ? customerRequest.getContactInformation().getContact0():customerRequest.getContactInformation().getContact1() ,customerRequest.getContactInformation().getContactType0()!=null ? customerRequest.getContactInformation().getContactType0():customerRequest.getContactInformation().getContactType1(),customerRequest.getContactInformation().getLandline0()!=null?customerRequest.getContactInformation().getLandline0():customerRequest.getContactInformation().getLandline1(),customerRequest.getContactInformation().getLandlineType0()!=null?customerRequest.getContactInformation().getLandlineType0():customerRequest.getContactInformation().getLandlineType1(),customerRequest.getSourcecustomerid(),"","I_OR_U",customerRequest.getEmailInformation().getEmail0()!=null?customerRequest.getEmailInformation().getEmail0():customerRequest.getEmailInformation().getEmail1(),customerRequest.getEmailInformation().getEmailType0()!=null?customerRequest.getEmailInformation().getEmailType0():customerRequest.getEmailInformation().getEmailType1(),"INTRADAY",timestamp,customerRequest.getContactInformation().getLandline0()!=null?customerRequest.getContactInformation().getLandline0():customerRequest.getContactInformation().getLandline1(),timestamp,"",customerRequest.getContactInformation().getContact0()!=null?customerRequest.getContactInformation().getContact0():customerRequest.getContactInformation().getContact1(),"",customerRequest.getAddressInformation().getPincode0(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState0()	
//					});
//					}
//					 if(customerRequest.getAddressInformation().getAddresstype1() != null && customerRequest.getAddressInformation().getAddresstype1() != ""){
//					i=i+jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress1(),customerRequest.getAddressInformation().getAddressid1(),customerRequest.getAddressInformation().getAddresstype1(),"",customerRequest.getAddressInformation().getCity1(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+customerRequest.getSourcesystemname(),
//									customerRequest.getContactInformation().getContact1()!=null ? customerRequest.getContactInformation().getContact1():customerRequest.getContactInformation().getContact0() ,customerRequest.getContactInformation().getContactType1()!=null ? customerRequest.getContactInformation().getContactType1():customerRequest.getContactInformation().getContactType0(),customerRequest.getContactInformation().getLandline1()!=null?customerRequest.getContactInformation().getLandline1():customerRequest.getContactInformation().getLandline0(),customerRequest.getContactInformation().getLandlineType1()!=null?customerRequest.getContactInformation().getLandlineType1():customerRequest.getContactInformation().getLandlineType0(),customerRequest.getSourcecustomerid(),"","I_OR_U",customerRequest.getEmailInformation().getEmail1()!=null?customerRequest.getEmailInformation().getEmail1():customerRequest.getEmailInformation().getEmail0(),customerRequest.getEmailInformation().getEmailType1()!=null?customerRequest.getEmailInformation().getEmailType1():customerRequest.getEmailInformation().getEmailType0(),"INTRADAY",timestamp,customerRequest.getContactInformation().getLandline1()!=null?customerRequest.getContactInformation().getLandline1():customerRequest.getContactInformation().getLandline0(),timestamp,"",customerRequest.getContactInformation().getContact1()!=null?customerRequest.getContactInformation().getContact1():customerRequest.getContactInformation().getContact0(),"",customerRequest.getAddressInformation().getPincode1(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState1()	
//							});
//					 }
//					 if(customerRequest.getAddressInformation().getAddresstype2() != null && customerRequest.getAddressInformation().getAddresstype2() != ""){
//						 i=i+jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress2(),customerRequest.getAddressInformation().getAddressid2(),customerRequest.getAddressInformation().getAddresstype2(),"",customerRequest.getAddressInformation().getCity2(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+customerRequest.getSourcesystemname(),
//							"","","","",customerRequest.getSourcecustomerid(),"","I_OR_U","","","INTRADAY",timestamp,"",timestamp,"","","",customerRequest.getAddressInformation().getPincode2(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState2()	
//					});
//					 }
//					 if(customerRequest.getAddressInformation().getAddresstype3() != null && customerRequest.getAddressInformation().getAddresstype3() != ""){
//						 i=i+jdbcTemplate.update(sql, new Object[] {customerRequest.getAddressInformation().getAddress3(),customerRequest.getAddressInformation().getAddressid3(),customerRequest.getAddressInformation().getAddresstype3(),"",customerRequest.getAddressInformation().getCity3(),customerRequest.getSourcecustomerid(),customerRequest.getSourcecustomerid()+"_"+customerRequest.getSourcesystemname(),
//							"","","","",customerRequest.getSourcecustomerid(),"","I_OR_U","","","INTRADAY",timestamp,"",timestamp,"","","",customerRequest.getAddressInformation().getPincode3(),psxBatchId,customerRequest.getRequestId(),customerRequest.getAddressInformation().getState3()	
//					});
//					 }
//					 
					 logger.info("Saved Customer ACE Intraday Information ::: "+i[0]+ " for the RequestId :: "+customerRequest.getRequestId());	
		} catch (Exception ex) {
			logger.error("Exception while Saving  Customer Address Intraday Information   "+ ex.getMessage());
			throw (new DAOException("Exception while Saving  Customer Address Intraday Information   "));
			//	throw (new DAOException(ex.getMessage()));
		}

	}

	@Override
	public PsxProfileBean getProfileById(int profileId) throws DAOException {
		logger.info("Inside getting Profile Information " + profileId);
		PsxProfileBean profileBean;
		String sql = environment.getProperty("select.psx.profile.query");
		try {
			profileBean = jdbcTemplate.queryForObject(sql,new Object[] { profileId }, new ProfileRowMapper());

		} catch (Exception e) {
			logger.error("Exception while getting Profile Information: "+ e.getMessage());
			throw (new DAOException("MATCHING_PROFILE_ID is not Valid"));
		}
		return profileBean;
	}

	@Override
	public List<CgclReportInputOutputBean> getAllMatchedCustomerDetails(long requestId) throws DAOException {
		String sql=environment.getProperty("select.report.io.details.query");
		List<CgclReportInputOutputBean> cgclReportInputOutputBeanList=new ArrayList<>();
		try{
			
			    cgclReportInputOutputBeanList=jdbcTemplate.query(sql,( rs,rowNum) -> {
				CgclReportInputOutputBean cgclReportInfoDetailsBean=new CgclReportInputOutputBean();
				cgclReportInfoDetailsBean.setAadharno(rs.getString("AADHAR_NO"));
				cgclReportInfoDetailsBean.setAccountstatus(rs.getString("ACCOUNT_STATUS"));
				cgclReportInfoDetailsBean.setAddress0(rs.getString("ADDRESS_0"));
				cgclReportInfoDetailsBean.setAddress1(rs.getString("ADDRESS_1"));
				cgclReportInfoDetailsBean.setAddress2(rs.getString("ADDRESS_2"));
				cgclReportInfoDetailsBean.setAddress3(rs.getString("ADDRESS_3"));
				cgclReportInfoDetailsBean.setAddress4(rs.getString("ADDRESS_4"));
				cgclReportInfoDetailsBean.setAddressid0(rs.getLong("ADDRESS_ID_0")+"");
				cgclReportInfoDetailsBean.setAddressid1(rs.getLong("ADDRESS_ID_1")+"");
				cgclReportInfoDetailsBean.setAddressid2(rs.getLong("ADDRESS_ID_2")+"");
				cgclReportInfoDetailsBean.setAddressid3(rs.getLong("ADDRESS_ID_3")+"");
				cgclReportInfoDetailsBean.setAddressid4(rs.getLong("ADDRESS_ID_4")+"");
				cgclReportInfoDetailsBean.setAddresstype0(rs.getString("ADDRESS_TYPE_0"));
				cgclReportInfoDetailsBean.setAddresstype1(rs.getString("ADDRESS_TYPE_1"));
				cgclReportInfoDetailsBean.setAddresstype2(rs.getString("ADDRESS_TYPE_2"));
				cgclReportInfoDetailsBean.setAddresstype3(rs.getString("ADDRESS_TYPE_3"));
				cgclReportInfoDetailsBean.setAddresstype4(rs.getString("ADDRESS_TYPE_4"));
				cgclReportInfoDetailsBean.setApplicantfullname(rs.getString("APPLICANT_FULL_NAME"));
				cgclReportInfoDetailsBean.setBatchid(rs.getString("BATCH_ID"));
				cgclReportInfoDetailsBean.setCibilscore(rs.getString("CIBIL_SCORE"));
				cgclReportInfoDetailsBean.setCin(rs.getString("CIN"));
				cgclReportInfoDetailsBean.setCity0(rs.getString("CITY_0"));
				cgclReportInfoDetailsBean.setCity1(rs.getString("CITY_1"));
				cgclReportInfoDetailsBean.setCity2(rs.getString("CITY_2"));
				cgclReportInfoDetailsBean.setCity3(rs.getString("CITY_3"));
				cgclReportInfoDetailsBean.setCity4(rs.getString("CITY_4"));
				cgclReportInfoDetailsBean.setCkycno(rs.getString("CKYC_NO"));
				cgclReportInfoDetailsBean.setCscore(rs.getString("CSCORE"));
				cgclReportInfoDetailsBean.setCustid(rs.getString("CUST_ID"));
				cgclReportInfoDetailsBean.setCustunqid(rs.getString("CUST_UNQ_ID"));
				cgclReportInfoDetailsBean.setCustomercategorytype(rs.getString("CUSTOMER_CATEGORY_TYPE"));
				cgclReportInfoDetailsBean.setCustomercontact0(rs.getString("CUSTOMER_CONTACT_0"));
				cgclReportInfoDetailsBean.setCustomercontact1(rs.getString("CUSTOMER_CONTACT_1"));
				cgclReportInfoDetailsBean.setCustomercontact2(rs.getString("CUSTOMER_CONTACT_2"));
				cgclReportInfoDetailsBean.setCustomercontact3(rs.getString("CUSTOMER_CONTACT_3"));
				cgclReportInfoDetailsBean.setCustomercontact4(rs.getString("CUSTOMER_CONTACT_4"));
				cgclReportInfoDetailsBean.setCustomercontacttype0(rs.getString("CUSTOMER_CONTACT_TYPE_0"));
				cgclReportInfoDetailsBean.setCustomercontacttype1(rs.getString("CUSTOMER_CONTACT_TYPE_1"));
				cgclReportInfoDetailsBean.setCustomercontacttype2(rs.getString("CUSTOMER_CONTACT_TYPE_2"));
				cgclReportInfoDetailsBean.setCustomercontacttype3(rs.getString("CUSTOMER_CONTACT_TYPE_3"));
				cgclReportInfoDetailsBean.setCustomercontacttype4(rs.getString("CUSTOMER_CONTACT_TYPE_4"));
				cgclReportInfoDetailsBean.setCustomerlandline0(rs.getString("CUSTOMER_LANDLINE_0"));
				cgclReportInfoDetailsBean.setCustomerlandline1(rs.getString("CUSTOMER_LANDLINE_1"));
				cgclReportInfoDetailsBean.setCustomerlandline2(rs.getString("CUSTOMER_LANDLINE_2"));
				cgclReportInfoDetailsBean.setCustomerlandline3(rs.getString("CUSTOMER_LANDLINE_3"));
				cgclReportInfoDetailsBean.setCustomerlandline4(rs.getString("CUSTOMER_LANDLINE_4"));
				cgclReportInfoDetailsBean.setCustomerlandlinetype0(rs.getString("CUSTOMER_LANDLINE_TYPE_0"));
				cgclReportInfoDetailsBean.setCustomerlandlinetype1(rs.getString("CUSTOMER_LANDLINE_TYPE_1"));
				cgclReportInfoDetailsBean.setCustomerlandlinetype2(rs.getString("CUSTOMER_LANDLINE_TYPE_2"));
				cgclReportInfoDetailsBean.setCustomerlandlinetype3(rs.getString("CUSTOMER_LANDLINE_TYPE_3"));
				cgclReportInfoDetailsBean.setCustomerlandlinetype4(rs.getString("CUSTOMER_LANDLINE_TYPE_4"));
				cgclReportInfoDetailsBean.setCustomerno(rs.getString("CUSTOMER_NO"));
				cgclReportInfoDetailsBean.setCustomerno0(rs.getString("CUSTOMER_NO_0"));
				cgclReportInfoDetailsBean.setCustomerno1(rs.getString("CUSTOMER_NO_1"));
				cgclReportInfoDetailsBean.setCustomerno2(rs.getString("CUSTOMER_NO_2"));
				cgclReportInfoDetailsBean.setCustomerno3(rs.getString("CUSTOMER_NO_3"));
				cgclReportInfoDetailsBean.setCustomerno4(rs.getString("CUSTOMER_NO_4"));
				cgclReportInfoDetailsBean.setCustomertypecode(rs.getString("CUSTOMER_TYPE_CODE"));
				cgclReportInfoDetailsBean.setCibilscore(rs.getString("CIBIL_SCORE"));
				cgclReportInfoDetailsBean.setDealidappid(rs.getString("DEAL_ID_APP_ID"));
				cgclReportInfoDetailsBean.setDin(rs.getString("DIN"));
				cgclReportInfoDetailsBean.setDob(rs.getDate("DOB"));
				cgclReportInfoDetailsBean.setDoi(rs.getDate("DOI"));
				cgclReportInfoDetailsBean.setDrivinglicno(rs.getString("DRIVING_LIC_NO"));
				cgclReportInfoDetailsBean.setDuiflag(rs.getString("DUIFLAG"));
				cgclReportInfoDetailsBean.setEmail0(rs.getString("EMAIL_0"));
				cgclReportInfoDetailsBean.setEmail1(rs.getString("EMAIL_1"));
				cgclReportInfoDetailsBean.setEmail2(rs.getString("EMAIL_2"));
				cgclReportInfoDetailsBean.setEmail3(rs.getString("EMAIL_3"));
				cgclReportInfoDetailsBean.setEmail4(rs.getString("EMAIL_4"));
				cgclReportInfoDetailsBean.setEmailtype0(rs.getString("EMAIL_TYPE_0"));
				cgclReportInfoDetailsBean.setEmailtype1(rs.getString("EMAIL_TYPE_1"));
				cgclReportInfoDetailsBean.setEmailtype2(rs.getString("EMAIL_TYPE_2"));
				cgclReportInfoDetailsBean.setEmailtype3(rs.getString("EMAIL_TYPE_3"));
				cgclReportInfoDetailsBean.setEmailtype4(rs.getString("EMAIL_TYPE_4"));
				cgclReportInfoDetailsBean.setEventtype(rs.getString("EVENTTYPE"));
				cgclReportInfoDetailsBean.setFathername(rs.getString("FATHER_NAME"));
				cgclReportInfoDetailsBean.setFirstname(rs.getString("FIRST_NAME"));
				cgclReportInfoDetailsBean.setGender(rs.getString("GENDER"));
				cgclReportInfoDetailsBean.setGstin(rs.getString("GSTIN"));
				cgclReportInfoDetailsBean.setHighesteducation(rs.getString("HIGHEST_EDUCATION"));
				cgclReportInfoDetailsBean.setInputpsxbatchid(rs.getString("INPUT_PSX_BATCH_ID"));
				cgclReportInfoDetailsBean.setInserttime(rs.getDate("INSERT_TIME"));
				cgclReportInfoDetailsBean.setLastname(rs.getString("LAST_NAME"));
				cgclReportInfoDetailsBean.setLchgtime(rs.getDate("LCHGTIME"));
				cgclReportInfoDetailsBean.setLeadid(rs.getString("LEAD_ID"));
				cgclReportInfoDetailsBean.setMaritalstatus(rs.getString("MARITAL_STATUS"));
				cgclReportInfoDetailsBean.setMatchtype(rs.getString("MATCH_TYPE"));
				cgclReportInfoDetailsBean.setMiddlename(rs.getString("MIDDLE_NAME"));
				cgclReportInfoDetailsBean.setMothermaidenname(rs.getString("MOTHER_MAIDEN_NAME"));
				cgclReportInfoDetailsBean.setMothername(rs.getString("MOTHER_NAME"));
				cgclReportInfoDetailsBean.setMrname(rs.getString("MRNAME"));
				cgclReportInfoDetailsBean.setName(rs.getString("NAME"));
				cgclReportInfoDetailsBean.setOldpsxbatchid(rs.getString("OLD_PSX_BATCH_ID"));
				cgclReportInfoDetailsBean.setPanno(rs.getString("PAN_NO"));
				cgclReportInfoDetailsBean.setPassportno(rs.getString("PASSPORT_NO"));
				cgclReportInfoDetailsBean.setPincode0(rs.getString("PINCODE_0"));
				cgclReportInfoDetailsBean.setPincode1(rs.getString("PINCODE_1"));
				cgclReportInfoDetailsBean.setPincode2(rs.getString("PINCODE_2"));
				cgclReportInfoDetailsBean.setPincode3(rs.getString("PINCODE_3"));
				cgclReportInfoDetailsBean.setPincode4(rs.getString("PINCODE_4"));
				cgclReportInfoDetailsBean.setPrimaryoccupation(rs.getString("PRIMARY_OCCUPATION"));
				cgclReportInfoDetailsBean.setPsxbatchid(rs.getString("PSX_BATCH_ID"));
				cgclReportInfoDetailsBean.setPsxtargetfcb(rs.getString("PSX_TARGET_FCB"));
				cgclReportInfoDetailsBean.setRank1(rs.getString("RANK1"));
				cgclReportInfoDetailsBean.setRecordtype(rs.getString("RECORD_TYPE"));
				cgclReportInfoDetailsBean.setRequestid(rs.getLong("REQUEST_ID"));
				cgclReportInfoDetailsBean.setResidentstatus(rs.getString("RESIDENT_STATUS"));
				cgclReportInfoDetailsBean.setScaletype(rs.getString("SCALE_TYPE"));
				cgclReportInfoDetailsBean.setSourcesystem(rs.getString("SOURCE_SYSTEM"));
				cgclReportInfoDetailsBean.setSpousename(rs.getString("SPOUSE_NAME"));
				cgclReportInfoDetailsBean.setState0(rs.getString("STATE_0"));
				cgclReportInfoDetailsBean.setState1(rs.getString("STATE_1"));
				cgclReportInfoDetailsBean.setState2(rs.getString("STATE_2"));
				cgclReportInfoDetailsBean.setState3(rs.getString("STATE_3"));
				cgclReportInfoDetailsBean.setState4(rs.getString("STATE_4"));
				cgclReportInfoDetailsBean.setTan(rs.getString("TAN"));
				cgclReportInfoDetailsBean.setTaxid(rs.getString("TAX_ID"));
				cgclReportInfoDetailsBean.setUcic(rs.getString("UCIC"));
				cgclReportInfoDetailsBean.setUcictype(rs.getString("UCIC_TYPE"));
				cgclReportInfoDetailsBean.setUid(rs.getString("UID"));
				cgclReportInfoDetailsBean.setUidtype(rs.getString("UID_TYPE"));
				cgclReportInfoDetailsBean.setVoteridno(rs.getString("VOTER_ID_NO"));
				cgclReportInfoDetailsBean.setMobileNo(rs.getString("MOBILE"));
				cgclReportInfoDetailsBean.setMobileType(rs.getString("MOBILE_TYPE"));
				cgclReportInfoDetailsBean.setPropertyAddress(rs.getString("PROPADDRESS"));
				cgclReportInfoDetailsBean.setPropertyAddressType(rs.getString("PROPADDRESS_TYPE"));
				cgclReportInfoDetailsBean.setAddress(rs.getString("ADDRESS"));
				cgclReportInfoDetailsBean.setAddressType(rs.getString("ADDRESS_TYPE"));
				cgclReportInfoDetailsBean.setSource(rs.getString("SOURCE"));
				cgclReportInfoDetailsBean.setReason(rs.getString("REASON"));
				cgclReportInfoDetailsBean.setFatherFirstName(rs.getString("FATHER_FIRST_NAME")); 
				cgclReportInfoDetailsBean.setFatherLastName(rs.getString("FATHER_LAST_NAME"));
				cgclReportInfoDetailsBean.setPermanentMaxStrength(rs.getString("ADDRESS0_STRENGTH"));
				cgclReportInfoDetailsBean.setCurrentMaxStrength(rs.getString("ADDRESS1_STRENGTH"));
				cgclReportInfoDetailsBean.setResidentMaxStrength(rs.getString("ADDRESS2_STRENGTH"));
				cgclReportInfoDetailsBean.setEmployeeMaxStrength(rs.getString("ADDRESS3_STRENGTH"));
					return cgclReportInfoDetailsBean;
				},requestId);
		}catch (Exception e) {
			logger.error("Exception while getting getAllMatchedCustomerDetails : "+ e.getMessage());
			throw (new DAOException("Exception while getting  getAllMatchedCustomerDetails"));
		}
		return cgclReportInputOutputBeanList;
	}

	@Override
	public List<CgclLoanDetailsBean> getMatchedLoanDetails(List<Long> customer_no) throws DAOException {
		String sql=environment.getProperty("select.loan.details.query");
		List<CgclLoanDetailsBean> matchedLoanDetails = new ArrayList<>();
		try{
			for(Long i:customer_no){
			jdbcTemplate.query(sql,new ResultSetExtractor<List<CgclLoanDetailsBean>>() {
						@Override
			public List<CgclLoanDetailsBean> extractData(ResultSet rs) throws SQLException,DataAccessException {
					while (rs.next()) {
				CgclLoanDetailsBean cgclLoanDetailsBean=new CgclLoanDetailsBean();
				cgclLoanDetailsBean.setApplication_no(rs.getString("APPLICATION_NO"));
				cgclLoanDetailsBean.setBasic_loan_amount(rs.getString("BASIC_LOAN_AMOUNT"));
				cgclLoanDetailsBean.setCurrent_pos(rs.getString("CURRENT_POS"));
				cgclLoanDetailsBean.setCustomer_no(rs.getString("CUSTOMER_NO"));
				cgclLoanDetailsBean.setDpd(rs.getString("DPD"));
				cgclLoanDetailsBean.setF_interest_rate(rs.getString("F_INTEREST_RATE"));
				cgclLoanDetailsBean.setId(rs.getLong("ID"));
				cgclLoanDetailsBean.setIncome(rs.getString("INCOME"));
				cgclLoanDetailsBean.setLoan_account_no(rs.getString("LOAN_ACCOUNT_NO"));
				cgclLoanDetailsBean.setLoan_amount(rs.getString("LOAN_AMOUNT"));
				cgclLoanDetailsBean.setLoan_status(rs.getString("LOAN_STATUS"));
				cgclLoanDetailsBean.setStatus(rs.getString("STATUS"));
				cgclLoanDetailsBean.setTenor(rs.getString("TENOR"));
				matchedLoanDetails.add(cgclLoanDetailsBean);
				}
				return matchedLoanDetails;
				}},i);
			}
		}catch (Exception e) {
			logger.error("Exception while getting Request Information: "+ e.getMessage());
			throw (new DAOException("Exception while getting  Matched Loan Details"));
		}
		return matchedLoanDetails;
		
	}

	@Override
	public long generateSequenceId(String seqname) throws DAOException {
		long sum =0;
		try{
		logger.info("Getting Generate SequenceId:: ");
		 sum = jdbcTemplate.queryForObject(environment.getProperty("select.seq.next.val.function"),Long.class, new Object[] { seqname });
		}catch(Exception e) {
			logger.error("Exception while getting generateSequenceId: "+ e.getMessage());
			throw (new DAOException("Exception while getting generateSequenceId"));
		}
		return sum;
	}

	@Override
	public PsxRequestBean getRequestInfo(long requestId) throws DAOException {
		logger.info("Inside getting Request Information " + requestId);
		PsxRequestBean 	psxRequestBean=new PsxRequestBean();
		String sql = environment.getProperty("select.psx.request.query");
		try {
			return jdbcTemplate.queryForObject(sql,new Object[] { requestId },new PsxRequestRowMapper());
		} catch (Exception e) {
			logger.error("Exception while getting Request Information: "+ e.getMessage());
			throw (new DAOException("Exception while getting Request Information"));
		}
	}
	
	@Override
	public void savePsxRequestInfo(PsxRequestBean request) throws DAOException {
		try {
			String sql = environment.getProperty("insert.psx.request.query");
			jdbcTemplate.update(sql, new Object[] {request.getRequestId(),request.getCustunqid(),request.getInserttime(),request.getLchgtime(),request.getProfileid(),request.getPsxbatchid(),request.getRequeststatus(),request.getAssignUcic()});
		} catch (Exception ex) {
			logger.error("Exception while getting savePsxRequestInfo: "+ ex.getMessage());
			throw (new DAOException("Exception while getting savePsxRequestInfo"));
		}

	}

	
	@Override
	public List<String> getValidProfilesList() throws DAOException {

		String sql = environment.getProperty("select.active.profile.info.query");
		try {
			List<String> ls = new ArrayList<>();
			return jdbcTemplate.query(sql,new ResultSetExtractor<List<String>>() {
						@Override
						public List<String> extractData(ResultSet rs)throws SQLException, DataAccessException {
							while (rs.next()) {
								ls.add(rs.getString("PROFILE_ID"));
							}
							return ls;
						}
					});
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while getting getValidProfilesList: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getValidProfilesList"));
		}
	}

	@Override
	public CgclDgBlkBean getDemographicInfoForUcic(long requestId) throws DAOException {
		String sql = environment.getProperty("select.dg.blk.request.ucic.query");
		CgclDgBlkBean cDgBlk = new CgclDgBlkBean();
		try {
				jdbcTemplate.query(sql, (ResultSet rs) -> {
					while (rs.next()) {
				cDgBlk.setAadharNo(rs.getString("AADHAR_NO"));
				cDgBlk.setAccount_status(rs.getString("ACCOUNT_STATUS"));
				cDgBlk.setApplicantFullName(rs.getString("APPLICANT_FULL_NAME"));
				//cDgBlk.setAssignUcic(rs.getString("ASSIGN_UCIC"));
				cDgBlk.setBatch_id(rs.getString("BATCH_ID"));
				cDgBlk.setCaste(rs.getString("CASTE"));
				cDgBlk.setCibil_score(rs.getString("CIBIL_SCORE"));
				cDgBlk.setCin(rs.getString("CIN"));
				cDgBlk.setCycNo(rs.getString("CKYC_NO"));
				cDgBlk.setCust_id(rs.getString("CUST_ID"));
				cDgBlk.setCust_unq_id(rs.getString("CUST_UNQ_ID"));
				cDgBlk.setCustomercategory(rs.getString("CUSTOMER_CATEGORY_TYPE"));
				cDgBlk.setSourcecustomerid(rs.getString("CUSTOMER_NO"));
				cDgBlk.setCustomertypecode(rs.getString("CUSTOMER_TYPE_CODE"));
				cDgBlk.setDeal_id_app_id(rs.getString("DEAL_ID_APP_ID"));
				cDgBlk.setDinno(rs.getString("DIN"));
				cDgBlk.setDob(rs.getDate("DOB"));
				cDgBlk.setDoi(rs.getDate("DOI"));
				cDgBlk.setDrivinglicense(rs.getString("DRIVING_LIC_NO"));
				cDgBlk.setDuiflag(rs.getString("DUIFLAG"));
				cDgBlk.setEventtype(rs.getString("EVENTTYPE"));
				cDgBlk.setFathername(rs.getString("FATHER_NAME"));
				cDgBlk.setFirstname(rs.getString("FIRST_NAME"));
				cDgBlk.setGender(rs.getString("GENDER"));
				cDgBlk.setGstin(rs.getString("GSTIN"));
				cDgBlk.setHighesteducation(rs.getString("HIGHEST_EDUCATION"));
				cDgBlk.setInserttime(rs.getDate("INSERT_TIME"));
				cDgBlk.setLan(rs.getString("LAN"));
				cDgBlk.setLastname(rs.getString("LAST_NAME"));
				cDgBlk.setLchgtime(rs.getDate("LCHGTIME"));
				cDgBlk.setLead_id(rs.getString("LEAD_ID"));
				cDgBlk.setMartialstatus(rs.getString("MARITAL_STATUS"));
				cDgBlk.setMatchingruleprofile(rs.getString("MATCHING_RULE_PROFILE"));
				cDgBlk.setMiddlename(rs.getString("MIDDLE_NAME"));
				cDgBlk.setMotherMaidenName(rs.getString("MOTHER_MAIDEN_NAME"));
				cDgBlk.setMotherName(rs.getString("MOTHER_NAME"));
				cDgBlk.setName(rs.getString("NAME"));
				cDgBlk.setOld_psx_batch_id(rs.getString("OLD_PSX_BATCH_ID"));
				cDgBlk.setPanno(rs.getString("PAN_NO"));
				cDgBlk.setPassportno(rs.getString("PASSPORT_NO"));
				cDgBlk.setPrimaryoccupation(rs.getString("PRIMARY_OCCUPATION"));
				cDgBlk.setProduct(rs.getString("PRODUCT"));
				cDgBlk.setPsx_batch_id(rs.getString("PSX_BATCH_ID"));
				cDgBlk.setRegistration_no(rs.getString("REGISTRATION_NO"));
				cDgBlk.setReligion(rs.getString("RELIGION"));
				cDgBlk.setRequestId(rs.getLong("REQUEST_ID"));
				cDgBlk.setResidencestatus(rs.getString("RESIDENT_STATUS"));
//				cDgBlk.setSourceapplicationid(rs.getInt("SOURCE_APPLICATION_ID"));
				cDgBlk.setSourceapplicationid(rs.getString("SOURCE_APPLICATION_ID"));
				cDgBlk.setSourceapplicationno(rs.getString("SOURCE_APPLICATION_NO"));
				cDgBlk.setSourceauthenticationtoken(rs.getString("SOURCE_AUTHENTICATION_TOKEN"));
				cDgBlk.setSourcecustomerid(rs.getString("SOURCE_CUSTOMER_ID"));
				cDgBlk.setSourcesystemname(rs.getString("SOURCE_SYSTEM"));
				cDgBlk.setSpousename(rs.getString("SPOUSE_NAME"));
				cDgBlk.setTanno(rs.getString("TAN"));
				cDgBlk.setTaxId(rs.getString("TAX_ID"));
				cDgBlk.setTitle(rs.getString("TITLE"));
				cDgBlk.setUcic(rs.getString("UCIC"));
				cDgBlk.setUcicType(rs.getString("UCIC_TYPE"));
				cDgBlk.setUid(rs.getInt("UID"));
				cDgBlk.setUidType(rs.getString("UID_TYPE"));
				cDgBlk.setVoterid(rs.getString("VOTER_ID_NO"));
				cDgBlk.setFatherFirstName(rs.getString("FATHER_FIRST_NAME")); 
				cDgBlk.setFatherLastName(rs.getString("FATHER_LAST_NAME"));
					}
				return cDgBlk;
			}, requestId);
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while getting getDemographicInfoForUcic: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getDemographicInfoForUcic"));
		}
		return cDgBlk;
	}

	@Override
	public List<CgclAceBlkBean> getAceInfoForUcic(long requestId) throws DAOException {
		String sql = environment.getProperty("select.ace.blk.request.ucic.query");
		 List<CgclAceBlkBean> cAceBlkList = new ArrayList<>();
		try {
			jdbcTemplate.query(sql, (ResultSet rs) -> {
							while (rs.next()) {
								CgclAceBlkBean cAceBlk = new CgclAceBlkBean();
								cAceBlk.setAddress(rs.getString("ADDRESS"));
								cAceBlk.setAddressid(rs.getInt("ADDRESS_ID"));
								cAceBlk.setAddresstype(rs.getString("ADDRESS_TYPE"));
								cAceBlk.setBatchId(rs.getString("BATCH_ID"));
								cAceBlk.setCity(rs.getString("CITY"));
								cAceBlk.setCustId(rs.getString("CUST_ID"));
								cAceBlk.setCustUnqId(rs.getString("CUST_UNQ_ID"));
								cAceBlk.setCustomerContact(rs.getString("CUSTOMER_CONTACT"));
								cAceBlk.setCustomerContactType(rs.getString("CUSTOMER_CONTACT_TYPE"));
								cAceBlk.setCustomerLandline(rs.getString("CUSTOMER_LANDLINE"));
								cAceBlk.setCustomerLandlineType(rs.getString("CUSTOMER_LANDLINE_TYPE"));
								cAceBlk.setCustomerNo(rs.getString("CUSTOMER_NO"));
								cAceBlk.setDealIdAppId(rs.getString("DEAL_ID_APP_ID"));
								cAceBlk.setDuiflag(rs.getString("DUIFLAG"));
								cAceBlk.setEmail(rs.getString("EMAIL"));
								cAceBlk.setEmailType(rs.getString("EMAIL_TYPE"));
								cAceBlk.setEventtype(rs.getString("EVENTTYPE"));
								cAceBlk.setInserttime(rs.getDate("INSERT_TIME"));
								cAceBlk.setLandlinenumber(rs.getString("LANDLINE_NUMBER"));
								cAceBlk.setLchgtime(rs.getDate("LCHGTIME"));
								cAceBlk.setLeadId(rs.getString("LEAD_ID"));
								cAceBlk.setMobilenumber(rs.getString("MOBILE_NUMBER"));
								cAceBlk.setOldPsxBatchId(rs.getString("OLD_PSX_BATCH_ID"));
								cAceBlk.setPincode(rs.getString("PINCODE"));
								cAceBlk.setPsxBatchId(rs.getString("PSX_BATCH_ID"));
								cAceBlk.setRequestId(rs.getLong("REQUEST_ID"));
								cAceBlk.setState(rs.getString("STATE"));
								cAceBlkList.add(cAceBlk);
							}
							return cAceBlkList;
						},requestId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e,e);
			logger.error("Exception while getting getAceInfoForUcic: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getAceInfoForUcic"));
		}
		
		return cAceBlkList;
	}

	@Override
	public void updateUcicAssignStatus(long reqPsxid, String ucicId,String ucicType, String status) throws DAOException  {
		String sql =  environment.getProperty("update.assign.ucic.status");	
		try {
			
			
//			 SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("ucicId", ucicId)
//					 .addValue("ucicType", ucicType)
//			            .addValue("status", status)
//			            .addValue("reqPsxid", reqPsxid);
//			 String sql =" UPDATE PSX_REQUEST SET  ASSIGNED_UCIC_NO=:ucicId, ASSIGNED_UCIC_TYPE=:ucicType, ASSIGNED_UCIC_STATUS=:status WHERE REQUEST_ID =:reqPsxid";
//			logger.info("Updating UCIC Assign Status in DAO::: " + reqPsxid +" ucicId :: "+ucicId +" ucicType ::: "+ucicType+ " Assign UCIC status ::: "+status);
//			Object[] params = { "33", ucicType, status, reqPsxid};
//			int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,	Types.BIGINT };
//	//		int rows = jdbcTemplate.update(sql, params, types);
//			int rows = 	    namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
//
//		logger.info(rows + " row(s) updated.");
			
			
			logger.info("Updating UCIC Assign Status in DAO::: " + reqPsxid +" ucicId :: "+ucicId +" ucicType ::: "+ucicType+ " Assign UCIC status ::: "+status);
			Object[] params = { ucicId, ucicType, status, reqPsxid};
			int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,	Types.VARCHAR };
			int rows = jdbcTemplate.update(sql, params, types);
		logger.info(rows + " row(s) updated.");
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while performing updateUcicAssignStatus: "+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public List<String> getValidRequestId() throws DAOException {
		String sql = environment.getProperty("select.all.requestids.query");
		try {
			List<String> ls = new ArrayList<>();
			return jdbcTemplate.query(sql,new ResultSetExtractor<List<String>>() {
						@Override
						public List<String> extractData(ResultSet rs)throws SQLException, DataAccessException {
							while (rs.next()) {
								ls.add(rs.getString("REQUEST_ID"));
							}
							return ls;
						}
					});
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while getting getValidRequestId: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getValidRequestId "));
		}
	}

	@Override
	public List<String> getSourceSystemAuthentication(String source_system) throws DAOException {
		String sql =environment.getProperty("select.sourcesystem.authentication.codes");
		List<String> sourceSystemCodes = new ArrayList<>();
		try{
		    return jdbcTemplate.query(sql,new ResultSetExtractor<List<String>>() {
					@Override
					public List<String> extractData(ResultSet rs)throws SQLException, DataAccessException {
						while (rs.next()) {
							sourceSystemCodes.add(rs.getString("SOURCE_SYSTEM_CODE"));
						//	sourceSystemCodes.add(rs.getString("SOURCE_SYSTEM_SHORTCUT"));
						}
						logger.info("Authentication codes for the Source System "+source_system+" ::: "+sourceSystemCodes);
						return sourceSystemCodes;
					}
				},source_system);
		 
		}catch(Exception e){
			logger.error(e,e);
			logger.error("Exception while getting getSourceSystemAuthentication: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getSourceSystemAuthentication "));
		}
	}

	@Override
	public List<String> getUcicInfo(long requestid) throws DAOException {
		String sql =environment.getProperty("select.ucic.info");
		List<String> ucicInfo = new ArrayList<>();
		
		try{
		    return jdbcTemplate.query(sql,new ResultSetExtractor<List<String>>() {
					@Override
					public List<String> extractData(ResultSet rs)throws SQLException, DataAccessException {
						while (rs.next()) {
							ucicInfo.add(rs.getString("ASSIGN_UCIC"));
							ucicInfo.add(rs.getString("ASSIGNED_UCIC_NO"));
							ucicInfo.add(rs.getString("ASSIGNED_UCIC_STATUS"));
							ucicInfo.add(rs.getString("ASSIGNED_UCIC_TYPE"));
							ucicInfo.add(rs.getString("REQUEST_STATUS"));
						}
						return ucicInfo;
					}
				},requestid);
		 
		}catch(Exception e){
			logger.error(e,e);
			logger.error("Exception while getting getUcicInfo: "+ e.getMessage());
			throw (new DAOException("Exception encountered while fetching getUcicInfo "));
		}
	}

	@Override
	public List<CgclLoanDetailsBean> getMatchedLoanDetails1(List<String> customer_no) throws DAOException {
		String sql=environment.getProperty("select.loan.details.query");
		List<CgclLoanDetailsBean> matchedLoanDetails = new ArrayList<>();
		try{
			for(String i:customer_no){
			jdbcTemplate.query(sql,new ResultSetExtractor<List<CgclLoanDetailsBean>>() {
						@Override
			public List<CgclLoanDetailsBean> extractData(ResultSet rs) throws SQLException,DataAccessException {
					while (rs.next()) {
				CgclLoanDetailsBean cgclLoanDetailsBean=new CgclLoanDetailsBean();
				cgclLoanDetailsBean.setApplication_no(rs.getString("APPLICATION_NO"));
				cgclLoanDetailsBean.setBasic_loan_amount(rs.getString("BASIC_LOAN_AMOUNT"));
				cgclLoanDetailsBean.setCurrent_pos(rs.getString("CURRENT_POS"));
				cgclLoanDetailsBean.setCustomer_no(rs.getString("CUSTOMER_NO"));
				cgclLoanDetailsBean.setDpd(rs.getString("DPD"));
				cgclLoanDetailsBean.setF_interest_rate(rs.getString("F_INTEREST_RATE"));
				cgclLoanDetailsBean.setId(rs.getLong("ID"));
				cgclLoanDetailsBean.setIncome(rs.getString("INCOME"));
				cgclLoanDetailsBean.setLoan_account_no(rs.getString("LOAN_ACCOUNT_NO"));
				cgclLoanDetailsBean.setLoan_amount(rs.getString("LOAN_AMOUNT"));
				cgclLoanDetailsBean.setLoan_status(rs.getString("LOAN_STATUS"));
				cgclLoanDetailsBean.setStatus(rs.getString("STATUS"));
				cgclLoanDetailsBean.setTenor(rs.getString("TENOR"));
				matchedLoanDetails.add(cgclLoanDetailsBean);
				}
				return matchedLoanDetails;
				}},i);
			}
		}catch (Exception e) {
			logger.error("Exception while getting Request Information: "+ e.getMessage());
			throw (new DAOException("Exception while getting  Matched Loan Details1"));
		}
		return matchedLoanDetails;
	}

}
