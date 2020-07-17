package com.retailsbs.logistikapp.financial.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.retailsbs.logistikapp.email.SendEmail;
import com.retailsbs.logistikapp.financial.domain.Almacen;
import com.retailsbs.logistikapp.financial.domain.CategoryProduct;
import com.retailsbs.logistikapp.financial.domain.Cost;
import com.retailsbs.logistikapp.financial.domain.Order;
import com.retailsbs.logistikapp.financial.domain.OrderDetail;
import com.retailsbs.logistikapp.financial.domain.Product;
import com.retailsbs.logistikapp.financial.domain.ProductAlmacen;
import com.retailsbs.logistikapp.financial.domain.StoreCategoryProduct;
import com.retailsbs.logistikapp.financial.dto.AddCostDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDTO;
import com.retailsbs.logistikapp.financial.dto.AddOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.AddProductDTO;
import com.retailsbs.logistikapp.financial.dto.AddTrxDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductDTO;
import com.retailsbs.logistikapp.financial.dto.CategoryProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ExtCategoryAndNoStoresDTO;
import com.retailsbs.logistikapp.financial.dto.GetMetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketCriteria;
import com.retailsbs.logistikapp.financial.dto.InfoProductsToTicketDTO;
import com.retailsbs.logistikapp.financial.dto.InfoTicketDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleDTO;
import com.retailsbs.logistikapp.financial.dto.MetricsSaleReportDTO;
import com.retailsbs.logistikapp.financial.dto.NoStoreVisitedByIdStoreCategoryCriteria;
import com.retailsbs.logistikapp.financial.dto.OrdersInfoToReportCriteria;
import com.retailsbs.logistikapp.financial.dto.OrdersInfoToReportDTO;
import com.retailsbs.logistikapp.financial.dto.PerTotalVisitedCriteria;
import com.retailsbs.logistikapp.financial.dto.PerTotalVisitedDTO;
import com.retailsbs.logistikapp.financial.dto.ProductAlmacenExample;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductByCategoryStoreToUpdateDTO;
import com.retailsbs.logistikapp.financial.dto.ProductCodeAvailableSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductDTO;
import com.retailsbs.logistikapp.financial.dto.ProductSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ProductsUsedsInOrderDetailDTO;
import com.retailsbs.logistikapp.financial.dto.RecoverProductFromSaleDTO;
import com.retailsbs.logistikapp.financial.dto.ReportByDriCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportByDriDTO;
import com.retailsbs.logistikapp.financial.dto.ReportDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtDTO;
import com.retailsbs.logistikapp.financial.dto.ReportExtSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.ReportSearchCriteria;
import com.retailsbs.logistikapp.financial.dto.SaleByDriDTO;
import com.retailsbs.logistikapp.financial.dto.TopClientsCriteria;
import com.retailsbs.logistikapp.financial.dto.TopClientsDTO;
import com.retailsbs.logistikapp.financial.dto.UpdProductDTO;
import com.retailsbs.logistikapp.financial.dto.WSProductsDetailCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProduct;
import com.retailsbs.logistikapp.financial.dto.WSProductCriteria;
import com.retailsbs.logistikapp.financial.dto.WSProductDetailDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionDTO;
import com.retailsbs.logistikapp.financial.dto.WSTransactionListDTO;
import com.retailsbs.logistikapp.financial.dto.TrxDTO;
import com.retailsbs.logistikapp.financial.dto.UpdCostDTO;
import com.retailsbs.logistikapp.financial.dto.UpdOrder;
import com.retailsbs.logistikapp.financial.dto.UpdOrderDetailDTO;
import com.retailsbs.logistikapp.financial.exception.CostNotFoundException;
import com.retailsbs.logistikapp.financial.exception.OrderDetailNotFoundException;
import com.retailsbs.logistikapp.financial.exception.OrderNotFoundException;
import com.retailsbs.logistikapp.financial.exception.ProductCodeDuplicateException;
import com.retailsbs.logistikapp.financial.exception.ProductNotFoundException;
import com.retailsbs.logistikapp.financial.service.FinancialService;
import com.retailsbs.logistikapp.financial.test.base.BaseTestJunit;

public class FinancialServiceTest extends BaseTestJunit {

	@Autowired
	private FinancialService service;

	/**
	 * order
	 */

	public void test_addOrder() {
		AddOrderDTO dto = new AddOrderDTO();

		dto.setDelivery(new Date());
		dto.setId_supplier(166L);
		dto.setInvoice(new Date());
		dto.setModified(new Date());
		dto.setStatus("CRE");

		Long id = service.addOrder(dto);
		assertNotNull(id);
	}

	public void test_updOrder() throws OrderNotFoundException {
		UpdOrder dto = new UpdOrder();
		dto.setId_order(103L);

		int row = service.updOrder(dto);
		assertTrue(row > 0);
	}

	public void test_getOrderById() throws OrderNotFoundException {
		Order order = service.getOrderById(103L);
		assertNotNull(order);
	}

	public void test_getAllOrder() {
		List<Order> list = service.getAllOrder();
		assertNotNull(list);
	}

	public void test_getOrderByIdSupplier() {
		List<Order> list = service.getOrderByIdSupplier(2L);
		assertNotNull(list);
	}

	/**
	 * finder order
	 */

	public void test_delOrderByIdSupplier() {
		int row = service.delOrderByIdSupplier(2L);
		assertTrue(row > 0);
	}

	public void test_getmetricssale() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		GetMetricsSaleDTO dto = new GetMetricsSaleDTO();
		dto.setId_supplier(166l);
		dto.setInitDate(calendar.getTime());

		calendar.add(Calendar.MONTH, 1);
		dto.setFinalDate(calendar.getTime());

		MetricsSaleDTO metric = service.getMetricsSale(dto);

		System.out.print("--> " + metric.getNotrx() + " : "
				+ metric.getTicketavg());

	}

	public void test_getMetricsSaleReport() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -12);
		calendar.add(Calendar.DATE, -28);

		GetMetricsSaleDTO dto = new GetMetricsSaleDTO();
		dto.setId_supplier(166l);
		dto.setInitDate(calendar.getTime());

		calendar.add(Calendar.MONTH, 1);
		dto.setFinalDate(calendar.getTime());

		List<MetricsSaleReportDTO> list = service.getMetricsSaleReport(dto);

		assertNotNull(list);

		assertTrue(list.size() > 0);

		for (MetricsSaleReportDTO i : list) {
			System.out.println("Tienda " + i.getStore());
		}

	}

	public void test_sendTicket() {
		InfoTicketDTO dto = service.getInfoTicket(57804l);

		assertNotNull(dto);

		if (dto != null) {

			String ticketInfo = "<tr>" + "<td><b>Plaza:</b></td>" + "<td>"
					+ dto.getRetail() + "</td>" + "</tr>" + "<tr>"
					+ "<td><b>Fecha</b>: " + dto.getDate() + "</td>"
					+ "<td><b>Hora</b>: " + dto.getHour() + "</td>" + "</tr>"
					+ "<tr>" + "<td><b>No. Transacción</b>:</td>" + "<td>"
					+ dto.getTrx_num() + "</td>" + "</tr>" + "<tr>"
					+ "<td><b>Estatus</b>:</td>" + "<td>" + dto.getStatus()
					+ "</td>" + "</tr>" + "<tr>" + "<td><b>Vendedor</b>:</td>"
					+ "<td>" + dto.getSeller() + "</td>" + "</tr>";

			System.out.println("--> " + dto.toString());

			Long totalProducts = 0l;
			Double totalVenta = 0d;

			InfoProductsToTicketCriteria criteria = new InfoProductsToTicketCriteria();
			criteria.setId_order(13689l);
			criteria.setTypetrx("VTA");

			List<InfoProductsToTicketDTO> list = service
					.getInfoProductsToTicket(criteria);
			String productsVta = "";
			for (InfoProductsToTicketDTO i : list) {
				productsVta += "<tr>" + "<td>[" + i.getQuantity() + "] "
						+ i.getName_short() + "</td>" + "<td>"
						+ i.getPrice_sale() + "</td>" + "<td>" + i.getSale()
						+ "</td>" + "</tr>";
				totalProducts += i.getQuantity();
				totalVenta += i.getSale();
			}

			criteria.setTypetrx("CHG");

			list = service.getInfoProductsToTicket(criteria);
			String productsCHG = "";
			for (InfoProductsToTicketDTO i : list) {
				productsCHG += "<tr>" + "<td>[" + i.getQuantity() + "] "
						+ i.getName_short() + "</td>" + "</tr>";
			}

			criteria.setTypetrx("DEV");

			list = service.getInfoProductsToTicket(criteria);
			String productsDEV = "";
			for (InfoProductsToTicketDTO i : list) {
				productsDEV += "<tr>" + "<td>[" + i.getQuantity() + "] "
						+ i.getName_short() + "</td>" + "</tr>";
			}

			String body = "<!DOCTYPE html>"
					+ "<html lang=\"en\">"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Tiecket</title>"
					+ "</head>"
					+ "<body>"
					+ "<div style=\"width: 90%; margin: 0 auto; padding: 5px 10px; box-shadow: 0 0 5px 5px lightgrey;\">"
					+ "<h1 style=\"text-align: center;\">"
					+ dto.getSupplier()
					+ "</h1>"
					+ "<h2 style=\"text-align: center;\">"
					+ dto.getStore()
					+ "</h2>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<tbody>"
					+ ticketInfo
					+ "</tbody>"
					+ "</table>"
					+ "<hr>"
					+ "<h4 style=\"text-align: center;\">Compra</h4>"
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
					+ totalProducts
					+ "</td>"
					+ "<td style=\"text-align: right;\"><b>Total Venta:</b> $"
					+ totalVenta
					+ "</td>"
					+ "</tr>"
					+ "</tbody>"
					+ "</table>"
					+ "<hr>"
					+ "<h4 style=\"text-align: center;\">Cambio</h4>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<thead>"
					+ "<tr>"
					+ "<th>Producto</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ productsCHG
					+ "</tbody>"
					+ "</table>"
					+ "<hr>"
					+ "<h4 style=\"text-align: center;\">Devolución</h4>"
					+ "<table style=\"width: 100%; text-align: left;\">"
					+ "<thead>"
					+ "<tr>"
					+ "<th>Producto</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ productsDEV
					+ "</tbody>"
					+ "</table>" + "</div>" + "</body>" + "</html>";

			SendEmail.sendTicket(dto.getStore_email(), dto.getStore(), body,
					dto.getRetail(), dto.getRetail_email(), dto.getSeller(),
					dto.getSeller_email(), dto.getSupplier());
		}
	}

	public void test_sendPrintTicket() {
		String ticket = service.sendPrintTicketByIdOrder(15871l);
		System.out.println(ticket);
	}

	public void test_getOrdersInfoToReport() {
		List<String> products = service.getProductsNameByIdSupplier(166l);
		assertTrue(products.size() == 4);

		OrdersInfoToReportCriteria criteria = new OrdersInfoToReportCriteria();
		criteria.setId_supplier(166l);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -5);
		criteria.setInvoice(calendar.getTime());
		calendar.add(Calendar.MONTH, 5);
		criteria.setInvoice_fin(calendar.getTime());
		List<OrdersInfoToReportDTO> orders = service
				.getOrdersInfoToReport(criteria);
		assertTrue(orders.size() >= 1);

		for (OrdersInfoToReportDTO i : orders) {
			System.out.println("--> " + i.getId_order());
		}

		if (orders.size() > 0) {
			List<Double> prices = service.getPriceProductsByIdOrder(orders.get(
					0).getId_order());
			if (prices.size() < products.size()) {
				do {
					prices.add(0d);
				} while (prices.size() < products.size());
			}

			assertTrue(prices.size() == 4);
		}

	}

	public void test_getTopClients() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		TopClientsCriteria criteria = new TopClientsCriteria();
		criteria.setId_supplier(251l);

		try {
			criteria.setFini(sdf.parse("01/04/2017"));
			criteria.setFfin(sdf.parse("30/04/2017"));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		List<TopClientsDTO> list = service.getTopClients(criteria);
		for (TopClientsDTO i : list) {
			System.out.println(i.getStore() + " : " + i.getSales());
		}
		assertNotNull(list);
		assertTrue(list.size() == 10);

	}

	public void test_per_sales() {
		List<CategoryAndNoStoresDTO> list = service
				.getCategoryAndNoStores(166l);
		assertTrue("Categorias", list.size() > 0);
		List<ExtCategoryAndNoStoresDTO> list_ext = new ArrayList<>();
		ExtCategoryAndNoStoresDTO dto = null;
		for (CategoryAndNoStoresDTO i : list) {
			dto = new ExtCategoryAndNoStoresDTO();
			NoStoreVisitedByIdStoreCategoryCriteria criteria = new NoStoreVisitedByIdStoreCategoryCriteria();
			criteria.setId_store_category(i.getId_store_category());
			Long visited = service.getNoStoreVisitedByIdStoreCategory(criteria);
			dto.setTotal_visited(visited);
			double per_total_visited = (double) (visited * 100)
					/ i.getTotal_stores();
			dto.setPer_total_visited(per_total_visited);
			dto.setTotal_not_visited(i.getTotal_stores() - visited);
			dto.setPer_total_not_visited(100.00 - per_total_visited);
			list_ext.add(dto);
			System.out.println(dto.toString());
		}

	}

	public void test_getPerTotalVisited() {
		PerTotalVisitedCriteria criteria = new PerTotalVisitedCriteria();
		criteria.setId_supplier(324l);
		criteria.setFini(new Date());
		criteria.setFfin(new Date());

		PerTotalVisitedDTO dto = service.getPerTotalVisited(criteria);
		assertNotNull(dto);
		for (ExtCategoryAndNoStoresDTO i : dto.getList()) {
			System.out.println(i.getCategory() + " ===== "
					+ i.getTotal_stores() + " ===== " + i.getTotal_visited());
		}
	}

	/**
	 * order detail
	 */

	public void test_addOrderDetail() {
		AddOrderDetailDTO dto = new AddOrderDetailDTO();
		dto.setId_order(103L);
		dto.setId_product(100L);
		dto.setPrice_sug(23.2);
		dto.setQuantity(10);

		Long id = service.addOrderDetail(dto);
		assertNotNull(id);
	}

	public void test_updOrderDetail() throws OrderDetailNotFoundException {
		UpdOrderDetailDTO dto = new UpdOrderDetailDTO();
		dto.setId_order_detail(102L);
		dto.setQuantity(22);

		int row = service.updOrderDetail(dto);
		assertTrue(row > 0);
	}

	public void test_getOrderDetailById() throws OrderDetailNotFoundException {
		OrderDetail orderDet = service.getOrderDetailById(102L);
		assertNotNull(orderDet);
	}

	public void test_getAllOrderDetail() {
		List<OrderDetail> list = service.getAllOrderDetail();
		assertNotNull(list);
	}

	/**
	 * finder order detail
	 */

	public void test_delOrderDetailByIdOrder() {
		int row = service.delOrderDetailByIdOrder(2L);
		assertTrue(row > 0);
	}

	public void test_delOrderDetailByIdProduct() {
		int row = service.delOrderDetailByIdProduct(2L);
		assertTrue(row > 0);
	}

	/**
	 * cost
	 */

	public void test_addCost() {
		AddCostDTO dto = new AddCostDTO();
		dto.setAccounting(new Date());
		dto.setActive("S");
		dto.setAmount(1.1);
		dto.setComment("comment");
		dto.setCreated(new Date());
		dto.setId_retail(100L);
		dto.setId_supplier(1L);
		dto.setLogin("login");
		dto.setModified(new Date());
		dto.setName("name");

		Long id = service.addCost(dto);
		assertNotNull(id);
	}

	public void test_updCost() throws CostNotFoundException {
		UpdCostDTO dto = new UpdCostDTO();
		dto.setId_cost(104L);
		dto.setComment("new comment");

		int row = service.updCost(dto);
		assertTrue(row > 0);
	}

	public void test_getAllCost() {
		List<Cost> list = service.getAllCost();
		assertNotNull(list);
	}

	public void test_getCostById() throws CostNotFoundException {
		Cost cost = service.getCostById(104L);
		assertNotNull(cost);
	}

	/*
	 * finder cost
	 */

	public void test_delCostByIdSupplier() {
		int row = service.delCostByIdSupplier(2L);
		assertTrue(row > 0);
	}

	/*
	 * CategoryProduct
	 */
	public void getAllCategoryProduct() {
		List<CategoryProduct> list = service.getAllCategoryProduct();
		for (CategoryProduct categoryProduct : list) {
			System.out.println(categoryProduct.getName());
		}
		assertNotNull(list);
	}

	public void test_selectCategoryProduct() {
		CategoryProductSearchCriteria dto = new CategoryProductSearchCriteria();
		dto.setId_supplier(1L);
		List<CategoryProductDTO> list = service
				.selectCategoryProductByCriteria(dto);
		for (CategoryProductDTO categoryProductDTO : list) {
			System.out.println(categoryProductDTO.getName());
		}
		assertNotNull(list);
	}

	public void test_getCategoryProductByIdSupplier() {
		List<CategoryProduct> list = service.getCategoryProductByIdSupplier(1L);
		for (CategoryProduct categoryProduct : list) {
			System.out.println(categoryProduct.getName());
		}
		assertNotNull(list);
	}

	public void test_delCategoryProductByIdSupplier() {
		int row = service.delCategoryProductByIdSupplier(2L);
		assertTrue(row > 0);
		// service.delCategoryProductByIdCategoryProduct(id_category_product);
	}

	/*
	 * Product
	 */
	public void test_addProduct() {
		AddProductDTO dto = new AddProductDTO();
		dto.setActive("S");
		dto.setCode("cod");
		dto.setCreated(new Date());
		dto.setId_category_product(100L);
		dto.setImage("/tmp/imagenes/default.png");
		dto.setLogin("login");
		dto.setModified(new Date());
		dto.setName_long("Pruebas");
		dto.setName_short("PR");
		dto.setOrderby(2);
		dto.setPrice_cost(10.1);
		dto.setPrice_sale(20.99);
		dto.setId_brand(1L);

		Long id = service.addProduct(dto);
		assertNotNull(id);
	}

	public void getAllProduct() {
		List<Product> list = service.getAllProduct();
		for (Product product : list) {
			System.out.println(product.getName_long());
		}
		assertNotNull(list);
	}

	public void test_selectProduct() {
		ProductSearchCriteria dto = new ProductSearchCriteria();
		dto.setId_supplier(166L);
		dto.setId_brand(104l);
		List<ProductDTO> list = service.selectProductByCriteria(dto);
		for (ProductDTO productDTO : list) {
			System.out.println(productDTO.getName_long());
		}
	}

	public void test_getProductCodeAvaibleByCriterial()
			throws ProductCodeDuplicateException {
		ProductCodeAvailableSearchCriteria dto = new ProductCodeAvailableSearchCriteria();
		dto.setActive("S");
		dto.setCode("ip6");
		dto.setId_product(100L);

		service.getProductCodeAvaibleByCriteria(dto);
	}

	public void test_getProductByIdCategoryProduct() {
		List<Product> list = service.getProductByIdCategoryProduct(101L);
		for (Product product : list) {
			System.out.println(product.getName_long());
		}
		assertNotNull(list);

	}

	public void test_delProductByIdCategoryProduct() {
		int row = service.delProductByIdCategoryProduct(1L);
		assertTrue(row > 0);
	}

	public void test_getTransactionList() {

		WSTransactionListDTO dto = new WSTransactionListDTO();
		dto.setInvoice(new Date());
		dto.setId_supplier(166l);

		System.out.println(new Date());

		List<WSTransactionDTO> list = service.getTransactionList(dto);
		for (WSTransactionDTO i : list) {
			System.out.println(i.toString());
		}
	}

	public void test_GetTransactionById() {
		WSTransactionListDTO dto = new WSTransactionListDTO();
		dto.setId_order(16L);

		List<WSTransactionDTO> list = service.getTransactionList(dto);
		for (WSTransactionDTO i : list) {
			System.out.println(i.toString());
		}
	}

	public void test_getProductsDetailByIdOrderAndTypeTrx() {
		WSProductsDetailCriteria criteria = new WSProductsDetailCriteria();
		criteria.setId_order(12l);
		criteria.setId_supplier(166l);
		criteria.setTypetrx("DEV");
		List<WSProductDetailDTO> list = service
				.getProductsDetailByIdOrderAndTypeTrx(criteria);
		for (WSProductDetailDTO i : list) {
			System.out.println(i);
		}
	}

	public void test_AddSale() throws ParseException {
		AddTrxDTO dto = new AddTrxDTO();
		TrxDTO pds;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dto.setInvoice(sdf.parse("20/08/2016"));
		dto.setId_supplier(166L);
		dto.setId_user(225L);
		dto.setId_store(19927L);
		List<TrxDTO> id_VTA = new ArrayList<TrxDTO>();
		List<TrxDTO> id_CHG = new ArrayList<TrxDTO>();
		List<TrxDTO> id_DEV = new ArrayList<TrxDTO>();

		// Productos Venta
		pds = new TrxDTO();
		pds.setId_product(101L);
		pds.setPrice_sale(12.50);
		pds.setPrice_sug(12.50);
		pds.setQuantity(10);
		id_VTA.add(pds);

		pds = new TrxDTO();
		pds.setId_product(114L);
		pds.setPrice_sale(22.0);
		pds.setPrice_sug(22.0);
		pds.setQuantity(5);
		id_VTA.add(pds);
		// Productos Venta
		// Productos Cambios
		pds = new TrxDTO();
		pds.setId_product(115L);
		pds.setQuantity(13);
		id_CHG.add(pds);
		// Productos Cambios
		// Productos Devolucion
		pds = new TrxDTO();
		pds.setId_product(114L);
		pds.setQuantity(200);
		id_DEV.add(pds);

		// Productos Devolucion

		dto.setProducts_VTA(id_VTA);
		dto.setProducts_CHG(id_CHG);
		dto.setProducts_DEV(id_DEV);

		Long control = 0l;
		try {
			control = service.addSale(dto);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(control);
		assertNotNull(control);

	}

	public void test_addSale() {
		AddTrxDTO dto = new AddTrxDTO();
		dto.setId_supplier(166L);
		dto.setId_user(225L);
		dto.setId_store(18129L);

		dto.setStatus("CRE");

		Long id = 0L;
		try {
			id = service.addSale(dto);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Id " + id);

		if (id == 0L)
			return;

		InfoTicketDTO ticket = service.getInfoTicket(id);
		System.out.println("Ticket");
		System.out.println(ticket.toString());
	}

	public void test_getProductsByIdSupplier() {
		WSProductCriteria criteria = new WSProductCriteria();
		criteria.setId_supplier(166l);
		criteria.setActive("S");

		List<WSProduct> list = service.getProductsByIdSupplier(criteria);

		for (WSProduct i : list) {
			System.out.println("---> " + i.toString());
		}

	}

	public void test_getTransactionsByIdStore() {
		WSTransactionListDTO dto = new WSTransactionListDTO();
		dto.setId_store(19927l);
		List<WSTransactionDTO> list = service.getTransactionList(dto);
		for (WSTransactionDTO i : list) {
			System.out.println(i.toString());
		}
	}

	public void test_getImageProductById() {
		String img = service.getImageProductyById(114l);
		System.out.println(img);
	}

	public void getReportByDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date fini = null;
		Date ffin = null;

		try {
			fini = sdf.parse("01/10/2016 00:00:00");
			ffin = sdf.parse("31/10/2016 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ReportSearchCriteria dto = new ReportSearchCriteria();
		dto.setInvoice(fini);
		dto.setInvoice_fin(ffin);
		dto.setId_supplier(166L);

		List<ReportDTO> list = service.getReportByDate(dto);
		assertTrue("Hay datos?", list.size() > 0);
		assertNotNull("Lista null?", list);
	}

	public void getReportExtByDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date fini = null;
		Date ffin = null;

		try {
			fini = sdf.parse("01/05/2017 00:00:00");
			ffin = sdf.parse("31/05/2017 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ReportExtSearchCriteria dto = new ReportExtSearchCriteria();
		dto.setFini(fini);
		dto.setFfin(ffin);
		dto.setId_supplier(251L);
		List<ReportExtDTO> list = service.getReportExtByDate(dto);
		assertNotNull("Lista null?", list);
		assertTrue("Hay datos?", list.size() > 0);
	}

	public void getSaleByDri() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -3);
		List<SaleByDriDTO> list = service.getSaleByDri(sdf.format(calendar
				.getTime()));
		DecimalFormat df = new DecimalFormat("0.00");
		for (SaleByDriDTO i : list) {
			System.out.println("U: " + i.getUsername() + " : $"
					+ df.format(i.getAmount()));
		}
	}

	/*
	 * Finder category_product
	 */
	public void test_getProductsUsedsInOrderDetail() {
		ProductsUsedsInOrderDetailDTO dto = new ProductsUsedsInOrderDetailDTO();
		dto.setId_supplier(166l);
		dto.setIds(new Long[] { 116l });

		List<Integer> list = service.getProductsUsedsInOrderDetail(dto);
		for (Integer i : list) {
			System.out.println("---> " + i);
		}

		assertTrue(list.size() > 0);

	}

	public void test_getProductByCategoryStoreToUpdate() {
		ProductByCategoryStoreToUpdateCriteria criteria = new ProductByCategoryStoreToUpdateCriteria();
		criteria.setId_supplier(166l);
		criteria.setId_store_category(252l);

		List<ProductByCategoryStoreToUpdateDTO> list = service
				.getProductByCategoryStoreToUpdate(criteria);
		assertNotNull(list);
		assertTrue(list.size() > 0);
		for (ProductByCategoryStoreToUpdateDTO i : list) {
			System.out.println("--> " + i.getName_short() + " : P: "
					+ i.getPrice_sale_category());
		}
	}

	public void test_insertStoreCategoryProduct() {
		StoreCategoryProduct dto = new StoreCategoryProduct();
		dto.setId_store_category(252l);
		dto.setId_product(114l);
		dto.setPrice_sale(64580d);
		Long res = service.addStoreCategoryProdut(dto);
		System.out.println("--> " + res);
	}

	public void test_updStoreCategoryProduct() {
		StoreCategoryProduct dto = new StoreCategoryProduct();
		dto.setId_store_category_product(16l);
		dto.setPrice_sale(640d);
		int res = service.updateStoreCategoryProduct(dto);
		System.out.println("--> " + res);
	}

	public void test_getReportByDri() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ReportByDriCriteria criteria = new ReportByDriCriteria();
		criteria.setId_supplier(166l);
		try {
			criteria.setFini(sdf.parse("04/05/2017"));
			criteria.setFfin(sdf.parse("04/05/2017"));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		List<ReportByDriDTO> list = service.getReportByDri(criteria);
		assertNotNull(list);
		for (ReportByDriDTO i : list) {
			System.out.println("--> " + i.getUsername() + " : " + i.getSales());
		}
	}

	public void test_surtir() {
		Almacen almacen = service.getAlmacenByIdSupplier(166l);
		assertNotNull("ALMACEN", almacen);

		System.out.println("IDALMACEN: " + almacen.getId_almacen());

		ProductAlmacenExample example = new ProductAlmacenExample();
		example.createCriteria().andId_almacenEqualTo(almacen.getId_almacen())
				.andId_productEqualTo(136l);

		ProductAlmacen pa = service.getProductsAlmacen(example).get(0);
		System.out.println("ID_P_A: " + pa.getId_product_almacen());
		System.out.println("QTY ACTUAL " + pa.getQty());
		pa.setQty(pa.getQty() - 6l);
		System.out.println("QTY AHORA " + pa.getQty());

		int res = service.updatProducteAlmacen(pa);

		assertNotNull("RES", res > 0);

	}

	public void testUpdateProduct() {
		try {
			Product product = service.getProductById(137l);
			UpdProductDTO upd = new UpdProductDTO();
			upd.setId_product(product.getId_product());
			upd.setName_short("Coca Cola light 1.5 Lts");
			int res = service.updProduct(upd);
			System.out.println("--> " + res);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRecoverProductFromSale() {
		RecoverProductFromSaleDTO params = new RecoverProductFromSaleDTO();
		params.setId_almacen(175l);
		params.setId_order(99428l);
		params.setOperator("-");

		int res = service.recoverProductFromSale(params);
		System.out.println("Result " + res);

	}

}
