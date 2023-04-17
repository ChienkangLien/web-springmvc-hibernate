package com.tibame.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.web.dto.RoomDTO;
import com.tibame.web.service.RoomService;
import com.tibame.web.vo.RoomTypeVO;
import com.tibame.web.vo.RoomVO;

@RestController
@RequestMapping("/admin/room/RoomController")
public class RoomController {
	@Autowired
	private RoomService service;
	
//	@Autowired
//	private ApplicationContext applicationContext;

	@GetMapping("/check")
	public Map<String, String> checkAva(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("roomId") String roomId,
			@RequestParam("orderId") String orderId) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> map = new HashMap<String, String>();
		String resultStr = "操作失敗";
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("roomId", roomId);
		map.put("orderId", orderId);
		if (map != null) {
			resultStr = service.checkAva(map);
		}
		result.put("message", resultStr);
		return result;
	}

	@GetMapping("/available")
	public ResponseEntity<List<RoomDTO>> getRoomsByDate(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("typeId") String typeId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("typeId", typeId);
		List<RoomDTO> list = service.getRoomsByDate(map);
		if (list.size() != 0) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<RoomDTO>> getRoomsByDate(@RequestParam("roomTypeId") String roomTypeId) {
//		RoomVO room = applicationContext.getBean(RoomVO.class);
		RoomVO room = new RoomVO();
//		RoomTypeVO roomType = applicationContext.getBean(RoomTypeVO.class);
		RoomTypeVO roomType = new RoomTypeVO();
		room.setRoomTypeVO(roomType);
		room.getRoomTypeVO().setRoomTypeId(Integer.parseInt(roomTypeId));

		if (room != null && room.getRoomTypeVO().getRoomTypeId() != null) {
			List<RoomDTO> list = service.getRoomsByType(room);
			if (list.size() != 0) {
				return ResponseEntity.ok(list);
			}
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public Map<String, String> editRoom(@RequestBody List<List<Map<String, Object>>> allData) {
		Map<String, String> result = new HashMap<>();
		String resultStr = null;
		List<Map<String, Object>> data1 = allData.get(0);
		List<Map<String, Object>> data2 = allData.get(1);

		if (data1 == null || data2 == null) {
			resultStr = "編輯內容有誤";
		} else {
			List<RoomVO> newRoomList = new ArrayList<>();
			for (Map<String, Object> map : data1) {
//				RoomVO room = applicationContext.getBean(RoomVO.class);
				RoomVO room = new RoomVO();
//				RoomTypeVO roomType = applicationContext.getBean(RoomTypeVO.class);
				RoomTypeVO roomType = new RoomTypeVO();
				room.setRoomName((String) map.get("roomName"));
				room.setRoomTypeVO(roomType);
				room.getRoomTypeVO().setRoomTypeId(Integer.parseInt((String) map.get("roomTypeId")));
				newRoomList.add(room);
			}
			List<RoomVO> updateRoomList = new ArrayList<>();
			for (Map<String, Object> map : data2) {
//				RoomVO room = applicationContext.getBean(RoomVO.class);
				RoomVO room = new RoomVO();
				room.setRoomName((String) map.get("roomName"));
				room.setRoomId(Integer.parseInt((String) map.get("roomId")));
				updateRoomList.add(room);
			}
			resultStr = service.editRoom(newRoomList, updateRoomList);
		}
		result.put("message", resultStr);
		return result;
	}

}
