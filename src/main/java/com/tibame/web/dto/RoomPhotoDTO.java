package com.tibame.web.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class RoomPhotoDTO {
	private Integer roomPhotoId;

	private String roomPhoto;

	private Integer roomTypeId;

	public Integer getRoomPhotoId() {
		return roomPhotoId;
	}

	public void setRoomPhotoId(Integer roomPhotoId) {
		this.roomPhotoId = roomPhotoId;
	}

	public String getRoomPhoto() {
		return roomPhoto;
	}

	public void setRoomPhoto(String roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	@Override
	public String toString() {
		return "RoomPhotoDTO [roomPhotoId=" + roomPhotoId + ", roomPhoto=" + roomPhoto + ", roomTypeId=" + roomTypeId
				+ "]";
	}

	public RoomPhotoDTO(Integer roomPhotoId, String roomPhoto, Integer roomTypeId) {
		super();
		this.roomPhotoId = roomPhotoId;
		this.roomPhoto = roomPhoto;
		this.roomTypeId = roomTypeId;
	}
	public RoomPhotoDTO() {
	}
}
