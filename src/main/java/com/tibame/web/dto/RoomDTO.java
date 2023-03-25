package com.tibame.web.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class RoomDTO {
	private Integer roomId;
	private String roomName;
	private Integer roomTypeId;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	@Override
	public String toString() {
		return "RoomDTO [roomId=" + roomId + ", roomName=" + roomName + ", roomTypeId=" + roomTypeId + "]";
	}
	public RoomDTO(Integer roomId, String roomName, Integer roomTypeId) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomTypeId = roomTypeId;
	}
	
	public RoomDTO() {
	}
	
}
