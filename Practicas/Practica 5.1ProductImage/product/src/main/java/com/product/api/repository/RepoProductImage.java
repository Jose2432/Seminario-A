package com.product.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE product_image SET product_image = :product_image WHERE product_image_id = :product_image_id", nativeQuery = true)
	Integer setProductImage(@Param("product_image_id") Integer product_image_id, @Param("product_image") String product_image);
	
}
