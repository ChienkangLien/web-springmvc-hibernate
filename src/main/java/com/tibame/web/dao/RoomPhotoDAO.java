package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.dto.RoomPhotoDTO;
import com.tibame.web.vo.RoomPhotoVO;


public interface RoomPhotoDAO {
	public int insert(RoomPhotoVO photo);
	public int delete(RoomPhotoVO photo);
	public List<RoomPhotoDTO> getAllByRoomTypeId(Integer id);
}
