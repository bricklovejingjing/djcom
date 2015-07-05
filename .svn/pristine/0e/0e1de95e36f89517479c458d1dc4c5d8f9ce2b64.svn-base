package org.tpri.djcom.entity.uam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 用户角色bean
 * @author zhaozijing
 * @since 2015-06-11
 */

@Entity
@Table(name="UAM_USERROLE")
public class UserRole extends ObjectBase{
	private static final long serialVersionUID = 1L;
	protected int objectType = ObjectType.UAM_USERROLE;
	
	protected String id;
	protected String userid;
	protected String roleid;
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="USERID")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Column(name="ROLEID")
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	

}
