package com.retailsbs.logistikapp.financial.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.retailsbs.logistikapp.email.SendEmail;
import com.retailsbs.logistikapp.financial.dao.AlmacenDAO;
import com.retailsbs.logistikapp.financial.dao.CategoryProductDAO;
import com.retailsbs.logistikapp.financial.dao.ConfigurationStockDAO;
import com.retailsbs.logistikapp.financial.dao.ConfigurationStockProductDAO;
import com.retailsbs.logistikapp.financial.dao.CostDAO;
import com.retailsbs.logistikapp.financial.dao.FinderAlmacen;
import com.retailsbs.logistikapp.financial.dao.FinderBrandProductDAO;
import com.retailsbs.logistikapp.financial.dao.FinderCategoryProductDAO;
import com.retailsbs.logistikapp.financial.dao.FinderConfigurationStock;
import com.retailsbs.logistikapp.financial.dao.FinderConfigurationStockProduct;
import com.retailsbs.logistikapp.financial.dao.FinderCostDAO;
import com.retailsbs.logistikapp.financial.dao.FinderOrderDAO;
import com.retailsbs.logistikapp.financial.dao.FinderOrderDetailDAO;
import com.retailsbs.logistikapp.financial.dao.FinderProductAlmacen;
import com.retailsbs.logistikapp.financial.dao.FinderProductDAO;
import com.retailsbs.logistikapp.financial.dao.FinderStoreCategoryProductDAO;
import com.retailsbs.logistikapp.financial.dao.FinderWebServiceDAO;
import com.retailsbs.logistikapp.financial.dao.OrderDAO;
import com.retailsbs.logistikapp.financial.dao.OrderDetailDAO;
import com.retailsbs.logistikapp.financial.dao.ProductAlmacenDAO;
import com.retailsbs.logistikapp.financial.dao.ProductDAO;
import com.retailsbs.logistikapp.financial.dao.BrandDAO;
import com.retailsbs.logistikapp.financial.dao.StoreCategoryProductDAO;
import com.retailsbs.logistikapp.financial.domain.Almacen;
import com.retailsbs.logistikapp.financial.domain.Brand;
import com.retailsbs.logistikapp.financial.domain.CategoryProduct;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStock;
import com.retailsbs.logistikapp.financial.domain.ConfigurationStockProduct;
import com.retailsbs.logistikapp.financial.domain.Cost;
import com.retailsbs.logistikapp.financial.domain.Order;
import com.retailsbs.logistikapp.financial.domain.OrderDetail;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.domain.ProductAlmacen;
import com.retailsbs.logistikapp.financial.domain.StoreCategoryProduct;
import com.retailsbs.logistikapp.financial.dto.AddAlmacenAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AddBrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddCategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddConfigurationStockProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddCostDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddTrxDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenExample;
import com.retailsbs.logistikapp.financial.dto.AlmacenInfoAndProductsDTO;
import com.retailsbs.logistikapp.financial.dto.AlmacenInfoDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductDTO;
import com.retailsbs.logistikapp.financial.dto.BrandProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.CategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductExample;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockExample;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductByIdCondigurationStockCriteria;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductDTO;
import com.retailsbs.logistikapp.financial.dto.ConfigurationStockProductExample;
import com.retailsbs.logistikapp.financial.dto.CostExample;
import com.retailsbs.logistikapp.financial.dto.ExtCategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketCriteria;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketDTO;
import com.retailsbs.logistikapp.financial.dto.InfoTicketDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleFullDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleReportDTO;
import com.retailsbs.logistikapp.financial.dto.NoStoreVisitedByIdStoreCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.OrderDetailExample;
import com.retailsbs.logistikapp.financial.dto.OrderExample;
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
import com.retailsbs.logistikapp.financial.dto.ProductExample;
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
import com.retailsbs.logistikapp.financial.dto.TrxDTO;
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
import com.retailsbs.logistikapp.financial.service.FinancialService;

public class FinancialServiceImpl implements FinancialService {

	private OrderDAO dao_order;
	private OrderDetailDAO dao_order_detail;
	private CostDAO dao_cost;
	private FinderCostDAO finder_cost;
	private FinderOrderDetailDAO finder_order_detail;
	private FinderOrderDAO finder_order;
	private CategoryProductDAO dao_category_product;
	private ProductDAO dao_product;
	private FinderCategoryProductDAO finder_category_product;
	private FinderBrandProductDAO finder_brand_product;
	private BrandDAO dao_brand_product;
	private FinderWebServiceDAO finder_webservice;
	private StoreCategoryProductDAO dao_store_category_product;
	private FinderStoreCategoryProductDAO finder_store_category_product;
	private AlmacenDAO dao_almacen;
	private FinderAlmacen finder_almacen;
	private ProductAlmacenDAO dao_product_almacen;
	private FinderProductAlmacen finder_product_almacen;
	private ConfigurationStockDAO dao_configuration_stock;
	private FinderConfigurationStock finder_configuration_stock;
	private ConfigurationStockProductDAO dao_configuration_stock_product;
	private FinderConfigurationStockProduct finder_configuration_stock_product;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	private float iva = 0.16f;
	private float ieps = 0.08f;
	
	public BrandDAO getDao_brand_product() {
		return dao_brand_product;

	}

	public void setDao_brand_product(BrandDAO dao_brand_product) {
		this.dao_brand_product = dao_brand_product;
	}

	public void setFinder_brand_product(
			FinderBrandProductDAO finder_brand_product) {
		this.finder_brand_product = finder_brand_product;
	}

	private FinderProductDAO finder_product;

	public void setFinder_order(FinderOrderDAO finder_order) {
		this.finder_order = finder_order;
	}

	public void setFinder_order_detail(FinderOrderDetailDAO finder_order_detail) {
		this.finder_order_detail = finder_order_detail;
	}

	public void setFinder_cost(FinderCostDAO finder_cost) {
		this.finder_cost = finder_cost;
	}

	public void setDao_order(OrderDAO dao_order) {
		this.dao_order = dao_order;
	}

	public void setDao_order_detail(OrderDetailDAO dao_order_detail) {
		this.dao_order_detail = dao_order_detail;
	}

	public void setDao_cost(CostDAO dao_cost) {
		this.dao_cost = dao_cost;
	}

	public void setDao_category_product(CategoryProductDAO dao_category_product) {
		this.dao_category_product = dao_category_product;
	}

	public void setDao_product(ProductDAO dao_product) {
		this.dao_product = dao_product;
	}

	public void setFinder_category_product(
			FinderCategoryProductDAO finder_category_product) {
		this.finder_category_product = finder_category_product;
	}

	public void setFinder_product(FinderProductDAO finder_product) {
		this.finder_product = finder_product;
	}

	public void setFinder_webservice(FinderWebServiceDAO finder_webservice) {
		this.finder_webservice = finder_webservice;
	}

	public void setDao_store_category_product(
			StoreCategoryProductDAO dao_store_category_product) {
		this.dao_store_category_product = dao_store_category_product;
	}

	public void setFinder_store_category_product(
			FinderStoreCategoryProductDAO finder_store_category_product) {
		this.finder_store_category_product = finder_store_category_product;
	}

	public void setDao_almacen(AlmacenDAO dao_almacen) {
		this.dao_almacen = dao_almacen;
	}

	public void setFinder_almacen(FinderAlmacen finder_almacen) {
		this.finder_almacen = finder_almacen;
	}

	public void setDao_product_almacen(ProductAlmacenDAO dao_product_almacen) {
		this.dao_product_almacen = dao_product_almacen;
	}

	public void setFinder_product_almacen(
			FinderProductAlmacen finder_product_almacen) {
		this.finder_product_almacen = finder_product_almacen;
	}

	public void setDao_configuration_stock(
			ConfigurationStockDAO dao_configuration_stock) {
		this.dao_configuration_stock = dao_configuration_stock;
	}

	public void setFinder_configuration_stock(
			FinderConfigurationStock finder_configuration_stock) {
		this.finder_configuration_stock = finder_configuration_stock;
	}

	public void setDao_configuration_stock_product(
			ConfigurationStockProductDAO dao_configuration_stock_product) {
		this.dao_configuration_stock_product = dao_configuration_stock_product;
	}

	public void setFinder_configuration_stock_product(
			FinderConfigurationStockProduct finder_configuration_stock_product) {
		this.finder_configuration_stock_product = finder_configuration_stock_product;
	}

	/**
	 * order
	 */

	public Long addOrder(AddOrderDTO dto) {
		Order record = new Order();
		// Setea los datos
		record.setDelivery(dto.getDelivery());
		record.setId_supplier(dto.getId_supplier());
		record.setInvoice(dto.getInvoice());
		record.setModified(dto.getModified());
		record.setStatus(dto.getStatus());

		// persiste el objeto
		Long id = dao_order.insert(record);
		return id;
	}

	public int updOrder(UpdOrder dto) throws OrderNotFoundException {
		Order record = dao_order.selectByPrimaryKey(dto.getId_order());

		// se verifica id_order exista
		if (record == null)
			throw new OrderNotFoundException("Order con id = "
					+ dto.getId_order() + " no existe.");
		// se setean los datos
		record.setDelivery(dto.getDelivery());
		record.setId_supplier(dto.getId_supplier());
		record.setInvoice(dto.getInvoice());
		record.setModified(dto.getModified());
		record.setStatus(dto.getStatus());
		// se persiste el objeto
		int row = dao_order.updateByPrimaryKeySelective(record);

		return row;
	}

	public Order getOrderById(Long id_order) throws OrderNotFoundException {
		Order order = dao_order.selectByPrimaryKey(id_order);
		if (order == null)
			throw new OrderNotFoundException("Order id = " + id_order
					+ " no existe.");
		return order;
	}

	public List<Order> getAllOrder() {
		OrderExample example = new OrderExample();
		return dao_order.selectByExample(example);
	}

	public List<Order> getOrderByIdSupplier(Long id_supplier) {
		OrderExample example = new OrderExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);

		return dao_order.selectByExample(example);
	}

	/*
	 * Finder/Order
	 */

	public int delOrderByIdSupplier(Long id_supplier) {
		return finder_order.delOrderByIdSupplier(id_supplier);
	}

	@Override
	public List<ReportExtDTO> getReportExtByDate(ReportExtSearchCriteria dto) {
		return finder_order.getReportExtByDate(dto);
	}

	@Override
	public List<OrdersInfoToReportDTO> getOrdersInfoToReport(
			OrdersInfoToReportCriteria criteria) {
		return finder_order.getOrdersInfoToReport(criteria);
	}

	@Override
	public List<ReportByDriDTO> getReportByDri(ReportByDriCriteria criteria) {
		return finder_order.getReportByDri(criteria);
	}

	/*
	 * OrderDetail
	 */

	public Long addOrderDetail(AddOrderDetailDTO dto) {
		OrderDetail record = new OrderDetail();
		// se settea datos
		record.setId_order(dto.getId_order());
		record.setId_product(dto.getId_product());
		record.setPrice_sug(dto.getPrice_sug());
		record.setQuantity(dto.getQuantity());
		record.setTax(dto.getTax());
		// se persiste el objeto
		Long id = dao_order_detail.insert(record);
		return id;
	}

	public int updOrderDetail(UpdOrderDetailDTO dto)
			throws OrderDetailNotFoundException {
		OrderDetail record = dao_order_detail.selectByPrimaryKey(dto
				.getId_order_detail());

		if (record == null)
			throw new OrderDetailNotFoundException("OrderDetail id = "
					+ dto.getId_order_detail() + " no existe");
		// seteo de datos
		record.setId_order(dto.getId_order());
		record.setId_product(dto.getId_product());
		record.setPrice_sug(dto.getPrice_sug());
		record.setQuantity(dto.getQuantity());
		record.setTax(dto.getTax());
		// se persiste el objeto
		int row = dao_order_detail.updateByPrimaryKeySelective(record);
		return row;
	}

	public OrderDetail getOrderDetailById(Long id_order_detail)
			throws OrderDetailNotFoundException {
		OrderDetail orderDetail = dao_order_detail
				.selectByPrimaryKey(id_order_detail);
		if (orderDetail == null)
			throw new OrderDetailNotFoundException("orderDetail id = "
					+ id_order_detail + " no existe.");
		return orderDetail;
	}

	public List<OrderDetail> getAllOrderDetail() {
		OrderDetailExample example = new OrderDetailExample();
		return dao_order_detail.selectByExample(example);
	}

	/**
	 * finder order detail
	 */

	public int delOrderDetailByIdOrder(Long id_order) {
		return finder_order_detail.delOrderDetailByIdOrder(id_order);
	}

	public int delOrderDetailByIdProduct(Long id_product) {
		return finder_order_detail.delOrderDetailByIdProduct(id_product);
	}

	@Override
	public List<Integer> getProductsUsedsInOrderDetail(
			ProductsUsedsInOrderDetailDTO dto) {
		return finder_order_detail.getProductsUsedsInOrderDetail(dto);
	};

	/*
	 * Cost
	 */

	public Long addCost(AddCostDTO dto) {
		Cost record = new Cost();
		// seteo de datos
		record.setAccounting(dto.getAccounting());
		record.setActive(dto.getActive());
		record.setAmount(dto.getAmount());
		record.setComment(dto.getComment());
		record.setCreated(dto.getCreated());
		record.setId_retail(dto.getId_retail());
		record.setId_supplier(dto.getId_supplier());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		// se persiste el objeto
		Long id = dao_cost.insert(record);
		return id;
	}

	public int updCost(UpdCostDTO dto) throws CostNotFoundException {
		Cost record = dao_cost.selectByPrimaryKey(dto.getId_cost());
		if (record == null)
			throw new CostNotFoundException("Cost id =" + dto.getId_cost()
					+ " no existe.");
		// se setean los datos
		record.setAccounting(dto.getAccounting());
		record.setActive(dto.getActive());
		record.setAmount(dto.getAmount());
		record.setComment(dto.getComment());
		record.setCreated(dto.getCreated());
		record.setId_retail(dto.getId_retail());
		record.setId_supplier(dto.getId_supplier());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		// se persiste el objeto
		int row = dao_cost.updateByPrimaryKeySelective(record);

		return row;
	}

	public Cost getCostById(Long id_cost) throws CostNotFoundException {
		Cost cost = dao_cost.selectByPrimaryKey(id_cost);
		if (cost == null)
			throw new CostNotFoundException("Cost id = " + id_cost
					+ " no existe.");
		return cost;
	}

	public List<Cost> getAllCost() {
		CostExample example = new CostExample();
		return dao_cost.selectByExample(example);
	}

	/**
	 * finder cost
	 */

	public int delCostByIdSupplier(Long id_supplier) {
		return finder_cost.delCostByIdSupplier(id_supplier);
	}

	/*
	 * CategoryProduct
	 */

	public Long addCategoryProduct(AddCategoryProductDTO dto) {
		// Se crea un nuevo objeto de dominio
		// a partir de los datos del dto
		CategoryProduct record = new CategoryProduct();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_supplier(dto.getId_supplier());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());

		// Se persiste el objeto
		Long id = dao_category_product.insert(record);

		return id;
	}

	public int updCategoryProduct(UpdCategoryProductDTO dto)
			throws CategoryProductNotFoundException {
		// Se obtiene objeto de dominio
		CategoryProduct record = dao_category_product.selectByPrimaryKey(dto
				.getId_category_product());
		if (record == null)
			throw new CategoryProductNotFoundException("CategoryProduct id="
					+ dto.getId_category_product() + " no existe");

		// Se setea objeto de dominio
		// a partir de los datos del dto
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_supplier(dto.getId_supplier());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());

		// Se persiste el objeto
		int rows = dao_category_product.updateByPrimaryKeySelective(record);

		return rows;
	}

	public CategoryProduct getCategoryProductById(Long id_category_product)
			throws CategoryProductNotFoundException {
		// Se obtiene objeto de dominio
		CategoryProduct record = dao_category_product
				.selectByPrimaryKey(id_category_product);
		if (record == null)
			throw new CategoryProductNotFoundException("CategoryProcuct id="
					+ id_category_product + " no existe");

		// Se retorna objeto de dominio
		return record;
	}

	public List<CategoryProduct> getAllCategoryProduct() {

		// Se define un criterio de orden
		CategoryProductExample example = new CategoryProductExample();
		example.setOrderByClause("orderby");

		// Se obtiene la lista de objetos de dominio
		List<CategoryProduct> list = dao_category_product
				.selectByExample(example);

		return list;
	}

	/**
	 * finder category product
	 */

	public List<CategoryProductDTO> selectCategoryProductByCriteria(
			CategoryProductSearchCriteria dto) {
		return finder_category_product.selectCategoryProductByCriteria(dto);
	}

	public List<CategoryProduct> getCategoryProductByIdSupplier(Long id_supplier) {
		CategoryProductExample example = new CategoryProductExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);

		return dao_category_product.selectByExample(example);
	}

	public int delCategoryProductByIdSupplier(Long id_supplier) {
		return finder_category_product
				.delCategoryProductByIdSupplier(id_supplier);
	}

	public int delCategoryProductByIdCategoryProduct(Long id_category_product) {
		return dao_category_product.deleteByPrimaryKey(id_category_product);
	}

	/**
	 * finder brand (mark) product
	 */

	public List<BrandProductDTO> selectBrandProductByCriteria(
			BrandProductSearchCriteria dto) {
		return finder_brand_product.selectBrandProductByCriteria(dto);
	}

	public Long addBrand(AddBrandProductDTO dto) {
		// Se crea un nuevo objeto de dominio
		// a partir de los datos del dto
		Brand record = new Brand();
		record.setId_supplier(dto.getId_supplier());
		record.setName(dto.getName());
		record.setActive(dto.getActive());
		// Se persiste el objeto
		Long id = dao_brand_product.insert(record);

		return id;
	}

	public int updBrandProduct(UpdBrandProductDTO dto)
			throws BrandProductNotFoundException {
		// Se obtiene objeto de dominio
		Brand record = dao_brand_product.selectByPrimaryKey(dto.getId_brand());
		if (record == null)
			throw new BrandProductNotFoundException("Brand id="
					+ dto.getId_brand() + " no existe");

		// Se setea objeto de dominio
		// a partir de los datos del dto
		record.setId_supplier(dto.getId_supplier());
		record.setName(dto.getName());
		record.setActive(dto.getActive());

		// Se persiste el objeto
		int rows = dao_brand_product.updateByPrimaryKey(record);

		return rows;
	}

	public Brand getBrandById(Long id_brand)
			throws BrandProductNotFoundException {
		// Se obtiene objeto de dominio
		Brand record = dao_brand_product.selectByPrimaryKey(id_brand);
		if (record == null)
			throw new BrandProductNotFoundException("Brand id=" + id_brand
					+ " no existe");

		// Se retorna objeto de dominio
		return record;
	}

	public int delBrandByIdBrand(Long id_brand) {
		return dao_brand_product.deleteByPrimaryKey(id_brand);
	}

	/*
	 * Product
	 */

	public Long addProduct(AddProductDTO dto) {
		// Se crea un objeto de dominio
		// a partir de los datos del dto
		Product record = new Product();
		record.setId_category_product(dto.getId_category_product());
		record.setCreated(dto.getCreated());
		record.setModified(dto.getModified());
		record.setLogin(dto.getLogin());
		record.setOrderby(dto.getOrderby());
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setName_short(dto.getName_short());
		record.setName_long(dto.getName_long());
		record.setPrice_sale(dto.getPrice_sale());
		record.setPrice_cost(dto.getPrice_cost());
		record.setImage(dto.getImage());
		record.setId_brand(dto.getId_brand());
		record.setFlag(dto.getFlag());
		record.setPiece_in_box(dto.getPiece_in_box());
		record.setPrice_cost_box(dto.getPrice_cost_box());
		record.setType(dto.getType());
		record.setPrice_sale_box(dto.getPrice_sale_box());
		record.setTax(dto.getTax());
		
		// Se persiste el objeto
		Long id = dao_product.insert(record);

		return id;
	}

	public int updProduct(UpdProductDTO dto) throws ProductNotFoundException {
		// Se obtiene objeto de dominio
		Product record = dao_product.selectByPrimaryKey(dto.getId_product());
		// Se controla que exista
		if (record == null)
			throw new ProductNotFoundException("Product id="
					+ dto.getId_product() + " no existe");

		// Se actualiza objeto de dominio
		// con los datos del dto
		record.setId_category_product(dto.getId_category_product());
		record.setCreated(dto.getCreated());
		record.setModified(dto.getModified());
		record.setLogin(dto.getLogin());
		record.setOrderby(dto.getOrderby());
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setName_short(dto.getName_short());
		record.setName_long(dto.getName_long());
		record.setPrice_sale(dto.getPrice_sale());
		record.setPrice_cost(dto.getPrice_cost());
		record.setImage(dto.getImage());
		record.setId_brand(dto.getId_brand());
		record.setFlag(dto.getFlag());
		record.setPiece_in_box(dto.getPiece_in_box());
		record.setPrice_cost_box(dto.getPrice_cost_box());
		record.setType(dto.getType());
		record.setPrice_sale_box(dto.getPrice_sale_box());
		record.setTax(dto.getTax());
		
		// Se persiste el objeto
		int rows = dao_product.updateByPrimaryKeySelective(record);

		return rows;
	}

	public Product getProductById(Long id_product)
			throws ProductNotFoundException {
		// Se obtiene objeto de dominio
		Product record = dao_product.selectByPrimaryKey(id_product);
		// Se controla que exista
		if (record == null)
			throw new ProductNotFoundException("Product id=" + id_product
					+ " no existe");

		// Se retorna objeto de dominio
		record.setType(record.getType() == null ? "PCS" : record.getType());
		return record;

	}

	public List<Product> getAllProduct() {
		// Se define criterio de orden
		ProductExample example = new ProductExample();
		example.setOrderByClause("orderby");
		// Se obtiene lista de objetos de dominio
		List<Product> list = dao_product.selectByExample(example);

		// Se retorna lista
		return list;
	}

	public List<ProductDTO> selectProductByCriteria(ProductSearchCriteria dto) {
		return finder_product.selectProductByCriteria(dto);
	}

	public void getProductCodeAvaibleByCriteria(
			ProductCodeAvailableSearchCriteria dto)
			throws ProductCodeDuplicateException {
		ProductExample example = new ProductExample();
		if (dto.getId_product() == null) {
			example.createCriteria().andActiveEqualTo(dto.getActive())
					.andCodeEqualTo(dto.getCode());
		} else {
			example.createCriteria().andActiveEqualTo(dto.getActive())
					.andCodeEqualTo(dto.getCode())
					.andId_productNotEqualTo(dto.getId_product());
		}
		List<Product> list = dao_product.selectByExample(example);

		if (list.size() > 0) {
			CategoryProductExample cpexample = new CategoryProductExample();
			cpexample
					.createCriteria()
					.andId_category_productEqualTo(
							list.get(0).getId_category_product())
					.andId_supplierEqualTo(dto.getId_supplier());
			if (dao_category_product.selectByExample(cpexample).size() > 0) {
				throw new ProductCodeDuplicateException("El codigo "
						+ dto.getCode() + " ya esta definido.");
			}
		}
	}

	public List<Product> getProductByIdCategoryProduct(Long id_category_product) {
		ProductExample example = new ProductExample();
		example.createCriteria().andId_category_productEqualTo(
				id_category_product);

		return dao_product.selectByExample(example);
	}

	public int delProductByIdCategoryProduct(Long id_category_product) {
		return finder_product
				.delProductByIdCategoryProduct(id_category_product);
	}

	public int delProductById(Long id_product) {
		return dao_product.deleteByPrimaryKey(id_product);
	}

	@Override
	public List<WSTransactionDTO> getTransactionList(WSTransactionListDTO dto) {
		return finder_webservice.getTransactionList(dto);
	}

	@Override
	public WSTransactionDTO getTransactionById(Long id_order) {
		return finder_webservice.getTransactionById(id_order);
	}

	@Override
	public List<WSProductDetailDTO> getProductsDetailByIdOrderAndTypeTrx(
			WSProductsDetailCriteria criteria) {
		return finder_webservice.getProductsDetailByIdOrderAndTypeTrx(criteria);
	}

	public List<ReportDTO> getReportByDate(ReportSearchCriteria dto) {
		return finder_order.getReportByDate(dto);
	}

	@Override
	public Long addSale(AddTrxDTO dto) throws ProductNotFoundException {

		Order dto_order = new Order();
		dto_order.setId_supplier(dto.getId_supplier());
		dto_order.setInvoice(new Date());
		dto_order.setDelivery(new Date());
		dto_order.setStatus(dto.getStatus());
		dto_order.setId_retail(dto.getId_retail());
		dto_order.setId_store(dto.getId_store());
		dto_order.setId_user(dto.getId_user());
		
		// AGREGADO DE ORDEN
		Long orden = dao_order.insert(dto_order);

		// Tipo VENTA
		List<TrxDTO> Vtas = dto.getProducts_VTA();

		OrderDetail dto_order_detail;
		
		if (Vtas != null) {

			for (TrxDTO trxDTO : Vtas) {
				dto_order_detail = new OrderDetail();
	
				dto_order_detail.setId_order(orden);
				dto_order_detail.setId_product(trxDTO.getId_product());
				dto_order_detail.setPrice_sug(trxDTO.getPrice_sug());
				dto_order_detail.setPrice_sale(trxDTO.getPrice_sale());
				dto_order_detail.setQuantity(trxDTO.getQuantity());
				dto_order_detail.setTypetrx("VTA");
				dto_order_detail.setProduct_type(trxDTO.getProduct_type());
				
				dao_order_detail.insert(dto_order_detail);
	
			}
		
		}

		// Tipo CHANGE
		List<TrxDTO> Chg = dto.getProducts_CHG();
		
		if (Chg != null) {

			for (TrxDTO trxDTOChg : Chg) {
				dto_order_detail = new OrderDetail();
	
				dto_order_detail.setId_order(orden);
				dto_order_detail.setId_product(trxDTOChg.getId_product());
				dto_order_detail.setTypetrx("CHG");
				dto_order_detail.setProduct_type(trxDTOChg.getProduct_type());
				dto_order_detail.setQuantity(trxDTOChg.getQuantity());
	
				dao_order_detail.insert(dto_order_detail);
			}
		
		}

		// Tipo DEV
		List<TrxDTO> Dev = dto.getProducts_DEV();
		
		if (Dev != null) {
		
			for (TrxDTO trxDTODev : Dev) {
				dto_order_detail = new OrderDetail();
	
				dto_order_detail.setId_order(orden);
				dto_order_detail.setId_product(trxDTODev.getId_product());
				dto_order_detail.setPrice_sug(trxDTODev.getPrice_sug());
				dto_order_detail.setPrice_sale(trxDTODev.getPrice_sale());
				dto_order_detail.setQuantity(trxDTODev.getQuantity());
				dto_order_detail.setTypetrx("DEV");
				dto_order_detail.setProduct_type(trxDTODev.getProduct_type());
				dto_order_detail.setQuantity(trxDTODev.getQuantity());
	
				dao_order_detail.insert(dto_order_detail);
			}
		
		}

		return orden;

	}

	@Override
	public List<WSProduct> getProductsByIdSupplier(WSProductCriteria criteria) {
		return finder_webservice.getProductsByIdSupplier(criteria);
	}

	@Override
	public String getImageProductyById(Long id_product) {
		return finder_webservice.getImageProductyById(id_product);
	}

	@Override
	public MetricsSaleDTO getMetricsSale(GetMetricsSaleDTO dto) {
		return finder_order.getMetricsSale(dto);
	}

	@Override
	public List<MetricsSaleFullDTO> getMetricsSaleFull(GetMetricsSaleDTO dto) {
		return finder_order.getMetricsSaleFull(dto);
	}

	@Override
	public List<SaleByDriDTO> getSaleByDri(String delivery) {
		return finder_webservice.getSaleByDri(delivery);
	}

	@Override
	public List<MetricsSaleReportDTO> getMetricsSaleReport(GetMetricsSaleDTO dto) {
		return finder_order.getMetricsSaleReport(dto);
	}

	@Override
	public InfoTicketDTO getInfoTicket(Long id_order) {
		return finder_order.getInfoTicket(id_order);
	}

	@Override
	public List<InfoProductsToTicketDTO> getInfoProductsToTicket(
			InfoProductsToTicketCriteria criteria) {
		return finder_order.getInfoProductsToTicket(criteria);
	}

	@Override
	public String sendTicketByIdOrder(Long id_order) {
		InfoTicketDTO dto = this.getInfoTicket(id_order);

		if (dto.getStore_email() == null || dto.getStore_email().isEmpty()) {
			return "La tienda no cuenta con un correo registrado";
		}

		String ticketInfo = "<tr>" + "<td><b>Plaza:</b></td>" + "<td>"
				+ dto.getRetail() + "</td>" + "</tr>" + "<tr>"
				+ "<td><b>Fecha</b>: " + dto.getDate() + "</td>"
				+ "<td><b>Hora</b>: " + dto.getHour() + "</td>" + "</tr>"
				+ "<tr>" + "<td><b>No. Transacci�n</b>:</td>" + "<td>"
				+ dto.getTrx_num() + "</td>" + "</tr>" + "<tr>"
				+ "<td><b>Estatus</b>:</td>" + "<td>" + dto.getStatus()
				+ "</td>" + "</tr>" + "<tr>" + "<td><b>Vendedor</b>:</td>"
				+ "<td>" + dto.getSeller() + "</td>" + "</tr>";

		Long totalProducts = 0l;
		Double totalVenta = 0d;
		Long totalProductsDev = 0l;
		Double totalVentaDev = 0d;

		InfoProductsToTicketCriteria criteria = new InfoProductsToTicketCriteria();
		criteria.setId_order(id_order);
		criteria.setTypetrx("VTA");

		List<InfoProductsToTicketDTO> list = this
				.getInfoProductsToTicket(criteria);
		String productsVta = "";
		for (InfoProductsToTicketDTO i : list) {
			productsVta += "<tr>" + "<td>[" + i.getQuantity() + "] "
					+ i.getName_short() + "</td>" + "<td>" + i.getPrice_sale()
					+ "</td>" + "<td>" + i.getSale() + "</td>" + "</tr>";
			totalProducts += i.getQuantity();
			totalVenta += i.getSale();
		}

		criteria.setTypetrx("CHG");

		list = this.getInfoProductsToTicket(criteria);
		String productsCHG = "";
		for (InfoProductsToTicketDTO i : list) {
			productsCHG += "<tr>" + "<td>[" + i.getQuantity() + "] "
					+ i.getName_short() + "</td>" + "</tr>";
		}

		criteria.setTypetrx("DEV");

		list = this.getInfoProductsToTicket(criteria);
		String productsDEV = "";
		for (InfoProductsToTicketDTO i : list) {
			productsDEV += "<tr>" + "<td>[" + i.getQuantity() + "] "
					+ i.getName_short() + "</td>" + "</tr>";

			totalProductsDev += i.getQuantity();
			totalVentaDev += i.getSale();
		}

		String body = "<!DOCTYPE html>"
				+ "<html lang=\"en\">"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Tiecket</title>"
				+ "</head>"
				+ "<body>"
				+ "<div style=\"width: 90%; margin: 0 auto; padding: 5px 10px; box-shadow: 0 0 5px 5px lightgrey;\">"
				+ "<h1 style=\"text-align: center;\">" + dto.getSupplier()
				+ "</h1>" + "<h2 style=\"text-align: center;\">"
				+ dto.getStore() + "</h2>"
				+ "<table style=\"width: 100%; text-align: left;\">"
				+ "<tbody>" + ticketInfo + "</tbody>" + "</table>" + "<hr>";
		if (!productsVta.isEmpty()) {
			body += "<h4 style=\"text-align: center;\">Compra</h4>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<thead>"
					+ "<tr>"
					+ "<th>Producto</th>"
					+ "<th>Precio</th>"
					+ "<th>Total</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ productsVta
					+ "</tbody>"
					+ "</table>"
					+ "<hr>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<tbody>"
					+ "<tr>"
					+ "<td style=\"text-align: left;\"><b>Total Productos:</b> "
					+ totalProducts + "</td>"
					+ "<td style=\"text-align: right;\"><b>Total Venta:</b> $"
					+ totalVenta + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<hr>";
		}

		if (!productsCHG.isEmpty()) {
			body += "<h4 style=\"text-align: center;\">Cambio</h4>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<thead>" + "<tr>" + "<th>Producto</th>" + "</tr>"
					+ "</thead>" + "<tbody>" + productsCHG + "</tbody>"
					+ "</table>" + "<hr>";
		}
		if (!productsDEV.isEmpty()) {
			body += "<h4 style=\"text-align: center;\">DevoluciÃ³n</h4>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<thead>"
					+ "<tr>"
					+ "<th>Producto</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ productsDEV
					+ "</tbody>"
					+ "</table>"
					+ "<hr>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<tbody>"
					+ "<tr>"
					+ "<td style=\"text-align: left;\"><b>Total Productos:</b> "
					+ totalProductsDev
					+ "</td>"
					+ "<td style=\"text-align: right;\"><b>Total DevoluciÃ³n:</b> $"
					+ totalVentaDev + "</td>" + "</tr>" + "</tbody>"
					+ "</table>" + "<hr>";
		}

		body += "</table>";

		if (!productsVta.isEmpty() && !productsDEV.isEmpty()) {
			body += "<h2 style=\"text-align: center;\">Total Ticket</h2>"
					+ "<hr />" + "<p>$" + (totalVenta - totalVentaDev) + "</p>"
					+ "<p>Total Ticket = Ventas - Devoluciones</p>";
		}

		body += "</div>" + "</body>" + "</html>";

		SendEmail.sendTicket(dto.getStore_email(), dto.getStore(), body,
				dto.getRetail(), dto.getRetail_email(), dto.getSeller(),
				dto.getSeller_email(), dto.getSupplier());
		if (dto.getRetail_email() == null || dto.getRetail_email().isEmpty())
			return "Correo no enviado a encargado de la plaza, ya que no cuenta con uno asignado";
		else
			return "";
	}

	@Override
	public String sendNotificationByIdOrder(String store, String supplier, String date, String seller, String r_email, String s_email, String telefono, String hora, String llegada) {

		if (s_email == null || s_email.isEmpty()) {
			return "La tienda no cuenta con un correo registrado";
		}

		String ticketInfo = 
				"<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Fecha: </b></td>" + "<td>"
				+ date + "</td>" + "</tr>" + "<tr>"
				+ "<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Salida: </b></td>" + "<td>"
				+ hora + "</td>" + "</tr>" + "<tr>"
				
				+ "<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Llegada: </b></td>" + "<td>"
				+ llegada + "</td>" + "</tr>" + "<tr>"
				
				+ "<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Conductor: </b></td>" + "<td>"
				+ seller + "</td>" + "</tr>" + "<tr>"
				+ "<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Teléfono: </b></td>" + "<td>"
				+ telefono + "</td>" + "</tr>" + "<tr>"
				+ "<tr>" + "<td style=\"font-size: initial; color: darkslategrey;\"><b>Correo: </b></td>" + "<td>"
				+ s_email + "</td>" + "</tr>";

		String body = "<!DOCTYPE html>"
				+ "<html lang=\"en\">"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>En camino...</title>"
				+ "</head>"
				+ "<body>"
				+ "<div style=\"width: 90%; margin: 0 auto; padding: 5px 10px; box-shadow: 0 0 5px 5px lightgrey;\">"
				+ "<h2 style=\"text-align: center; color: darkslategrey;\">" + store
				+ "</h2>" + "<h1 style=\"text-align: center; color: darkslategrey;\">"
				+ "¡El conductor va en camino!" + "</h1>"
				+ "<h2 style=\"text-align: center; color: darkslategrey;\">"
				+ supplier + "</h2>"
				+ "<table style=\"width: 100%; text-align: left;\">"
				+ "<tbody>" + ticketInfo + "</tbody>" 
				+ "</table>" + "<hr>";
		
		body += "</table>";

		body += "</div>" + "<div style=\"padding-top: 35px; padding-left: 40px;\" >" + "<a href=\"http://logistikapp.com\"> <img class=\"center\" src=\"http://logistikapp.com/images/logo.png\" alt=\"LogistikApp\" > </a> </div>" 
		+"</body>" + "</html>";

		SendEmail.sendTicket(s_email, store, body,
				"retail", r_email, seller, s_email, supplier);
		if (r_email == null || r_email.isEmpty())
			return "Correo de entrega no enviado, compruebe su informaci�n.";
		else
			return "";
	}
	
	@Override
	public String sendPrintTicketByIdOrder(Long id_order) {
		String body = "";

		InfoTicketDTO dto = this.getInfoTicket(id_order);

		String ticketInfo = "Plaza: " + dto.getRetail() + "\n" + "Fecha: "
				+ dto.getDate() + "\n" + "Hora: " + dto.getHour() + "\n"
				+ "No. Transacci�n: " + dto.getTrx_num() + "\n" + "Estatus: "
				+ dto.getStatus() + "\n" + "Vendedor: " + dto.getSeller();

		Long totalProducts = 0l;
		Double totalVenta = 0d;
		Long totalProductsDev = 0l;
		Double totalVentaDev = 0d;

		InfoProductsToTicketCriteria criteria = new InfoProductsToTicketCriteria();
		criteria.setId_order(id_order);
		criteria.setTypetrx("VTA");

		List<InfoProductsToTicketDTO> list = this
				.getInfoProductsToTicket(criteria);
		String productsVta = " ";
		String typeTax_vta = "IVA:         ";
		float taxVta = 0;
		for (InfoProductsToTicketDTO i : list) {			
			String tmp = "[" + i.getQuantity() + "] " + i.getName_short();

			int size = tmp.length() <= 32 ? tmp.length() : 32;

			tmp = tmp.substring(0, size);

			productsVta += tmp + "\n  " + i.getPrice_sale() + " - "
					+ i.getSale() + "\n";
			totalProducts += i.getQuantity();
			totalVenta += i.getSale();
			
			if(i.getTax()!=null){
				if(i.getTax().equals(1)){
					taxVta += i.getSale()*iva;
					typeTax_vta = "IVA:         ";
				}
				else if(i.getTax().equals(2)){
					taxVta += i.getSale()*ieps;
					typeTax_vta = "IEPS:       ";
				}
				else if(i.getTax().equals(3)){
					taxVta += i.getSale()*iva;
					taxVta += taxVta*ieps;
					typeTax_vta = "IEPS e IVA:  ";
				}		
			}

		}

		criteria.setTypetrx("CHG");

		list = this.getInfoProductsToTicket(criteria);
		String productsCHG = "";
		for (InfoProductsToTicketDTO i : list) {

			String tmp = "[" + i.getQuantity() + "] " + i.getName_short();

			int size = tmp.length() <= 32 ? tmp.length() : 32;

			tmp = tmp.substring(0, size);

			productsCHG += tmp + "\n";
		}

		criteria.setTypetrx("DEV");

		list = this.getInfoProductsToTicket(criteria);
		String productsDEV = " ";
		String typeTax_dev = "IVA:             ";
		float taxDev = 0;
		for (InfoProductsToTicketDTO i : list) {
			String tmp = "[" + i.getQuantity() + "] " + i.getName_short();

			int size = tmp.length() <= 32 ? tmp.length() : 32;

			tmp = tmp.substring(0, size);

			productsDEV += tmp + "\n";
			totalProductsDev += i.getQuantity();
			totalVentaDev += i.getSale();
			
			if(i.getTax()!=null){
				if(i.getTax().equals(1)){
					taxDev += i.getSale()*iva;
					typeTax_dev = "IVA:             ";
				}
				else if(i.getTax().equals(2)){
					taxDev += i.getSale()*ieps;
					typeTax_dev = "IEPS:           ";
				}
				else if(i.getTax().equals(3)){
					taxDev += i.getSale()*iva;
					taxDev += taxDev*ieps;
					typeTax_dev = "IEPS e IVA:  ";
				}
			}
		}

		body = dto.getSupplier().toUpperCase() + "\n\n"
				+ dto.getStore().toUpperCase() + "\n\n" + ticketInfo + "\n\n";
		if (!productsVta.isEmpty() && totalProducts>0) {
			double subTotal_vta = totalVenta-taxVta;
			body += "================================\n"
					+ "             Compra\n" + "Producto  -  " + "Precio  -  "
					+ "Total" + "\n" + productsVta + "\nTotal Productos: "
					+ totalProducts + "\n\n" 
					+ "Subtotal:    $" + df2.format(subTotal_vta) + "\n"
					+ typeTax_vta + "$" + df2.format(taxVta) + "\n" 
					+ "Total Venta: $" + df2.format(totalVenta) + "\n";
		}

		if (!productsCHG.isEmpty() && !productsCHG.equals("")) {
			body += "\n================================\n"
					+ "             Cambio\n" + "Producto" + "\n" + productsCHG;
		}
		if (!productsDEV.isEmpty() && totalProductsDev>0) {
			double subTotal_dev = totalVentaDev-taxDev;
			body += "\n================================\n"
					+ "           Devoluci�n\n" + "Producto" + "\n"
					+ productsDEV + "\nTotal Productos: " + totalProductsDev + "\n\n"
					+ "Subtotal:       $" + df2.format(subTotal_dev) + "\n" 
					+ typeTax_dev + " $" + df2.format(taxDev) + "\n" 
					+ "Total Devoluci�n: $" + df2.format(totalVentaDev);
		}

		if((!productsVta.isEmpty() && totalProducts>0) && (!productsDEV.isEmpty() && totalProductsDev>0)) {
			body += "\n================================\n"
					+ "          Total Ticket\n" + "$"
					+ df2.format(totalVenta - totalVentaDev) + "\n"
					+ "          TOTAL TICKET:\n"
					+ "      VENTAS - DEVOLUCIONES\n";
		}

		body += "\n\nDEBO(MOS) Y PAGARE(MOS) INCONDI-\nCIONALMENTE A LA ORDEN DE: "+ dto.getSupplier().toUpperCase() + "\nEL " +
	            "IMPORTE DE ESTA NOTA EN LA FECHA\nDE SU PRESENTACI�N EN LA CIUDAD DE\n_______________,____. " +
	            "VALOR RECIBIDO\nEN MERCANCIA A MI (NUESTRA) SATISFACCI�N\nCONVINIENDO DE NO HACERLO PAGAR EL __%" + "\n" +
	            "MENSUAL A PARTIR DE LA FECHA DE ESTE\nDOCUMENTO M�S GASTOS QUE SE GENERAN POR\nCOBRANZA. ACEPTO(MOS) ________________.";
		
		return body;
	}

	/*
	 * StoreCategoryProduct
	 */
	@Override
	public Long addStoreCategoryProdut(StoreCategoryProduct dto) {
		return dao_store_category_product.insert(dto);
	}

	@Override
	public int updateStoreCategoryProduct(StoreCategoryProduct dto) {
		return dao_store_category_product.updateByPrimaryKeySelective(dto);
	}

	@Override
	public int deleteStoreCategoryProduct(Long id_category_product) {
		return dao_store_category_product
				.deleteByPrimaryKey(id_category_product);
	}

	/*
	 * Finder/StoreCategoryProduct
	 */
	@Override
	public List<ProductByCategoryStoreToUpdateDTO> getProductByCategoryStoreToUpdate(
			ProductByCategoryStoreToUpdateCriteria criteria) {
		return finder_store_category_product
				.getProductByCategoryStoreToUpdate(criteria);
	}

	@Override
	public List<String> getProductsNameByIdSupplier(Long id_supplier) {
		return finder_product.getProductsNameByIdSupplier(id_supplier);
	}

	@Override
	public List<Double> getPriceProductsByIdOrder(Long id_order) {
		return finder_product.getPriceProductsByIdOrder(id_order);
	}

	@Override
	public List<ProductToSubAlmacenDTO> getProductsToCreateSubStock(
			ProductSearchCriteria criteria) {
		return finder_product.getProductsToCreateSubStock(criteria);
	}

	@Override
	public List<TopClientsDTO> getTopClients(TopClientsCriteria criteria) {
		return finder_order.getTopClients(criteria);
	}

	@Override
	public List<SaleByCategoryDTO> getSaleByCategory(
			SaleByCategoryCriteria criteria) {
		return finder_order.getSaleByCategory(criteria);
	}

	@Override
	public List<CategoryAndNoStoresDTO> getCategoryAndNoStores(Long id_supplier) {
		return finder_order.getCategoryAndNoStores(id_supplier);
	}

	@Override
	public Long getNoStoreVisitedByIdStoreCategory(
			NoStoreVisitedByIdStoreCategoryCriteria criteria) {
		return finder_order.getNoStoreVisitedByIdStoreCategory(criteria);
	}

	@Override
	public PerTotalVisitedDTO getPerTotalVisited(PerTotalVisitedCriteria cri) {

		List<CategoryAndNoStoresDTO> list = this.getCategoryAndNoStores(cri
				.getId_supplier());
		List<ExtCategoryAndNoStoresDTO> list_ext = new ArrayList<>();
		ExtCategoryAndNoStoresDTO dto = null;
		Long total_stores = 0l;
		Long total_visiteds = 0l;

		for (CategoryAndNoStoresDTO i : list) {
			dto = new ExtCategoryAndNoStoresDTO();
			dto.setCategory(i.getCategory());
			dto.setId_store_category(i.getId_store_category());
			dto.setTotal_stores(i.getTotal_stores());
			NoStoreVisitedByIdStoreCategoryCriteria criteria = new NoStoreVisitedByIdStoreCategoryCriteria();
			criteria.setId_store_category(i.getId_store_category());
			if (cri.getFini() != null) {
				criteria.setFini(cri.getFini());
			}
			if (cri.getFfin() != null) {
				criteria.setFfin(cri.getFfin());
			}
			Long visited = this.getNoStoreVisitedByIdStoreCategory(criteria);
			dto.setTotal_visited(visited);
			double per_total_visited = (visited.doubleValue() * 100d)
					/ i.getTotal_stores().doubleValue();
			dto.setPer_total_visited(per_total_visited);
			dto.setTotal_not_visited(i.getTotal_stores() - visited);
			dto.setPer_total_not_visited(100d - per_total_visited);
			list_ext.add(dto);
			total_stores += i.getTotal_stores();
			total_visiteds += visited;
		}

		PerTotalVisitedDTO ptvdto = new PerTotalVisitedDTO();
		ptvdto.setList(list_ext);
		ptvdto.setTotal_stores(total_stores);
		ptvdto.setTotal_visited(total_visiteds);
		ptvdto.setTotal_no_visited(total_stores - total_visiteds);
		Double per_total_visited = (total_visiteds.doubleValue() * 100d)
				/ total_stores.doubleValue();
		ptvdto.setPer_total_visited(per_total_visited);
		ptvdto.setPer_total_not_visited(100d - per_total_visited);

		return ptvdto;
	}

	@Override
	public Long addAlmacen(Almacen almacen) {
		return dao_almacen.insert(almacen);
	}

	@Override
	public int updateAlmacen(Almacen almacen) {
		return dao_almacen.updateByPrimaryKeySelective(almacen);
	}

	@Override
	public int deleteAlmacen(Long id_almacen) {
		return dao_almacen.deleteByPrimaryKey(id_almacen);
	}

	@Override
	public Almacen getAlmacenById(Long id_almacen) {
		return dao_almacen.selectByPrimaryKey(id_almacen);
	}

	@Override
	public List<Almacen> getAlmacens(AlmacenExample example) {
		return dao_almacen.selectByExample(example);
	}

	@Override
	public List<Almacen> getAlmacens(Long id_supplier) {

		AlmacenExample example = new AlmacenExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);

		return getAlmacens(example);
	}

	@Override
	public Almacen getAlmacenByIdSupplier(Long id_supplier) {

		AlmacenExample example = new AlmacenExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier)
				.andId_userIsNull().andId_retailIsNull();
		example.setOrderByClause("id_almacen ASC");

		List<Almacen> almacens = getAlmacens(example);

		return almacens == null || almacens.size() == 0 ? null : almacens
				.get(0);
	}

	@Override
	public List<Almacen> getSubAlmacensByIdSupplier(Long id_supplier) {

		AlmacenExample example = new AlmacenExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier)
				.andId_userIsNotNull().andId_retailIsNotNull();
		example.setOrderByClause("code ASC");

		return getAlmacens(example);
	}

	@Override
	public AlmacenAndProductsDTO getAlmacenAndProductsByIdAlmacen(
			Long id_almacen, Long id_supplier) {

		AlmacenAndProductsDTO dto = new AlmacenAndProductsDTO();
		Almacen almacen = getAlmacenById(id_almacen);
		dto.setAlmacen(almacen);

		ProductByIdAlmacenCriteria criteria = new ProductByIdAlmacenCriteria();
		criteria.setId_almacen(id_almacen);
		criteria.setId_supplier(id_supplier);

		List<ProductAlmacenDTO> products = getProductsByIdAlmacen(criteria);
		dto.setProducts(products);

		return dto;
	}

	@Override
	public AlmacenAndProductsDTO getAlmacenAndProductsByIdSupplier(
			Long id_supplier) {
		AlmacenAndProductsDTO dto = new AlmacenAndProductsDTO();

		Almacen almacen = this.getAlmacenByIdSupplier(id_supplier);
		dto.setAlmacen(almacen);

		if (almacen != null) {

			ProductByIdAlmacenCriteria criteria = new ProductByIdAlmacenCriteria();
			criteria.setId_almacen(almacen.getId_almacen());
			criteria.setId_supplier(id_supplier);

			List<ProductAlmacenDTO> products = this
					.getProductsByIdAlmacen(criteria);
			dto.setProducts(products);
		} else {
			dto.setProducts(new ArrayList<ProductAlmacenDTO>());
		}

		return dto;
	}

	@Override
	public Long getAlmacenNextValue() {
		return finder_almacen.getAlmacenNextValue();
	}

	@Override
	public List<AlmacenInfoDTO> getSubAlmacenInfo(Long id_supplier) {
		return finder_almacen.getSubAlmacenInfo(id_supplier);
	}

	@Override
	public AlmacenInfoDTO getSubAlmacenInfoByIdAlmacen(Long id_almacen) {
		return finder_almacen.getSubAlmacenInfoByIdAlmacen(id_almacen);
	}

	@Override
	public AlmacenInfoAndProductsDTO getAlmacenInfoAndProductsByIdAlmacen(
			Long id_almacen, Long id_supplier) {

		AlmacenInfoAndProductsDTO dto = new AlmacenInfoAndProductsDTO();
		dto.setAlmacen(getSubAlmacenInfoByIdAlmacen(id_almacen));
		ProductByIdAlmacenCriteria criteria = new ProductByIdAlmacenCriteria();
		criteria.setId_almacen(id_almacen);
		criteria.setId_supplier(id_supplier);

		dto.setProducts(getProductsByIdAlmacen(criteria));

		return dto;
	}

	@Override
	public int recoverProductFromSale(RecoverProductFromSaleDTO params) {
		return finder_almacen.recoverProductFromSale(params);
	}

	@Override
	public boolean addAlmacenFull(AddAlmacenAndProductsDTO dto) {

		AlmacenExample example = new AlmacenExample();
		example.createCriteria().andCodeEqualTo(dto.getAlmacen().getCode())
				.andId_supplierEqualTo(dto.getAlmacen().getId_supplier());

		int rows = dao_almacen.countByExample(example);

		if (rows > 0)
			return false;

		Almacen almacen = dto.getAlmacen();

		Long id_almacen = addAlmacen(almacen);

		if (id_almacen == 0)
			return false;

		for (ProductAlmacen productAlmacen : dto.getProducts()) {
			productAlmacen.setId_almacen(id_almacen);
			addProductAlmacen(productAlmacen);
		}

		return true;
	}

	@Override
	public boolean updateAlmacenFull(UpdateAlmacenAndProductsDTO dto) {

		int res = updateAlmacen(dto.getAlmacen());

		if (res == 0)
			return false;

		Long id_almacen = dto.getAlmacen().getId_almacen();

		res = deleteProductAlmacenByIdAlmacen(id_almacen);

		if (res == 0)
			return false;

		for (ProductAlmacen productAlmacen : dto.getProducts()) {
			productAlmacen.setId_almacen(id_almacen);
			addProductAlmacen(productAlmacen);
		}

		return true;
	}

	@Override
	public boolean addProductsToAlmacen(Long id_almacen,
			List<ProductAlmacen> products) {

		ProductAlmacen pa;
		ProductAlmacenExample example;
		Almacen almacen = getAlmacenById(id_almacen);

		for (ProductAlmacen p : products) {
			if (p.getQty().equals(0))
				continue;

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(id_almacen)
					.andId_productEqualTo(p.getId_product());

			List<ProductAlmacen> productAlmacens = dao_product_almacen
					.selectByExample(example);

			if (productAlmacens.size() == 0) {
				if (almacen != null) {
					pa = new ProductAlmacen();
					pa.setId_almacen(id_almacen);
					pa.setId_product(p.getId_product());
					pa.setMin(0l);
					pa.setMax(p.getQty());
					pa.setQty(p.getQty());
					pa.setType("VTA");
					addProductAlmacen(pa);
				}
			} else {
				pa = productAlmacens.get(0);
				pa.setQty(pa.getQty() + p.getQty());
				updatProducteAlmacen(pa);
			}

		}

		return true;
	}

	@Override
	public boolean addProductsToSubAlmacen(Long id_supplier, Long id_almacen,
			List<ProductAlmacen> products) {

		if (!this.addProductsToAlmacen(id_almacen, products)) {
			return false;
		}

		Almacen almacen = getAlmacenByIdSupplier(id_supplier);
		Long id_almacen_gen = almacen.getId_almacen();

		ProductAlmacen pa;
		ProductAlmacenExample example;

		for (ProductAlmacen p : products) {

			if (p.getQty() == 0l) {
				continue;
			}

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(id_almacen_gen)
					.andId_productEqualTo(p.getId_product());

			pa = getProductsAlmacen(example).get(0);

			pa.setQty(pa.getQty() - p.getQty());
			updatProducteAlmacen(pa);
		}

		return true;
	}

	@Override
	public boolean addSubAlmacenFull(AddAlmacenAndProductsDTO dto) {

		if (!this.addAlmacenFull(dto)) {
			return false;
		}

		Almacen almacen = getAlmacenByIdSupplier(dto.getAlmacen()
				.getId_supplier());
		Long id_almacen = almacen.getId_almacen();

		ProductAlmacen pa;
		ProductAlmacenExample example;

		for (ProductAlmacen p : dto.getProducts()) {

			if (p.getQty() == 0l) {
				continue;
			}

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(id_almacen)
					.andId_productEqualTo(p.getId_product());

			pa = dao_product_almacen.selectByExample(example).get(0);

			pa.setQty(pa.getQty() - p.getQty());
			this.updatProducteAlmacen(pa);
		}

		return true;
	}

	@Override
	public boolean updateSubAlmacenFull(UpdateAlmacenAndProductsDTO dto) {
		int res = updateAlmacen(dto.getAlmacen());

		if (res == 0)
			return false;

		Almacen almacen = getAlmacenByIdSupplier(dto.getAlmacen()
				.getId_supplier());
		Long id_almacen = almacen.getId_almacen();

		ProductAlmacen pa;
		ProductAlmacenExample example;

		for (ProductAlmacen p : dto.getProducts()) {
			example = new ProductAlmacenExample();
			example.createCriteria()
					.andId_almacenEqualTo(dto.getAlmacen().getId_almacen())
					.andId_productEqualTo(p.getId_product());

			ProductAlmacen ptmp = dao_product_almacen.selectByExample(example)
					.get(0);
			p.setId_product_almacen(ptmp.getId_product_almacen());

			if (p.getQty().equals(ptmp.getQty()))
				continue;

			if (this.updatProducteAlmacen(p) == 0)
				continue;

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(id_almacen)
					.andId_productEqualTo(p.getId_product());

			pa = dao_product_almacen.selectByExample(example).get(0);

			if (p.getQty() > ptmp.getQty()) {

				pa.setQty(pa.getQty() - (p.getQty() - ptmp.getQty()));
				this.updatProducteAlmacen(pa);

			} else {
				pa.setQty(pa.getQty() + (ptmp.getQty() - p.getQty()));
				this.updatProducteAlmacen(pa);
			}

		}

		return true;
	}

	@Override
	public boolean liquidateSubAlmacen(Long id_almacen) {
		Almacen almacen = getAlmacenById(id_almacen);

		almacen = getAlmacenByIdSupplier(almacen.getId_supplier());

		ProductAlmacenExample productAlmacenExample = new ProductAlmacenExample();
		productAlmacenExample.createCriteria().andId_almacenEqualTo(id_almacen)
				.andTypeEqualTo("VTA");

		List<ProductAlmacen> products = getProductsAlmacen(productAlmacenExample);

		ProductAlmacen pa;
		ProductAlmacenExample example;
		for (ProductAlmacen p : products) {
			example = new ProductAlmacenExample();
			example.createCriteria()
					.andId_almacenEqualTo(almacen.getId_almacen())
					.andId_productEqualTo(p.getId_product());

			List<ProductAlmacen> list = dao_product_almacen.selectByExample(example);
			if (list.size() > 0) {
				pa = list.get(0);
				
				long qty = p.getQty();
				
				pa.setQty(pa.getQty() + qty);
				updatProducteAlmacen(pa);
			}
			p.setQty(0l);
			updatProducteAlmacen(p);
			
		}

		removeProductsNotSale(id_almacen);

		return true;
	}

	@Override
	public boolean removeProductsNotSale(Long id_almacen) {
		return finder_product_almacen.removeProductsNotSale(id_almacen) > 0;
	}

	@Override
	public Long addProductAlmacen(ProductAlmacen productAlmacen) {
		return dao_product_almacen.insert(productAlmacen);
	}

	@Override
	public int updatProducteAlmacen(ProductAlmacen productAlmacen) {
		return dao_product_almacen.updateByPrimaryKeySelective(productAlmacen);
	}

	@Override
	public int deleteProductAlmacen(Long id_product_almacen) {
		return dao_product_almacen.deleteByPrimaryKey(id_product_almacen);
	}

	@Override
	public ProductAlmacen getProductAlmacenById(Long id_product_almacen) {
		return dao_product_almacen.selectByPrimaryKey(id_product_almacen);
	}

	@Override
	public List<ProductAlmacen> getProductsAlmacen(ProductAlmacenExample example) {
		return dao_product_almacen.selectByExample(example);
	}

	@Override
	public List<ProductAlmacen> getProductsAlmacen(Long id_almacen) {

		ProductAlmacenExample example = new ProductAlmacenExample();
		example.createCriteria().andId_almacenEqualTo(id_almacen);

		return getProductsAlmacen(example);
	}

	@Override
	public boolean removeAlmacen(Long id_almacen) {
		if (!this.liquidateSubAlmacen(id_almacen))
			return false;

		deleteProductAlmacenByIdAlmacen(id_almacen);

		deleteAlmacen(id_almacen);

		return true;
	}

	@Override
	public Long getProductAlmacenNextValue() {
		return finder_product_almacen.getProductAlmacenNextValue();
	}

	@Override
	public List<ProductAlmacenDTO> getProductsByIdAlmacen(
			ProductByIdAlmacenCriteria criteria) {
		return finder_product_almacen.getProductsByIdAlmacen(criteria);
	}

	@Override
	public List<ProductAlmacenDTO> getProductsFromAlmacen(
			ProductByIdAlmacenCriteria criteria) {
		return finder_product_almacen.getProductsFromAlmacen(criteria);
	}

	@Override
	public List<ProductAlmacenDTO> getSaledProductsByIdAlmacen(
			ProductByIdAlmacenCriteria criteria) {
		return finder_product_almacen.getSaledProductsByIdAlmacen(criteria);
	}

	@Override
	public boolean addMissingProducts(Long id_almacen, Long id_supplier) {
		ProductByIdAlmacenCriteria criteria = new ProductByIdAlmacenCriteria();
		criteria.setId_almacen(id_almacen);
		criteria.setId_supplier(id_supplier);
		List<ProductAlmacenDTO> products = getSaledProductsByIdAlmacen(criteria);

		Almacen almacen = getAlmacenByIdSupplier(id_supplier);
		Long aid = almacen.getId_almacen();

		ProductAlmacen pa;
		ProductAlmacenExample example;

		for (ProductAlmacenDTO p : products) {

			if (p.getQty() == 0l) {
				continue;
			}

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(id_almacen)
					.andId_productEqualTo(p.getId_product());

			pa = dao_product_almacen.selectByExample(example).get(0);
			pa.setQty(pa.getQty() + p.getQty());
			this.updatProducteAlmacen(pa);

			example = new ProductAlmacenExample();
			example.createCriteria().andId_almacenEqualTo(aid)
					.andId_productEqualTo(p.getId_product());

			pa = dao_product_almacen.selectByExample(example).get(0);

			pa.setQty(pa.getQty() - p.getQty());
			this.updatProducteAlmacen(pa);

		}

		return true;
	}

	@Override
	public int deleteProductAlmacenByIdAlmacen(Long id_almacen) {
		return finder_product_almacen
				.deleteProductAlmacenByIdAlmacen(id_almacen);
	}

	@Override
	public Long addConfigurationStock(ConfigurationStock configurationStock) {
		return dao_configuration_stock.insert(configurationStock);
	}

	@Override
	public int updateConfigurationStock(ConfigurationStock configurationStock) {
		return dao_configuration_stock.updateByPrimaryKey(configurationStock);
	}

	@Override
	public int deleteConfigurationStock(Long id_configuration_stock) {
		return dao_configuration_stock
				.deleteByPrimaryKey(id_configuration_stock);
	}

	@Override
	public List<ConfigurationStock> getConfigurationsStockByIdSupplier(
			Long id_supplier) {

		ConfigurationStockExample example = new ConfigurationStockExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);

		return dao_configuration_stock.selectByExample(example);
	}

	@Override
	public boolean deleteFullConfigurationStock(Long id_configuration_stock) {

		if (deleteConfigurationStockProductByIdConfigurationStock(id_configuration_stock) == 0) {
			return false;
		}

		return deleteConfigurationStock(id_configuration_stock) > 0;
	}

	@Override
	public Long getConfigurationStockNextValue() {
		return finder_configuration_stock.getConfigurationStockNextValue();
	}

	@Override
	public Long addConfigurationStockProduct(
			ConfigurationStockProduct configurationStockProduct) {
		return dao_configuration_stock_product
				.insert(configurationStockProduct);
	}

	@Override
	public int updateConfigurationStockProduct(
			ConfigurationStockProduct configurationStockProduct) {
		return dao_configuration_stock_product
				.updateByPrimaryKey(configurationStockProduct);
	}

	@Override
	public int deleteConfigurationStockProduct(
			Long id_configuration_stock_product) {
		return dao_configuration_stock_product
				.deleteByPrimaryKey(id_configuration_stock_product);
	}

	@Override
	public List<ConfigurationStockProduct> getConfigurationsStockByIdConfigurationStock(
			Long id_configuration_stock) {

		ConfigurationStockProductExample example = new ConfigurationStockProductExample();
		example.createCriteria().andId_configuration_stock_productEqualTo(
				id_configuration_stock);

		return dao_configuration_stock_product.selectByExample(example);
	}

	@Override
	public Long getConfigurationStockProduct() {
		return finder_configuration_stock_product
				.getConfigurationStockProductNextValue();
	}

	@Override
	public List<ConfigurationStockProductDTO> getConfigurationStockProductByIdCondigurationStock(
			ConfigurationStockProductByIdCondigurationStockCriteria criteria) {
		return finder_configuration_stock_product
				.getConfigurationStockProductByIdCondigurationStock(criteria);
	}

	@Override
	public boolean addFullConfigurationStockProduct(
			AddConfigurationStockProductDTO dto) {

		ConfigurationStockExample example = new ConfigurationStockExample();
		example.createCriteria()
				.andNameEqualTo(dto.getConfiguratin().getName());

		int rows = dao_configuration_stock.countByExample(example);

		if (rows > 0)
			return false;

		Long id_config = addConfigurationStock(dto.getConfiguratin());
		if (id_config == 0)
			return false;

		for (ConfigurationStockProduct i : dto.getProducts()) {
			i.setId_configuration_stock(id_config);
			addConfigurationStockProduct(i);
		}

		return true;
	}

	@Override
	public int deleteConfigurationStockProductByIdConfigurationStock(
			Long id_configuration_stock) {
		return finder_configuration_stock_product
				.deleteConfigurationStockProductByIdConfigurationStock(id_configuration_stock);
	}

	@Override
	public int updateOrderAndRecoverToAlmacen(UpdateOrderAndRecoverToAlmacenDTO dto) {
		int res = 0;
		try {
			res = this.updOrder(dto.getUpdOrder());
		} catch (OrderNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
		
		
		if (res > 0) {
			
			Order order = null;
			try {
				order = this.getOrderById(dto.getUpdOrder().getId_order());
			} catch (OrderNotFoundException e) {
				e.printStackTrace();
			}
			
			if (order != null) {
				if (order.getStatus() != dto.getUpdOrder().getStatus()) {
					AlmacenExample example = new AlmacenExample();
					example.createCriteria()
					.andId_supplierEqualTo(dto.getId_supplier())
					.andId_userEqualTo(dto.getId_user());
				
					List<Almacen> almacenes = this.dao_almacen.selectByExample(example);
					if (almacenes != null && almacenes.size() > 0) {
						RecoverProductFromSaleDTO rec = new RecoverProductFromSaleDTO();
						rec.setId_order(dto.getUpdOrder().getId_order());
						rec.setId_almacen(almacenes.get(0).getId_almacen());
						String operator = dto.getUpdOrder().getStatus() == "APR" ? "-" : "+";
						rec.setOperator(operator);	
						return this.recoverProductFromSale(rec);
					}
					
				}
				
			}
			
		}
		
		return 0;
		
	}

	@Override
	public List<Double> getSubtotal(ProductSearchCriteria dto) {
		List<Double> list = new ArrayList<>(Arrays.asList(1.38, 2.56, 4.3));
		return list;
	}

}
