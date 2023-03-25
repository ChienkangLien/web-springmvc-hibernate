package com.tibame.web.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.tibame.web.dao.RoomDAO;
import com.tibame.web.dto.RoomDTO;
import com.tibame.web.vo.RoomVO;

@Repository
public class RoomDAOImpl implements RoomDAO {
	@Autowired
	private ApplicationContext applicationContext;

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public String updateRooms(List<RoomVO> insertRooms, List<RoomVO> updateRooms) {

		for (RoomVO room : insertRooms) {
			this.getSession().save(room);
		}

		for (RoomVO room : updateRooms) {
			if (room.getRoomId() != null) {
				RoomVO temp = this.getSession().get(RoomVO.class, room.getRoomId());
				if (temp != null) {
					temp.setRoomName(room.getRoomName());
				}
			}
		}
		return "修改成功";
	}

	@Override
	public List<RoomDTO> getAllByType(RoomVO room) {
		List<RoomDTO> list = new ArrayList<RoomDTO>();

		Query<RoomVO> query = this.getSession().createQuery("FROM RoomVO WHERE roomTypeVO.roomTypeId = :roomTypeId",
				RoomVO.class);
		query.setParameter("roomTypeId", room.getRoomTypeVO().getRoomTypeId());
		List<RoomVO> resultList = query.getResultList();
		for (RoomVO roomVO : resultList) {
			RoomDTO roomDTO = applicationContext.getBean(RoomDTO.class);
			roomDTO.setRoomName(roomVO.getRoomName());
			roomDTO.setRoomId(roomVO.getRoomId());
			list.add(roomDTO);
		}
		return list;
	}

	@Override
	public List<RoomDTO> getAvaByDate(Map<String, String> map) {
		List<RoomVO> allRooms = new ArrayList<>();
		List<RoomDTO> allDTORooms = new ArrayList<>();
		Set<Integer> unavaSet = new HashSet<Integer>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// 取得日期間有衝突的房間id
		String hql1 = "select o.roomVO.roomId from RoomOrderVO o where "
				+ "((:startDate between o.orderStartDate and o.orderEndDate) or "
				+ "(:endDate between o.orderStartDate and o.orderEndDate) or "
				+ "(:startDate < o.orderStartDate and :endDate > o.orderEndDate)) and "
				+ "o.orderStatus != '完成單' and o.roomVO.roomTypeVO.roomTypeId = :typeId";
		Query<Integer> query1 = this.getSession().createQuery(hql1, Integer.class);
		try {
			query1.setParameter("startDate", dateFormat.parse(map.get("startDate")));
			query1.setParameter("endDate", dateFormat.parse(map.get("endDate")));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		query1.setParameter("typeId", Integer.parseInt(map.get("typeId")));

		unavaSet.addAll(query1.list());
		unavaSet.add(0); // 要加一個0、以防空陣列

		// 從此房型所有房間、去除掉衝突房房間
		String hql2 = "from RoomVO where roomTypeVO.roomTypeId = :typeId and roomId not in (0,:unavaSet)";
		Query<RoomVO> query2 = this.getSession().createQuery(hql2, RoomVO.class);
		query2.setParameter("typeId", Integer.parseInt(map.get("typeId")));
		query2.setParameter("unavaSet", unavaSet);

		allRooms = query2.getResultList();
		for (RoomVO room : allRooms) {
			RoomDTO roomDTO = applicationContext.getBean(RoomDTO.class);
			roomDTO.setRoomName(room.getRoomName());
			roomDTO.setRoomId(room.getRoomId());
			roomDTO.setRoomTypeId(room.getRoomTypeVO().getRoomTypeId());
			allDTORooms.add(roomDTO);
		}
		return allDTORooms;
	}


	@Override
	public List<String> findDuplicateRoomNamesForInsert(List<RoomVO> rooms) {
		// 搜尋名稱重複的房間(欲新增的房間)
		Query query = session.createQuery("SELECT r.roomName FROM RoomVO r WHERE r.roomName IN :roomNames");
		List<String> roomNames = new ArrayList<>();
		for (RoomVO room : rooms) {
			roomNames.add(room.getRoomName());
		}
		query.setParameterList("roomNames", roomNames);
		List<String> duplicateRoomNames = query.list();
		return duplicateRoomNames;
	}


	@Override
	public String findDuplicateRoomNamesForUpdate(List<RoomVO> rooms) {
		// 搜尋名稱重複的房間(欲變更的房間)
		for (RoomVO room : rooms) {
			if (room.getRoomId() != null) {
				RoomVO temp = this.getSession().get(RoomVO.class, room.getRoomId());
				if (temp != null) {
					String newRoomName = room.getRoomName();
					RoomVO existingRoom = (RoomVO) this.getSession().createCriteria(RoomVO.class)
							.add(Restrictions.eq("roomName", newRoomName)).uniqueResult();

					if (existingRoom != null && !existingRoom.getRoomId().equals(temp.getRoomId())) {
						return newRoomName + "名稱重複";
					}
				}
			}
		}
		return "無重複";
	}

}
