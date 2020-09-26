package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.CoronakitException;

public interface ProductService {

	public ProductMaster addNewProduct(ProductMaster product) throws CoronakitException;
	public List<ProductMaster> getAllProducts() throws CoronakitException;
	public boolean deleteProduct(int productId) throws CoronakitException;
	public ProductMaster getProductById(int productId) throws CoronakitException;
}
