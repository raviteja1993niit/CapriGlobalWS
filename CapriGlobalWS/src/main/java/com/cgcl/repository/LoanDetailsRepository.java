//package com.cgcl.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.cgcl.entities.CGCL_LOAN_DETAILS;
//@Repository
//public interface LoanDetailsRepository extends JpaRepository<CGCL_LOAN_DETAILS, Integer>{
//
//	@Query("SELECT p FROM CGCL_LOAN_DETAILS p  WHERE p.customer_no = ?1")
//	List<CGCL_LOAN_DETAILS> findAllByCustomerId(String customer_no);
//
//}
