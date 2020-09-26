package com.eval.coronakit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eval.coronakit.entity.ProductMaster;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Integer>{
	
	ProductMaster findById(int id);
	
	boolean existsByProductName(String productName);
	
}
