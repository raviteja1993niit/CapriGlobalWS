//package com.cgcl.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.cgcl.entities.CGCL_ACE_BLK_REQUEST;
//@Repository
//public interface AddressRequestRepository extends JpaRepository<CGCL_ACE_BLK_REQUEST, Long>{
//
//	@Query("SELECT p FROM CGCL_ACE_BLK_REQUEST p  WHERE p.requestId = ?1")
//	public CGCL_ACE_BLK_REQUEST findByRequestId(long requestId);
//
//}
