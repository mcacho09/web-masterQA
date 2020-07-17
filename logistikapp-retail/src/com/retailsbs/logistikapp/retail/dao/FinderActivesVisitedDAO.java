package com.retailsbs.logistikapp.retail.dao;

import java.util.List;

import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesAndVisitsDTO;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedCriteria;
import com.retailsbs.logistikapp.retail.dto.ActivesVisitedExt;
import com.retailsbs.logistikapp.retail.dto.VerifyAndRegistCheckInCriteria;

public interface FinderActivesVisitedDAO {
	int deleteActivesVisitedByIdActive(Long id_active);
	List<ActivesVisitedExt> getVisitedList(ActivesVisitedCriteria criteria);
	List<ActivesAndVisitsDTO> getActivesAndVisits(ActivesAndVisitsCriteria criteria);
	String verifyAndRegistCheckIn(VerifyAndRegistCheckInCriteria criteria);
}
