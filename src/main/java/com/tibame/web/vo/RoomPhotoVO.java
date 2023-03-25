package com.tibame.web.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ROOM_PHOTO")
@Component
@Scope(scopeName = "prototype")
public class RoomPhotoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROOM_PHOTO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomPhotoId;

	@Column(name = "ROOM_PHOTO", columnDefinition = "longblob")
	private String roomPhoto;

	@ManyToOne
	@JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "ROOM_TYPE_ID")
	private RoomTypeVO roomTypeVO;

	@Override
	public String toString() {
		return "RoomPhotoVO [roomPhotoId=" + roomPhotoId + ", roomPhoto=" + roomPhoto + "]";
	}

	public RoomPhotoVO() {
	}

	public RoomPhotoVO(Integer roomPhotoId, String roomPhoto, RoomTypeVO roomTypeVO) {
		super();
		this.roomPhotoId = roomPhotoId;
		this.roomPhoto = roomPhoto;
		this.roomTypeVO = roomTypeVO;
	}

	public RoomTypeVO getRoomTypeVO() {
		return roomTypeVO;
	}

	public void setRoomTypeVO(RoomTypeVO roomTypeVO) {
		this.roomTypeVO = roomTypeVO;
	}

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

}
