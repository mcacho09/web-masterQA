package com.retailsbs.logistikapp.web.ctrl.communication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.retailsbs.logistikapp.logistic.service.LogisticService;
import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.dto.UserDTO;
import com.retailsbs.logistikapp.user.dto.UserSearchCriteria;
import com.retailsbs.logistikapp.user.notification.service.UserNotificationService;
import com.retailsbs.logistikapp.user.service.UserService;

public class NotificationsTasks {
	
	public static final Log logger = LogFactory.getLog(NotificationsTasks.class);
	
	SimpleDateFormat sdf;
	SimpleDateFormat sdf2;
	LogisticService logisticService;
	UserNotificationService userNotificationService;
	UserService userService;
	
	public NotificationsTasks() {
		this.sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		this.sdf2 = new SimpleDateFormat("dd/MM/YYYY");
	}
	
	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public void setUserNotificationService(
			UserNotificationService userNotificationService) {
		this.userNotificationService = userNotificationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void print() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
		List<Long> suppliers = logisticService.getSuppliersWithStoresNotVisited(calendar.getTime());
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setProfile("SOP");
		List<UserDTO> sop = userService.getUserByCriteria(criteria);
		
		for (Long i : suppliers) {
			logger.debug("Se envio al supplier de id: " + i);
			UserSearchCriteria dto = new UserSearchCriteria();
			dto.setId_supplier(i);
			dto.setProfile("SUP");
			
			List<Integer> users = userService.getSupIdsBySuppliers(i);
			logger.debug("Se enviara a " + users.size() + " usuario(s)");
			if (users.size() > 0) {
				AddNotificationDTO notification = new AddNotificationDTO();
				notification.setCreated(new Date());
				notification.setIcon("fa fa-bell-o");
				notification.setId_user(sop.get(0).getId_user());
				notification.setMessage("Tienes clientes que no fueron visitados en los viajes del día <span class='label label-info'>" + sdf2.format(new Date()) + "</span>");
				notification.setPriority("1");
				notification.setId_supplier(i);
				notification.setLink("usersnotvisited.htm");
				Long res = userNotificationService.createNotificationWithList(notification, users);
				logger.debug("Se envio not con id: " + res);
			} else {
				logger.debug("No cuenta con SUPs");
			}
		}
	}

}
