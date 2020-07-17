package com.retailsbs.logistikapp.user.notification.service;

import java.util.List;

import com.retailsbs.logistikapp.user.dto.AddNotificationDTO;
import com.retailsbs.logistikapp.user.notification.domain.ListNotificationDTO;

public interface UserNotificationService {
	
	long createNotification(AddNotificationDTO datos, String code);
	
	long createNotificationWithList(AddNotificationDTO datos, List<Integer> usuarios);
	
	long createNotificationWithIdNotification(Long id_notification, List<Integer> usuarios);
	
	List<ListNotificationDTO> searchNotification(Long id_user,String code); 
	
	void updateNotification(Long id_user);
	
}
