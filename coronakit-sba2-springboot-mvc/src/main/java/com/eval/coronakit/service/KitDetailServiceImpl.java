package com.eval.coronakit.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.KitDetailRepository;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.exception.CoronakitException;

@Service
public class KitDetailServiceImpl implements KitDetailService {

	@Autowired
	KitDetailRepository repository;
	
	@Override
	@Transactional
	public KitDetail addKitItem(KitDetail kitItem) throws CoronakitException  {
		repository.save(kitItem);
		return kitItem;
	}

	@Override
	public KitDetail getKitItemById(int itemId) throws CoronakitException  {
		return null;
		//if(repository.findById(itemId))
	}

	@Override
	public List<KitDetail> getAllKitItemsOfAKit(int kitId) throws CoronakitException {
		return repository.findAllByCornonaKitId(kitId);
		
		//return repository.findAll();
	}

}
