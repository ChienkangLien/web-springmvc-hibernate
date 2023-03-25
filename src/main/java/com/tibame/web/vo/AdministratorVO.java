package com.tibame.web.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ADMINISTRATOR")
@Component
@Scope(scopeName = "prototype")
public class AdministratorVO {
	@Id
	@Column(name = "ADMIN_ID")
	private Integer adminId;
	@Column(name = "ADMIN_NAME")
	private String adminName;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "AdministratorVO [adminId=" + adminId + ", adminName=" + adminName + "]";
	}
}
