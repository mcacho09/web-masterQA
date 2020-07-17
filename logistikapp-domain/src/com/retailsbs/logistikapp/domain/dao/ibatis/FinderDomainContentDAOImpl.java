package com.retailsbs.logistikapp.domain.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.domain.dao.FinderDomainContentDAO;
import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;

public class FinderDomainContentDAOImpl extends SqlMapClientDaoSupport implements FinderDomainContentDAO {

	public FinderDomainContentDAOImpl(){
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DomainContentDTO> getDomainContentByCriteria(DomainContentSearchCriteria dto) {
		return (List<DomainContentDTO>) getSqlMapClientTemplate().queryForList("finder_domain_content.getDomainContent",dto);
	}

	@Override
	public Integer getDomainContentTotalByCriteria(DomainContentSearchCriteria dto) {
		return (Integer) getSqlMapClientTemplate().queryForObject("finder_domain_content.getTotalDomainContent", dto);
	}

}
