package com.tibame.web.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.dto.RoomOrderDTO;
import com.tibame.web.vo.AdministratorVO;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.RoomOrderVO;
import com.tibame.web.vo.RoomTypeVO;
import com.tibame.web.vo.RoomVO;

@Repository
public class RoomOrderDAOImpl implements RoomOrderDAO {
	@Autowired
	private ApplicationContext applicationContext;

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public int insert(RoomOrderVO order) {
//		// 驗證是否晚了一步新增訂單
//		String hql1 = "select count(*) from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate)) and orderStatus != '完成單'";
//		Query<Long> query1 = this.getSession().createQuery(hql1, Long.class);
//		query1.setParameter("roomId", order.getRoomVO().getRoomId());
//		query1.setParameter("startDate", order.getOrderStartDate());
//		query1.setParameter("endDate", order.getOrderEndDate());
//
//		Long count = query1.uniqueResult();
//		if (count > 0) {
//			return 0;
//		}

		return (int) this.getSession().save(order) > 0 ? 1 : -1;
	}

	@Override
	public int update(RoomOrderVO order) {
//		// 驗證是否晚了一步新增訂單
//		String hql1 = "select count(*) from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate)) and orderStatus != '完成單'";
//		Query<Long> query1 = this.getSession().createQuery(hql1, Long.class);
//		query1.setParameter("roomId", order.getRoomVO().getRoomId());
//		query1.setParameter("startDate", order.getOrderStartDate());
//		query1.setParameter("endDate", order.getOrderEndDate());
//
//		Long count = query1.uniqueResult();
//		if (count > 0) {
//			return 0;
//		}

		if (order.getRoomOrderId() != null) {
			RoomOrderVO temp = this.getSession().get(RoomOrderVO.class, order.getRoomOrderId());
			if (temp != null) {
				temp.setOrderStartDate(order.getOrderStartDate());
				temp.setOrderEndDate(order.getOrderEndDate());
				temp.setOrderResident(order.getOrderResident());
				temp.setOrderPrice(order.getOrderPrice());
				temp.setOrderRemark(order.getOrderRemark());
				return 1;
			}
		}
		return -1;
	}

	@Override
	public int delete(RoomOrderVO order) {
		RoomOrderVO orderTemp = this.getSession().get(RoomOrderVO.class, order.getRoomOrderId());
		if (orderTemp != null) {
			this.getSession().delete(orderTemp);
			return 1;
		}
		return -1;
	}

	@Override
	public List<RoomOrderVO> getAll(String status, Integer limit) {
		String hql = "SELECT o.roomOrderId, o.roomVO.roomTypeVO.roomTypeName, o.roomVO.roomName, "
				+ "o.orderStartDate, o.orderEndDate, o.orderCreateTime, "
				+ "o.orderChangeTime, o.orderResident, o.orderPrice, "
				+ "o.memberVO.userName, o.administratorVO.adminName, o.orderRemark, o.orderStatus "
				+ "FROM RoomOrderVO o LEFT JOIN o.roomVO.roomTypeVO LEFT JOIN "
				+ "o.memberVO LEFT JOIN o.administratorVO WHERE o.orderStatus = " + ":status ORDER BY o.roomOrderId";

		List<RoomOrderVO> list = new ArrayList<>();

		Query<Object[]> query = this.getSession().createQuery(hql, Object[].class);
		query.setParameter("status", status);
		query.setFirstResult(limit); // 分頁效果
		query.setMaxResults(5);

		List<Object[]> results = query.list();

		for (Object[] result : results) {
			RoomOrderVO order = applicationContext.getBean(RoomOrderVO.class);
			RoomVO roomVO = applicationContext.getBean(RoomVO.class);
			RoomTypeVO roomTypeVO = applicationContext.getBean(RoomTypeVO.class);
			MemberVO memberVO = applicationContext.getBean(MemberVO.class);
			AdministratorVO administratorVO = applicationContext.getBean(AdministratorVO.class);

			order.setRoomOrderId((Integer) result[0]);
			order.setRoomTypeName((String) result[1]);
			order.setRoomVO(roomVO);
			order.getRoomVO().setRoomName((String) result[2]);
			order.setOrderStartDate((Date) result[3]);
			order.setOrderEndDate((Date) result[4]);
			order.setFormattedCreateTimestamp((Timestamp) result[5]);
			if ((Timestamp) result[6] != null) {
				order.setFormattedChangeTimestamp((Timestamp) result[6]);
			}
			order.setOrderResident((String) result[7]);
			order.setOrderPrice((Integer) result[8]);
			order.setMemberVO(memberVO);
			order.getMemberVO().setUserName((String) result[9]);
			order.setAdministratorVO(administratorVO);
			order.getAdministratorVO().setAdminName((String) result[10]);
			order.setOrderRemark((String) result[11]);
			order.setOrderStatus((String) result[12]);

			list.add(order);
		}

		return list;
	}

	@Override
	public int updateStatus(RoomOrderVO order) {
		if (order.getRoomOrderId() != null) {
			RoomOrderVO temp = this.getSession().get(RoomOrderVO.class, order.getRoomOrderId());
			if (temp != null) {
				temp.setOrderStatus(order.getOrderStatus());
				return 1;
			}
		}
		return -1;
	}

	@Override
	public List<RoomOrderVO> getByRoomId(Integer roomId) {
		String hql = "select o.orderStartDate, o.orderEndDate, o.orderRemark, o.orderResident, m.userName, a.adminName from RoomOrderVO o left join o.memberVO m left join o.administratorVO a where o.roomVO.roomId = :roomId";
		List<Object[]> result = this.getSession().createQuery(hql).setParameter("roomId", roomId).list();

		List<RoomOrderVO> list = new ArrayList<>();
		for (Object[] row : result) {
			RoomOrderVO order = applicationContext.getBean(RoomOrderVO.class);
			MemberVO memberVO = applicationContext.getBean(MemberVO.class);
			AdministratorVO administratorVO = applicationContext.getBean(AdministratorVO.class);

			order.setOrderStartDate((Date) row[0]);
			order.setOrderEndDate((Date) row[1]);
			order.setOrderRemark((String) row[2]);
			order.setOrderResident((String) row[3]);
			order.setMemberVO(memberVO);
			order.getMemberVO().setUserName((String) row[4]);
			order.setAdministratorVO(administratorVO);
			order.getAdministratorVO().setAdminName((String) row[5]);
			list.add(order);
		}

		return list;
	}

	@Override
	public List<RoomOrderVO> getByUserId(Integer userId) {
		String hql = "SELECT o.roomOrderId, t.roomTypeName, r.roomName, o.orderStartDate, o.orderEndDate, o.orderCreateTime, o.orderChangeTime, o.orderResident, o.orderPrice, o.orderRemark, r.roomId "
				+ "FROM RoomOrderVO o " + "LEFT JOIN o.roomVO r " + "JOIN r.roomTypeVO t "
				+ "WHERE o.memberVO.userId = :userId";
		List<Object[]> result = this.getSession().createQuery(hql).setParameter("userId", userId).list();

		List<RoomOrderVO> list = new ArrayList<>();
		for (Object[] row : result) {
			RoomOrderVO myVO = applicationContext.getBean(RoomOrderVO.class);
			RoomVO roomVO1 = applicationContext.getBean(RoomVO.class);
			myVO.setRoomOrderId((Integer) row[0]);
			myVO.setRoomTypeName((String) row[1]);
			myVO.setRoomVO(roomVO1);
			myVO.getRoomVO().setRoomName((String) row[2]);
			myVO.setOrderStartDate((Date) row[3]);
			myVO.setOrderEndDate((Date) row[4]);
			myVO.setFormattedCreateTimestamp((Timestamp) row[5]);
			if ((Timestamp) row[6] != null) {
				myVO.setFormattedChangeTimestamp((Timestamp) row[6]);
			}
			myVO.setOrderResident((String) row[7]);
			myVO.setOrderPrice((Integer) row[8]);
			myVO.setOrderRemark((String) row[9]);
			myVO.getRoomVO().setRoomId((Integer) row[10]);
			list.add(myVO);
		}
		return list;
	}

	@Override
	public RoomOrderDTO getByOrderId(Integer orderId) {
		RoomOrderVO roomOrderVO = this.getSession().get(RoomOrderVO.class, orderId);
		RoomOrderDTO roomOrderDTO = applicationContext.getBean(RoomOrderDTO.class);
		roomOrderDTO.setRoomOrderId(roomOrderVO.getRoomOrderId());
		roomOrderDTO.setOrderStartDate(roomOrderVO.getOrderStartDate());
		roomOrderDTO.setOrderEndDate(roomOrderVO.getOrderEndDate());
		roomOrderDTO.setOrderPrice(roomOrderVO.getOrderPrice());
		roomOrderDTO.setOrderResident(roomOrderVO.getOrderResident());
		roomOrderDTO.setOrderRemark(roomOrderVO.getOrderRemark());
		roomOrderDTO.setOrderStatus(roomOrderVO.getOrderStatus());

		return roomOrderDTO;
	}

	@Override
	public int checkExistingNumForUpdate(Map<String, String> map) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 欲變更的日期若存在超過一筆訂單、代表有其他既有訂單
		String hql1 = "select count(*) from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate)) and orderStatus != '完成單'";
		Query<Long> query1 = this.getSession().createQuery(hql1, Long.class);
		query1.setParameter("roomId", Integer.parseInt(map.get("roomId")));
		try {
			query1.setParameter("startDate", dateFormat.parse(map.get("startDate")));
			query1.setParameter("endDate", dateFormat.parse(map.get("endDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Long count = query1.uniqueResult();
		if (count > 1) {
			return 0;
		}
		return 1;
	}

	@Override
	public int checkbelong(Map<String, String> map) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 欲變更的日期若存在只有一筆訂單、但是該訂單不屬於這位使用者、代表是屬於其他住客
		String hql2 = "select roomOrderId from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate))";
		Query<Integer> query2 = this.getSession().createQuery(hql2, Integer.class);
		query2.setParameter("roomId", Integer.parseInt(map.get("roomId")));
		try {
			query2.setParameter("startDate", dateFormat.parse(map.get("startDate")));
			query2.setParameter("endDate", dateFormat.parse(map.get("endDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Integer id = query2.uniqueResult();
		if (id != null && id != Integer.parseInt(map.get("orderId"))) {
			return 0;
		}
		return 1;
	}

	@Override
	public int checkExistingNumForInsert(RoomOrderVO order) {
		// 驗證是否晚了一步新增訂單
		String hql1 = "select count(*) from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate)) and orderStatus != '完成單'";
		Query<Long> query1 = this.getSession().createQuery(hql1, Long.class);
		query1.setParameter("roomId", order.getRoomVO().getRoomId());
		query1.setParameter("startDate", order.getOrderStartDate());
		query1.setParameter("endDate", order.getOrderEndDate());

		Long count = query1.uniqueResult();
		if (count > 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public int checkExistingNumForUpdate(RoomOrderVO order) {
		// 驗證是否晚了一步新增訂單
		String hql1 = "select count(*) from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate)) and orderStatus != '完成單'";
		Query<Long> query1 = this.getSession().createQuery(hql1, Long.class);
		query1.setParameter("roomId", order.getRoomVO().getRoomId());
		query1.setParameter("startDate", order.getOrderStartDate());
		query1.setParameter("endDate", order.getOrderEndDate());

		Long count = query1.uniqueResult();
		if (count > 1) {
			return 0;
		}
		return 1;
	}

	@Override
	public int checkbelong(RoomOrderVO order) {
		String hql2 = "select roomOrderId from RoomOrderVO where roomVO.roomId = :roomId and (:startDate between orderStartDate and orderEndDate or :endDate between orderStartDate and orderEndDate or (:startDate < orderStartDate and :endDate > orderEndDate))";
		Query<Integer> query2 = this.getSession().createQuery(hql2, Integer.class);
		query2.setParameter("roomId", order.getRoomVO().getRoomId());
		query2.setParameter("startDate", order.getOrderStartDate());
		query2.setParameter("endDate", order.getOrderEndDate());

		Integer id = query2.uniqueResult();
		if (id != null && id != order.getRoomOrderId()) {
			return 0;
		}
		return 1;
	}

}
