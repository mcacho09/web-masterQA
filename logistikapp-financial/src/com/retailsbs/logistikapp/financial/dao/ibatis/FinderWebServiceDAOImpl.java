package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderWebServiceDAO;
import com.retailsbs.logistikapp.financial.dto.SaleByDriDTO;
import com.retailsbs.logistikapp.financial.dto.WSProductsDetailCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProduct;
import com.retailsbs.logistikapp.financial.dto.WSProductCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProductDetailDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionListDTO;

public class FinderWebServiceDAOImpl extends SqlMapClientDaoSupport implements FinderWebServiceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<WSTransactionDTO> getTransactionList(WSTransactionListDTO dto) {
		return (List<WSTransactionDTO>) getSqlMapClientTemplate().queryForList("finder_webservice.getTransactionList", dto);
	}

	@Override
	public WSTransactionDTO getTransactionById(Long id_order) {
		return (WSTransactionDTO) getSqlMapClientTemplate().queryForObject("finder_webservice.getTransactionList", id_order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSProductDetailDTO> getProductsDetailByIdOrderAndTypeTrx(WSProductsDetailCriteria criteria) {
		return (List<WSProductDetailDTO>) getSqlMapClientTemplate().queryForList("finder_webservice.getProductsDetailByIdOrder", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WSProduct> getProductsByIdSupplier(WSProductCriteria criteria) {
		return (List<WSProduct>) getSqlMapClientTemplate().queryForList("finder_webservice.getProductsByIdSupplier", criteria);
	}

	@Override
	public String getImageProductyById(Long id_product) {
		return getSqlMapClientTemplate().queryForObject("finder_webservice.getImageProductyById", id_product).toString();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<SaleByDriDTO> getSaleByDri(String delivery) {
		return (List<SaleByDriDTO>) getSqlMapClientTemplate().queryForList("finder_webservice.getSaleByDri", delivery);
	}

}
