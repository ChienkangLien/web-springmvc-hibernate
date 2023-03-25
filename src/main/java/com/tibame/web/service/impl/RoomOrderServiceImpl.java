package com.tibame.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tibame.web.dao.RoomOrderDAO;
import com.tibame.web.dto.RoomOrderDTO;
import com.tibame.web.service.RoomOrderService;
import com.tibame.web.vo.RoomOrderVO;

@Service
@Transactional
public class RoomOrderServiceImpl implements RoomOrderService {
	@Autowired
	private RoomOrderDAO orderDao;

	@Override
	public List<RoomOrderVO> getAllOrders(String status, Integer limit) {
		if (status.equals("new") && limit >= 0) {
			return orderDao.getAll("客訂單", limit);
		} else if (status.equals("history") && limit >= 0) {
			return orderDao.getAll("完成單", limit);
		} else if (status.equals("maintain") && limit >= 0) {
			return orderDao.getAll("關房單", limit);
		}
		return null;
	}

	@Override
	public String changeStatus(RoomOrderVO roomOrder) {
		if (roomOrder != null && roomOrder.getRoomOrderId() != null) {
			return orderDao.updateStatus(roomOrder) > 0 ? "狀態更新成功" : "狀態更新失敗";
		}
		return "狀態更新失敗";
	}

	@Override
	public String remove(RoomOrderVO roomOrder) {
		if (roomOrder != null && roomOrder.getRoomOrderId() != null) {
			return orderDao.delete(roomOrder) > 0 ? "刪除成功" : "刪除失敗";
		}
		return "刪除失敗";
	}

	@Override
	public List<RoomOrderVO> getOrdersForCalendar(Integer roomId) {
		if (roomId != null) {
			return orderDao.getByRoomId(roomId);
		}
		return null;
	}

	@Override
	public String addOrder(RoomOrderVO roomOrder) {
		if (orderDao.checkExistingNumForInsert(roomOrder) != 1) {
			return "晚了一步，此時段此房間已被其他房客預定";
		} else {
			return orderDao.insert(roomOrder) > 0 ? "新增成功" : "新增失敗";
		}
	}

	@Override
	public List<RoomOrderVO> getOrdersByUser(Integer userId) {
		if (userId != null) {
			return orderDao.getByUserId(userId);
		}
		return null;
	}

	@Override
	public RoomOrderDTO getByOrderId(Integer orderId) {
		if (orderId != null) {
			return orderDao.getByOrderId(orderId);
		}
		return null;
	}

	@Override
	public String editOrder(RoomOrderVO roomOrder) {
		if (roomOrder != null && roomOrder.getRoomOrderId() != null) {
			if (orderDao.checkExistingNumForUpdate(roomOrder) == 1) {
				if (orderDao.checkbelong(roomOrder) == 1) {
					return orderDao.update(roomOrder) > 0 ? "訂單更新成功" : "訂單更新失敗";
				}
			}
			return "晚了一步，此時段此房間已被其他房客預定";
		}
		return "訂單更新失敗";
	}

}
