package com.retailsbs.logistikapp.user.dto;

import java.util.List;

import com.retailsbs.logistikapp.user.domain.Notification;
import com.retailsbs.logistikapp.user.domain.ToDo;

/**
 * Clase para almacenar todos los datos de las notificaciones que se visualizan en el header
 * @author JORGE
 * @since 11-02-2015
 */
public class HeaderNotificationDTO {

	private Integer alert_qty;
	private Integer message_qty;
	private Integer todo_qty;
	private List<Notification> alert;
	private List<MessageNoReadDTO> message;
	private List<ToDo> todo;
	
	public Integer getAlert_qty() {
		return alert_qty;
	}
	public void setAlert_qty(Integer alert_qty) {
		this.alert_qty = alert_qty;
	}
	public Integer getMessage_qty() {
		return message_qty;
	}
	public void setMessage_qty(Integer message_qty) {
		this.message_qty = message_qty;
	}
	public Integer getTodo_qty() {
		return todo_qty;
	}
	public void setTodo_qty(Integer todo_qty) {
		this.todo_qty = todo_qty;
	}
	public List<Notification> getAlert() {
		return alert;
	}
	public void setAlert(List<Notification> alert) {
		this.alert = alert;
	}
	public List<MessageNoReadDTO> getMessage() {
		return message;
	}
	public void setMessage(List<MessageNoReadDTO> message) {
		this.message = message;
	}
	public List<ToDo> getTodo() {
		return todo;
	}
	public void setTodo(List<ToDo> todo) {
		this.todo = todo;
	}
	
}
