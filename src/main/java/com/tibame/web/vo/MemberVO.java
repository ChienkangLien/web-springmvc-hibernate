package com.tibame.web.vo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "MEMBER")
@Component
@Scope(scopeName = "prototype")
public class MemberVO {
	@Id
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_NAME")
	private String userName;
	
	@OneToMany(
			mappedBy = "memberVO"
	)
	private Set<RoomOrderVO> roomOrderVOs;
	
	public Set<RoomOrderVO> getRoomOrderVOs() {
		return roomOrderVOs;
	}
	public void setRoomOrderVOs(Set<RoomOrderVO> roomOrderVOs) {
		this.roomOrderVOs = roomOrderVOs;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userName=" + userName + "]";
	}
}
