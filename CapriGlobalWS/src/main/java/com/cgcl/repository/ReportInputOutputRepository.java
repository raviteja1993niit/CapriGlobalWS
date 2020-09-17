//package com.cgcl.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.cgcl.entities.CGCL_REPORT_INPUT_OUTPUT;
//
//public interface ReportInputOutputRepository extends JpaRepository<CGCL_REPORT_INPUT_OUTPUT, Long> {
//
//	@Query("SELECT p FROM CGCL_REPORT_INPUT_OUTPUT p  WHERE p.requestid = ?1 and RECORD_TYPE NOT IN ('INPUT')")
//	List<CGCL_REPORT_INPUT_OUTPUT> findAllByRequestId(long requestId,String recordtype);
//}
