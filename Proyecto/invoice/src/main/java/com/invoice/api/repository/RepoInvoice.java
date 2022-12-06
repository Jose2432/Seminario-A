package com.invoice.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invoice.api.entity.Invoice;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer>{

	List<Invoice> findByRfcAndStatus(String rfc, Integer status);
	
	Invoice findByRfc(String rfc);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE invoice SET subtotal = :subtotal1, taxes = :taxes1, total = :total1 WHERE invoice_id = :invoice_id", nativeQuery = true)
	void updateInvoice(@Param("invoice_id") Integer invoice_id,@Param("subtotal1") Double subtotal1, @Param("taxes1") Double taxes1, @Param("total1") Double total1);
	
    @Query(value = "SELECT * FROM invoice WHERE status = 1 AND rfc = :rfc1", nativeQuery = true)
    Invoice findByrfcStatus(@Param("rfc1") String rfc);
    
	@Modifying
	@Transactional
	@Query(value ="UPDATE invoice SET status = 0 WHERE invoice_id = :invoice_id", nativeQuery = true)
	void updateStatus(@Param("invoice_id") Integer invoice_id);
}
