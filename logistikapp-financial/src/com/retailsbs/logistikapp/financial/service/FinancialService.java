package com.retailsbs.logistikapp.financial.service;

import java.util.List;

import com.retailsbs.logistikapp.financial.domain.Almacen;
import com.retailsbs.logistikapp.financial.domain.Brand;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStock;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStockProduct;
import com.retailsbs.logistikapp.financial.domain.Cost;
import com.retailsbs.logistikapp.financial.domain.Order;
import com.retailsbs.logistikapp.financial.domain.OrderDetail;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.domain.ProductAlmacen;
import com.retailsbs.logistikapp.financial.domain.StoreCategoryProduct;
import com.retailsbs.logistikapp.financial.dto.AddAlmacenAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AddConfigurationStockProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddCostDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.AddTrxDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenExample;
import com.retailsbs.logistikapp.financial.dto.AlmacenInfoAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenInfoDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.CategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductByIdCondigurationStockCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductDTO;
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
import com.retailsbs.logistikapp.financial.dto.PerTotalVisitedCriteria;
import com.retailsbs.logistikapp.financial.dto.PerTotalVisitedDTO;
import com.retailsbs.logistikapp.financial.dto.ProductAlmacenDTO;
import com.retailsbs.logistikapp.financial.dto.ProductAlmacenExample;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateDTO;
import com.retailsbs.logistikapp.financial.dto.ProductByIdAlmacenCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductCodeAvailableSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductToSubAlmacenDTO;
import com.retailsbs.logistikapp.financial.dto.ProductsUsedsInOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.RecoverProductFromSaleDTO;
import com.retailsbs.logistikapp.financial.dto.ReportByDriCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportByDriDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByCategoryDTO;
import com.retailsbs.logistikapp.financial.dto.SaleByDriDTO;
import com.retailsbs.logistikapp.financial.dto.TopClientsCriteria;
import com.retailsbs.logistikapp.financial.dto.TopClientsDTO;
import com.retailsbs.logistikapp.financial.dto.UpdateAlmacenAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.UpdateOrderAndRecoverToAlmacenDTO;
import com.retailsbs.logistikapp.financial.dto.WSProductsDetailCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProduct;
import com.retailsbs.logistikapp.financial.dto.WSProductCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProductDetailDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionListDTO;
import com.retailsbs.logistikapp.financial.dto.ReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.UpdBrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.UpdCategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.UpdCostDTO;
import com.retailsbs.logistikapp.financial.dto.UpdOrder;
import com.retailsbs.logistikapp.financial.dto.UpdOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.UpdProductDTO;
import com.retailsbs.logistikapp.financial.exception.BrandProductNotFoundException;
import com.retailsbs.logistikapp.financial.exception.CategoryProductNotFoundException;
import com.retailsbs.logistikapp.financial.exception.CostNotFoundException;
import com.retailsbs.logistikapp.financial.exception.OrderDetailNotFoundException;
import com.retailsbs.logistikapp.financial.exception.OrderNotFoundException;
import com.retailsbs.logistikapp.financial.exception.ProductCodeDuplicateException;
import com.retailsbs.logistikapp.financial.exception.ProductNotFoundException;
import com.retailsbs.logistikapp.financial.dto.AddCategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddBrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.domain.CategoryProduct;

/**
 * Clase interfaz servicio de financial
 * @author juan carlos
 * @since 15-03-2015
 */
public interface FinancialService {

	/*
	 * Order
	 */

	/**
	 * Agrega un order nuevo
	 * @param dto con datos de nuevo order
	 * @return id de order agregado
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	Long addOrder(AddOrderDTO dto);
	/**
	 * Actualiza datos de order
	 * @param dto con datos de order
	 * @return numero de registros modificados
	 * @throws OrderNotFoundException
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	int updOrder(UpdOrder dto) throws OrderNotFoundException;
	/**
	 * Obtiene datos de order
	 * @param id_order id order
	 * @return objeto de dominio order} 
	 * @author juan carlos
	 * @throws OrderNotFoundException 
	 * @since 15-03-2015
	 */
	Order getOrderById(Long id_order) throws OrderNotFoundException;
	/**
	 * obtiene todos los order que hay
	 * @return lista de todos los ojetos de dominio {order}
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	List<Order> getAllOrder();
	/**
	 * obtiene lista de order por id_supplier
	 * @param id_supplier
	 * @return lista de objetos de dominio {order}
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	List<Order> getOrderByIdSupplier(Long id_supplier);
	
	/*
	 * Order Detail
	 */

	/**
	 * Agrega un OrderDetail
	 * @param dto con datos de OrderDetail a agregar
	 * @return id de OrderDetail agregado
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	Long addOrderDetail(AddOrderDetailDTO dto);
	/**
	 * Actualiza datos de OrderDetail
	 * @param dto con datos de OrderDetail
	 * @return numero de registros modificados
	 * @throws OrderDetailNotFoundException 
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	int updOrderDetail(UpdOrderDetailDTO dto) throws OrderDetailNotFoundException;
	/**
	 * Obtiene datos de OrderDetail por medio de id
	 * @param id_order_detail id order detail
	 * @return obtjeto de dominio {OrderDetail}
	 * @throws OrderDetailNotFoundException 
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	OrderDetail getOrderDetailById(Long id_order_detail) throws OrderDetailNotFoundException;
	/**
	 * Obtiene todos lod OrderDetail que hay
	 * @return lista de objetos de dominio {orderDetail}
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	List<OrderDetail> getAllOrderDetail();
	
	/*
	 * Finder/Order
	 */
	
	/**
	 * elimina order por id_supplier
	 * @param id_supplier
	 * @return numero de order eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderByIdSupplier(Long id_supplier);
	
	/**
	 * Obtiene un listado extendido de transacciones y productos
	 * @param dto Dto con datos de criterio de busqueda
	 * @return Lista de objetos List<ReportExtDTO>
	 * @author JorgeSilva
	 * @since 03/10/2016 
	 */
	List<ReportExtDTO> getReportExtByDate(ReportExtSearchCriteria dto);
	
	/*
	 * Obtiene listado de informaci�n para el reporte de metricas de venta
	 * @param dto GetMetricsSaleDTO
	 * @return MetricsSaleReportDTO
	 * @author DMarin
	 * @since 28/02/2017
	 */
	
	List<MetricsSaleReportDTO> getMetricsSaleReport(GetMetricsSaleDTO dto);
	
	/*
	 * Obtiene listado de productos vendidos / cambiados / devueltos segun el tipo de transacción especificada
	 * @param InfoProductsToTicketCriteria
	 * @return List<InfoProductsToTicketDTO>
	 * @author/DMarin
	 * @since 08/03/2017
	 */
	List<InfoProductsToTicketDTO> getInfoProductsToTicket(InfoProductsToTicketCriteria criteria);
	
	/*
	 * Obtiene la información necesaría para enviar el ticket
	 * @param Long id_order
	 * @return InfoTicketDTO
	 * @author DMarin
	 * @since 08/03/2017
	 */
	InfoTicketDTO getInfoTicket(Long id_order);
	
	/*
	 * Metodo para enviar el ticket de una trx por email
	 * @param Long
	 * @return String "Mensaje"
	 * @author DMarin
	 * @since 09/03/2017
	 */
	String sendTicketByIdOrder(Long id_order);
	
	/*
	 * Metodo para enviar notificaci�n entrega de pedido por email
	 * @param Long
	 * @return String "Mensaje"
	 * @author ErickGtz
	 * @since 02/06/2020
	 */
	String sendNotificationByIdOrder(String store, String supplier, String date, String seller, String r_email, String s_email, String telefono, String hora, String llegada);
	
	/*
	 * Metodo que genera la estructura para imprimir un ticket de venta
	 * @param Long id_order
	 * @return String "Ticket"
	 * @author DMarin
	 * @since 29/03/2017
	 */
	String sendPrintTicketByIdOrder(Long id_order);
	
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
	 * @return List<Long>
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
	
	/**
	 * finder order detail
	 */
	
	/**
	 * elimina orderDetail por id_order
	 * @param id_order
	 * @return numero de order Detail eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderDetailByIdOrder(Long id_order);
	
	/**
	 * elimina orderDetail por id_product
	 * @param id_product
	 * @return numero de order Detail eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delOrderDetailByIdProduct(Long id_product);
	
	/*
	 * Busca si los ids mandados se encuentran usados en alguna venta y regresara los ids que si son usados
	 * @param ProductsUsedsInOrderDetailDTO
	 * @return Long[]
	 * @author DMarin
	 * @since 17-02-2017
	 */
	
	List<Integer> getProductsUsedsInOrderDetail(ProductsUsedsInOrderDetailDTO dto);
	
	/*
	 * Cost
	 */

	/**
	 * Agrega un nuevo Cost
	 * @param dto dto con datos de cost
	 * @return id de registo agregado
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	Long addCost(AddCostDTO dto);
	/**
	 * Actualiza datos de Cost
	 * @param dto dto con datos de cost
	 * @return numero de registros actualizados
	 * @throws CostNotFoundException 
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	int updCost(UpdCostDTO dto) throws CostNotFoundException;
	/**
	 * Obtiene datos de Cost
	 * @param id_cost id cost
	 * @return objeto de dominio {Cost}
	 * @throws CostNotFoundException 
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	Cost getCostById(Long id_cost) throws CostNotFoundException;
	/**
	 * obtiene todos los cost que hay
	 * @return lista de objetos de dominio {Cost}
	 * @author juan carlos
	 * @since 15-03-2015
	 */
	List<Cost> getAllCost();
	
	/*
	 * finder cost
	 */
	
	/**
	 * Elimina cost por id_supplier
	 * @param id_supplier
	 * @return numero de cost esliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCostByIdSupplier(Long id_supplier);
	
	
	/*
	 * CategoryProduct
	 */
	
	/**
	 * Agrega una nueva category product
	 * @param dto
	 * @return Id category product
	 * @author jorge
	 * @since 10-12-2014
	 */
	Long addCategoryProduct(AddCategoryProductDTO dto);
	
	/**
	 * Actualiza los datos de un category product
	 * @param dto Dto con datos
	 * @return Objeto de dominio {CategoryProduct}
	 * @throws CategoryProductNotFoundException
	 * @author jorge
	 * @since 10-12-2014
	 */
	int updCategoryProduct(UpdCategoryProductDTO dto) throws CategoryProductNotFoundException;
	
	/**
	 * Obtiene un category product por id
	 * @param id_category_product Id
	 * @return Objeto de dominio {CategoryProduct}
	 * @throws CategoryProductNotFoundException
	 * @author jorge
	 * @since 10-12-2014
	 */
	CategoryProduct getCategoryProductById(Long id_category_product) throws CategoryProductNotFoundException;
	
	/**
	 * Obtiene un listado de category product
	 * @return Lista de objetos de dominio {CategoryProduct}
	 * @author jorge
	 * @since 10-12-2014
	 */
	List<CategoryProduct> getAllCategoryProduct();

	/**
	 * finder category product
	 */
	
	/**
	 * Obtiene un listado de category producto a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos
	 * @author jorge
	 * @since 12-12-2014
	 */
	List<CategoryProductDTO> selectCategoryProductByCriteria(CategoryProductSearchCriteria dto);
	/**
	 * Elimina categoryProduct por id_supplier
	 * @param id_supplier
	 * @return numero de category product eliminados
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delCategoryProductByIdSupplier(Long id_supplier);
	
	/**
	 * Elimina categoryProduct por id_category_product
	 * @param id_category_product
	 * @return numero de category_product eliminados
	 * @author Jazz
	 * @since 09-08-2016
	 */
	int delCategoryProductByIdCategoryProduct(Long id_category_product);
	
	
	/**
	 * obtiene categoryProduct por id_supplier
	 * @param id_supplier
	 * @return lista de objetos de dominio {categoryProduct}
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	List<CategoryProduct> getCategoryProductByIdSupplier (Long id_supplier);
	
	/*
	 * Brand (Mark)
	 */
	/**
	 * Obtiene un listado de marcas de producto a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos
	 */
	List<BrandProductDTO> selectBrandProductByCriteria(BrandProductSearchCriteria dto);
	
	/**
	 * Agrega una nueva brand product
	 * @param dto
	 * @return Id brand 
	 * @author Jazz
	 * @since 10-08-2016
	 */
	Long addBrand(AddBrandProductDTO dto);
	
	/**
	 * Actualiza los datos de una brand product
	 * @param dto Dto con datos
	 * @return Objeto de dominio {brand}
	 * @throws BrandProductNotFoundException
	 * @author Jazz
	 * @since 10-08-2016
	 */
	int updBrandProduct(UpdBrandProductDTO dto) throws BrandProductNotFoundException;
	
	/**
	 * Obtiene una marca product por id
	 * @param id_brand Id
	 * @return Objeto de dominio {brand}
	 * @throws BrandProductNotFoundException
	 * @author Jazz
	 * @since 10-18-2016
	 */
	Brand getBrandById(Long id_brand) throws BrandProductNotFoundException;
	
	/**
	 * Elimina brand por id_brand
	 * @param id_brand
	 * @return numero de brand eliminados
	 * @author Jazz
	 * @since 10-08-2016
	 */
	int delBrandByIdBrand(Long id_brand);
	
	/*
	 * Product
	 */
	
	/**
	 * Agrega un nuevo objeto de dominio {Product}
	 * @param dto Dto con datos
	 * @return Id del objeto de dominio {Product} agregado
	 * @author jorge
	 * @since 10-12-2014
	 */
	Long addProduct(AddProductDTO dto);
	
	/**
	 * Actualiza el objeto de dominio {Product}
	 * @param dto Dto con datos
	 * @return Cantidad de registros de objeto de dominio {Product} actualizado
	 * @throws ProductNotFoundException
	 * @author jorge
	 * @since 10-12-2014
	 */
	int updProduct(UpdProductDTO dto) throws ProductNotFoundException;
	
	/**
	 * Obtiene un objeto de dominio {Product} a partir del id
	 * @param id_product Id objeto de dominio
	 * @return Objeto de dominio {Product}
	 * @throws ProductNotFoundException
	 * @author jorge
	 * @since 10-12-2014
	 */
	Product getProductById(Long id_product) throws ProductNotFoundException;
	
	/**
	 * Obtiene una lista de objetos de dominio {Product}
	 * @return List<Product>
	 * @author jorge
	 * @since 10-12-2014
	 */
	List<Product> getAllProduct();
	
	/**
	 * Obtiene un listado de product a partir de un criterio de busqueda
	 * @param dto Dto con criterio de busqueda
	 * @return Lista de objetos List<ProductDTO>
	 * @author jorge
	 * @since 12-12-2014
	 */
	List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto);
	/**
	 * varifica que el codigo de producto que se va a agregar no exista
	 * @param dto con datos de busqueda
	 * @throws ProductCodeDuplicateException
	 * @author juan carlos
	 * @since 24-03-2015
	 */
	void getProductCodeAvaibleByCriteria(ProductCodeAvailableSearchCriteria dto) throws ProductCodeDuplicateException;
	/**
	 * obtiene lista de product po id_category_product
	 * @param id_category_product
	 * @return lista de objetos de dominio {product}
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	List<Product> getProductByIdCategoryProduct(Long id_category_product);
	/**
	 * elimina product por id_category_product
	 * @param id_category_product
	 * @return numero de product que se eliminaron
	 * @author JuanCarlosRamosCampos
	 * @since 23-11-2015
	 */
	int delProductByIdCategoryProduct(Long id_category_product);
	/**
	 * elimina product por id_product
	 * @param id_product
	 * @return numero de product que se eliminaron
	 * @author Jazz
	 */
	int delProductById(Long id_product);
	/**
	 * agrega venta
	 * @param AddTrxDTO
	 * @return número de orden creada
	 * @author David Rosales Muñoz
	 */
	Long addSale(AddTrxDTO dto) throws ProductNotFoundException;
	
	/*
	 * Obtiene una lista de las transacciones
	 * @param id_supplier
	 * @param invoice
	 * @author DMarin
	 * @since 16-08-2016
	 * @return List<TransactionDTO>
	 */
	
	List<WSTransactionDTO> getTransactionList(WSTransactionListDTO dto);
	
	/*
	 * Obtiene la informaci�n de una transacci�n a�adiendo el id_order
	 * @param id_order
	 * @author DMarin
	 * @since 17-08-2016
	 * @return TransactionDTO
	 */
	WSTransactionDTO getTransactionById(Long id_order);
	
	/*
	 * Obtiene una lista de los detalles de productos vendidos en una orden
	 * @param ProductsDetailCriteria
	 * @author DMarin
	 * @since 17-08-2016
	 * @return List<WSProductDetailDTO>
	 */
	List<WSProductDetailDTO> getProductsDetailByIdOrderAndTypeTrx(WSProductsDetailCriteria criteria);
	
	/**
	 *obtiene lista de transacciones por fecha
	 *@param date
	 *@return lista de objetos de dominio {ReportDTO}
	 *@author Gaby
	 *@since 16-08-2016 
	 * */
	List<ReportDTO> getReportByDate(ReportSearchCriteria dto);
	
	/*
	 * obtiene lista de productos especificos de algun supplier
	 * @param WSProductCriteria
	 * @return List<WSProduct>
	 * @author DMarin
	 * @since 16-08-2016
	 */
	List<WSProduct> getProductsByIdSupplier(WSProductCriteria criteria);

	/*
	 * Obtiene la imagen de un producto
	 * @param id_product:INTEGER
	 * @return Strin
	 * @author DMarin
	 * @since 25-08-2016
	 */
	String getImageProductyById(Long id_product);
	
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
	 * Obtiene listado de informaci�n con la venta por vendedor filtrado por fecha
	 * @param String
	 * @return List<SaleByDriDTO>
	 * @author DMarin
	 * @since 09/01/2016
	 */
	List<SaleByDriDTO> getSaleByDri(String delivery);
	
	/*
	 * Obtiene el listado de nombre de productos de un supplier ordenados de manera ascendente
	 * @param id_supplier
	 * @return List<String>
	 * @author Daniel Marin
	 * @since 10-05-2017
	 */
	List<String> getProductsNameByIdSupplier(Long id_supplier);
	
	/*
	 * Obtiene el listado de precios de los productos para el reporte trx/productos
	 * @param id_order
	 * @return List<Double>
	 * @author Daniel Marin
	 * @since 10-05-2017
	 */
	List<Double> getPriceProductsByIdOrder(Long id_order);
	
	List<ProductToSubAlmacenDTO> getProductsToCreateSubStock(ProductSearchCriteria criteria);
	
	/*
	 * StoreCategoryProduct
	 */
	
	/*
	 * Agrega un registro a store_category_product
	 * @param StoreCategoryProduct
	 * @return Long
	 * @since 21/03/2017
	 * @author DMarin
	 */
	Long addStoreCategoryProdut(StoreCategoryProduct dto);
	
	/*
	 * Actualiza un registro a store_category_product
	 * @param StoreCategoryProduct
	 * @return Long
	 * @since 21/03/2017
	 * @author DMarin
	 */
	int updateStoreCategoryProduct(StoreCategoryProduct dto);
	
	/*
	 * Elimina un registro a store_category_product
	 * @param StoreCategoryProduct
	 * @return Long
	 * @since 21/03/2017
	 * @author DMarin
	 */
	int deleteStoreCategoryProduct(Long id_store_category_product);	
	
	/*
	 * Finder/StoreCategoryProduct
	 */
	
	/**
	 * Obtiene los productos con los precios relacionados a la categor�a
	 * @param ProductByCategoryStoreToUpdateCriteria
	 * @return List<ProductByCategoryStoreToUpdate>
	 * @since 21/03/2017
	 * @author DMarin
	 */
	List<ProductByCategoryStoreToUpdateDTO> getProductByCategoryStoreToUpdate(ProductByCategoryStoreToUpdateCriteria criteria);
	
	/*
	 * Obtiene listado de categorias con los porcentajes de tiendas que se les han y no vendido, así como el total
	 * @param Long (id_supplier)
	 * @return PerTotalVisitedDTO
	 * @author DMarin
	 * @since 17-05-2017
	 */
	PerTotalVisitedDTO getPerTotalVisited(PerTotalVisitedCriteria criteria);
	
	/*
	 * Almacen
	 */
	
	Long addAlmacen(Almacen almacen);
	
	int updateAlmacen(Almacen almacen);
	
	int deleteAlmacen(Long id_almacen);
	
	Almacen getAlmacenById(Long id_almacen);
	
	List<Almacen> getAlmacens(AlmacenExample example);

	List<Almacen> getAlmacens(Long id_supplier);
	
	Almacen getAlmacenByIdSupplier(Long id_supplier);
	
	List<Almacen> getSubAlmacensByIdSupplier(Long id_supplier);
	
	AlmacenAndProductsDTO getAlmacenAndProductsByIdAlmacen(Long id_almacen, Long id_supplier);

	AlmacenAndProductsDTO getAlmacenAndProductsByIdSupplier(Long id_supplier);
	
	/*
	 * Finder/Almacen
	 */
	
	Long getAlmacenNextValue();
	
	List<AlmacenInfoDTO> getSubAlmacenInfo(Long id_supplier);
	
	AlmacenInfoDTO getSubAlmacenInfoByIdAlmacen(Long id_almacen);
	
	AlmacenInfoAndProductsDTO getAlmacenInfoAndProductsByIdAlmacen(Long id_almacen, Long id_supplier);
	
	int recoverProductFromSale(RecoverProductFromSaleDTO params);
	
	/*
	 * Almacen Full Methods
	 */
	
	boolean addAlmacenFull(AddAlmacenAndProductsDTO dto);
	
	boolean updateAlmacenFull(UpdateAlmacenAndProductsDTO dto);
	
	boolean addProductsToAlmacen(Long id_almacen, List<ProductAlmacen> products);
	
	boolean addSubAlmacenFull(AddAlmacenAndProductsDTO dto);
	
	boolean updateSubAlmacenFull(UpdateAlmacenAndProductsDTO dto);
	
	boolean addProductsToSubAlmacen(Long id_supplier, Long id_almacen, List<ProductAlmacen> products);
	
	boolean liquidateSubAlmacen(Long id_almacen);
	
	boolean removeProductsNotSale(Long id_almacen);
	
	/*
	 * ProductsAlmacen
	 */
	
	Long addProductAlmacen(ProductAlmacen productAlmacen);
	
	int updatProducteAlmacen(ProductAlmacen productAlmacen);
	
	int deleteProductAlmacen(Long id_product_almacen);
	
	ProductAlmacen getProductAlmacenById(Long id_product_almacen);
	
	List<ProductAlmacen> getProductsAlmacen(ProductAlmacenExample example);
	
	List<ProductAlmacen> getProductsAlmacen(Long id_almacen);
	
	boolean removeAlmacen(Long id_almacen);
	
	/*
	 * Finder/ProductAlmacen
	 */
	
	Long getProductAlmacenNextValue();
	
	List<ProductAlmacenDTO> getProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria);
	
	int deleteProductAlmacenByIdAlmacen(Long id_almacen);
	
	List<ProductAlmacenDTO> getProductsFromAlmacen(ProductByIdAlmacenCriteria criteria);
	
	List<ProductAlmacenDTO> getSaledProductsByIdAlmacen(ProductByIdAlmacenCriteria criteria);
	
	boolean addMissingProducts(Long id_almacen, Long id_supplier);
	
	/*
	 * ConfigurationStock
	 */
	
	Long addConfigurationStock(ConfigurationStock configurationStock);
	
	int updateConfigurationStock(ConfigurationStock configurationStock);
	
	int deleteConfigurationStock(Long id_configuration_stock);
	
	List<ConfigurationStock> getConfigurationsStockByIdSupplier(Long id_supplier);
	
	boolean deleteFullConfigurationStock(Long id_configuration_stock);
	
	/*
	 * Finder/ConfigurationStock
	 */
	
	Long getConfigurationStockNextValue();
	
	/*
	 * ConfigurationStockProduct
	 */
	
	Long addConfigurationStockProduct(ConfigurationStockProduct configurationStockProduct);
	
	int updateConfigurationStockProduct(ConfigurationStockProduct configurationStockProduct);
	
	int deleteConfigurationStockProduct(Long id_configuration_stock_product);
	
	List<ConfigurationStockProduct> getConfigurationsStockByIdConfigurationStock(Long id_configuration_stock);
	
	/*
	 * Finder/ConfigurationStockProduct
	 */
	
	Long getConfigurationStockProduct();
	
	List<ConfigurationStockProductDTO> getConfigurationStockProductByIdCondigurationStock(ConfigurationStockProductByIdCondigurationStockCriteria criteria);
	
	boolean addFullConfigurationStockProduct(AddConfigurationStockProductDTO dto);
	
	int deleteConfigurationStockProductByIdConfigurationStock(Long id_configuration_stock);
	
	/*
	 * Orders To Almacen
	 */
	int updateOrderAndRecoverToAlmacen(UpdateOrderAndRecoverToAlmacenDTO dto);
	
}
