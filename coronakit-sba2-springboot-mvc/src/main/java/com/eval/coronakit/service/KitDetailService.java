package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.exception.CoronakitException;

public interface KitDetailService {
	public KitDetail addKitItem(KitDetail kitItem) throws CoronakitException  ;
	public KitDetail getKitItemById(int itemId)throws CoronakitException  ;
	public List<KitDetail> getAllKitItemsOfAKit(int kitId) throws CoronakitException ;
}
