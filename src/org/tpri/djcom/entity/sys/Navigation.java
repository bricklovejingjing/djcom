package org.tpri.djcom.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 导航配置bean
 * @author 易文俊
 * @since 2015-04-08
 */

@Entity
@Table(name="SYS_NAVIGATION")
public class Navigation  extends ObjectBase{
	
	protected int objectType = ObjectType.SYS_NAVIGATION;
	
	public static String TYPE_MGR="mgr";
	
	protected String id;
	protected String type;
	protected String code;
	protected String name;
	protected String func;
	protected String parentCode;
	protected int orderNo;
	protected String privilegeId;


	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="FUNC")
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	@Column(name="PARENTCODE")
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	@Column(name="ORDERNO")
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="PRIVILEGEID")
	public String getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	
	
}
