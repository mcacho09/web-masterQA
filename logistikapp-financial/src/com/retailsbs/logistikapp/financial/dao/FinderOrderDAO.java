package com.retailsbs.logistikapp.financial.dao;

import java.util.List;

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

/**
 * Class interface para obtener order
 * @author JuanCarlosRamosCampos
 * @since 23-11-2015
 */

public interface FinderOrderDAO {

	/**
	 * elimina order por id_supplier
	 * @param id_supplier
	 * @return numero de order eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderByIdSupplier(Long id_supplier);
	
	/**
	 *Muestra todas las transacciones del d�a o rango seleccionado
	 *@param dto
	 *@return transacciones por dia o rango
	 *@author Gaby
	 *@since 16-08-2016
	 * */
	List<ReportDTO> getReportByDate(ReportSearchCriteria dto);
	
	/**
	 * Obtiene un listado extendido de transacciones y productos
	 * @param dto Dto con datos de criterio de busqueda
	 * @return Lista de objetos List<ReportExtDTO>
	 * @author JorgeSilva
	 * @since 03/10/2016 
	 */
	List<ReportExtDTO> getReportExtByDate(ReportExtSearchCriteria dto);
	
	/*
	 * Obtiene informaci�n para metricas de venta
	 * @param dto GetMetricSaleDTO
	 * @return MetricsSaleDTO
	 * @author DMarin
	 * @since 28/12/2016
	 */
	MetricsSaleDTO getMetricsSale(GetMetricsSaleDTO dto);

	/*
	 * Obtiene listado de informaci�n para metricas de venta de manera detallada
	 * @param dto GetMetricSaleDTO
	 * @return MetricsSaleDTO
	 * @author DMarin
	 * @since 28/12/2016
	 */
	List<MetricsSaleFullDTO> getMetricsSaleFull(GetMetricsSaleDTO dto);
	
	/*
	 * Obtiene listado de informaci�n para el reporte de metricas de venta
	 * @param dto GetMetricsSaleDTO
	 * @return MetricsSaleReportDTO
	 * @author DMarin
	 * @since 28/02/2017
	 */
	
	List<MetricsSaleReportDTO> getMetricsSaleReport(GetMetricsSaleDTO dto);
	
	/*
	 * Obtiene la informaci�n necesar�a para enviar el ticket
	 * @param Long id_order
	 * @return InfoTicketDTO
	 * @author DMarin
	 * @since 08/03/2017
	 */
	InfoTicketDTO getInfoTicket(Long id_order);
	
	/*
	 * Obtiene listado de productos vendidos / cambiados / devueltos segun el tipo de transacci�n especificada
	 * @param InfoProductsToTicketCriteria
	 * @return List<InfoProductsToTicketDTO>
	 * @author DMarin
	 * @since 08/03/2017
	 */
	List<InfoProductsToTicketDTO> getInfoProductsToTicket(InfoProductsToTicketCriteria criteria);
	
	/*
	 * Obtiene información para el reporte de trxproduct
	 * @param OrdersInfoToReportCriteria
	 * @return List<OrdersInfoToReportDTO>
	 * @author DMarin
	 * @since 10-05-2017
	 */
	List<OrdersInfoToReportDTO> getOrdersInfoToReport(OrdersInfoToReportCriteria criteria);
	
	/*
	 * Obtiene el top 10 de clientes con mejores compras
	 * @param TopClientsCriteria
	 * @return List<TopClientsDTO>
	 * @author DMarin
	 * @since 11-05-2017
	 */
	List<TopClientsDTO> getTopClients(TopClientsCriteria criteria);
	
	/*
	 * Obtiene listado de categorias de clientes y lo que se le ha vendido
	 * @param SaleByCategoryCriteria
	 * @return List<SaleByCategoryDTO>
	 * @author DMarin
	 * @since 12-05-2017
	 */
	List<SaleByCategoryDTO> getSaleByCategory(SaleByCategoryCriteria criteria);
	
	/*
	 * Obtiene el listado de categorias y su número de tiendas
	 * @param Long (id_supplier)
	 * @return List<CategoryAndNoStoresDTO>
	 * @author DMarin
	 * @since 13-05-2017
	 */
	List<CategoryAndNoStoresDTO> getCategoryAndNoStores(Long id_supplier);
	
	/*
	 * Obtiene el total de tiendas visitadas
	 * @param Long (id_store_category)
	 * @return Long
	 * @author DMarin
	 * @since 13-04-2017
	 */
	Long getNoStoreVisitedByIdStoreCategory(NoStoreVisitedByIdStoreCategoryCriteria criteria);
	
	/*
	 * Obtiene el listado de ventas por vendedor en un criterio de fechas respectivas
	 * @param ReportByDriCriteria
	 * @return List<ReportByDriDTO>
	 * @author DMarin
	 * @since 02-06-2017
	 */
	List<ReportByDriDTO> getReportByDri(ReportByDriCriteria criteria);
	
}
