package com.retailsbs.logistikapp.commercial.service.impl;

import java.util.List;

import com.retailsbs.logistikapp.commercial.dao.RetailcatGoalDAO;
import com.retailsbs.logistikapp.commercial.domain.RetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.AddRetailcatGoal;
import com.retailsbs.logistikapp.commercial.dto.RetailcatGoalExample;
import com.retailsbs.logistikapp.commercial.dto.UpdRetailcatGoal;
import com.retailsbs.logistikapp.commercial.exception.RetailcatGoalNotFoundException;
import com.retailsbs.logistikapp.commercial.service.CommercialService;

public class CommercialServiceImpl implements CommercialService {
	
	private RetailcatGoalDAO dao_retailcat_goal;
	
	public void setDao_retailcat_goal(RetailcatGoalDAO dao_retailcat_goal) {
		this.dao_retailcat_goal = dao_retailcat_goal;
	}

	public Long addRetailcatGoal(AddRetailcatGoal dto) {
		RetailcatGoal record = new RetailcatGoal();
		record.setAmount(dto.getAmount());
		record.setCreated(dto.getCreated());
		record.setId_retail_category(dto.getId_retail_category());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setMonth(dto.getMonth());
		record.setYear(dto.getYear());
		
		Long id = dao_retailcat_goal.insert(record);
		return id;
	}

	public int updRetailcatGoal(UpdRetailcatGoal dto) throws RetailcatGoalNotFoundException {
		RetailcatGoal record = dao_retailcat_goal.selectByPrimaryKey(dto.getId_retailcat_goal());
		//se verifica si existe el retaicatGoal
		if(record == null) throw new RetailcatGoalNotFoundException("RetailcatGoal con id = "+dto.getId_retailcat_goal()+" no existe.");
		//seteo de datos
		record.setAmount(dto.getAmount());
		record.setCreated(dto.getCreated());
		record.setId_retail_category(dto.getId_retail_category());
		record.setLogin(dto.getLogin());
		record.setModified(dto.getModified());
		record.setMonth(dto.getMonth());
		record.setYear(dto.getYear());
		//se persiste el objeto
		int row = dao_retailcat_goal.updateByPrimaryKeySelective(record);
		return row;
	}

	public RetailcatGoal getRetailcatGoalById(Long id_retailcat_goal) throws RetailcatGoalNotFoundException {
		RetailcatGoal record = dao_retailcat_goal.selectByPrimaryKey(id_retailcat_goal);
		if(record == null) throw new RetailcatGoalNotFoundException("RetailcatGoal con id = "+id_retailcat_goal+" no existe.");
		return record;
	}

	public List<RetailcatGoal> getAllRetailcatGoal() {
		RetailcatGoalExample example = new RetailcatGoalExample();
		return dao_retailcat_goal.selectByExample(example);
	}

}
