package com.tibame.web.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ROOM_TYPE")
@Component
public class RoomTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROOM_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomTypeId;

	@Column(name = "ROOM_TYPE_NAME", nullable = false, unique = true)
	private String roomTypeName;

	@Transient
	private Integer roomQuantity;

	@Column(name = "ROOM_PRICE", nullable = false)
	private Integer roomPrice;

	@Lob
	@Column(name = "ROOM_DESCRIPTION", nullable = false, columnDefinition = "text")
	private String roomDescription;

	@Column(name = "ROOM_CREATE_TIME", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp roomCreateTime;

	@Column(name = "ROOM_CHANGE_TIME", insertable = false)
	@UpdateTimestamp
	private Timestamp roomChangeTime;

	@Column(name = "ROOM_STATUS", nullable = false)
	private String roomStatus;

	@Transient
	private String formattedCreateTimestamp;

	@Transient
	private String formattedChangeTimestamp;

	@OneToMany(mappedBy = "roomTypeVO")
	private Set<RoomVO> roomVOs;

	@OneToMany(mappedBy = "roomTypeVO")
	private Set<RoomPhotoVO> roomPhotoVOs;

	public Set<RoomVO> getRoomVOs() {
		return roomVOs;
	}

	public void setRoomVOs(Set<RoomVO> roomVOs) {
		this.roomVOs = roomVOs;
	}

	public Set<RoomPhotoVO> getRoomPhotoVOs() {
		return roomPhotoVOs;
	}

	public void setRoomPhotoVOs(Set<RoomPhotoVO> roomPhotoVOs) {
		this.roomPhotoVOs = roomPhotoVOs;
	}

	public RoomTypeVO() {
	}

	public RoomTypeVO(Integer roomTypeId, String roomTypeName, Integer roomQuantity, Integer roomPrice,
			String roomDescription, Timestamp roomCreateTime, Timestamp roomChangeTime, String roomStatus,
			String formattedCreateTimestamp, String formattedChangeTimestamp, Set<RoomVO> roomVOs,
			Set<RoomPhotoVO> roomPhotoVOs) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.roomQuantity = roomQuantity;
		this.roomPrice = roomPrice;
		this.roomDescription = roomDescription;
		this.roomCreateTime = roomCreateTime;
		this.roomChangeTime = roomChangeTime;
		this.roomStatus = roomStatus;
		this.formattedCreateTimestamp = formattedCreateTimestamp;
		this.formattedChangeTimestamp = formattedChangeTimestamp;
		this.roomVOs = roomVOs;
		this.roomPhotoVOs = roomPhotoVOs;
	}

	public String getFormattedCreateTimestamp() {
		return formattedCreateTimestamp;
	}

	public void setFormattedCreateTimestamp(Timestamp timestamp) {
		this.formattedCreateTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	public String getFormattedChangeTimestamp() {
		return formattedChangeTimestamp;
	}

	public void setFormattedChangeTimestamp(Timestamp timestamp) {
		this.formattedChangeTimestamp = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(timestamp);
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public Integer getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Integer roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public Timestamp getRoomCreateTime() {
		return roomCreateTime;
	}

	public void setRoomCreateTime(Timestamp roomCreateTime) {
		this.roomCreateTime = roomCreateTime;
	}

	public Timestamp getRoomChangeTime() {
		return roomChangeTime;
	}

	public void setRoomChangeTime(Timestamp roomChangeTime) {
		this.roomChangeTime = roomChangeTime;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	@Override
	public String toString() {
		return "RoomTypeVO [roomTypeId=" + roomTypeId + ", roomTypeName=" + roomTypeName + ", roomQuantity="
				+ roomQuantity + ", roomPrice=" + roomPrice + ", roomDescription=" + roomDescription
				+ ", roomCreateTime=" + roomCreateTime + ", roomChangeTime=" + roomChangeTime + ", roomStatus="
				+ roomStatus + ", formattedCreateTimestamp=" + formattedCreateTimestamp + ", formattedChangeTimestamp="
				+ formattedChangeTimestamp + "]";
	}

}
