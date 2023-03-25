package com.tibame.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tibame.web.dto.RoomOrderDTO;
import com.tibame.web.service.RoomOrderService;
import com.tibame.web.vo.AdministratorVO;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.RoomOrderVO;

@RestController
@RequestMapping("/admin/room/RoomOrderController")
public class RoomOrderController {
	@Autowired
	private RoomOrderService service;

	@GetMapping("/orderIdSearch")
	public ResponseEntity<RoomOrderDTO> getByOrderId(@RequestParam("orderId") String orderId) {
		if (orderId != null) {
			final RoomOrderDTO roomOrder = service.getByOrderId(Integer.parseInt(orderId));
			if (roomOrder != null) {
				return ResponseEntity.ok(roomOrder);
			}
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/userSearch")
	public ResponseEntity<List<RoomOrderVO>> getOrdersByUser() {
//		public ResponseEntity<List<RoomOrderVO>> getOrdersByUser(HttpSession session){
//		Integer userId = (Integer)session.getAttribute("UserId");
//		屆時登入功能完成要使用
		Integer userId = 1;
		if (userId != null) {
			final List<RoomOrderVO> list = service.getOrdersByUser(userId);
			if (list.size() != 0) {
				return ResponseEntity.ok(list);
			}
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/calendar")
	public ResponseEntity<List<RoomOrderVO>> getOrdersForCalendar(@RequestParam("roomId") String roomId) {
		if (roomId != null) {
			final List<RoomOrderVO> list = service.getOrdersForCalendar(Integer.parseInt(roomId));
			if (list.size() != 0) {
				return ResponseEntity.ok(list);
			}
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<RoomOrderVO>> getAllOrders(@RequestParam("status") String status,
			@RequestParam("limit") String limit) {
		if (status != null || limit != null) {
			final List<RoomOrderVO> list = service.getAllOrders(status, Integer.parseInt(limit));
			if (list.size() != 0) {
				return ResponseEntity.ok(list);
			}
		}
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public Map<String, String> updateRoomOrder(@RequestBody RoomOrderVO roomOrder,
			@RequestParam("action") String action) {
		Map<String, String> result = new HashMap<String, String>();
		String resultStr = "操作失敗";
		if (action.equals("status") && roomOrder != null) {
			resultStr = service.changeStatus(roomOrder);
		} else if (action.equals("edit") && roomOrder != null) {
			resultStr = service.editOrder(roomOrder);
		}

		result.put("message", resultStr);
		return result;
	}

	@DeleteMapping
	public Map<String, String> remove(@RequestBody RoomOrderVO roomOrder) {
		Map<String, String> result = new HashMap<String, String>();
		String resultStr = "操作失敗";
		if (roomOrder != null) {
			resultStr = service.remove(roomOrder);
		}
		result.put("message", resultStr);
		return result;
	}

	@PostMapping
	public Map<String, String> addOrder(@RequestBody RoomOrderVO roomOrder) {
//		public Map<String, String> addOrder(@RequestBody RoomOrderVO roomOrder, HttpSession session){
		Map<String, String> result = new HashMap<String, String>();
		String resultStr = "操作失敗";

		if (roomOrder != null && roomOrder.getOrderResident() == null) {
//			AdministratorVO administratorVO = new AdministratorVO();
//			administratorVO.setAdminId((Integer)session.getAttribute("adminId"));
//			roomOrder.setAdministratorVO(administratorVO);
//			屆時登入功能完成要使用
			AdministratorVO administratorVO = new AdministratorVO();
			roomOrder.setAdministratorVO(administratorVO);
			roomOrder.getAdministratorVO().setAdminId(1);
			resultStr = service.addOrder(roomOrder);
		} else if (roomOrder != null && roomOrder.getOrderResident() != null) {
//			MemberVO memberVO = new MemberVO();
//			roomOrder.setUserId((Integer)session.getAttribute("userId"));
//			roomOrder.setMemberVO(memberVO);
//			屆時登入功能完成要使用
			MemberVO memberVO = new MemberVO();
			roomOrder.setMemberVO(memberVO);
			roomOrder.getMemberVO().setUserId(1);
			resultStr = service.addOrder(roomOrder);
		}
		result.put("message", resultStr);
		return result;
	}

}
