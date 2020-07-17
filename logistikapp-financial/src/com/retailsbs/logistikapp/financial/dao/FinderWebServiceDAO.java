package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.SaleByDriDTO;
import com.retailsbs.logistikapp.financial.dto.WSProductsDetailCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProduct;
import com.retailsbs.logistikapp.financial.dto.WSProductCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProductDetailDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionListDTO;

public interface FinderWebServiceDAO {

	List<WSTransactionDTO> getTransactionList(WSTransactionListDTO dto);
	
	WSTransactionDTO getTransactionById(Long id_order);
	
	List<WSProductDetailDTO> getProductsDetailByIdOrderAndTypeTrx(WSProductsDetailCriteria criteria);
	
	List<WSProduct> getProductsByIdSupplier(WSProductCriteria criteria);
	
	String getImageProductyById(Long id_product);
	
	List<SaleByDriDTO> getSaleByDri(String delivery);
	
}
