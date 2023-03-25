package com.tibame.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.dto.RoomDTO;
import com.tibame.web.service.RoomService;
import com.tibame.web.vo.RoomVO;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDAO roomDao;
	@Autowired
	private RoomOrderDAO orderDAO;

	@Override
	public List<RoomDTO> getRoomsByType(RoomVO room) {
		if (room != null && room.getRoomTypeVO().getRoomTypeId() != null) {
			return roomDao.getAllByType(room);
		}
		return null;
	}

	@Override
	public String editRoom(List<RoomVO> insertRoomVOList, List<RoomVO> updateRoomVOList) {
		String result = "";
		if (insertRoomVOList != null && !insertRoomVOList.isEmpty()
				|| updateRoomVOList != null && !updateRoomVOList.isEmpty()) {

			String str = roomDao.findDuplicateRoomNamesForUpdate(updateRoomVOList);
			if (str != "無重複") {
				return str;
			}

			List<String> list = roomDao.findDuplicateRoomNamesForInsert(insertRoomVOList);
			if (list.size() != 0) {
				return list.get(0) + "名稱重複";
			}

			result = roomDao.updateRooms(insertRoomVOList, updateRoomVOList);
			return result;
		}
		return result == "" ? "沒有資料異動" : "操作失敗";
	}

	@Override
	public List<RoomDTO> getRoomsByDate(Map<String, String> map) {
		if (map.get("startDate") != null && map.get("endDate") != null && map.get("typeId") != null) {
			return roomDao.getAvaByDate(map);
		}
		return null;
	}

	@Override
	public String checkAva(Map<String, String> map) {
		if (map.get("startDate") != null && map.get("endDate") != null && map.get("orderId") != null
				&& map.get("roomId") != null) {
//			return roomDao.getAvaByRoomId(map);
			if (orderDAO.checkExistingNumForUpdate(map) == 1 && orderDAO.checkbelong(map) == 1) {
				return "可以變更";
			} else {
				return "此期間已有其他房客入住、不得變更";
			}
		}
		return "操作失敗";
	}

}
