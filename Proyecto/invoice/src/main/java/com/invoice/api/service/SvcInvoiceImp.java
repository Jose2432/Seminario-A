package com.invoice.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoCustomer;
import com.invoice.api.entity.Cart;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.repository.RepoCart;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;
import com.invoice.configuration.client.CustomerClient;
import com.invoice.configuration.client.ProductClient;
import com.invoice.exception.ApiException;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;
	
	@Autowired
	RepoCart repoCart;
	
	@Autowired
	ProductClient productClient;
	
	@Autowired
	CustomerClient customerCl;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	@Override
	  public ApiResponse generateInvoice(String rfc)
	  {
	    /*
	     * Requerimiento 5
	     * Implementar el método para generar una factura 
	     */
		if(!validateCustomer(rfc))
			throw new ApiException(HttpStatus.BAD_REQUEST, "customer does not exist");
		else
		{
		    List<Cart> cartCliente = repoCart.findByRfcAndStatus(rfc,1);
			if (cartCliente.size() == 0)
			{
				throw new ApiException(HttpStatus.NOT_FOUND, "cart has no items");
			}
				Double total = .0;
			    Double totalInvoice = .0;
			    Double taxes = .0;
			    Double taxesInvoice = .0;
			    Double subtotal = .0;
			    Double subtotalInvoice = .0;
			    Double unitPrice = .0;
			    
			    LocalDateTime today = LocalDateTime.now();
			    Invoice invoice = new Invoice();
			    invoice.setStatus(1);
			    invoice.setRfc(rfc);
			    invoice.setSubtotal(.0);
			    invoice.setTaxes(.0);
			    invoice.setTotal(.0);
			    invoice.setCreated_at(today);
			    repo.save(invoice);
			    
			    invoice = repo.findByrfcStatus(rfc);
			    
			    for (Cart cart : cartCliente)
			    {
			      Item item = new Item(null,null,null,null,null,null,null,null,null);
			      taxes=.0;
			      subtotal=.0;
			      total=.0;
			      unitPrice=.0;
			      item.setId_invoice(invoice.getInvoice_id());
			      item.setGtin(cart.getGtin());
			      item.setQuantity(cart.getQuantity());
			      unitPrice = productClient.getProductDto(cart.getGtin()).getPrice();
			      item.setUnit_price(unitPrice);
			      taxes = (unitPrice * .16)*item.getQuantity();
			      total = item.getUnit_price() * item.getQuantity();
			      item.setTotal(total);
			      item.setTaxes(taxes);
			      subtotal = total - taxes;
			      item.setSubtotal(subtotal);
			      item.setStatus(1);
			      totalInvoice += total;
			      taxesInvoice += taxes;
			      subtotalInvoice += subtotal;
			      productClient.updateProductStock1(item.getGtin(),item.getQuantity());
			      repoItem.save(item);
			    }
			    
			    repo.updateInvoice(invoice.getInvoice_id(),subtotalInvoice, taxesInvoice, totalInvoice);
			    repoCart.clearCart(rfc);
			    repoCart.deleteCart();
			    repo.updateStatus(invoice.getInvoice_id());
			    return new ApiResponse("invoice generated");
		}
	  }
	
	private boolean validateCustomer(String rfc) {
		try {
			ResponseEntity<DtoCustomer> response = customerCl.getCustomer(rfc);
			if(response.getStatusCode() == HttpStatus.OK)
				return true;
			else
				return false;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve customer information");
		}
	}

}
