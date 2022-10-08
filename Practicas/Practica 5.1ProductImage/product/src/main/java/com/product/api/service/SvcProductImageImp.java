package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;

@Service
public class SvcProductImageImp implements SvcProductImage {

	@Autowired
	RepoProductImage repo;
	
	@Override
	public ApiResponse setProductImage(ProductImage in) {
		Integer updated = repo.setProductImage(in.getProduct_image_id(), in.getProduct_image());
		if (updated == 1)
			return new ApiResponse("product image updated");
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "product image does not exist");
	}

}
