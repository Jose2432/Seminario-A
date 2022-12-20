package com.product.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;
import com.product.exception.ApiException;

@RestController 
@RequestMapping("/product-image")
public class CtrlProductImage {

	@Autowired
	SvcProductImage svc; 
	
	@PutMapping 
	public ResponseEntity<ApiResponse> setProductImage(@Valid @RequestBody ProductImage in, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.setProductImage(in), HttpStatus.OK);
	}
	
}
