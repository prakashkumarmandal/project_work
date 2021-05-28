package com.et.portal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.et.portal.entity.ETUserEntity;
import com.et.portal.entity.TranEntity;

@Repository
public interface TranRepository extends JpaRepository<TranEntity,Long>{
	List<TranEntity> findAllByUser(ETUserEntity user);
}
