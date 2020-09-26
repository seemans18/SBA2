package com.eval.coronakit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.ProductMasterRepository;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.CoronakitException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMasterRepository repository;
	
	@Override
	@Transactional
	public ProductMaster addNewProduct(ProductMaster product) throws CoronakitException {
		if(product!=null) {
			if(repository.existsByProductName(product.getProductName())) {
				throw new CoronakitException("Product with the same name already exists!!");
			}
			repository.save(product);
		}
		return product;
	}

	@Override
	public List<ProductMaster> getAllProducts() throws CoronakitException {
		return repository.findAll();
	}

	@Override
	@Transactional
	public boolean deleteProduct(int productId) throws CoronakitException{
		if(!repository.existsById(productId)) {
			throw new CoronakitException("Product with the id " + productId +" is not found!!");
		}
		 repository.deleteById(productId);
		 return true;
	}

	@Override
	public ProductMaster getProductById(int productId) throws CoronakitException {
		if(!repository.existsById(productId)) {
			throw new CoronakitException("Product with the id " + productId +" is not found!!");
		}
		return repository.findById(productId);	  
	}

}
