package com.tibame.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.tibame.web.dao.RoomTypeDAO;
import com.tibame.web.dto.RoomTypeDTO;
import com.tibame.web.vo.RoomTypeVO;

@Repository
public class RoomTypeDAOImpl implements RoomTypeDAO {
//	@Autowired
//	private ApplicationContext applicationContext;

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public int insert(RoomTypeVO type) {
		return (int) this.getSession().save(type) > 0 ? 1 : -1;
	}

	@Override
	public String update(RoomTypeVO type) {
		if (type.getRoomTypeId() != null) {
			RoomTypeVO temp = this.getSession().get(RoomTypeVO.class, type.getRoomTypeId());
			if (temp != null) {
					temp.setRoomTypeName(type.getRoomTypeName());
					temp.setRoomPrice(type.getRoomPrice());
					temp.setRoomDescription(type.getRoomDescription());
					temp.setRoomStatus(type.getRoomStatus());
					return "修改成功";
			}
		}
		return "修改失敗";
	}

	@Override
	public String checkName(RoomTypeVO type) {
		if (type.getRoomTypeId() != null) {
			RoomTypeVO temp = this.getSession().get(RoomTypeVO.class, type.getRoomTypeId());
			if (temp != null) {
				// 驗證房型名稱有無重複
				String newRoomTypeName = type.getRoomTypeName();
				RoomTypeVO existingRoomType = (RoomTypeVO) this.getSession().createCriteria(RoomTypeVO.class)
						.add(Restrictions.eq("roomTypeName", newRoomTypeName)).uniqueResult();

				if (existingRoomType != null && !existingRoomType.getRoomTypeId().equals(temp.getRoomTypeId())) {
					return newRoomTypeName + "名稱重複";
				} else {

					return "無重複";
				}
			}
		}
		return "操作失敗";
	}

	@Override
	public RoomTypeDTO findByPrimaryKey(RoomTypeVO type) {
		RoomTypeVO vo = this.getSession().get(RoomTypeVO.class, type.getRoomTypeId());
		if (vo != null) {
//			RoomTypeDTO dto = applicationContext.getBean(RoomTypeDTO.class);
			RoomTypeDTO dto = new RoomTypeDTO();
			dto.setRoomStatus(vo.getRoomStatus());
			dto.setRoomTypeName(vo.getRoomTypeName());
			dto.setRoomDescription(vo.getRoomDescription());
			dto.setRoomPrice(vo.getRoomPrice());

			return dto;
		}
		return null;
	}

	@Override
	public List<RoomTypeDTO> getAll() {
		List<RoomTypeDTO> list = new ArrayList<>();

		Query<RoomTypeVO> query1 = this.getSession().createQuery("FROM RoomTypeVO", RoomTypeVO.class);
		List<RoomTypeVO> roomTypeVOList = query1.getResultList();

		for (RoomTypeVO roomTypeVO : roomTypeVOList) {
			Query query2 = this.getSession().createQuery("FROM RoomTypeVO WHERE roomTypeId = :roomTypeId");
			query2.setParameter("roomTypeId", roomTypeVO.getRoomTypeId());
			RoomTypeVO result = (RoomTypeVO) query2.uniqueResult();

//			RoomTypeDTO roomTypeDTO = applicationContext.getBean(RoomTypeDTO.class);
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			BeanUtils.copyProperties(roomTypeVO, roomTypeDTO); // 屬性轉換VO -> DTO

			if (result != null) {
				Integer totalRooms = result.getRoomVOs().size();
				roomTypeDTO.setRoomQuantity(totalRooms);
			}

			roomTypeDTO.setFormattedCreateTimestamp(roomTypeVO.getRoomCreateTime().toString());
			if (roomTypeVO.getRoomChangeTime() != null) {
				roomTypeDTO.setFormattedChangeTimestamp(roomTypeVO.getRoomChangeTime().toString());
			}
			list.add(roomTypeDTO);
		}
		return list;
	}

	@Override
	public RoomTypeVO findByRoomTypeName(RoomTypeVO type) {
		Query<RoomTypeVO> query = this.getSession().createQuery("FROM RoomTypeVO WHERE roomTypeName=:roomTypeName",
				RoomTypeVO.class);
		query.setParameter("roomTypeName", type.getRoomTypeName());
		RoomTypeVO roomTypeVO = query.uniqueResult();
		return roomTypeVO;
	}

}
