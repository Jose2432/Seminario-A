package com.product.api.service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {

	ApiResponse setProductImage(ProductImage in);
	
}
