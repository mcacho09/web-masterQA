package com.retailsbs.logistikapp.logistic.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.retailsbs.logistikapp.logistic.dao.FinderRouteDAO;
import com.retailsbs.logistikapp.logistic.dao.FinderRouteStoreDAO;
import com.retailsbs.logistikapp.logistic.dao.FinderTravelDAO;
import com.retailsbs.logistikapp.logistic.dao.FinderWayBillDAO;
import com.retailsbs.logistikapp.logistic.dao.RouteDAO;
import com.retailsbs.logistikapp.logistic.dao.RouteStoreDAO;
import com.retailsbs.logistikapp.logistic.dao.TravelDAO;
import com.retailsbs.logistikapp.logistic.dao.UserPositionDAO;
import com.retailsbs.logistikapp.logistic.dao.WayBillDAO;
import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.domain.RouteStore;
import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.domain.UserPosition;
import com.retailsbs.logistikapp.logistic.domain.WayBill;
import com.retailsbs.logistikapp.logistic.dto.AvailableUserScheduleSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.AviableCodeRouteSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.CountRouteSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.CustomersNotVisitedDTO;
import com.retailsbs.logistikapp.logistic.dto.InfoReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterCriteria;
import com.retailsbs.logistikapp.logistic.dto.MetricsCounterDTO;
import com.retailsbs.logistikapp.logistic.dto.MultipleUsersDTO;
import com.retailsbs.logistikapp.logistic.dto.ParameterReportPromotion;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesCriteria;
import com.retailsbs.logistikapp.logistic.dto.ProgressMetricsOperativesDTO;
import com.retailsbs.logistikapp.logistic.dto.ReasignWaybillFromOldTravelDTO;
import com.retailsbs.logistikapp.logistic.dto.RouteExample;
import com.retailsbs.logistikapp.logistic.dto.RouteStoreExample;
import com.retailsbs.logistikapp.logistic.dto.RoutesByListRetails;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierCriteria;
import com.retailsbs.logistikapp.logistic.dto.StoresNotVisitedByDateAndSupplierDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TravelDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelExample;
import com.retailsbs.logistikapp.logistic.dto.TravelSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.TravelsToVisitedCriteria;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;
import com.retailsbs.logistikapp.logistic.dto.UserPositionExample;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.dto.WayBillExample;
import com.retailsbs.logistikapp.logistic.dto.WayBillSearchCriteria;
import com.retailsbs.logistikapp.logistic.exception.CodeRouteDuplicateException;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.RouteStoreNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.TravelNotFoundException;
import com.retailsbs.logistikapp.logistic.exception.UserScheduleNotAvailableException;
import com.retailsbs.logistikapp.logistic.exception.WayBillNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;

public class LogisticServiceImpl implements LogisticService {

	protected final Log logger = LogFactory.getLog(getClass());

	private RouteDAO dao_route;
	private RouteStoreDAO dao_route_store;
	private TravelDAO dao_travel;
	private WayBillDAO dao_waybill;
	private FinderTravelDAO finder_travel;
	private FinderWayBillDAO finder_waybill;
	private FinderRouteStoreDAO finder_route_store;
	private FinderRouteDAO finder_route;
	private UserPositionDAO dao_user_position;

	public void setFinder_route(FinderRouteDAO finder_route) {
		this.finder_route = finder_route;
	}

	public void setFinder_route_store(FinderRouteStoreDAO finder_route_store) {
		this.finder_route_store = finder_route_store;
	}

	public RouteDAO getDao_route() {
		return dao_route;
	}

	public FinderTravelDAO getFinder_travel() {
		return finder_travel;
	}

	public void setFinder_travel(FinderTravelDAO finder_travel) {
		this.finder_travel = finder_travel;
	}

	public void setDao_route(RouteDAO dao_route) {
		this.dao_route = dao_route;
	}

	public RouteStoreDAO getDao_route_store() {
		return dao_route_store;
	}

	public void setDao_route_store(RouteStoreDAO dao_route_store) {
		this.dao_route_store = dao_route_store;
	}

	public TravelDAO getDao_travel() {
		return dao_travel;
	}

	public void setDao_travel(TravelDAO dao_travel) {
		this.dao_travel = dao_travel;
	}

	public WayBillDAO getDao_waybill() {
		return dao_waybill;
	}

	public void setDao_waybill(WayBillDAO dao_waybill) {
		this.dao_waybill = dao_waybill;
	}

	public void setFinder_waybill(FinderWayBillDAO finder_waybill) {
		this.finder_waybill = finder_waybill;
	}

	public void setDao_user_position(UserPositionDAO dao_user_position) {
		this.dao_user_position = dao_user_position;
	}

	@Override
	public Long addRoute(Route route) {
		return dao_route.insert(route);
	}

	/*
	 * Route
	 */

	@Override
	public int updRouteById(Route record) throws RouteNotFoundException {
		Route row = dao_route.selectByPrimaryKey(record.getId_route());
		if (row == null)
			throw new RouteNotFoundException("Ruta " + record.getId_route()
					+ " no existe.");
		return dao_route.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delRouteById(Long id_route) throws RouteNotFoundException {
		Route row = dao_route.selectByPrimaryKey(id_route);
		if (row == null)
			throw new RouteNotFoundException("Ruta " + id_route + " no existe.");
		return dao_route.deleteByPrimaryKey(id_route);
	}

	@Override
	public Route getRouteById(Long id_route) throws RouteNotFoundException {
		Route row = dao_route.selectByPrimaryKey(id_route);
		if (row == null)
			throw new RouteNotFoundException("Ruta " + id_route + " no existe.");
		return row;
	}

	@Override
	public List<Route> getAllRoute() {
		RouteExample example = new RouteExample();
		List<Route> list = dao_route.selectByExample(example);
		return list;
	}

	@Override
	public List<Route> getAllRouteByIdSupplier(Long id_supplier) {
		RouteExample example = new RouteExample();
		example.createCriteria().andId_supplierEqualTo(id_supplier);
		example.setOrderByClause("name ASC");
		List<Route> list = dao_route.selectByExample(example);
		return list;
	}

	/*
	 * Finder/Route
	 */

	@Override
	public int delRouteByIdSupplier(Long id_supplier) {
		return finder_route.delRouteByIdSupplier(id_supplier);
	}

	@Override
	public List<Travel> getTravelByIdRoute(Long id_route) {
		TravelExample example = new TravelExample();
		example.createCriteria().andId_routeEqualTo(id_route);
		List<Travel> list = dao_travel.selectByExample(example);
		return list;
	}

	@Override
	public void getAvaibleCodeRouteByCriteria(AviableCodeRouteSearchCriteria dto)
			throws CodeRouteDuplicateException {
		RouteExample example = new RouteExample();
		if (dto.getId_route() == null) {
			example.createCriteria().andCodeEqualTo(dto.getCode());
		} else {
			example.createCriteria().andCodeEqualTo(dto.getCode())
					.andId_routeNotEqualTo(dto.getId_route());
		}

		List<Route> list = dao_route.selectByExample(example);

		if (list.size() > 0)
			throw new CodeRouteDuplicateException(
					"Codigo utilizado por otra Ruta");
	}

	/*
	 * RouteStore
	 */

	@Override
	public Long addRouteStore(RouteStore route_store) {
		return dao_route_store.insert(route_store);
	}

	@Override
	public int updRouteStoreById(RouteStore record)
			throws RouteStoreNotFoundException {
		RouteStore row = dao_route_store.selectByPrimaryKey(record
				.getId_route_store());
		if (row == null)
			throw new RouteStoreNotFoundException("Routestore "
					+ record.getId_route_store() + " no existe.");
		return dao_route_store.updateByPrimaryKeySelective(record);

	}

	@Override
	public int delRouteStoreById(Long id_route_store)
			throws RouteStoreNotFoundException {
		RouteStore row = dao_route_store.selectByPrimaryKey(id_route_store);
		if (row == null)
			throw new RouteStoreNotFoundException("Routestore "
					+ id_route_store + " no existe.");
		return dao_route_store.deleteByPrimaryKey(id_route_store);
	}

	@Override
	public RouteStore getRouteStoreById(Long id_route_store)
			throws RouteStoreNotFoundException {
		RouteStore row = dao_route_store.selectByPrimaryKey(id_route_store);
		if (row == null)
			throw new RouteStoreNotFoundException("Routestore "
					+ id_route_store + " no existe.");
		return row;
	}

	@Override
	public List<RouteStore> getAllRouteStore() {
		RouteStoreExample example = new RouteStoreExample();
		List<RouteStore> list = dao_route_store.selectByExample(example);
		return list;
	}

	public int updateRouteStoreByList(List<Long> oldList, List<Long> newList,
			Long id_route, String log_created_login) {
		List<Long> add = new ArrayList<Long>();
		List<Long> del = new ArrayList<Long>();

		boolean found;
		for (int i = 0; i < oldList.size(); i++) {
			found = false;
			for (int j = 0; j < newList.size(); j++) {
				if (newList.get(j) == oldList.get(i)) {
					found = true;
					break;
				}
			}

			if (!found) {
				del.add(oldList.get(i));
			}
		}
		for (int i = 0; i < newList.size(); i++) {
			found = false;
			for (int j = 0; j < oldList.size(); j++) {
				if (oldList.get(j) == newList.get(i)) {
					found = true;
					break;
				}
			}

			if (!found) {
				add.add(newList.get(i));
			}
		}
		int rowsDel = 0;
		if (del.size() != 0) {
			RouteStoreExample example = new RouteStoreExample();
			example.createCriteria().andId_routeEqualTo(id_route)
					.andId_storeIn(del);
			rowsDel = dao_route_store.deleteByExample(example);
		}

		int rowsadd = 0;

		if (add.size() != 0) {
			for (int i = 0; i < add.size(); i++) {
				RouteStore record = new RouteStore();
				record.setId_route(id_route);
				record.setId_store(add.get(i));
				record.setLog_created(new Date());
				record.setLog_created_login(log_created_login);

				dao_route_store.insert(record);
				rowsadd++;
			}
		}

		return rowsadd + rowsDel;
	}

	/*
	 * WayBill
	 */

	@Override
	public Long addWayBill(WayBill wayBill) {
		return dao_waybill.insert(wayBill);

	}

	@Override
	public int updWayBillById(WayBill record) throws WayBillNotFoundException {
		WayBill row = dao_waybill.selectByPrimaryKey(record.getId_waybill());
		if (row == null)
			throw new WayBillNotFoundException("WayBill "
					+ record.getId_waybill() + " no existe.");
		return dao_waybill.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delWayBillById(Long id_wayBill) throws WayBillNotFoundException {
		WayBill row = dao_waybill.selectByPrimaryKey(id_wayBill);
		if (row == null)
			throw new WayBillNotFoundException("WayBill " + id_wayBill
					+ " no existe.");
		return dao_waybill.deleteByPrimaryKey(id_wayBill);
	}

	@Override
	public WayBill getWayBillById(Long id_wayBill)
			throws WayBillNotFoundException {
		WayBill row = dao_waybill.selectByPrimaryKey(id_wayBill);
		if (row == null)
			throw new WayBillNotFoundException("WayBill " + id_wayBill
					+ " no existe.");
		return row;
	}

	@Override
	public List<WayBill> getAllWayBill() {
		WayBillExample example = new WayBillExample();
		List<WayBill> list = dao_waybill.selectByExample(example);
		return list;
	}

	@Override
	public List<WayBill> getAllWayBillByIdTravel(Long id_travel) {
		WayBillExample example = new WayBillExample();
		example.createCriteria().andId_travelEqualTo(id_travel);
		example.setOrderByClause("orderby");
		List<WayBill> list = dao_waybill.selectByExample(example);
		return list;
	}

	/*
	 * Finder/Waybill
	 */
	@Override
	public List<WayBillDTO> getWayBillByIdTravel(Long id_travel) {
		return finder_waybill.getWayBillByIdTravel(id_travel);
	}

	/*
	 * Travel
	 */

	@Override
	public Long addTravel(Travel travel) {
		return dao_travel.insert(travel);
	}

	@Override
	public int updTravelById(Travel record) throws TravelNotFoundException {
		Travel row = dao_travel.selectByPrimaryKey(record.getId_travel());
		if (row == null)
			throw new TravelNotFoundException("Travel " + record.getId_travel()
					+ " no existe.");
		return dao_travel.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delTravelById(Long id_travel) throws TravelNotFoundException {
		Travel row = dao_travel.selectByPrimaryKey(id_travel);
		if (row == null)
			throw new TravelNotFoundException("Travel " + id_travel
					+ " no existe.");
		return dao_travel.deleteByPrimaryKey(id_travel);
	}

	@Override
	public Travel getTravelById(Long id_travel) throws TravelNotFoundException {
		Travel row = dao_travel.selectByPrimaryKey(id_travel);
		if (row == null)
			throw new TravelNotFoundException("Travel " + id_travel
					+ " no existe.");
		return row;
	}

	@Override
	public List<Travel> getAllTravel() {
		TravelExample example = new TravelExample();
		List<Travel> list = dao_travel.selectByExample(example);
		return list;
	}

	@Override
	public List<Travel> getTravelByIdUser(Long id_user) {
		TravelExample example = new TravelExample();
		example.createCriteria().andId_userEqualTo(id_user);
		return dao_travel.selectByExample(example);
	}

	/*
	 * Finder/Travel
	 */

	@Override
	public List<TravelDTO> getTravelByCriteria(TravelSearchCriteria dto) {
		List<TravelDTO> list = finder_travel.getTravelByCriteria(dto);
		return list;
	}

	@Override
	public List<TravelByIdStoreDTO> getTravelByIdStore(TravelByIdStoreSearch dto) {
		return finder_travel.getTravelByIdStore(dto);
	}

	@Override
	public List<TravelAndStatusStr> getTravelAndStatusStrByIdStore(
			TrvStatusStrSearchCriteria dto) {
		return finder_travel.getTravelAndStatusStrByIdStore(dto);
	}

	@Override
	public void getAvailableUserSchedule(AvailableUserScheduleSearchCriteria dto)
			throws UserScheduleNotAvailableException {
		TravelExample example = new TravelExample();
		example.createCriteria().andId_userEqualTo(dto.getId_user())
				.andScheduleEqualTo(dto.getSchedule()).andStatusEqualTo("PRO");
		List<Travel> list = dao_travel.selectByExample(example);
		if (list != null && list.size() > 0)
			throw new UserScheduleNotAvailableException(
					"Usuario no disponible para ese d&iacute;a");
	}

	@Override
	public int delTravelByIdRoute(Long id_route) {
		return finder_travel.delTravelByIdRoute(id_route);
	}

	@Override
	public int delTravelByIdUser(Long id_user) {
		return finder_travel.delTravelByIdUser(id_user);
	}

	@Override
	public List<Travel> getTravelsToVisited(TravelsToVisitedCriteria criteria) {
		return finder_travel.getTravelsToVisited(criteria);
	}
	
	/*
	 * Finder/Route/Store
	 */

	@Override
	public int delRouteStoreByIdRoute(Long id_route) {
		return finder_route_store.delRouteStoreByIdRoute(id_route);
	}

	@Override
	public List<WayBillDTO> getWayBillByCriteria(WayBillSearchCriteria dto) {
		return finder_waybill.getWayBillByCriteria(dto);
	}

	@Override
	public int delWaybillByIdStore(Long id_store) {
		return finder_waybill.delWaybillByIdStore(id_store);
	}

	@Override
	public int delRouteStoreByIdStore(Long id_store) {
		return finder_route_store.delRouteStoreByIdStore(id_store);
	}

	@Override
	public int getCountRouteSupplierByCriteria(CountRouteSupplierCriteria dto) {
		return finder_route.getCountRouteSupplierByCriteria(dto);
	}

	@Override
	public MetricsCounterDTO getMetricsCounter(MetricsCounterCriteria dto) {
		return finder_travel.getMetricsCounter(dto);
	}

	@Override
	public List<InfoReportPromotion> getTravelWithPromotion(
			ParameterReportPromotion data) {
		// TODO Auto-generated method stub
		return finder_travel.getTravelWithPromotion(data);
	}

	@Override
	public List<Route> getRouteByListRetail(RoutesByListRetails dto) {
		// TODO Auto-generated method stub
		return finder_route.getRouteByListRetail(dto);
	}

	@Override
	public List<Long> getIdsRouteByIdTravel(Long id_travel) {
		return finder_route.getIdsRouteByIdTravel(id_travel);
	}

	@Override
	public List<Route> getRoutesByIdTravel(Long id_travel) {
		// TODO Auto-generated method stub
		return finder_route.getRoutesByIdTravel(id_travel);
	}

	@Override
	public int addWayBill(MultipleUsersDTO dto) {
		int cont=0;
		for (WayBill cliente_new : dto.getClientsToAdd()) {
			if(dao_waybill.insert(cliente_new) > 0)
				cont++;
		}	
		return cont;
	}
	
	/*
	 * UserPosition
	 */

	@Override
	public List<UserPosition> getUserPositionByIdUser(Long id_user, Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		UserPositionExample ex = new UserPositionExample();
		ex.setOrderByClause("id_user_position asc");
		try {
			ex.createCriteria().andId_userEqualTo(id_user)
				.andCreatedBetween(sdf2.parse(sdf.format(date) + " 00:00:00"), sdf2.parse(sdf.format(date) + " 23:59:59"));
			List<UserPosition> list = dao_user_position.selectByExample(ex);
			List<UserPosition> temp = new ArrayList<>();
			UserPosition last = null;
			UserPosition current = null;
			
			if (list.size() > 0) {
				temp.add(list.get(0));
			}
			
			for (int i = 1, size = list.size(); i < size; i++) {
				last = list.get(i - 1);
				current = list.get(i);
				
				boolean res = (current.getLatitude().doubleValue() == last.getLatitude().doubleValue()) && (current.getLongitude().doubleValue() == last.getLongitude().doubleValue());
				
				if (!res) {
					temp.add(list.get(i));
				}
				
			}
			
			return temp;
		} catch (ParseException e) {
			return null;
		}
		
	}
	

	@Override
	public List<UserPosition> getUserPositionByIdTravel(Long id_travel) {
		UserPositionExample ex = new UserPositionExample();
		ex.setOrderByClause("id_user_position asc");
		ex.createCriteria().andId_travelEqualTo(id_travel);
		
		List<UserPosition> list = dao_user_position.selectByExample(ex);
		List<UserPosition> temp = new ArrayList<>();
		UserPosition last = null;
		UserPosition current = null;
		
		if (list.size() > 0) {
			temp.add(list.get(0));
		}
		
		for (int i = 1, size = list.size(); i < size; i++) {
			last = list.get(i - 1);
			current = list.get(i);
			
			boolean res = (current.getLatitude().doubleValue() == last.getLatitude().doubleValue()) && (current.getLongitude().doubleValue() == last.getLongitude().doubleValue());
			
			if (!res) {
				temp.add(list.get(i));
			}
			
		}
		
		return temp;
	}

	@Override
	public Long addUserPosition(UserPosition userPosition) {
		return dao_user_position.insert(userPosition);
	}

	@Override
	public List<UserPosition> getUserPositionByIdUserAndDate(Long id_user, Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		UserPositionExample ex = new UserPositionExample();
		ex.setOrderByClause("id_user_position asc");
		try {
			ex.createCriteria().andId_userEqualTo(id_user)
				.andCreatedBetween(sdf2.parse(sdf.format(date) + " 00:00:00"), sdf2.parse(sdf.format(date) + " 23:59:59"));
			List<UserPosition> list = dao_user_position.selectByExample(ex);
			List<UserPosition> temp = new ArrayList<>();
			UserPosition last = null;
			UserPosition current = null;
			
			if (list.size() > 0) {
				temp.add(list.get(0));
			}
			
			for (int i = 1, size = list.size(); i < size; i++) {
				last = list.get(i - 1);
				current = list.get(i);
				
				boolean res = (current.getLatitude().doubleValue() == last.getLatitude().doubleValue()) && (current.getLongitude().doubleValue() == last.getLongitude().doubleValue());
				
				if (!res) {
					temp.add(list.get(i));
				}
				
			}
			
			return temp;
		} catch (ParseException e) {
			return null;
		}
		
	}

	@Override
	public List<UserPosition> getUserPositionByIdTravelAndDate(Long id_travel, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		UserPositionExample ex = new UserPositionExample();
		ex.setOrderByClause("id_user_position asc");
		try {
			ex.createCriteria().andId_travelEqualTo(id_travel)
				.andCreatedBetween(sdf2.parse(sdf.format(date) + " 00:00:00"), sdf2.parse(sdf.format(date) + " 23:59:59"));
			List<UserPosition> list = dao_user_position.selectByExample(ex);
			List<UserPosition> temp = new ArrayList<>();
			UserPosition last = null;
			UserPosition current = null;
			
			if (list.size() > 0) {
				temp.add(list.get(0));
			}
			
			for (int i = 1, size = list.size(); i < size; i++) {
				last = list.get(i - 1);
				current = list.get(i);
				
				boolean res = (current.getLatitude().doubleValue() == last.getLatitude().doubleValue()) && (current.getLongitude().doubleValue() == last.getLongitude().doubleValue());
				
				if (!res) {
					temp.add(list.get(i));
				}
				
			}
			
			return temp;
		} catch (ParseException e) {
			return null;
		}
		
	}

	@Override
	public Travel getLastTravelTRAorFIN(long id_user) {
		return finder_travel.getLastTravelTRAorFIN(id_user);
	}

	@Override
	public List<CustomersNotVisitedDTO> getCustomersNotVisited(CustomersNotVisitedCriteria criteria) {
		return finder_waybill.getCustomersNotVisited(criteria);
	}

	@Override
	public int delWayBillByIdTravel(Long id_travel) {
		Travel tra = dao_travel.selectByPrimaryKey(id_travel);
		if (tra == null) return 0;

		return finder_waybill.delWayBillByIdTravel(id_travel);

	}

	@Override
	public List<ProgressMetricsOperativesDTO> getProgressMetricsOperatives(ProgressMetricsOperativesCriteria criteria) {
		return finder_travel.getProgressMetricsOperatives(criteria);
	}

	@Override
	public List<WayBill> getWayBillVisited() {
		WayBillExample criteria = new WayBillExample();
		criteria.createCriteria().andStatusEqualTo("S");
		return this.dao_waybill.selectByExample(criteria);
	}

	@Override
	public List<Long> getSuppliersWithStoresNotVisited(Date date) {
		return finder_waybill.getSuppliersWithStoresNotVisited(date);
	}

	@Override
	public List<StoresNotVisitedByDateAndSupplierDTO> getStoresNotVisitedByDateAndSupplier(StoresNotVisitedByDateAndSupplierCriteria criteria) {
		return finder_waybill.getStoresNotVisitedByDateAndSupplier(criteria);
	}

	@Override
	public int reasignWaybillFromOldTravel(ReasignWaybillFromOldTravelDTO dto) {
		if (dto.getTravelName() == null || dto.getTravelName().isEmpty()) {
			return 0;
		}
		
		WayBillExample example = new WayBillExample();
		example.createCriteria().andId_travelEqualTo(dto.getId_travel());
		int size = dao_waybill.countByExample(example);
		WayBill record;
		for (Long i: dto.getIds()) {
			size++;
			record = new WayBill();
			record.setComment("Reasignado de: " + dto.getTravelName());
			record.setId_store(i);
			record.setId_travel(dto.getId_travel());
			record.setStatus("N");
			record.setLog_created(new Date());
			record.setOrderby(size);
			addWayBill(record);
		}
		
		return 1;
	}
	
}
