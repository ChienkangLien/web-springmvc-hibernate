package com.tibame.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.tibame.web.dao.RoomPhotoDAO;
import com.tibame.web.dto.RoomPhotoDTO;
import com.tibame.web.vo.RoomPhotoVO;
@Repository
public class RoomPhotoDAOImpl implements RoomPhotoDAO {
//	@Autowired
//    private ApplicationContext applicationContext;
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public int insert(RoomPhotoVO photo) {
		return (int) this.getSession().save(photo) > 0 ? 1 : -1;
	}

	@Override
	public int delete(RoomPhotoVO photo) {
		RoomPhotoVO photoTemp = this.getSession().get(RoomPhotoVO.class, photo.getRoomPhotoId());
		if(photoTemp!=null) {
			this.getSession().delete(photoTemp);
			return 1;
		}
		return -1;
	}

	@Override
	public List<RoomPhotoDTO> getAllByRoomTypeId(Integer id) {
		Query<RoomPhotoVO> query = this.getSession().createQuery(
		        "FROM RoomPhotoVO WHERE roomTypeVO.roomTypeId=:roomTypeId", RoomPhotoVO.class);
		    query.setParameter("roomTypeId", id);
		    List<RoomPhotoVO> photoList = query.list();

		    List<RoomPhotoDTO> dtoList = new ArrayList<>();
		    for (RoomPhotoVO photoVO : photoList) {
//		        RoomPhotoDTO photoDTO = applicationContext.getBean(RoomPhotoDTO.class);
		        RoomPhotoDTO photoDTO = new RoomPhotoDTO();
		        photoDTO.setRoomPhotoId(photoVO.getRoomPhotoId());
		        photoDTO.setRoomPhoto(photoVO.getRoomPhoto());
		        photoDTO.setRoomTypeId(photoVO.getRoomTypeVO().getRoomTypeId());
		        dtoList.add(photoDTO);
		    }
		    return dtoList;
	}
}
