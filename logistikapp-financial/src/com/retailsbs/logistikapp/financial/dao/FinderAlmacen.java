package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

import com.retailsbs.logistikapp.financial.dto.AlmacenInfoDTO;
import com.retailsbs.logistikapp.financial.dto.RecoverProductFromSaleDTO;

public interface FinderAlmacen {

	Long getAlmacenNextValue();
	
	List<AlmacenInfoDTO> getSubAlmacenInfo(Long id_supplier);
	
	AlmacenInfoDTO getSubAlmacenInfoByIdAlmacen(Long id_almacen);
	
	int recoverProductFromSale(RecoverProductFromSaleDTO params);
	
}
