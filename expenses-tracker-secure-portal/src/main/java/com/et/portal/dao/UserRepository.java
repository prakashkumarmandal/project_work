package com.et.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.et.portal.entity.ETUserEntity;

@Repository
public interface UserRepository extends JpaRepository<ETUserEntity,Integer>{

	boolean existsByEmailId(String emailId);
	ETUserEntity findByEmailId(String emailId);
}
