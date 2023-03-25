package com.tibame.web.dto;

import java.sql.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class RoomOrderDTO {
	private Integer roomOrderId;

	private Date orderStartDate;

	private Date orderEndDate;

	private String orderResident;

	private Integer orderPrice;

	private String orderRemark;

	private String orderStatus;

	public RoomOrderDTO() {
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public RoomOrderDTO(Integer roomOrderId, Date orderStartDate, Date orderEndDate, String orderResident,
			Integer orderPrice, String orderRemark, String orderStatus) {
		super();
		this.roomOrderId = roomOrderId;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.orderResident = orderResident;
		this.orderPrice = orderPrice;
		this.orderRemark = orderRemark;
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "RoomOrderDTO [roomOrderId=" + roomOrderId + ", orderStartDate=" + orderStartDate + ", orderEndDate="
				+ orderEndDate + ", orderResident=" + orderResident + ", orderPrice=" + orderPrice + ", orderRemark="
				+ orderRemark + ", orderStatus=" + orderStatus + "]";
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getRoomOrderId() {
		return roomOrderId;
	}

	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}

	public Date getOrderStartDate() {
		return orderStartDate;
	}

	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public String getOrderResident() {
		return orderResident;
	}

	public void setOrderResident(String orderResident) {
		this.orderResident = orderResident;
	}

	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

}
