package com.retailsbs.logistikapp.manager.logistic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Route;
import com.retailsbs.logistikapp.logistic.dto.TravelAndStatusStr;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreDTO;
import com.retailsbs.logistikapp.logistic.dto.TravelByIdStoreSearch;
import com.retailsbs.logistikapp.logistic.dto.TrvStatusStrSearchCriteria;
import com.retailsbs.logistikapp.logistic.exception.RouteNotFoundException;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.manager.logistic.ManagerLogisticService;
import com.retailsbs.logistikapp.manager.logistic.dto.RouteStoreDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreByNameAddressLogistic;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreInfoTravelDTO;
import com.retailsbs.logistikapp.manager.logistic.dto.StoreTravelDTO;
import com.retailsbs.logistikapp.retail.domain.Store;
import com.retailsbs.logistikapp.retail.dto.StoreByNameAddress;
import com.retailsbs.logistikapp.retail.dto.StoreCallCenterDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoDTO;
import com.retailsbs.logistikapp.retail.dto.StoreInfoSearchCriteria;
import com.retailsbs.logistikapp.retail.exception.RetailNotFoundException;
import com.retailsbs.logistikapp.retail.exception.StoreNotFoundException;
import com.retailsbs.logistikapp.retail.service.RetailService;

public class ManagerLogisticServiceImpl implements ManagerLogisticService {

	private RetailService retailService;
	private LogisticService logisticService;
	
	public void setRetailService(RetailService retailService) {
		this.retailService = retailService;
	}
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	@Override
	public List<RouteStoreDTO> getRouteStoreByIdSuplier(Long id_supplier) {
		
		List<Route> listRoute = logisticService.getAllRouteByIdSupplier(id_supplier);
		List<RouteStoreDTO> listRouteStore = new ArrayList<RouteStoreDTO>();
		for(int i = 0; i < listRoute.size(); i++)
		{
			RouteStoreDTO rs = new RouteStoreDTO();
			rs.setName(listRoute.get(i).getName());
			rs.setColor(listRoute.get(i).getColor());
			rs.setCode(listRoute.get(i).getCode());
			rs.setId_route(listRoute.get(i).getId_route());
			rs.setId_supplier(listRoute.get(i).getId_supplier());
			rs.setLog_created(listRoute.get(i).getLog_created());
			rs.setLog_created_login(listRoute.get(i).getLog_created_login());
			rs.setLog_modified(listRoute.get(i).getLog_modified());
			rs.setLog_modified_login(listRoute.get(i).getLog_modified_login());
			rs.setStatus(listRoute.get(i).getStatus());
			rs.setStore(retailService.getStoreByRoute(listRoute.get(i).getId_route()));
			
			listRouteStore.add(rs);
		}
		
		
		return listRouteStore;
	}

	@Override
	public RouteStoreDTO getRouteStoreByIdRoute(Long id_route) throws RouteNotFoundException {
		Route ruta = logisticService.getRouteById(id_route);
		
		RouteStoreDTO rs = new RouteStoreDTO();
		
		rs.setName(ruta.getName());
		rs.setColor(ruta.getColor());
		rs.setCode(ruta.getCode());
		rs.setId_route(ruta.getId_route());
		rs.setId_supplier(ruta.getId_supplier());
		rs.setLog_created(ruta.getLog_created());
		rs.setLog_created_login(ruta.getLog_created_login());
		rs.setLog_modified(ruta.getLog_modified());
		rs.setLog_modified_login(ruta.getLog_modified_login());
		rs.setStatus(ruta.getStatus());
		rs.setStore(retailService.getStoreByRoute(id_route));
		
		return rs;
	}

	@Override
	public List<StoreTravelDTO> getStoreByNameAddressAndTravels(StoreByNameAddressLogistic dto) {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

		
		StoreByNameAddress dto_sto = new StoreByNameAddress();
		dto_sto.setDireccion(dto.getDireccion());
		dto_sto.setId_supplier(dto.getId_supplier());
		dto_sto.setNombre(dto.getNombre());
		dto_sto.setSchedule(dto.getSchedule());
		
		List<StoreCallCenterDTO> list_store = retailService.getStoreByNameAddress(dto_sto);
		List<StoreTravelDTO> list_stra = new ArrayList<StoreTravelDTO>();
		
		for(int i=0; i<list_store.size(); i++){
			StoreTravelDTO pre = new  StoreTravelDTO();
			pre.setAddress1(list_store.get(i).getAddress1());
			pre.setAddress2(list_store.get(i).getAddress2());
			pre.setId_route(list_store.get(i).getId_route());
			pre.setId_store(list_store.get(i).getId_store());
			pre.setLatitude(list_store.get(i).getLatitude());
			pre.setLongitude(list_store.get(i).getLongitude());
			pre.setPostal(list_store.get(i).getPostal());
			pre.setRoute_name(list_store.get(i).getRoute_name());
			pre.setStore_name(list_store.get(i).getStore_name());
			pre.setActive(list_store.get(i).getActive());
			
			TravelByIdStoreSearch dto_tra = new TravelByIdStoreSearch();
			dto_tra.setId_store(list_store.get(i).getId_store());
			try {
				dto_tra.setSchedule(sd.parse(sd.format(dto.getSchedule())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//persiste el objeto
			List<TravelByIdStoreDTO> list_travel = logisticService.getTravelByIdStore(dto_tra);
			
			pre.setTravelStore(list_travel);
			// agrego los viajes a la tienda
			list_stra.add(pre);
		}
		
		return list_stra;
	}
	
	@Override
	public List<StoreInfoTravelDTO> getStoreInfoTravel(StoreInfoSearchCriteria dto) {
		
		List<StoreInfoDTO> list = retailService.getStoreInfoByCriteria(dto);
		List<StoreInfoTravelDTO> listStrInfo = new ArrayList<StoreInfoTravelDTO>();
		
		StoreInfoTravelDTO pre = null;
		StoreInfoDTO sidto = null;
		List<TravelAndStatusStr> list_travel = null;
		TrvStatusStrSearchCriteria tvdto = null;
		
		for ( int i = 0; i < list.size(); i++ ) {
			pre = new StoreInfoTravelDTO();
			
			sidto = list.get(i);
			
			pre.setAddress1( sidto.getAddress1() );
			pre.setAddress2( sidto.getAddress2() );
			pre.setCity_name( sidto.getCity_name() );
			pre.setId_city( sidto.getId_city() );
			pre.setId_retail( sidto.getId_retail() );
			pre.setId_store( sidto.getId_store() );
			pre.setId_store_category( sidto.getId_store_category() );
			pre.setLatitude( sidto.getLatitude() );
			pre.setLongitude( sidto.getLongitude() );
			pre.setRetail_name( sidto.getRetail_name() );
			pre.setStore_category_name( sidto.getStore_category_name() );
			pre.setStore_code( sidto.getStore_code() );
			pre.setStore_name( sidto.getStore_name() );
			pre.setShelf( sidto.getShelf() );
			pre.setEmail(sidto.getEmail());
			
//			String profile = dto.getProfile();
//			if ( !profile.contains("DRI") ) {
				// Se define dto para el criterio de busqueda
				// de la informacion de viajes
				tvdto = new TrvStatusStrSearchCriteria();
				tvdto.setId_supplier( dto.getId_supplier() );
				tvdto.setId_store( sidto.getId_store() );
				tvdto.setLim_inf(1);
				tvdto.setLim_sup(5);
				
				// Obtiene los viajes de una tienda. Si no tienes viajes se sigue con la siguiente tienda encontrada 
				list_travel = logisticService.getTravelAndStatusStrByIdStore(tvdto);
				if ( list_travel.size() > 0 ) {
					//listStrInfo.get(i).setList_travel(list_travel);
					pre.setList_travel(list_travel);
				}
//			}
			
			listStrInfo.add(pre);
		}
		
		return listStrInfo;
	}
	

	public int delRetailAndStores(String[] idsArray) throws RetailNotFoundException, StoreNotFoundException{
		// Inicializacion de variables
		Long idRetail = 0L;
		Long idStore = 0L;
		List<Store> stores = null;
		int rows = 0;
		for ( int i = 0; i < idsArray.length; i++ ) {
			idRetail = Long.parseLong( idsArray[i] );
			stores = retailService.getStoreByIdRetail(idRetail);
			for(Store store: stores){
				idStore = store.getId_store();
				//elimina waybills asociados
				logisticService.delWaybillByIdStore(idStore);
				//elimina rutas asociadas
				logisticService.delRouteStoreByIdStore(idStore);
				retailService.delStoreById(idStore);	
			}
			//elimina el retail
			rows += retailService.delRetailById(idRetail);
		}
		return rows;
	}
}
