package com.tibame.web.service;

import java.util.List;

import com.tibame.web.dto.RoomTypeDTO;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

public interface RoomTypeService {
	String createRoomType(RoomTypeVO roomType);
	String createRoomType(RoomTypeVO roomType,List<RoomPhotoVO> roomPhotoVO);
//	String deleteRoomType(RoomTypeVO roomType);
//	List<RoomTypeVO> getAllTypes();
	List<RoomTypeDTO> getAllTypes();
	String editRoomType(RoomTypeVO roomType);
//	RoomTypeVO getRoomType(RoomTypeVO roomType);
	RoomTypeDTO getRoomType(RoomTypeVO roomType);
}
