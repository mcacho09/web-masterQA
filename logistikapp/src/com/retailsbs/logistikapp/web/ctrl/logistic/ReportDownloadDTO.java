package com.retailsbs.logistikapp.web.ctrl.logistic;

import java.util.Date;
import java.util.List;

import com.retailsbs.logistikapp.logistic.domain.Travel;
import com.retailsbs.logistikapp.logistic.dto.WayBillDTO;
import com.retailsbs.logistikapp.logistic.exception.TravelNotFoundException;
import com.retailsbs.logistikapp.user.domain.User;
import com.retailsbs.logistikapp.user.exception.UserNotFoundException;

public class ReportDownloadDTO {

	Travel travel;
	List<WayBillDTO> waybills;
	User chofer;
	Date iniciado;
	Date finalizado;
	String porcentaje;
	int visitados;
	
	public ReportDownloadDTO(Travel viaje,List<WayBillDTO> waybill,User user) throws TravelNotFoundException, UserNotFoundException{
		
		setTravel(viaje);
		setWaybills(waybill);
		setChofer(user);
		
		
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public List<WayBillDTO> getWaybills() {
		return waybills;
	}

	public void setWaybills(List<WayBillDTO> waybills) {
		this.waybills = waybills;
	}

	public User getChofer() {
		return chofer;
	}

	public void setChofer(User chofer) {
		this.chofer = chofer;
	}

	public Date getIniciado() {
		return iniciado;
	}

	public void setIniciado(Date iniciado) {
		this.iniciado = iniciado;
	}

	public Date getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Date finalizado) {
		this.finalizado = finalizado;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getVisitados() {
		return visitados;
	}

	public void setVisitados(int visitados) {
		this.visitados = visitados;
	}

	
	
	
	
	
	
}
