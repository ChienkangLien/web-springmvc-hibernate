package com.tibame.web.vo;

import java.util.List;

public class RoomTypeAndPhotoVO {
	private RoomTypeVO roomTypeVO;
	private List<RoomPhotoVO> roomPhotoVOs;
	
	public RoomTypeAndPhotoVO() {
	}

	public RoomTypeAndPhotoVO(RoomTypeVO roomTypeVO, List<RoomPhotoVO> roomPhotoVOs) {
		super();
		this.roomTypeVO = roomTypeVO;
		this.roomPhotoVOs = roomPhotoVOs;
	}

	public RoomTypeVO getRoomTypeVO() {
		return roomTypeVO;
	}

	public void setRoomTypeVO(RoomTypeVO roomTypeVO) {
		this.roomTypeVO = roomTypeVO;
	}

	public List<RoomPhotoVO> getRoomPhotoVOs() {
		return roomPhotoVOs;
	}

	public void setRoomPhotoVOs(List<RoomPhotoVO> roomPhotoVOs) {
		this.roomPhotoVOs = roomPhotoVOs;
	}
	
}
