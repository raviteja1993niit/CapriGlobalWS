//package com.cgcl.repository;
//
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.cgcl.entities.Profile;
//
//@Repository
//public interface ProfileRepository extends JpaRepository<Profile, Integer>{
//
//	@Query("SELECT p FROM Profile p  WHERE p.profileId = ?1 and ACTIVE='Y'")
//	public Profile getProfileById(int profileId);
//
//	@Query("SELECT p FROM Profile p  WHERE ACTIVE='Y'")
//	public List<Profile> getAllActiveProfiles();
//}
