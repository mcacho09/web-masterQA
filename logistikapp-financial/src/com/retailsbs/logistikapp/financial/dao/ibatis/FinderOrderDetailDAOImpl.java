package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderOrderDetailDAO;
import com.retailsbs.logistikapp.financial.dto.ProductsUsedsInOrderDetailDTO;

public class FinderOrderDetailDAOImpl extends SqlMapClientDaoSupport implements FinderOrderDetailDAO {
	
	@Override
	public int delOrderDetailByIdOrder(Long id_order) {
		return getSqlMapClientTemplate().delete("finder_order_detail.delOrderDetailByIdOrder", id_order);
	}

	@Override
	public int delOrderDetailByIdProduct(Long id_product) {
		return getSqlMapClientTemplate().delete("finder_order_detail.delOrderDetailByIdProduct", id_product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductsUsedsInOrderDetail(ProductsUsedsInOrderDetailDTO dto) {
		return (List<Integer>)getSqlMapClientTemplate().queryForList("finder_order_detail.getProductsUsedsInOrderDetail", dto);
	}
	
}
