//package com.cgcl.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.cgcl.entities.PSX_REQUEST;
//
//@Repository
//public interface RequestRepository extends JpaRepository<PSX_REQUEST, Long> {
//
//	@Query("SELECT p FROM PSX_REQUEST p  WHERE p.requestId = ?1")
//	public PSX_REQUEST findByRequestId(long requestId);
//
//}
