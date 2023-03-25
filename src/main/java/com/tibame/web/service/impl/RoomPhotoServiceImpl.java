package com.tibame.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibame.web.dao.RoomPhotoDAO;
import com.tibame.web.dto.RoomPhotoDTO;
import com.tibame.web.service.RoomPhotoService;
import com.tibame.web.vo.RoomPhotoVO;

@Service
@Transactional
public class RoomPhotoServiceImpl implements RoomPhotoService {
	@Autowired
	private RoomPhotoDAO photoDao;
	
	@Override
	public List<RoomPhotoDTO> getAllPhotos(Integer id) {
		if(id!=null ) {
			return photoDao.getAllByRoomTypeId(id);		
		}
		return null;
	}

	@Override
	public String editRoomTypePhoto(List<RoomPhotoVO> insertRoomPhotoVOList, List<RoomPhotoVO> deleteRoomPhotoVOList) {
		if(insertRoomPhotoVOList.size()!=0) {
			for(int i = 0; i<insertRoomPhotoVOList.size();i++) {
				int insertResultCount = photoDao.insert(insertRoomPhotoVOList.get(i));
				if(insertResultCount<1) {
					return "照片加入失敗";
				}
			}
		}
		if(deleteRoomPhotoVOList.size()!=0) {
			for(int i = 0; i<deleteRoomPhotoVOList.size();i++) {
				int deleteResultCount = photoDao.delete(deleteRoomPhotoVOList.get(i));
				if(deleteResultCount<1) {
					return "照片加入失敗";
				}
			}
		}
		return "修改成功";
	}
}
