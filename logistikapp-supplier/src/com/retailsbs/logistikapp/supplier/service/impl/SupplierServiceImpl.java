package com.retailsbs.logistikapp.supplier.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;










import com.retailsbs.logistikapp.supplier.dao.FinderSupplierDAO;
import com.retailsbs.logistikapp.supplier.dao.PlanDAO;
import com.retailsbs.logistikapp.supplier.dao.SupplierDAO;
import com.retailsbs.logistikapp.supplier.domain.Plan;
import com.retailsbs.logistikapp.supplier.domain.Supplier;
import com.retailsbs.logistikapp.supplier.dto.AddSupplierDTO;
import com.retailsbs.logistikapp.supplier.dto.AddPlanDTO;
import com.retailsbs.logistikapp.supplier.dto.PlanExample;
import com.retailsbs.logistikapp.supplier.dto.SupplierExtDTO;
import com.retailsbs.logistikapp.supplier.dto.UpdPlanDTO;
import com.retailsbs.logistikapp.supplier.dto.AvaibleSupplierCodeSearchCriteria;
import com.retailsbs.logistikapp.supplier.dto.SupplierExample;
import com.retailsbs.logistikapp.supplier.dto.UpdSupplierDTO;
import com.retailsbs.logistikapp.supplier.exception.PlanNotFoundException;
import com.retailsbs.logistikapp.supplier.exception.SupplierCodeDuplicateException;
import com.retailsbs.logistikapp.supplier.exception.SupplierNotFoundException;
import com.retailsbs.logistikapp.supplier.service.SupplierService;

/**
 * Implementacion service supplier
 * @author jorge
 * @since 05-12-2014
 */
public class SupplierServiceImpl implements SupplierService {

	private SupplierDAO dao_supplier;
	
	private PlanDAO dao_plan;
	
	private FinderSupplierDAO finder_supplier;

	public void setDao_supplier(SupplierDAO dao_supplier) {
		this.dao_supplier = dao_supplier;
	}
	
	public void setDao_plan(PlanDAO dao_plan){
		this.dao_plan = dao_plan;
	}
		
	public void setFinder_supplier(FinderSupplierDAO finder_supplier) {
		this.finder_supplier = finder_supplier;
	}


	/*
	 * Supplier
	 */
	

	@Override
	public Long addSupplier(AddSupplierDTO dto) {
		Supplier record = new Supplier();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		
		record.setId_plan(dto.getId_plan());
		record.setPlan_created(dto.getPlan_created());
		record.setPlan_started(dto.getPlan_started());
		record.setPlan_end(dto.getPlan_end());
		record.setPayment(dto.getPayment());
		record.setAdjustment(dto.getAdjustment());
		record.setUnpaid(dto.getUnpaid());
		
		//se persiste el objeto
		Long id = dao_supplier.insert(record);
		return id;
	}
	@Override
	public int updSupplier(UpdSupplierDTO dto) throws SupplierNotFoundException {
		//se obtiene el objeto que se quiere actualizar
		Supplier record = dao_supplier.selectByPrimaryKey(dto.getId_supplier());
		//Si el objeto que se quiere actualizar no existe se lanza exception
		if(record==null)
			throw new SupplierNotFoundException("Supplier con id = "+dto.getId_supplier()+" no existe");
		
		//se setean los datos
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		
		//se persiste el objeto
		int row = dao_supplier.updateByPrimaryKeySelective(record);
		return row;
	}
	@Override
	public Supplier getSupplierById(Long id_supplier) throws SupplierNotFoundException {
		// Se obtiene objeto dominio
		Supplier record = dao_supplier.selectByPrimaryKey(id_supplier);
		// si el objeto no existe se lanza exception
		if ( record == null )
			throw new SupplierNotFoundException("Supplier con id = "+id_supplier+" no existe");
		
		return record;
	}
	@Override
	public List<Supplier> getAllSupplier() {
		SupplierExample example = new SupplierExample();
		example.setOrderByClause("active desc, orderby, name");
		return dao_supplier.selectByExample(example);
	}

	@Override
	public void getAvaibleSupplierCodeSearchCriteria(AvaibleSupplierCodeSearchCriteria dto) throws SupplierCodeDuplicateException {
		SupplierExample example = new SupplierExample();
		if(dto.getId_supplier() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_supplierNotEqualTo(dto.getId_supplier());
		}
		List<Supplier> list = dao_supplier.selectByExample(example);
		if(list.size() > 0)
			throw new SupplierCodeDuplicateException("El codigo ya esta definido");
	}
	
	@Override
	public int delSupplierById(Long id_supplier) throws SupplierNotFoundException {
		Supplier sup = dao_supplier.selectByPrimaryKey(id_supplier);
		if(sup==null)
			throw new SupplierNotFoundException("No existe el proveedor que se quiere borrar");
		
		return dao_supplier.deleteByPrimaryKey(id_supplier);
	}

	@Override
	public int getTotalSupplier(Long id_plan) {
		SupplierExample example = new SupplierExample();
		example.createCriteria().andId_planEqualTo(id_plan);
		
		return dao_supplier.countByExample(example);
	}

	@Override
	public int updSupplierPlan(Long id_supplier) {
		Supplier supplier = dao_supplier.selectByPrimaryKey(id_supplier);
		Date datePlan = supplier.getPlan_end();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datePlan);
		calendar.add(Calendar.MONTH, 1);
		supplier.setPlan_end(calendar.getTime());
		return dao_supplier.updateByPrimaryKey(supplier);
	}

	/*
	 * Plan
	 */

	@Override
	public Plan getPlanById(Long id_plan) throws PlanNotFoundException {
		Plan record = dao_plan.selectByPrimaryKey(id_plan);
		//TODO falta implementación finderPlan.getPlanByIdSupplier
		if ( record == null )
			throw new PlanNotFoundException("No existe informacion del plan");
			
		return record;
	}
	@Override
	public Long addPlan(AddPlanDTO dto) {
		Plan record = new Plan();
		record.setAmount(dto.getAmount());
		record.setCallcenter(dto.getCallcenter());
		record.setCustomers(dto.getCustomers());
		record.setGroups(dto.getGroups());
		record.setHistorical(dto.getHistorical());
		record.setId_plan(dto.getId_plan());
		record.setMessages(dto.getMessages());
		record.setNotifications(dto.getNotifications());
		record.setPlan_name(dto.getPlan_name());
		record.setRoutes(dto.getRoutes());
		record.setScheduling(dto.getScheduling());
		record.setSearchbox(dto.getSearchbox());
		record.setTasks(dto.getTasks());
		record.setTravels(dto.getTravels());
		record.setUsers(dto.getUsers());
		
		Long rows = dao_plan.insert(dto); 
		return rows;
	}
	@Override
	public int updPlan(UpdPlanDTO dto) {
		Plan record = new Plan();

		record.setAmount(dto.getAmount());
		record.setCallcenter(dto.getCallcenter());
		record.setCustomers(dto.getCustomers());
		record.setGroups(dto.getGroups());
		record.setHistorical(dto.getHistorical());
		record.setId_plan(dto.getId_plan());
		record.setMessages(dto.getMessages());
		record.setNotifications(dto.getNotifications());
		record.setPlan_name(dto.getPlan_name());
		record.setRoutes(dto.getRoutes());
		record.setScheduling(dto.getScheduling());
		record.setSearchbox(dto.getSearchbox());
		record.setTasks(dto.getTasks());
		record.setTravels(dto.getTravels());
		record.setUsers(dto.getUsers());
		
		return dao_plan.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int delPlanByIdPlan(Long id_plan) {
		return dao_plan.deleteByPrimaryKey(id_plan);
	}

	@Override
	public boolean existSupplier(String name) {
		
		SupplierExample ex = new SupplierExample();
		ex.createCriteria().andNameEqualTo(name);
		return dao_supplier.countByExample(ex) > 0;
		
	}

	@Override
	public List<Plan> getAllPlans() {
		return dao_plan.selectByExample(new PlanExample());
	}

	@Override
	public int updSupplierSelective(Supplier domain) {
		return dao_supplier.updateByPrimaryKeySelective(domain);
	}

	@Override
	public List<SupplierExtDTO> getSupplierExt() {
		return finder_supplier.getSupplierExt();
	}

	@Override
	public int changeSupplierPlan(Long id_supplier, Long id_plan) {
		Supplier sup = dao_supplier.selectByPrimaryKey(id_supplier);
		sup.setId_plan(id_plan);
		return dao_supplier.updateByPrimaryKeySelective(sup);
	}	
	
}
