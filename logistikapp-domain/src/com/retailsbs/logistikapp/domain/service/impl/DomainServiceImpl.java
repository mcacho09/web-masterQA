package com.retailsbs.logistikapp.domain.service.impl;

import java.util.List;

import com.retailsbs.logistikapp.domain.dao.DomainContentDAO;
import com.retailsbs.logistikapp.domain.dao.DomainDAO;
import com.retailsbs.logistikapp.domain.dao.FinderDomainContentDAO;
import com.retailsbs.logistikapp.domain.domain.Domain;
import com.retailsbs.logistikapp.domain.domain.DomainContent;
import com.retailsbs.logistikapp.domain.dto.AddDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.AddDomainDTO;
import com.retailsbs.logistikapp.domain.dto.AvaibleCodeSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.DomainAvaibleSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.DomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.DomainContentExample;
import com.retailsbs.logistikapp.domain.dto.DomainContentSearchCriteria;
import com.retailsbs.logistikapp.domain.dto.DomainExample;
import com.retailsbs.logistikapp.domain.dto.UpdDomainContentDTO;
import com.retailsbs.logistikapp.domain.dto.UpdDomainDTO;
import com.retailsbs.logistikapp.domain.exception.DomainContentCodeDuplicateException;
import com.retailsbs.logistikapp.domain.exception.DomainContentNotFoundException;
import com.retailsbs.logistikapp.domain.exception.DomainDuplicadoException;
import com.retailsbs.logistikapp.domain.exception.DomainNotFoundException;
import com.retailsbs.logistikapp.domain.service.DomainService;
/**
 * Implementacion de servicio domain
 * @author Juan Carlos Ramos Campos
 * @since 24-11-2014
 */
public class DomainServiceImpl implements DomainService {

	private DomainDAO dao_domain;
	private DomainContentDAO dao_domain_content;
	private FinderDomainContentDAO finder_domain_content;
	
	public void setFinder_domain_content(FinderDomainContentDAO finder_domain_content) {
		this.finder_domain_content = finder_domain_content;
	}

	public void setDao_domain(DomainDAO dao_domain) {
		this.dao_domain = dao_domain;
	}

	public void setDao_domain_content(DomainContentDAO dao_domain_content) {
		this.dao_domain_content = dao_domain_content;
	}

	/**
	 * Domain
	 */
	@Override
	public Long addDomain(AddDomainDTO dto) {
		Domain domain = new Domain();
		domain.setActive(dto.getActive());
		domain.setCode(dto.getCode());
		domain.setCreated(dto.getCreated());
		domain.setLogin(dto.getLogin());
		domain.setModified(dto.getModified());
		domain.setName(dto.getName());
		domain.setOrderby(dto.getOrderby());
		
		Long id_domain = dao_domain.insert(domain);
		return id_domain;
	}

	@Override
	public int updDomain(UpdDomainDTO dto) throws DomainNotFoundException{
		//Se obtienen el objueto que se quiere actualizar
		Domain domain = dao_domain.selectByPrimaryKey(dto.getId_domain());
		//Si no existe el objeto se lanza exception
		if(domain == null)
			throw new DomainNotFoundException("El domain con id = "+dto.getId_domain()+" no existe.");
		//se setean nuevos datos.
		domain.setActive(dto.getActive());
		domain.setCode(dto.getCode());
		domain.setLogin(dto.getLogin());
		domain.setModified(dto.getModified());
		domain.setName(dto.getName());
		domain.setOrderby(dto.getOrderby());
		//se persiste el objeto
		int row = dao_domain.updateByPrimaryKeySelective(domain);
		return row;
	}

	@Override
	public Domain getDomainById(Long id_domain) throws DomainNotFoundException {
		Domain domain = dao_domain.selectByPrimaryKey(id_domain);
		//si no se encuantra el objeto se lanza exception
		if(domain == null)
			throw new DomainNotFoundException("El dominio con id = "+id_domain+" no existe.");
		
		return domain;
	}

	@Override
	public List<Domain> getAllDomain() {
		DomainExample example = new DomainExample();
		example.setOrderByClause("orderby");
		List<Domain> list = dao_domain.selectByExample(example);
		return list;
	}

	@Override
	public void getDomainAvaibleByCriteria(DomainAvaibleSearchCriteria dto) throws DomainDuplicadoException{
		DomainExample example = new DomainExample();
		if(dto.getId_domain() == null){
			example.createCriteria()
			.andCodeEqualTo(dto.getCode())
			.andActiveEqualTo(dto.getActive());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_domainNotEqualTo(dto.getId_domain());
		}
		List<Domain> domain = dao_domain.selectByExample(example);
		if(domain.size() > 0)
			throw new DomainDuplicadoException("El nombre o codigo de dominio ya pertenece a otro dominio.");
	}

	/*
	 * DomainContent
	 */

	@Override
	public Long addDomainContent(AddDomainContentDTO dto) {
		DomainContent dom_cont = new DomainContent();
		dom_cont.setActive(dto.getActive());
		dom_cont.setCode(dto.getCode());
		dom_cont.setCreated(dto.getCreated());
		dom_cont.setId_domain(dto.getId_domain());
		dom_cont.setLogin(dto.getLogin());
		dom_cont.setModified(dto.getModified());
		dom_cont.setName(dto.getName());
		dom_cont.setOrderby(dto.getOrderby());
		dom_cont.setParam(dto.getParam());
		dom_cont.setValue(dto.getValue());
		
		Long id_domain_content = dao_domain_content.insert(dom_cont);
		return id_domain_content;
	}

	@Override
	public int updDomainContent(UpdDomainContentDTO dto) throws DomainContentNotFoundException{
		//Se obtiene el objeto que se quiere actualizar
		DomainContent dom_cont = dao_domain_content.selectByPrimaryKey(dto.getId_domain_content());
		//Si el objeto no existe se lanza una exception
		if(dom_cont == null)
			throw new DomainContentNotFoundException("El DomainContent con id = "+dto.getId_domain_content()+" no existe.");
		//Se setean los nuevos datos.
		dom_cont.setActive(dto.getActive());
		dom_cont.setCode(dto.getCode());
		dom_cont.setId_domain(dto.getId_domain());
		dom_cont.setLogin(dto.getLogin());
		dom_cont.setModified(dto.getModified());
		dom_cont.setName(dto.getName());
		dom_cont.setOrderby(dto.getOrderby());
		dom_cont.setParam(dto.getParam());
		dom_cont.setValue(dto.getValue());
		//se persiste el objeto
		int row = dao_domain_content.updateByPrimaryKeySelective(dom_cont);
		return row;
	}

	@Override
	public DomainContent getDomainContentById(Long id_domain_content) throws DomainContentNotFoundException {
		DomainContent dom_cont = dao_domain_content.selectByPrimaryKey(id_domain_content);
		//se verifica que el objeto que se quiere obtener exista, sino, se lanza exception
		if(dom_cont == null)
			throw new DomainContentNotFoundException("El DomainContent con id = "+id_domain_content+" no existe.");
		return dom_cont;
	}

	@Override
	public List<DomainContent> getAllDomainContent() {
		return dao_domain_content.selectByExample(new DomainContentExample());
	}

	@Override
	public void getAvaibleCodeByCriteria(AvaibleCodeSearchCriteria dto) throws DomainContentCodeDuplicateException {
		DomainContentExample example = new DomainContentExample();
		if(dto.getId_domain_content() == null){
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andId_domainEqualTo(dto.getId_domain())
			.andCodeEqualTo(dto.getCode());
		}else{
			example.createCriteria()
			.andActiveEqualTo(dto.getActive())
			.andCodeEqualTo(dto.getCode())
			.andId_domainEqualTo(dto.getId_domain())
			.andId_domain_contentNotEqualTo(dto.getId_domain_content());
		}
		List<DomainContent> list = dao_domain_content.selectByExample(example);
		if(list.size() > 0)
			throw new DomainContentCodeDuplicateException("El codigo que se quiere usar ya esta definido.");
	}

	/**
	 * FINDER DOMAIN CONTENT
	 */
	
	@Override
	public List<DomainContentDTO> getDomainContentByCriteria(DomainContentSearchCriteria dto) {
		return finder_domain_content.getDomainContentByCriteria(dto);
	}

	@Override
	public int getDomainContentTotalByCriteria(DomainContentSearchCriteria dto) {
		return finder_domain_content.getDomainContentTotalByCriteria(dto);
	}

}
