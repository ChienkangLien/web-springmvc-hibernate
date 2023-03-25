package com.tibame.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.web.dto.RoomTypeDTO;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.vo.RoomTypeAndPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

@RestController
@RequestMapping("/admin/room/RoomTypeController")
public class RoomTypeController {
	@Autowired
	private RoomTypeService service;

	@Autowired
	private ApplicationContext applicationContext;

	@PostMapping
	public Map<String, String> createRoomType(@RequestBody RoomTypeAndPhotoVO roomTypeAndPhotoVO) {
		Map<String, String> result = new HashMap<>();
		String resultStr = "操作錯誤";
		try {
			if (roomTypeAndPhotoVO.getRoomPhotoVOs() == null || roomTypeAndPhotoVO.getRoomPhotoVOs().isEmpty()) {
				resultStr = service.createRoomType(roomTypeAndPhotoVO.getRoomTypeVO());
			} else {
				resultStr = service.createRoomType(roomTypeAndPhotoVO.getRoomTypeVO(),
						roomTypeAndPhotoVO.getRoomPhotoVOs());
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultStr = "此房型名稱已被使用";
			result.put("message", resultStr);
			return result;
		}

		result.put("message", resultStr);
		return result;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<RoomTypeDTO>> getAllRoomTypes() {
		final List<RoomTypeDTO> list = service.getAllTypes();
		if (list.size() == 0 || list == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(list);
		}
	}

	@GetMapping("/getSingle")
	public ResponseEntity<RoomTypeDTO> getSingleRoomType(@RequestParam("roomTypeId") Integer roomTypeId) {
		RoomTypeVO roomType = applicationContext.getBean(RoomTypeVO.class);
		roomType.setRoomTypeId(roomTypeId);
		RoomTypeDTO roomTypeDTO = service.getRoomType(roomType);
		if (roomTypeDTO != null) {
			return ResponseEntity.ok(roomTypeDTO);
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public Map<String, String> editRoomType(@RequestBody RoomTypeVO roomType) {
		Map<String, String> result = new HashMap<>();
		String resultStr = "編輯內容有誤";
		if (roomType != null) {
			resultStr = service.editRoomType(roomType);
		}
		result.put("message", resultStr);
		return result;
	}

}
