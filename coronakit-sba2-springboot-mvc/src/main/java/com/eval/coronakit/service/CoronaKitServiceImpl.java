package com.eval.coronakit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.CoronaKitRepository;
import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.exception.CoronakitException;

@Service
public class CoronaKitServiceImpl implements CoronaKitService {

	@Autowired
	CoronaKitRepository repository;
	
	@Override
	@Transactional
	public CoronaKit saveKit(CoronaKit kit) throws CoronakitException {
		/*if(! repository.existsById(kit.getId())) {
			throw new CoronakitException("Corona kit with the id " + kit.getId() +" already exists!!");
		}*/
		repository.save(kit);
		return kit;
	}

	@Override
	public CoronaKit getKitById(int kitId) throws CoronakitException {
		if(! repository.existsById(kitId)) {
			throw new CoronakitException("Corona kit with the id " + kitId +" is not found!!");
		}
		return repository.findById(kitId);
	}

}
