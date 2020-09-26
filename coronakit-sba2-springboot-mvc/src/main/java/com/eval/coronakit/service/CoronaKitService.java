package com.eval.coronakit.service;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.exception.CoronakitException;

public interface CoronaKitService {
	public CoronaKit saveKit(CoronaKit kit) throws CoronakitException;
	public CoronaKit getKitById(int kitId) throws CoronakitException;
}
