package com.tibame.web.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class RoomTypeDTO {
	private Integer roomTypeId;
    private String roomTypeName;
    private Integer roomQuantity;
    private Integer roomPrice;
    private String roomDescription;
    private String roomStatus;
    private String formattedCreateTimestamp;
    private String formattedChangeTimestamp;
    
    public RoomTypeDTO() {
	}
    
    
	@Override
	public String toString() {
		return "RoomTypeDTO [roomTypeId=" + roomTypeId + ", roomTypeName=" + roomTypeName + ", roomQuantity="
				+ roomQuantity + ", roomPrice=" + roomPrice + ", roomDescription=" + roomDescription + ", roomStatus="
				+ roomStatus + ", formattedCreateTimestamp=" + formattedCreateTimestamp + ", formattedChangeTimestamp="
				+ formattedChangeTimestamp + "]";
	}


	public RoomTypeDTO(Integer roomTypeId, String roomTypeName, Integer roomQuantity, Integer roomPrice,
			String roomDescription, String roomStatus, String formattedCreateTimestamp,
			String formattedChangeTimestamp) {
		super();
		this.roomTypeId = roomTypeId;
		this.roomTypeName = roomTypeName;
		this.roomQuantity = roomQuantity;
		this.roomPrice = roomPrice;
		this.roomDescription = roomDescription;
		this.roomStatus = roomStatus;
		this.formattedCreateTimestamp = formattedCreateTimestamp;
		this.formattedChangeTimestamp = formattedChangeTimestamp;
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
	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getFormattedCreateTimestamp() {
		return formattedCreateTimestamp;
	}
	public void setFormattedCreateTimestamp(String formattedCreateTimestamp) {
		this.formattedCreateTimestamp = formattedCreateTimestamp;
	}
	public String getFormattedChangeTimestamp() {
		return formattedChangeTimestamp;
	}
	public void setFormattedChangeTimestamp(String formattedChangeTimestamp) {
		this.formattedChangeTimestamp = formattedChangeTimestamp;
	}
    
}
