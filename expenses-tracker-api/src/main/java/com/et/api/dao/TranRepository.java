package com.et.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.et.api.entity.ETUserEntity;
import com.et.api.entity.TranEntity;

@Repository
public interface TranRepository extends JpaRepository<TranEntity,Long>{
	List<TranEntity> findAllByUser(ETUserEntity user);
}
