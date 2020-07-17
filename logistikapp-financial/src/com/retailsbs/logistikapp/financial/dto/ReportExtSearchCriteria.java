package com.retailsbs.logistikapp.financial.dto;

import java.util.Date;
import java.util.List;

/**
 * Clase de criterio de busqueda para reporte extendido de productos
 * @author JorgeSilva
 * @since 03/10/2016
 */
public class ReportExtSearchCriteria {

	private Date fini; /* Define el rango inicial para obtener datos */
	private Date ffin; /* Definie el rango final para obtener datos */
	private Long id_supplier; /* Se utiliza para obtener datos sólo de un proveedor */
	private Long id_user; /* Se utiliza para obtener datos sólo de un usuario */
	private List<Long> ids; //Ids de las orden
	private List<Long> idus; //Ids de los usuarios
	
	public Date getFini() {
		return fini;
	}
	public void setFini(Date fini) {
		this.fini = fini;
	}
	public Date getFfin() {
		return ffin;
	}
	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}
	public Long getId_supplier() {
		return id_supplier;
	}
	public void setId_supplier(Long id_supplier) {
		this.id_supplier = id_supplier;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public List<Long> getIdus() {
		return idus;
	}
	public void setIdus(List<Long> idus) {
		this.idus = idus;
	}

}
