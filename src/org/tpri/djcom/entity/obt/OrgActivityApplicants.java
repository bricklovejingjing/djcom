package org.tpri.djcom.entity.obt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * 
 * @Description: 组织生活通知实体
 * @author zhaozijing
 * @date 2015年7月1日 上午11:45:18
 */
@Entity
@Table(name="OBT_ORG_ACTIVITY_APPLICANTS")
public class OrgActivityApplicants extends ObjectBase {
	protected int objectType = ObjectType.OBT_ORG_ACTIVITY_APPLICANTS;
	
	private static final long serialVersionUID = 1L;
	
	public static final int status_0 = 0;	//未报名
	public static final int status_1 = 1;	//已报名	
	public static final int status_2 = 2;	//忽略
	
	protected String activityId;
	protected String userId;
	protected String userName;
	protected String ccpartyId;
	protected String ccpartyName;
	protected int status;
	protected Date applyTime;
	protected Date createTime;
	
	@Id  
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ACTIVITYID")
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="CCPARTYID")
	public String getCcpartyId() {
		return ccpartyId;
	}
	public void setCcpartyId(String ccpartyId) {
		this.ccpartyId = ccpartyId;
	}
	@Column(name="CCPARTYNAME")
	public String getCcpartyName() {
		return ccpartyName;
	}
	public void setCcpartyName(String ccpartyName) {
		this.ccpartyName = ccpartyName;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="APPLYTIME")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
