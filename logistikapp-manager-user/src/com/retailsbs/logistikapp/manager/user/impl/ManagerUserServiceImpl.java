package com.retailsbs.logistikapp.manager.user.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.retailsbs.logistikapp.manager.user.ManagerUserService;
import com.retailsbs.logistikapp.manager.user.dto.AutoRegistroDTO;
import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.dto.AddSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.PlanNotFoundException;
import com.retailsbs.logistikapp.user.dto.AddAccessDTO;
import com.retailsbs.logistikapp.user.dto.AddUserDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.user.service.UserService;
import com.retailsbs.logistikapp.retail.service.RetailService;
import com.retailsbs.logistikapp.supplier.service.SupplierService;
import com.retailsbs.logistikapp.email.EmailDataDTO;
import com.retailsbs.logistikapp.email.SendEmail;

public class ManagerUserServiceImpl implements ManagerUserService {
	protected final Log logger = LogFactory.getLog(getClass());

	private UserService userservice;
	private RetailService retailservice;
	private SupplierService supplierservice;

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public void setSupplierservice(SupplierService supplierservice) {
		this.supplierservice = supplierservice;
	}

	public void setRetailservice(RetailService retailservice) {
		this.retailservice = retailservice;
	}

	@Override
	public void autoregistro(AutoRegistroDTO dto) {

		// DTO's
		AddUserDTO user = new AddUserDTO();
		AddSupplierDTO supplier = new AddSupplierDTO();
		AddAccessDTO access = new AddAccessDTO();

		AddRetailCategoryDTO category = new AddRetailCategoryDTO();
		AddRetailDTO retail = new AddRetailDTO();
		AddStoreCategoryDTO storecategory = new AddStoreCategoryDTO();
		AddStoreDTO store = new AddStoreDTO();

		// USER
		user.setCreated(new Date());
		user.setLogin("Web");
		user.setActive("S");
		user.setUsername(dto.getUsername());
		user.setUserlogin(dto.getUserlogin().toLowerCase());
		user.setPasswd(dto.getPasswd());
		user.setProfile("SUP1");
		user.setSuperuser("S");
		user.setEmail(dto.getEmail());
		user.setImage("img/users/default.png");
		user.setPhone1((dto.getPhone1() == null ? "" : dto.getPhone1()));

		Long id_user = userservice.addUser(user);
		String userlogin = dto.getUserlogin().toLowerCase();

		// SUPPLIER
		// Obtención de Código de Empresa
		String nombre = dto.getName();
		String nombre_esp = nombre.replace(" ", "");
		int tamano = nombre_esp.length();
		String final_res = nombre_esp.substring(0, (tamano > 9 ? 9 : tamano));
		logger.debug("CODE:---->" + final_res + "<--");

		supplier.setCreated(new Date());
		supplier.setLogin("Web");
		supplier.setActive("S");
		supplier.setName(dto.getName());
		supplier.setCode(final_res);
		// Seteamos el id del plan que se escogio en el formulario de registro
		supplier.setId_plan(1l);
		// Fecha de creación del plan
		supplier.setPlan_created(new Date());
		// Fecha en que inicia el periodo del plan
		supplier.setPlan_started(new Date());

		Calendar cal = new GregorianCalendar();
		Plan datos_planes;
		try {
			datos_planes = supplierservice.getPlanById(1l);

			// Obtenemos el precio del plan guardado en la tabla lgk_plan
			double precio = datos_planes.getAmount();

			// Se obtiene el ultimo dia del Mes
			int dia_max = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

			// Se obtiene el día actual del sistema
			int dia_actual = cal.get(GregorianCalendar.DAY_OF_MONTH);

			// Se calcula el ajuste correspondiente por fecha de registro
			double ajuste = (((dia_max - dia_actual) * precio) / (cal
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)));

			supplier.setAdjustment((ajuste));

			// Se define estado del pago
			supplier.setPayment("S");

			Calendar cal_last = Calendar.getInstance();
			cal_last.set(cal_last.get(Calendar.YEAR),
					cal_last.get(Calendar.MONTH),
					cal_last.getActualMaximum(Calendar.DAY_OF_MONTH),
					cal_last.getMaximum(Calendar.HOUR_OF_DAY),
					cal_last.getMaximum(Calendar.MINUTE),
					cal_last.getMaximum(Calendar.SECOND));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, 30);

			Date fecha_fin = calendar.getTime();
			supplier.setPlan_end(fecha_fin);

			supplier.setUnpaid((long) 1);

			Long id_supplier = supplierservice.addSupplier(supplier);

			// AddAccess lgk_user & lgk_supplier

			access.setId_user(id_user);
			access.setId_empresa((long) 1);
			access.setId_supplier(id_supplier);
			access.setCreated(new Date());
			access.setLogin("Web");
			access.setActive("S");

			userservice.addAcces(access);

			// AddRetailCategory
			category.setId_supplier(id_supplier);
			category.setCreated(new Date());
			category.setLogin(userlogin);
			category.setOrderby(10);
			category.setActive("S");
			category.setCode("CAT 1");
			category.setName("Categoria 1");

			Long id_retail_category = retailservice.addRetailCategory(category);

			// AddRetail
			retail.setId_supplier(id_supplier);
			retail.setId_retail_category(id_retail_category);
			retail.setId_country((long) 1);
			retail.setId_state((long) 1);
			retail.setId_city((long) 100);
			retail.setCreated(new Date());
			retail.setLogin(userlogin);
			retail.setOrderby(10);
			retail.setActive("S");
			retail.setCode("PL 1");
			retail.setName("Plaza 1");

			Long id_retail = retailservice.addRetail(retail);

			// AddStoreCategory
			storecategory.setId_supplier(id_supplier);
			storecategory.setCreated(new Date());
			storecategory.setLogin(userlogin);
			storecategory.setOrderby(10);
			storecategory.setActive("S");
			storecategory.setCode("Cat 1");
			storecategory.setName("Categoria 1");

			Long id_store_category = retailservice
					.addStoreCategory(storecategory);

			// AddStore
			store.setId_retail(id_retail);
			store.setId_store_category(id_store_category);
			store.setId_country((long) 1);
			store.setId_state((long) 1);
			store.setId_city((long) 100);
			store.setCreated(new Date());
			store.setLogin(userlogin);
			store.setOrderby(10);
			store.setActive("S");
			store.setCode("Cli 1");
			store.setName("Cliente 1");
			store.setLatitude(21.885786);
			store.setLongitude(-102.284123);
			store.setAddress1("28 DE AGOSTO 318");
			store.setAddress2("LA ESTACION");
			store.setPostal(20259);

			retailservice.addStore(store);

			// Seteo de información para enviado de Email
			EmailDataDTO edto = new EmailDataDTO();
			edto.setFrom("soporte@logistikapp.com");
			edto.setName_from("Soporte LogistikApp");
			edto.setTo(dto.getEmail());
			edto.setBcc("soporte@logistikapp.com");
			edto.setSubject("Bienvenido a LogistikApp");
			edto.setUsername(dto.getUsername());
			edto.setUserlogin(dto.getUserlogin());
			edto.setPasswd(dto.getPasswd());
			edto.setName(dto.getName());
			edto.setPlan_started(supplier.getPlan_started());
			edto.setPlan_end(supplier.getPlan_end());
			edto.setPlan_name(datos_planes.getPlan_name());
			edto.setEmail(dto.getEmail());
			edto.setIdPlan(1l);
			edto.setCustomers(datos_planes.getCustomers());
			edto.setUsers(datos_planes.getUsers());
			edto.setPhone(dto.getPhone1());

			SendEmail.SendEmailInfo(edto);

		} catch (PlanNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
