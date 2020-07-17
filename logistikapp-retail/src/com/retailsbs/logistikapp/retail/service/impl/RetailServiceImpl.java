package com.retailsbs.logistikapp.retail.service.impl;

import java.util.Date;
import java.util.List;

import com.retailsbs.logistikapp.retail.dao.ActivesDAO;
import com.retailsbs.logistikapp.retail.dao.ActivesVisitedDAO;
import com.retailsbs.logistikapp.retail.dao.CityDAO;
import com.retailsbs.logistikapp.retail.dao.CountryDAO;
import com.retailsbs.logistikapp.retail.dao.FinderActivesVisitedDAO;
import com.retailsbs.logistikapp.retail.dao.FinderCityDAO;
import com.retailsbs.logistikapp.retail.dao.FinderCountryDAO;
import com.retailsbs.logistikapp.retail.dao.FinderLocalityDAO;
import com.retailsbs.logistikapp.retail.dao.FinderRetailCategoryDAO;
import com.retailsbs.logistikapp.retail.dao.FinderRetailDAO;
import com.retailsbs.logistikapp.retail.dao.FinderRetailDeptDAO;
import com.retailsbs.logistikapp.retail.dao.FinderStateDAO;
import com.retailsbs.logistikapp.retail.dao.FinderStoreCategoryDAO;
import com.retailsbs.logistikapp.retail.dao.FinderStoreDAO;
import com.retailsbs.logistikapp.retail.dao.LocalityDAO;
import com.retailsbs.logistikapp.retail.dao.RetailCategoryDAO;
import com.retailsbs.logistikapp.retail.dao.RetailDAO;
import com.retailsbs.logistikapp.retail.dao.RetailDeptDAO;
import com.retailsbs.logistikapp.retail.dao.StateDAO;
import com.retailsbs.logistikapp.retail.dao.StoreCategoryDAO;
import com.retailsbs.logistikapp.retail.dao.StoreDAO;
import com.retailsbs.logistikapp.retail.domain.Actives;
import com.retailsbs.logistikapp.retail.domain.ActivesVisited;
import com.retailsbs.logistikapp.retail.domain.City;
import com.retailsbs.logistikapp.retail.domain.Country;
import com.retailsbs.logistikapp.retail.domain.Locality;
import com.retailsbs.logistikapp.retail.domain.Retail;
import com.retailsbs.logistikapp.retail.domain.RetailCategory;
import com.retailsbs.logistikapp.retail.domain.RetailDept;
import com.retailsbs.logistikapp.retail.domain.State;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.domain.StoreCategory;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsDTO;
import com.retailsbs.logistikapp.retail.dto.ActivesExample;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExample;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExt;
import com.retailsbs.logistikapp.retail.dto.AddCityDTO;
import com.retailsbs.logistikapp.retail.dto.AddCountryDTO;
import com.retailsbs.logistikapp.retail.dto.AddLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDTO;
import com.retailsbs.logistikapp.retail.dto.AddRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.AddStateDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AddStoreDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetail;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdRetailIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategory;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdStoreCategoryIdRoute;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreByIdSupplierIdRoute;
import com.retailsbs.logistikapp.retail.dto.ChangeRetailCriteria;
import com.retailsbs.logistikapp.retail.dto.CityCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.CityDTO;
import com.retailsbs.logistikapp.retail.dto.CityExample;
import com.retailsbs.logistikapp.retail.dto.CountryAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.CountryDTO;
import com.retailsbs.logistikapp.retail.dto.CountryExample;
import com.retailsbs.logistikapp.retail.dto.CountrySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetCitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetLocalitySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.GetStateSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityCodeSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.LocalityDTO;
import com.retailsbs.logistikapp.retail.dto.LocalityExample;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetai;
import com.retailsbs.logistikapp.retail.dto.QtyStrCheckByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.RetailCategoryExample;
import com.retailsbs.logistikapp.retail.dto.RetailDTO;
import com.retailsbs.logistikapp.retail.dto.RetailDeptExample;
import com.retailsbs.logistikapp.retail.dto.RetailExample;
import com.retailsbs.logistikapp.retail.dto.RetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StateDTO;
import com.retailsbs.logistikapp.retail.dto.StateExample;
import com.retailsbs.logistikapp.retail.dto.StateQtyCityDTO;
import com.retailsbs.logistikapp.retail.dto.StoreActiveByIdsRetailSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInRouteCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreAvailableInTravelCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.AvailableStoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByIdsSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreByRetailDTO;
import com.retailsbs.logistikapp.retail.dto.StoreByRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryActiveSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCategoryExample;
import com.retailsbs.logistikapp.retail.dto.StoreCategorySearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreCodeAvaibleSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreDTO;
import com.retailsbs.logistikapp.retail.dto.StoreExample;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreNotInTravelSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.StoreRouteDTO;
import com.retailsbs.logistikapp.retail.dto.StoreSearchCriteria;
import com.retailsbs.logistikapp.retail.dto.TotalStoreCreateCriteria;
import com.retailsbs.logistikapp.retail.dto.UpdCityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdCountryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdLocalityDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDTO;
import com.retailsbs.logistikapp.retail.dto.UpdRetailDeptDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStateDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreCategoryDTO;
import com.retailsbs.logistikapp.retail.dto.UpdStoreDTO;
import com.retailsbs.logistikapp.retail.dto.VerifyAndRegistCheckInCriteria;
import com.retailsbs.logistikapp.retail.exception.CityCodeDuplicateException;
import com.retailsbs.logistikapp.retail.exception.CityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.CountryCodeDuplicateException;
import com.retailsbs.logistikapp.retail.exception.CountryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.LocalityCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.exception.LocalityNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDeptNotFoundException;
import com.retailsbs.logistikapp.retail.exception.RetailDuplicadoException;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StateDuplicateException;
import com.retailsbs.logistikapp.retail.exception.StateNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreCategoryNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreCodeDuplicadeException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;

/**
 * Implementacion servicio Retail
 * @author Juan Carlos Ramos Campos
 * @since 20-11-2014
 * @modified 28-12-2014 -- JORGE -- Se considera orderby en metodo getAllState, getAllCity, getAllLocality, getAllRetailDept, getAllRetail
 */
public class RetailServiceImpl implements RetailService {

	private CountryDAO dao_country;
	private StateDAO dao_state;
	private CityDAO dao_city;
	private LocalityDAO dao_locality;
	private RetailDAO dao_retail;
	private RetailDeptDAO dao_retail_dept;
	private StoreDAO dao_store;
	private ActivesDAO dao_actives;
	private ActivesVisitedDAO dao_actives_visited;
	private RetailCategoryDAO dao_retail_category;
	private FinderRetailDAO dao_finder_retail;
	private FinderStoreDAO dao_finder_store;
	private FinderStateDAO dao_finder_state;
	private FinderCityDAO dao_finder_city;
	private FinderLocalityDAO dao_finder_locality;
	private FinderCountryDAO dao_finder_country;
	private StoreCategoryDAO dao_store_category;
	private FinderStoreCategoryDAO dao_finder_store_category;
	private FinderRetailCategoryDAO dao_finder_retail_category;
	private FinderRetailDeptDAO dao_finder_retail_dept;
	private FinderActivesVisitedDAO dao_finder_actives_visited;
	
	public void setDao_finder_retail_dept(FinderRetailDeptDAO dao_finder_retail_dept) {
		this.dao_finder_retail_dept = dao_finder_retail_dept;
	}
	public void setDao_finder_retail_category(
			FinderRetailCategoryDAO dao_finder_retail_category) {
		this.dao_finder_retail_category = dao_finder_retail_category;
	}
	public void setDao_finder_store_category(
			FinderStoreCategoryDAO dao_finder_store_category) {
		this.dao_finder_store_category = dao_finder_store_category;
	}
	public void setDao_store_category(StoreCategoryDAO dao_store_category) {
		this.dao_store_category = dao_store_category;
	}
	public void setDao_finder_country(FinderCountryDAO dao_finder_country) {
		this.dao_finder_country = dao_finder_country;
	}
	public void setDao_finder_locality(FinderLocalityDAO dao_finder_locality) {
		this.dao_finder_locality = dao_finder_locality;
	}
	public void setDao_retail_category(RetailCategoryDAO dao_retail_category) {
		this.dao_retail_category = dao_retail_category;
	}
	public void setDao_finder_city(FinderCityDAO dao_finder_city) {
		this.dao_finder_city = dao_finder_city;
	}
	public void setDao_finder_state(FinderStateDAO dao_finder_state) {
		this.dao_finder_state = dao_finder_state;
	}
	public void setDao_finder_store(FinderStoreDAO dao_finder_store) {
		this.dao_finder_store = dao_finder_store;
	}
	public void setDao_country(CountryDAO dao_country) {
		this.dao_country = dao_country;
	}
	public void setDao_state(StateDAO dao_state) {
		this.dao_state = dao_state;
	}
	public void setDao_city(CityDAO dao_city) {
		this.dao_city = dao_city;
	}
	public void setDao_locality(LocalityDAO dao_locality) {
		this.dao_locality = dao_locality;
	}
	public void setDao_retail(RetailDAO dao_retail) {
		this.dao_retail = dao_retail;
	}
	public void setDao_retail_dept(RetailDeptDAO dao_retail_dept) {
		this.dao_retail_dept = dao_retail_dept;
	}
	public void setDao_store(StoreDAO dao_store) {
		this.dao_store = dao_store;
	}
	public void setDao_finder_retail(FinderRetailDAO dao_finder_retail) {
		this.dao_finder_retail = dao_finder_retail;
	}
	public void setDao_actives(ActivesDAO dao_actives) {
		this.dao_actives = dao_actives;
	}
	public void setDao_actives_visited(ActivesVisitedDAO dao_actives_visited) {
		this.dao_actives_visited = dao_actives_visited;
	}	
	public void setDao_finder_actives_visited(
			FinderActivesVisitedDAO dao_finder_actives_visited) {
		this.dao_finder_actives_visited = dao_finder_actives_visited;
	}
	
	/*
	 * (non-Javadoc)
	 * Country
	 */
	@Override
	public Long addCountry(AddCountryDTO dto) {
		Country record = new Country();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setPrefix(dto.getPrefix());
		
		Long id = dao_country.insert(record);
		return id;
	}

	@Override
	public int updCountry(UpdCountryDTO dto) throws CountryNotFoundException {
		Country ctry = dao_country.selectByPrimaryKey(dto.getId_country());
		if(ctry == null)
			throw new CountryNotFoundException("Country con id = "+dto.getId_country()+" no existe.");
		
		ctry.setActive(dto.getActive());
		ctry.setCode(dto.getCode());
		ctry.setCreated(dto.getCreated());
		ctry.setLogin(dto.getLogin());
		ctry.setModified(dto.getModified());
		ctry.setName(dto.getName());
		ctry.setOrderby(dto.getOrderby());
		ctry.setPrefix(dto.getPrefix());
		int row = dao_country.updateByPrimaryKeySelective(ctry);

		return row;
	}

	@Override
	public Country getCountryById(Long id_country) throws CountryNotFoundException {
		Country ctry = dao_country.selectByPrimaryKey(id_country);
		if(ctry == null)
			throw new CountryNotFoundException("Country con id = "+id_country+" no existe.");

		return ctry;
	}

	@Override
	public List<Country> getAllCountry() {
		CountryExample example = new CountryExample();
		example.setOrderByClause("active desc, orderby, name");
		return dao_country.selectByExample(example);
	}

	@Override
	public void getCountryAvaibleByCriteria(CountryAvaibleSearchCriteria dto) throws CountryCodeDuplicateException {
		CountryExample example = new CountryExample();
		if(dto.getId_country() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_countryNotEqualTo(dto.getId_country());
		}
		List<Country> list = dao_country.selectByExample(example);
		if(list.size() > 0)
			throw new CountryCodeDuplicateException("C\u00f3digo de pais ya definido");
	}

	/*
	 * (non-Javadoc)
	 * State
	 */
	@Override
	public Long addState(AddStateDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		State record = new State();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setModified(dto.getModified());
		record.setId_country(dto.getId_country());
		record.setLogin(dto.getLogin());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setPrefix(dto.getPrefix());
		
		// Se persiste el objeto
		Long id = dao_state.insert(record);
		return id;
	}
	@Override
	public int updState(UpdStateDTO dto) throws StateNotFoundException {
		// Se controla que el objeto de dominio exista
		State record = dao_state.selectByPrimaryKey(dto.getId_state());
		if( record == null )
			throw new StateNotFoundException("State id = "+dto.getId_state()+" no existe");
		
		// Se setea el objeto de dominio
		// con los datos del dto
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setId_country(dto.getId_country());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setPrefix(dto.getPrefix());

		// Se persiste el objeto
		int rows = dao_state.updateByPrimaryKeySelective(record);
		return rows;
	}
	@Override
	public State getStateById(Long id_state) throws StateNotFoundException {
		// Se controla que el objeto de dominio exista
		State record = dao_state.selectByPrimaryKey(id_state);
		if( record == null )
			throw new StateNotFoundException("State id = "+id_state+" no existe");
		
		return record;
	}
	@Override
	public List<State> getAllState() {
		StateExample example = new StateExample();
		example.setOrderByClause("orderby");
		return dao_state.selectByExample(example);
	}
	@Override
	public void getStateAvaibleByCriteria(StateAvaibleSearchCriteria dto) throws StateDuplicateException {
		StateExample example = new StateExample();
		if(dto.getId_state() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_stateNotEqualTo(dto.getId_state());
		}
		List<State> list = dao_state.selectByExample(example);
		if(list.size() > 0)
			throw new StateDuplicateException("Estado code=" + dto.getCode()+" utilizado por otro estado");
	}
	@Override
	public List<State> getAllStatesByIdCountry(Long id_country) {
		// Se define criterio de busqueda
		StateExample example = new StateExample();
		example.createCriteria().andId_countryEqualTo(id_country);
		example.setOrderByClause("orderby");
		// Se obtiene y se retorna listado de objetos
		return dao_state.selectByExample(example);
	}
	
	/*
	 * City
	 */
	
	@Override
	public Long addCity(AddCityDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		City record = new City();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_state(dto.getId_state());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		
		//Se persiste el objeto
		Long id = dao_city.insert(record);
		return id;
	}
	@Override
	public int updCity(UpdCityDTO dto) throws CityNotFoundException {
		// Se obtiene y se controla que exista el objeto de dominio
		City record = dao_city.selectByPrimaryKey(dto.getId_city());
		if( record == null )
			throw new CityNotFoundException("City id = "+dto.getId_city()+" no existe");

		// Se setea el objeto de dominio
		// con los datos del dto
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setId_state(dto.getId_state());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		
		//Se persiste el objeto
		int rows = dao_city.updateByPrimaryKeySelective(record);
		return rows;
	}
	@Override
	public City getCityById(Long id_city) throws CityNotFoundException {
		//se obtiene los datos del registro con id = id_city 
		City city = dao_city.selectByPrimaryKey(id_city);
		//En caso de no encontrar el objeto que se busca se lanza una exception
		if(city == null)
			throw new CityNotFoundException("City id = "+id_city+" no existe");
		//En caso que si exista se retorna el objeto
		return city;
	}
	@Override
	public List<City> getAllCity() {
		CityExample example = new CityExample();
		example.setOrderByClause("orderby");
		return dao_city.selectByExample(example);
	}
	@Override
	public void getCityCodeAvaibleByCriteria(CityCodeAvaibleSearchCriteria dto) throws CityCodeDuplicateException {
		CityExample example = new CityExample();
		if(dto.getId_city() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_cityNotEqualTo(dto.getId_city());
		}
		List<City> list = dao_city.selectByExample(example);
		if(list.size() > 0)
			throw new CityCodeDuplicateException("C\u00f3digo de ciudad ya definido");
	}
	@Override
	public List<City> getAllCityByIdState(Long id_state) {
		// Se define criterio de busqueda
		CityExample example = new CityExample();
		example.createCriteria().andId_stateEqualTo(id_state);
		example.setOrderByClause("orderby, name asc");
		
		// Se obtiene y se retorna listado de objetos de dominio
		return dao_city.selectByExample(example);
	}

	/*
	 * Locality
	 */
	@Override
	public Long addLocality(AddLocalityDTO dto) {
		Locality locality = new Locality();
		//Se setean datos para el nuevo registro locality
		locality.setActive(dto.getActive());
		locality.setCode(dto.getCode());
		locality.setCreated(dto.getCreated());
		locality.setId_city(dto.getId_city());
		locality.setLogin(dto.getLogin());
		locality.setModified(dto.getModified());
		locality.setName(dto.getName());
		locality.setOrderby(dto.getOrderby());
		//Se persiste el objeto
		Long id_locality = dao_locality.insert(locality);
		//Retorna el id del registro que se creo 
		return id_locality;
	}
	@Override
	public int updLocality(UpdLocalityDTO dto) throws LocalityNotFoundException {
		Locality locality = dao_locality.selectByPrimaryKey(dto.getId_locality());
		//Se verifica que el objeto que se quiere actualizar exista, sino, se lanza una exception 
		if(locality == null)
			throw new LocalityNotFoundException("Locality id = "+dto.getId_locality()+" no existe");
		//Se setean datos para el objeto
		locality.setActive(dto.getActive());
		locality.setCode(dto.getCode());
		locality.setId_city(dto.getId_city());
		locality.setLogin(dto.getLogin());
		locality.setModified(dto.getModified());
		locality.setName(dto.getName());
		locality.setOrderby(dto.getOrderby());
		
		//Se persiste el objeto
		int row = dao_locality.updateByPrimaryKeySelective(locality);
		return row;
	}
	@Override
	public Locality getLocalityById(Long id_locality) throws LocalityNotFoundException {
		// Se obtiene objeto locality por id_locality
		Locality locality = dao_locality.selectByPrimaryKey(id_locality);
		//Si el onbjeto no existe se lanza exception
		if(locality == null)
			throw new LocalityNotFoundException("Locality id = "+id_locality+" no existe");
		
		return locality;
	}
	@Override
	public List<Locality> getAllLocality() {
		LocalityExample example = new LocalityExample();
		example.setOrderByClause("orderby");
		return dao_locality.selectByExample(example);
	}
	@Override
	public void getLocalityCodeAvaibleByCriteria(LocalityCodeSearchCriteria dto) throws LocalityCodeDuplicadeException {
		LocalityExample example = new LocalityExample();
		if(dto.getId_locality() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_localityNotEqualTo(dto.getId_locality());
		}
		List<Locality> list = dao_locality.selectByExample(example);
		if(list.size() > 0)
			throw new LocalityCodeDuplicadeException("C\u00f3digo de localidad ya definido");
	}
	public List<Locality> getAllLocalityByIdCity(Long id_city) {
		// Se define criterio de busqueda
		LocalityExample example = new LocalityExample();
		example.createCriteria().andId_cityEqualTo(id_city);
		example.setOrderByClause("orderby");
		// Se obtiene y se retorna listado de objetos de dominio
		return dao_locality.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * Retail
	 */
	@Override
	public Long addRetail(AddRetailDTO dto) {
		Retail retail = new Retail();
		//Se setean datos que se agregaran en el nuevo objeto
		retail.setActive(dto.getActive());
		retail.setAddress1(dto.getAddress1());
		retail.setAddress2(dto.getAddress2());
		retail.setCode(dto.getCode());
		retail.setCreated(dto.getCreated());
		retail.setId_city(dto.getId_city());
		retail.setId_country(dto.getId_country());
		retail.setId_locality(dto.getId_locality());
		retail.setId_retail_category(dto.getId_retail_category());
		retail.setId_state(dto.getId_state());
		retail.setId_supplier(dto.getId_supplier());
		retail.setLogin(dto.getLogin());
		retail.setModified(dto.getModified());
		retail.setName(dto.getName());
		retail.setOrderby(dto.getOrderby());
		retail.setPostal(dto.getPostal());
		retail.setEmail(dto.getEmail());
		retail.setPhone(dto.getPhone());
		retail.setObs(dto.getObs());
		//Se persiste el objeto
		Long id_retail = dao_retail.insert(retail);
		return id_retail;
	}
	@Override
	public int updRetail(UpdRetailDTO dto) throws RetailNotFoundException {
		// Se controla que el objeto de dominio exista
		Retail retail = dao_retail.selectByPrimaryKey(dto.getId_retail());
		if ( retail == null )
			throw new RetailNotFoundException("Retail id="+dto.getId_retail()+" no existe");
		
		// Se setea el objeto de dominio
		// con los datos del dto
		retail.setActive(dto.getActive());
		retail.setAddress1(dto.getAddress1());
		retail.setAddress2(dto.getAddress2());
		retail.setCode(dto.getCode());
		retail.setCreated(dto.getCreated());
		retail.setId_city(dto.getId_city());
		retail.setId_country(dto.getId_country());
		retail.setId_locality(dto.getId_locality());
		retail.setId_retail_category(dto.getId_retail_category());
		retail.setId_state(dto.getId_state());
		retail.setId_supplier(dto.getId_supplier());
		retail.setLogin(dto.getLogin());
		retail.setModified(dto.getModified());
		retail.setName(dto.getName());
		retail.setOrderby(dto.getOrderby());
		retail.setPostal(dto.getPostal());
		retail.setEmail(dto.getEmail());
		retail.setPhone(dto.getPhone());
		retail.setObs(dto.getObs());
		
		//Se setea el objeto
		int row = dao_retail.updateByPrimaryKeySelective(retail);
		return row;
	}
	@Override
	public Retail getRetailById(Long id_retail) throws RetailNotFoundException {
		// Se controla que el objeto de dominio exista
		Retail retail = dao_retail.selectByPrimaryKey(id_retail);
		if ( retail == null )
			throw new RetailNotFoundException("Retail id="+id_retail+" no existe");
		
		return retail;
	}
	@Override
	public List<Retail> getAllRetail() {
		// Se define criterio de b�squeda
		RetailExample example = new RetailExample();
		example.setOrderByClause("orderby");
		// Se obtiene una lista de objetos de dominio
		return dao_retail.selectByExample(example);
	}
	@Override
	public void getRetailAvaibleByCriteria(RetailAvaibleSearchCriteria dto) throws RetailDuplicadoException {
		RetailExample example = new RetailExample();
		if(dto.getId_retail() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_supplierEqualTo(dto.getId_supplier());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_retailNotEqualTo(dto.getId_retail())
			.andId_supplierEqualTo(dto.getId_supplier());
		}
		List<Retail> list = dao_retail.selectByExample(example);
		if(list.size() > 0)
			throw new RetailDuplicadoException("C\u00f3digo de cliente ya definido");
	}
	@Override
	public int delRetailById(Long id_retail) throws RetailNotFoundException {
		Retail retail = dao_retail.selectByPrimaryKey(id_retail);
		if(retail==null)
			throw new RetailNotFoundException("No existe la plaza.");

		return dao_retail.deleteByPrimaryKey(id_retail);
	}
	@Override
	public List<Retail> getRetailByIdSupplier(Long id_supplier) {
		RetailExample example = new RetailExample();
		example.createCriteria()
		.andId_supplierEqualTo(id_supplier);
		return dao_retail.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * RetailDept
	 */
	@Override
	public Long addRetailDept(AddRetailDeptDTO dto) {
		RetailDept rd = new RetailDept();
		rd.setActive(dto.getActive());
		rd.setContact(dto.getContact());
		rd.setCreated(dto.getCreated());
		rd.setEmail(dto.getEmail());
		rd.setId_retail(dto.getId_retail());
		rd.setLogin(dto.getLogin());
		rd.setModified(dto.getModified());
		rd.setName(dto.getName());
		rd.setOrderby(dto.getOrderby());
		rd.setPhone(dto.getPhone());
		//Se persiste el objeto
		Long id = dao_retail_dept.insert(rd);
		return id;
	}

	@Override
	public int updRetailDept(UpdRetailDeptDTO dto) throws RetailDeptNotFoundException {
		// Se obtiene el objeto que s quiere atualizar
		RetailDept rd = dao_retail_dept.selectByPrimaryKey(dto.getId_retail_dept());
		//Si el objeto no existe se lanza exceptio
		if(rd == null)
			throw new RetailDeptNotFoundException("RetailDept con id = "+dto.getId_retail_dept()+" no existe.");
		//En caso contrariko se setean los datos.
		rd.setActive(dto.getActive());
		rd.setContact(dto.getContact());
		rd.setEmail(dto.getEmail());
		rd.setId_retail(dto.getId_retail());
		rd.setLogin(dto.getLogin());
		rd.setModified(dto.getModified());
		rd.setName(dto.getName());
		rd.setOrderby(dto.getOrderby());
		rd.setPhone(dto.getPhone());
		//Se setea el objeto
		int row = dao_retail_dept.updateByPrimaryKeySelective(rd);
		
		return row;
	}
	@Override
	public RetailDept getRetailDeptById(Long id_retail_dept) throws RetailDeptNotFoundException {
		RetailDept rd = dao_retail_dept.selectByPrimaryKey(id_retail_dept);
		//si el objeto no se encuentra se lanza exception
		if(rd == null)
			throw new RetailDeptNotFoundException("RetailDept con id = "+id_retail_dept+" no existe.");
		return rd;
	}
	@Override
	public List<RetailDept> getAllRetailDept() {
		RetailDeptExample example = new RetailDeptExample();
		example.setOrderByClause("orderby");
		return dao_retail_dept.selectByExample(new RetailDeptExample());
	}
	@Override
	public List<RetailDept> getRetailDeptsByIdRetail(Long id_retail) {
		RetailDeptExample example = new RetailDeptExample();
		example.createCriteria().andId_retailEqualTo(id_retail);
		
		List<RetailDept> list = dao_retail_dept.selectByExample(example);
		return list;
	}
	@Override
	public int delRetailDeptById(Long id_retaildept) throws RetailDeptNotFoundException {
		RetailDept retaildept = dao_retail_dept.selectByPrimaryKey(id_retaildept);
		if(retaildept==null) 
			throw new RetailDeptNotFoundException("Retail dept, id ="+id_retaildept+" no existe");
		
		int row = dao_retail_dept.deleteByPrimaryKey(id_retaildept);
		return row;
	}

	/*
	 * (non-Javadoc)
	 * Store
	 */
	@Override
	public Long addStore(AddStoreDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		Store record = new Store();
		record.setActive(dto.getActive());
		record.setAddress1(dto.getAddress1());
		record.setAddress2(dto.getAddress2());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_store_category(dto.getId_store_category());
		record.setId_city(dto.getId_city());
		record.setId_country(dto.getId_country());
		record.setId_locality(dto.getId_locality());
		record.setId_retail(dto.getId_retail());
		record.setId_state(dto.getId_state());
		record.setLatitude(dto.getLatitude());
		record.setLogin(dto.getLogin());
		record.setLongitude(dto.getLongitude());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setPostal(dto.getPostal());
		record.setEmail(dto.getEmail());
		record.setPhone(dto.getPhone());
		record.setShelf(dto.getShelf());
		
		// Se persiste el objeto
		Long id = dao_store.insert(record);
		return id;
	}
	@Override
	public int updStore(UpdStoreDTO dto) throws StoreNotFoundException {
		// Se obtiene y se controla que el objeto de dominio exista
		Store record = dao_store.selectByPrimaryKey(dto.getId_store());
		if( record == null )
			throw new StoreNotFoundException("Store id = "+dto.getId_store()+" no existe");

		// Se setea el objeto de dominio
		// con los datos del dto
		record.setActive(dto.getActive());
		record.setAddress1(dto.getAddress1());
		record.setAddress2(dto.getAddress2());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_city(dto.getId_city());
		record.setId_store_category(dto.getId_store_category());
		record.setId_country(dto.getId_country());
		record.setId_locality(dto.getId_locality());
		record.setId_retail(dto.getId_retail());
		record.setId_state(dto.getId_state());
		record.setId_store(dto.getId_store());
		record.setLatitude(dto.getLatitude());
		record.setLogin(dto.getLogin());
		record.setLongitude(dto.getLongitude());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setPostal(dto.getPostal());
		record.setEmail(dto.getEmail());
		record.setPhone(dto.getPhone());
		record.setShelf(dto.getShelf());
		
		//Se persiste el objeto
		int rows = dao_store.updateByPrimaryKeySelective(record);
		return rows;
	}
	@Override
	public Store getStoreById(Long id_store) throws StoreNotFoundException {
		// Se obtiene y se controla que exista objeto de dominio
		Store record = dao_store.selectByPrimaryKey(id_store);
		if( record == null )
			throw new StoreNotFoundException("Store id = "+id_store+" no existe");
		
		return record;
	}
	@Override
	public List<Store> getAllStore() {
		// Se define criterio de busqueda
		StoreExample example = new StoreExample();
		example.setOrderByClause("orderby");
		// Se obtiene listado de objetos de dominio
		return dao_store.selectByExample(example);
	}
	@Override
	public void getStoreCodeAvaibleByCriteria(StoreCodeAvaibleSearchCriteria dto) throws StoreCodeDuplicadeException {
		StoreExample example = new StoreExample();
		if(dto.getId_store() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_retailEqualTo(dto.getId_retail());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_storeNotEqualTo(dto.getId_store())
			.andId_retailEqualTo(dto.getId_retail());
		}
		List<Store> list = dao_store.selectByExample(example);
		if( list.size() > 0 )
			throw new StoreCodeDuplicadeException("El c\u00f3digo '"+ dto.getCode() +"' ya existe");
	}

	@Override
	public int delStoreById(Long id_store) throws StoreNotFoundException {
		Store store = dao_store.selectByPrimaryKey(id_store);
		if(store==null) 
			throw new StoreNotFoundException("Tienda no existe.");
		
		return dao_store.deleteByPrimaryKey(id_store);
	}
	
	/*
	 * (non-Javadoc)
	 * Finder/retail
	 */
	
	@Override
	public List<RetailDTO> getRetailByCriteria(RetailSearchCriteria dto) {
		return dao_finder_retail.getRetailByCriteria(dto);
	}
	@Override
	public RetailDTO getRetailExtById(Long id_retail) throws RetailNotFoundException {
		// Se controla que el objeto de dominio Retail exista
		Retail record = dao_retail.selectByPrimaryKey(id_retail);
		if( record == null )
			throw new RetailNotFoundException("Retail, id="+id_retail+" no existe");
		
		// Se obtiene y se retorna objeto con informacion
		// extendida del dto
		return dao_finder_retail.getRetailExtById(id_retail);
	}
	@Override
	public List<QtyStrCheckByRetailDTO> getRetailCountStrCheck(QtyStrCheckByRetai dto) {
		List<QtyStrCheckByRetailDTO> list = dao_finder_retail.getRetailCountStrCheck(dto);
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getQty_chec()!=0)
				list.get(i).setPorcent((list.get(i).getQty_chec()*100.00)/list.get(i).getQty_tot());
			else
				list.get(i).setPorcent(0.0);
		}
		return list;
	}
	
	@Override
	public int delRetailByIdSupplier(Long id_supplier) {
		return dao_finder_retail.delRetailByIdSupplier(id_supplier);
	}

	/*
	 * (non-Javadoc)
	 * Finder/store
	 */
	
	@Override
	public List<StoreDTO> getStoreByCriteria(StoreSearchCriteria dto) {
		return dao_finder_store.getStoreByCriteria(dto);
	}
	@Override
	public List<Store> getStoreByIds(StoreByIdsSearchCriteria dto) {
		return dao_finder_store.getStoreByIds(dto);
	}
	@Override
	public List<Store> getStoreByIdRetail(Long id_retail) {
		return dao_finder_store.getStoreByIdRetail(id_retail);
	}
	@Override
	public List<Store> getAvailableStoreByIdRetail(AvailableStoreByIdRetail dto) {
		return dao_finder_store.getAvailableStoreByIdRetail(dto);
	}
	@Override
	public List<Store> getAvailableStoreByIdRetailAndIdRoute(AvailableStoreByIdRetailIdRoute dto) {
		return dao_finder_store.getAvailableStoreByIdRetailAndIdRoute(dto);
	}
	@Override
	public List<Store> getStoreByIdRetailCategory(Long id_retail_category) {
		return dao_finder_store.getStoreByIdRetailCategory(id_retail_category);
	}
	@Override
	public StoreDTO getStoreExtById(Long id_store) throws StoreNotFoundException {
		// Se controla que el objeto de dominio Store exista
		Store record = dao_store.selectByPrimaryKey(id_store);
		if( record == null )
			throw new StoreNotFoundException("Store, id="+id_store+" no existe");
		
		// Se obtiene y se retorna objeto con informacion
		// extendida del dto
		return dao_finder_store.getStoreExtById(id_store);
	}
	@Override
	public List<Long> getIdStoresActiveByIdRetail(Long id_retail) {
		return dao_finder_store.getIdStoresActiveByIdRetail(id_retail);
	}
	
	@Override
	public List<StoreByRouteDTO> getStoreByRoute(Long id_route) {
		return dao_finder_store.getStoreByRoute(id_route);
	}
	@Override
	public List<StoreDTO> getStoreAvailableByRoute(StoreAvailableInRouteCriteria dto) {
		return dao_finder_store.getStoreAvailableByRoute(dto);
	}
	@Override
	public List<StoreDTO> getStoreInTravelByRoute(StoreAvailableInRouteCriteria dto) {
		return dao_finder_store.getStoreInTravelByRoute(dto);
	}
	@Override
	public List<StoreDTO> getStoreInOtherTravelByCriteria(StoreSearchCriteria dto) {
		return dao_finder_store.getStoreInOtherTravelByCriteria(dto);
	}
	@Override
	public Integer getTotalStoreByCriteria(TotalStoreCreateCriteria dto) {
		return dao_finder_store.getTotalStoreByCriteria(dto);
	}
	@Override
	public int delStoreByIdStoreCategory(Long id_store_category) {
		return dao_finder_store.delStoreByIdStoreCategory(id_store_category);
	}
	@Override
	public List<AvailableStoreDTO> getAvailableStoreByCriteria(AvailableStoreSearchCriteria dto) {
		return dao_finder_store.getAvailableStoreByCriteria(dto);
	}

	/*
	 * (non-Javadoc)
	 * Finder/state
	 */
	
	@Override
	public List<StateDTO> getStateByCriteria(GetStateSearchCriteria dto) {
		return dao_finder_state.getStateByCriteria(dto);
	}
	@Override
	public List<StateQtyCityDTO> getAllStateQtyCityByIdCountry(Long id_country) {
		return dao_finder_state.getAllStateQtyCityByIdCountry(id_country);
	}

	/*
	 * (non-Javadoc)
	 * Finder/city
	 */
	
	@Override
	public List<CityDTO> getCityByCriteria(GetCitySearchCriteria dto) {
		return dao_finder_city.getCityByCriteria(dto);
	}
	@Override
	public Integer getCityTotalByCriteria(GetCitySearchCriteria dto) {
		return dao_finder_city.getCityTotalByCriteria(dto);
	}

	/*
	 * (non-Javadoc)
	 * RetailCategory
	 */
	
	@Override
	public Long addRetailCategory(AddRetailCategoryDTO dto) {
		// Se crea el objeto de dominio
		// a partir de los datos del dto
		RetailCategory record = new RetailCategory();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setId_supplier(dto.getId_supplier());
		// Se persiste el objeto
		Long id = dao_retail_category.insert(record);
		return id;
	}
	@Override
	public int updRetailCategory(UpdRetailCategoryDTO dto) throws RetailCategoryNotFoundException {
		// se obtiene y se controla objeto de dominio
		RetailCategory record = dao_retail_category.selectByPrimaryKey(dto.getId_retail_category());
		if( record == null )
			throw new RetailCategoryNotFoundException("RetailCategory id = "+dto.getId_retail_category()+" no existe");
		
		// Se setea objeto de dominio
		// con los datos del dto
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		
		// Se persiste el objeto
		int rows = dao_retail_category.updateByPrimaryKeySelective(record);
		return rows;
	}
	@Override
	public RetailCategory getRetailCategoryById(Long id_retail_category) throws RetailCategoryNotFoundException {
		// se obtiene y se controla objeto de dominio
		RetailCategory record = dao_retail_category.selectByPrimaryKey(id_retail_category);
		if( record == null )
			throw new RetailCategoryNotFoundException("RetailCategory id = "+id_retail_category+" no existe");
		
		return record;
	}

	@Override
	public List<RetailCategory> getAllRetailCategory() {
		// Se define criterio de busqueda
		RetailCategoryExample example = new RetailCategoryExample();
		example.setOrderByClause("orderby");
		// Se obtiene una lista de objetos de dominio
		return dao_retail_category.selectByExample(new RetailCategoryExample());
	}
	@Override
	public List<RetailCategory> getAllRetailCategoryByIdSupplier(Long id_supplier) {
		// Se define criterio de busqueda
		RetailCategoryExample example = new RetailCategoryExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);
		example.setOrderByClause("active desc, orderby, name");
		// Se obtiene una lista de objetos de dominio
		return dao_retail_category.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * Finder/locality
	 */
	
	@Override
	public List<LocalityDTO> getLocalityByCriteria(GetLocalitySearchCriteria dto) {
		return dao_finder_locality.getLocalityByCriteria(dto);
	}
	@Override
	public Integer getLocalityTotalByCriteria(GetLocalitySearchCriteria dto) {
		return dao_finder_locality.getLocalityTotalByCriteria(dto);
	}

	/*
	 * (non-Javadoc)
	 * Finder/country
	 */
	@Override
	public List<CountryDTO> getCountryByCriteria(CountrySearchCriteria dto) {
		return dao_finder_country.getCountryByCriteria(dto);
	}
	@Override
	public Integer getCountryTotalByCriteria(CountrySearchCriteria dto) {
		return dao_finder_country.getCountryTotalByCriteria(dto);
	}

	/*
	 * (non-Javadoc)
	 * StoreCategory
	 */
	
	@Override
	public Long addStoreCategory(AddStoreCategoryDTO dto) {
		StoreCategory record = new StoreCategory();
		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_supplier(dto.getId_supplier());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setLogin(dto.getLogin());
		
		Long id = dao_store_category.insert(record);
		return id;
	}

	@Override
	public int updStoreCategory(UpdStoreCategoryDTO dto) throws StoreCategoryNotFoundException {
		StoreCategory record = dao_store_category.selectByPrimaryKey(dto.getId_store_category());
		if(record == null)
			throw new StoreCategoryNotFoundException("StoreCategory con id= "+dto.getId_store_category()+" no existe.");

		record.setActive(dto.getActive());
		record.setCode(dto.getCode());
		record.setCreated(dto.getCreated());
		record.setId_supplier(dto.getId_supplier());
		record.setModified(dto.getModified());
		record.setName(dto.getName());
		record.setOrderby(dto.getOrderby());
		record.setLogin(dto.getLogin());
		
		int row = dao_store_category.updateByPrimaryKeySelective(record);
		
		return row;
	}

	@Override
	public StoreCategory getStoreCategoryById(Long id_store_category) throws StoreCategoryNotFoundException {
		StoreCategory sc = dao_store_category.selectByPrimaryKey(id_store_category);
		if(sc == null)
			throw new StoreCategoryNotFoundException("StoreCategory con id = "+id_store_category+" no existe.");
		
		return sc;
	}

	@Override
	public List<StoreCategory> getAllStoreCategory() {
		StoreCategoryExample example = new StoreCategoryExample();
		List<StoreCategory> list = dao_store_category.selectByExample(example);
		return list;
	}
	@Override
	public List<StoreCategory> getAllStoreCategoryByIdSupplier(Long id_supplier) {
		StoreCategoryExample example = new StoreCategoryExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);
		example.setOrderByClause("name ASC");
		
		List<StoreCategory> list = dao_store_category.selectByExample(example);
		return list;
	}
	@Override
	public List<Store> getAllStoreByIdStoreCategory(Long id_store_category) {
		StoreExample example = new StoreExample();
		example.createCriteria().
		andId_store_categoryEqualTo(id_store_category);
		
		List<Store> list = dao_store.selectByExample(example);
		return list;
	}
	@Override
	public List<Store> getAvailableStoreByIdStoreCategory(AvailableStoreByIdStoreCategory dto) {
		List<Store> list =dao_finder_store.getAvailableStoreByIdStoreCategory(dto);
		return list;
	}
	@Override
	public List<Store> getAvailableStoreByIdStoreCategoryAndIdRoute(AvailableStoreByIdStoreCategoryIdRoute dto) {
		return dao_finder_store.getAvailableStoreByIdStoreCategoryAndIdRoute(dto);
	}
	@Override
	public List<StoreCategory> getAllStoreCategory(String estado) {
		StoreCategoryExample example = new StoreCategoryExample();
		example.createCriteria().andActiveEqualTo(estado);
		
		List<StoreCategory> list_category = dao_store_category.selectByExample(example);
		return list_category;
	}
	@Override
	public List<StoreCategory> getAllStoreCategoryBySupplier(StoreCategorySearchCriteria dto) {
		StoreCategoryExample example = new StoreCategoryExample();
		example.createCriteria().andActiveEqualTo(dto.getActive()).andId_supplierEqualTo(dto.getId_supplier());
		List<StoreCategory> list = dao_store_category.selectByExample(example);
		return list;
	}
	/*
	 * Finder Store Category
	 */
	@Override
	public List<StoreCategory> getStoresCategoryActiveByCriteria(StoreCategoryActiveSearchCriteria dto) {
		
		return dao_finder_store_category.getStoresCategoryActiveByCriteria(dto);
	}
	@Override
	public int delStoreCategoryByIdSupplier(Long id_supplier) {
		return dao_finder_store_category.delStoreCategoryByIdSupplier(id_supplier);
	}
	/*
	 * Finder Retail Category
	 */
	@Override
	public List<RetailCategory> getRetailsCategoryActiveByCriteria(RetailCategoryActiveSearchCriteria dto) {
		return dao_finder_retail_category.getRetailsCategoryActiveByCriteria(dto);
	}
	@Override
	public int delRetailCategoryByIdSupplier(Long id_supplier) {
		return dao_finder_retail_category.delRetailCategoryByIdSupplier(id_supplier);
	}
	
	@Override
	public int delStoreCategoryById(Long id_store_category){
		return dao_store_category.deleteByPrimaryKey(id_store_category);
	}

	@Override
	public Integer getTotalStoreByIdSupplier(Long id_supplier) {
		return dao_finder_store.getTotalStoreByIdSupplier(id_supplier);
	}
	@Override
	public List<Store> getStoreByIdSupplier(Long id_supplier) {
		return dao_finder_store.getStoreByIdSupplier(id_supplier);
	}
	@Override
	public List<Store> getAvailableStoreByIdSupplier(Long id_supplier) {
		return dao_finder_store.getAvailableStoreByIdSupplier(id_supplier);
	}
	@Override
	public List<Store> getAvailableStoreByIdSupplierAndIdRoute(AvailableStoreByIdSupplierIdRoute dto) {
		return dao_finder_store.getAvailableStoreByIdSupplierAndIdRoute(dto);
	}
	@Override		   
	public List<Long> getIdStoresActiveByIdsRetail(StoreActiveByIdsRetailSearchCriteria dto) {
		return dao_finder_store.getIdStoresActiveByIdsRetail(dto);
	}
	@Override
	public List<Retail> getRetailByIds(RetailByIdsSearchCriteria dto) {
		return dao_finder_retail.getRetailByIds(dto);
	}
	@Override
	public List<StoreByRouteDTO> getStoreNotInTravel(StoreNotInTravelSearchCriteria dto) {
		return dao_finder_store.getStoreNotInTravel(dto);
	}
	@Override
	public List<StoreInfoDTO> getStoreInfoByCriteria(StoreInfoSearchCriteria dto) {
		return dao_finder_store.getStoreInfoByCriteria(dto);
	}
	@Override
	public List<StoreCallCenterDTO> getStoreByNameAddress(StoreByNameAddress dto) {
		return dao_finder_store.getStoreByNameAddress(dto);
	}
	@Override
	public int delRetailDeptByIdRetail(Long id_retail) {
		return dao_finder_retail_dept.delRetailDepByIdRetail(id_retail);
	}
	
	@Override
	public List<StoreByRetailDTO> getStoreByRetail(Long id_supplier) {
		return dao_finder_store.getStoreByRetail(id_supplier);
	}
	
	@Override
	public List<StoreByCategoryDTO> getStoreByCategory(Long id_supplier) {
		return dao_finder_store.getStoreByCategory(id_supplier);
	}
	@Override
	public Integer getCliQtyByCriteria(RetailSearchCriteria dto) {
		return dao_finder_retail.getCliQtyByCriteria(dto);
	}
	@Override
	public List<StoreByRouteDTO> getStoreAvailableInTravel(StoreAvailableInTravelCriteria criteria) {
		return dao_finder_store.getStoreAvailableInTravel(criteria);
	}
	@Override
	public List<StoreRouteDTO> getStoresByIdsRoute(StoreByIdsSearchCriteria dto) {
		return dao_finder_store.getStoresByIdsRoute(dto);
	}
	@Override
	public int changeStoresRetail(ChangeRetailCriteria criteria) {
		return dao_finder_retail.changeStoresRetail(criteria);
	}
	@Override
	public Long getTotalStore(Long id_retail) {
		return dao_finder_store.getTotalStore(id_retail);
	}
	
	/**
	 * Actives
	 */
	@Override
	public int addActive(Actives actives) {
		
		ActivesExample example = new ActivesExample();
		example.createCriteria()
			.andCodeEqualTo(actives.getCode())
			.andId_supplierEqualTo(actives.getId_supplier());
			
		List<Actives> listActives = dao_actives.selectByExample(example);
		if (listActives == null || listActives.size() == 0) {
			actives.setCreated(new Date());
			return dao_actives.insert(actives);
		}
		
		return 0;
	}
	@Override
	public int updateActive(Actives actives) {
		
		ActivesExample example = new ActivesExample();
		example.createCriteria()
			.andCodeEqualTo(actives.getCode())
			.andIdActivesNotEqualTo(actives.getIdActives())
			.andId_supplierEqualTo(actives.getId_supplier());
		
		List<Actives> listActives = dao_actives.selectByExample(example);
		if (listActives == null || listActives.size() == 0) {
			actives.setModified(new Date());
			return dao_actives.updateByPrimaryKeySelective(actives);
		}
		
		return -1;
	}
	@Override
	public int deleteActive(Long id_active) {
		
		deleteActivesVisitedByIdActive(id_active);
		
		return dao_actives.deleteByPrimaryKey(id_active.intValue());
	}
	@Override
	public List<Actives> getActives(Long id_supplier, Long id_store) {
		ActivesExample example = new ActivesExample();
		example.createCriteria()
			.andId_supplierEqualTo(id_supplier)
			.andId_storeEqualTo(id_store);
		return dao_actives.selectByExample(example);
	}
	@Override
	public Actives getActiveById(Long id_active) {
		return dao_actives.selectByPrimaryKey(id_active.intValue());
	}
	
	@Override
	public int transferActive(Long id_active, Long id_store) {
		Actives actives = new Actives();
		actives.setId_store(id_store);
		actives.setIdActives(id_active.intValue());
		return dao_actives.updateByPrimaryKeySelective(actives);
	}
	/**
	 * Actives Visited
	 */
	@Override
	public int addActiveVisited(ActivesVisited activesVisited) {
		activesVisited.setCreated(new Date());
		return dao_actives_visited.insert(activesVisited);
	}
	@Override
	public int updateActive(ActivesVisited activesVisited) {
		return dao_actives_visited.updateByPrimaryKeySelective(activesVisited);
	}
	@Override
	public int deleteActiveVisited(Long id_active_visited) {
		return dao_actives_visited.deleteByPrimaryKey(id_active_visited.intValue());
	}
	@Override
	public List<ActivesVisited> getActivesVisited(Long id_active) {
		ActivesVisitedExample example = new ActivesVisitedExample();
		example.createCriteria()
			.andId_activesEqualTo(id_active);
		return dao_actives_visited.selectByExample(example);
	}
	@Override
	public ActivesVisited getActiveVisitedById(Long id_active_visited) {
		return dao_actives_visited.selectByPrimaryKey(id_active_visited.intValue());
	}
	
	/**
	 * Finder Actives/Visited
	 */
	@Override
	public int deleteActivesVisitedByIdActive(Long id_active) {
		return dao_finder_actives_visited.deleteActivesVisitedByIdActive(id_active);
	}
	@Override
	public List<ActivesVisitedExt> getVisitedList(ActivesVisitedCriteria criteria) {
		return dao_finder_actives_visited.getVisitedList(criteria);
	}
	@Override
	public List<ActivesAndVisitsDTO> getActivesAndVisits(ActivesAndVisitsCriteria criteria) {
		return dao_finder_actives_visited.getActivesAndVisits(criteria);
	}
	@Override
	public String verifyAndRegistCheckIn(VerifyAndRegistCheckInCriteria criteria) {
		return dao_finder_actives_visited.verifyAndRegistCheckIn(criteria);
	}

}
