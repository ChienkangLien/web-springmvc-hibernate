package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.dto.RoomTypeDTO;
import com.tibame.web.vo.RoomTypeVO;



public interface RoomTypeDAO {
	public int insert(RoomTypeVO type);
	public String update(RoomTypeVO type);
	public RoomTypeDTO findByPrimaryKey(RoomTypeVO type);
	public RoomTypeVO findByRoomTypeName(RoomTypeVO type);
	public List<RoomTypeDTO> getAll();
	public String checkName(RoomTypeVO type);
}
