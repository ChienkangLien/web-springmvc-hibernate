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
@Table(name = "ROOM")
@Component
@Scope(scopeName = "prototype")
public class RoomVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ROOM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;

	@Column(name = "ROOM_NAME", nullable = false, unique = true)
	private String roomName;

	@ManyToOne
	@JoinColumn(name = "ROOM_TYPE_ID", referencedColumnName = "ROOM_TYPE_ID")
	private RoomTypeVO roomTypeVO;
	
	@Override
	public String toString() {
		return "RoomVO [roomId=" + roomId + ", roomName=" + roomName + "]";
	}

	public RoomVO() {
	}
	
	public RoomVO(Integer roomId, String roomName, RoomTypeVO roomTypeVO) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomTypeVO = roomTypeVO;
	}

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

	public RoomTypeVO getRoomTypeVO() {
		return roomTypeVO;
	}

	public void setRoomTypeVO(RoomTypeVO roomTypeVO) {
		this.roomTypeVO = roomTypeVO;
	}

	
}
