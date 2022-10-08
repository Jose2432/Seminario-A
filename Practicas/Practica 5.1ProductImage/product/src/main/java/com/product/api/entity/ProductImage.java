package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_image_id")
	private Integer product_image_id;
	
	@NotNull
	@Column(name = "product_image")
	private String product_image;
	
	public ProductImage() {
		this.product_image = "";		
	}

	public Integer getProduct_image_id() {
		return product_image_id;
	}

	public void setProduct_image_id(Integer product_image_id) {
		this.product_image_id = product_image_id;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
	
	
}
