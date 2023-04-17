package com.tibame.web.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ROOM_ORDER")
@Component
//@Scope(scopeName = "prototype")
public class RoomOrderVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROOM_ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomOrderId;

	@ManyToOne
	@JoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
	private RoomVO roomVO;

	@Transient
	private String roomTypeName;

	@Column(name = "ORDER_START_DATE", nullable = false)
	private Date orderStartDate;

	@Column(name = "ORDER_END_DATE", nullable = false)
	private Date orderEndDate;

	@Column(name = "ORDER_CREATE_TIME", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp orderCreateTime;

	@Column(name = "ORDER_CHANGE_TIME", insertable = false)
	@UpdateTimestamp
	private Timestamp orderChangeTime;

	@Column(name = "ORDER_RESIDENT")
	private String orderResident;

	@Column(name = "ORDER_PRICE")
	private Integer orderPrice;

	@Lob
	@Column(name = "ORDER_REMARK", columnDefinition = "text")
	private String orderRemark;

	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private MemberVO memberVO;

	@ManyToOne
	@JoinColumn(name = "ADMIN_ID", referencedColumnName = "ADMIN_ID")
	private AdministratorVO administratorVO;

	@Column(name = "ORDER_STATUS", nullable = false)
	private String orderStatus;

	@Transient
	private String formattedCreateTimestamp;

	@Transient
	private String formattedChangeTimestamp;

	public RoomOrderVO() {
	}
	
	public RoomOrderVO(Integer roomOrderId, RoomVO roomVO, String roomTypeName, Date orderStartDate, Date orderEndDate,
			Timestamp orderCreateTime, Timestamp orderChangeTime, String orderResident, Integer orderPrice,
			String orderRemark, MemberVO memberVO, AdministratorVO administratorVO, String orderStatus,
			String formattedCreateTimestamp, String formattedChangeTimestamp) {
		super();
		this.roomOrderId = roomOrderId;
		this.roomVO = roomVO;
		this.roomTypeName = roomTypeName;
		this.orderStartDate = orderStartDate;
		this.orderEndDate = orderEndDate;
		this.orderCreateTime = orderCreateTime;
		this.orderChangeTime = orderChangeTime;
		this.orderResident = orderResident;
		this.orderPrice = orderPrice;
		this.orderRemark = orderRemark;
		this.memberVO = memberVO;
		this.administratorVO = administratorVO;
		this.orderStatus = orderStatus;
		this.formattedCreateTimestamp = formattedCreateTimestamp;
		this.formattedChangeTimestamp = formattedChangeTimestamp;
	}

	public Integer getRoomOrderId() {
		return roomOrderId;
	}

	public void setRoomOrderId(Integer roomOrderId) {
		this.roomOrderId = roomOrderId;
	}

	public RoomVO getRoomVO() {
		return roomVO;
	}

	public void setRoomVO(RoomVO roomVO) {
		this.roomVO = roomVO;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
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

	public Timestamp getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Timestamp orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Timestamp getOrderChangeTime() {
		return orderChangeTime;
	}

	public void setOrderChangeTime(Timestamp orderChangeTime) {
		this.orderChangeTime = orderChangeTime;
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

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public AdministratorVO getAdministratorVO() {
		return administratorVO;
	}

	public void setAdministratorVO(AdministratorVO administratorVO) {
		this.administratorVO = administratorVO;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFormattedCreateTimestamp() {
		return formattedCreateTimestamp;
	}

	public void setFormattedCreateTimestamp(Timestamp  timestamp) {
		this.formattedCreateTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	public String getFormattedChangeTimestamp() {
		return formattedChangeTimestamp;
	}

	public void setFormattedChangeTimestamp(Timestamp  timestamp) {
		this.formattedChangeTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	@Override
	public String toString() {
		return "RoomOrderVO [roomOrderId=" + roomOrderId + ", roomTypeName=" + roomTypeName + ", orderStartDate="
				+ orderStartDate + ", orderEndDate=" + orderEndDate + ", orderCreateTime=" + orderCreateTime
				+ ", orderChangeTime=" + orderChangeTime + ", orderResident=" + orderResident + ", orderPrice="
				+ orderPrice + ", orderRemark=" + orderRemark + ", orderStatus=" + orderStatus
				+ ", formattedCreateTimestamp=" + formattedCreateTimestamp + ", formattedChangeTimestamp="
				+ formattedChangeTimestamp + "]";
	}

	


}