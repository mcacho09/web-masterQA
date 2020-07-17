package com.retailsbs.logistikapp.financial.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.retailsbs.logistikapp.financial.dao.FinderOrderDAO;
import com.retailsbs.logistikapp.financial.dto.CategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketCriteria;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketDTO;
import com.retailsbs.logistikapp.financial.dto.InfoTicketDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleFullDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleReportDTO;
import com.retailsbs.logistikapp.financial.dto.NoStoreVisitedByIdStoreCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.OrdersInfoToReportCriteria;
import com.retailsbs.logistikapp.financial.dto.OrdersInfoToReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportByDriCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportByDriDTO;
import com.retailsbs.logistikapp.financial.dto.ReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryDTO;
import com.retailsbs.logistikapp.financial.dto.TopClientsCriteria;
import com.retailsbs.logistikapp.financial.dto.TopClientsDTO;

public class FinderOrderDAOImpl extends SqlMapClientDaoSupport implements FinderOrderDAO {
	
	@Override
	public int delOrderByIdSupplier(Long id_supplier) {
		return getSqlMapClientTemplate().delete("finder_order.delOrderByIdSupplier", id_supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportDTO> getReportByDate(ReportSearchCriteria dto) {
		return getSqlMapClientTemplate().queryForList("finder_order.getReportByDate", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportExtDTO> getReportExtByDate(ReportExtSearchCriteria dto) {
		return (List<ReportExtDTO>) getSqlMapClientTemplate().queryForList("finder_order.getReportExtByDate", dto);
	}

	@Override
	public MetricsSaleDTO getMetricsSale(GetMetricsSaleDTO dto) {
		return (MetricsSaleDTO) getSqlMapClientTemplate().queryForObject("finder_order.getMetricsSale", dto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetricsSaleFullDTO> getMetricsSaleFull(GetMetricsSaleDTO dto) {
		return (List<MetricsSaleFullDTO>) getSqlMapClientTemplate().queryForList("finder_order.getMetricsSaleFull", dto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetricsSaleReportDTO> getMetricsSaleReport(GetMetricsSaleDTO dto) {
		return (List<MetricsSaleReportDTO>) getSqlMapClientTemplate().queryForList("finder_order.getMetricsSaleReport", dto);
	}

	@Override
	public InfoTicketDTO getInfoTicket(Long id_order) {
		return (InfoTicketDTO) getSqlMapClientTemplate().queryForObject("finder_order.getInfoToTicket", id_order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoProductsToTicketDTO> getInfoProductsToTicket(InfoProductsToTicketCriteria criteria) {
		return (List<InfoProductsToTicketDTO>) getSqlMapClientTemplate().queryForList("finder_order.getInfoProductsToTicket", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersInfoToReportDTO> getOrdersInfoToReport(OrdersInfoToReportCriteria criteria) {
		return (List<OrdersInfoToReportDTO>) getSqlMapClientTemplate().queryForList("finder_order.getOrdersInfoToReport", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopClientsDTO> getTopClients(TopClientsCriteria criteria) {
		return (List<TopClientsDTO>) getSqlMapClientTemplate().queryForList("finder_order.getTopClients", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleByCategoryDTO> getSaleByCategory(SaleByCategoryCriteria criteria) {
		return (List<SaleByCategoryDTO>) getSqlMapClientTemplate().queryForList("finder_order.getSaleByCategory", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryAndNoStoresDTO> getCategoryAndNoStores(Long id_supplier) {
		return (List<CategoryAndNoStoresDTO>) getSqlMapClientTemplate().queryForList("finder_order.getCategoryAndNoStores", id_supplier);
	}

	@Override
	public Long getNoStoreVisitedByIdStoreCategory(NoStoreVisitedByIdStoreCategoryCriteria criteria) {
		return (Long)getSqlMapClientTemplate().queryForObject("finder_order.getNoStoreVisitedByIdStoreCategory", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportByDriDTO> getReportByDri(ReportByDriCriteria criteria) {
		return (List<ReportByDriDTO>) getSqlMapClientTemplate().queryForList("finder_order.getReportByDri", criteria);
	}

}
