package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderBrandProductDAO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;

public class FinderBrandProductDAOImpl extends SqlMapClientDaoSupport implements FinderBrandProductDAO {

	@SuppressWarnings("unchecked")
	public List<BrandProductDTO> selectBrandProductByCriteria(BrandProductSearchCriteria dto) {
		return (List<BrandProductDTO>)getSqlMapClientTemplate().queryForList("finder_brand_product.selectBrandProduct", dto);
	}

}
