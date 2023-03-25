package com.tibame.web.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.tibame.web.dto.RoomDTO;
import com.tibame.web.vo.RoomVO;




public interface RoomDAO {
	public String updateRooms(List<RoomVO> insertRooms,List<RoomVO> updateRooms);
	public List<RoomDTO> getAllByType(RoomVO room);	
	public List<RoomDTO> getAvaByDate(Map<String, String> map);
	public List<String> findDuplicateRoomNamesForInsert(List<RoomVO> rooms);
	public String findDuplicateRoomNamesForUpdate(List<RoomVO> rooms);
}
