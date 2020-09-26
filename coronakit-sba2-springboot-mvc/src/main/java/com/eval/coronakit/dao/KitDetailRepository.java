package com.eval.coronakit.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;


@Repository
public interface KitDetailRepository extends JpaRepository<KitDetail, Integer>{

	KitDetail findById(int id) ;
	
	@Query("SELECT k FROM KitDetail k WHERE k.coronaKitId = :coronaKitId")
	List<KitDetail> findAllByCornonaKitId(int coronaKitId);
	
}
