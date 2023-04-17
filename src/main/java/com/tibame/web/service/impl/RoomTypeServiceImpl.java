package com.tibame.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibame.web.dao.RoomPhotoDAO;
import com.tibame.web.dao.RoomTypeDAO;
import com.tibame.web.dto.RoomTypeDTO;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {
//	@Autowired
//	private ApplicationContext applicationContext;
	@Autowired
	private RoomTypeDAO typeDao;
	@Autowired
	private RoomPhotoDAO photoDao;

	@Override
	public String createRoomType(RoomTypeVO roomType) {
		String roomTypeName = roomType.getRoomTypeName().trim();
		Integer roomPrice = roomType.getRoomPrice();
		String roomDescription = roomType.getRoomDescription();
		String roomStatus = roomType.getRoomStatus();

		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "房型名稱不合規則";
		}

		if (roomPrice <= 0 || roomPrice % 1 != 0 || roomPrice == null) {
			return "房間單價不合規則";
		}

		if (roomDescription == null || roomDescription.isEmpty() || roomDescription.equals("<p><br></p>")) {
			return "房型描述不合規則";
		}

		if (roomStatus == null || roomStatus.isEmpty()) {
			return "房型狀態不合規則";
		}
		final int resultCount = typeDao.insert(roomType);
		return resultCount > 0 ? "新增成功" : "此房型名稱已被使用";
	}

	@Override
	public String createRoomType(RoomTypeVO roomType, List<RoomPhotoVO> roomPhotoVO) {
		String roomTypeName = roomType.getRoomTypeName().trim();
		Integer roomPrice = roomType.getRoomPrice();
		String roomDescription = roomType.getRoomDescription();
		String roomStatus = roomType.getRoomStatus();

		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "房型名稱不合規則";
		}

		if (roomPrice <= 0 || roomPrice % 1 != 0 || roomPrice == null) {
			return "房間單價不合規則";
		}

		if (roomDescription == null || roomDescription.isEmpty() || roomDescription.equals("<p><br></p>")) {
			return "房型描述不合規則";
		}

		if (roomStatus == null || roomStatus.isEmpty()) {
			return "房型狀態不合規則";
		}

		int insertResultCount = typeDao.insert(roomType);

		if (insertResultCount < 1) {
			return "此房型名稱已被使用";
		}

		RoomTypeVO roomTypeVO = typeDao.findByRoomTypeName(roomType);
		Integer roomTypeId = roomTypeVO.getRoomTypeId();
		for (int i = 0; i < roomPhotoVO.size(); i++) {
//			RoomTypeVO roomTypeVO2 = applicationContext.getBean(RoomTypeVO.class);
			RoomTypeVO roomTypeVO2 = new RoomTypeVO();
			roomPhotoVO.get(i).setRoomTypeVO(roomTypeVO2);
			roomPhotoVO.get(i).getRoomTypeVO().setRoomTypeId(roomTypeId);
			insertResultCount = photoDao.insert(roomPhotoVO.get(i));
			if (insertResultCount < 1) {
				return "照片加入失敗，請直接進入該房型做編輯";
			}
		}
		return "新增成功";
	}

	@Override
	public List<RoomTypeDTO> getAllTypes() {
		return typeDao.getAll();
	}

	@Override
	public String editRoomType(RoomTypeVO roomType) {
		String roomTypeName = roomType.getRoomTypeName().trim();
		Integer roomPrice = roomType.getRoomPrice();
		String roomDescription = roomType.getRoomDescription();
		String roomStatus = roomType.getRoomStatus();

		if (roomTypeName == null || roomTypeName.isEmpty()) {
			return "房型名稱不合規則";
		}

		if (roomPrice <= 0 || roomPrice % 1 != 0 || roomPrice == null) {
			return "房間單價不合規則";
		}

		if (roomDescription == null || roomDescription.isEmpty() || roomDescription.equals("<p><br></p>")) {
			return "房型描述不合規則";
		}

		if (roomStatus == null || roomStatus.isEmpty()) {
			return "房型狀態不合規則";
		}

		if (roomStatus.equals("上架") && roomType.getRoomQuantity() == 0) {
			return "房間數量不得為零";
		}
		String dupString = typeDao.checkName(roomType);
		if (dupString != "無重複") {
			return dupString;
		}
		return typeDao.update(roomType);
	}

	@Override
	public RoomTypeDTO getRoomType(RoomTypeVO roomType) {
		if (roomType != null && roomType.getRoomTypeId() != null) {
			return typeDao.findByPrimaryKey(roomType);
		}
		return null;
	}
}
