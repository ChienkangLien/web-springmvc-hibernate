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

import com.tibame.web.dto.RoomPhotoDTO;
import com.tibame.web.service.RoomPhotoService;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;


@RestController
@RequestMapping("/admin/room/RoomPhotoController")
public class RoomPhotoController {
	@Autowired
	private RoomPhotoService service;

//	@Autowired
//	private ApplicationContext applicationContext;

	@GetMapping
	public ResponseEntity<List<RoomPhotoDTO>> getRoomPhotos(@RequestParam Integer id) {
		List<RoomPhotoDTO> list = service.getAllPhotos(id);

		if (list == null || list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(list);
	}

	@PostMapping
	public Map<String, String> editRoomTypePhoto(@RequestBody List<List<Map<String, Object>>> allData) {
		Map<String, String> result = new HashMap<>();
		List<Map<String, Object>> data1 = allData.get(0);
		List<Map<String, Object>> data2 = allData.get(1);

		if (data1 == null || data2 == null) {
			result.put("message", "編輯內容有誤");
		} else {
			List<RoomPhotoVO> newPhotoList = new ArrayList<>();
			for (Map<String, Object> map : data1) {
//				RoomPhotoVO photo = applicationContext.getBean(RoomPhotoVO.class);
				RoomPhotoVO photo = new RoomPhotoVO();
//				RoomTypeVO roomType = applicationContext.getBean(RoomTypeVO.class);
				RoomTypeVO roomType = new RoomTypeVO();
				photo.setRoomPhoto((String) map.get("roomPhoto"));
				photo.setRoomTypeVO(roomType);
				photo.getRoomTypeVO().setRoomTypeId(Integer.parseInt((String) map.get("roomTypeId")));
				newPhotoList.add(photo);
			}

			List<RoomPhotoVO> removePhotoList = new ArrayList<>();
			for (Map<String, Object> map : data2) {
//				RoomPhotoVO photo = applicationContext.getBean(RoomPhotoVO.class);
				RoomPhotoVO photo = new RoomPhotoVO();
				photo.setRoomPhotoId(Integer.parseInt((String) map.get("roomPhotoId")));
				removePhotoList.add(photo);
			}
			result.put("message", service.editRoomTypePhoto(newPhotoList, removePhotoList));
		}
		return result;
	}

}
