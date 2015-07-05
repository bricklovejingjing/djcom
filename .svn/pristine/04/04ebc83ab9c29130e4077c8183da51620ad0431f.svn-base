package org.tpri.djcom.entity.pub;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 问卷测评对象bean
 * @author zhaozijing
 * @since 2015-06-22
 */

@Entity
@Table(name="PUB_ASSESSTARGET")
public class Assesstarget extends ObjectBase{
	private static final long serialVersionUID = 9018054539434507341L;

	protected int objectType = ObjectType.PUB_ASSESSTARGET;
	
	public static final String ASSESSTARGET_STATUS_0 = "0";	//状态 正常
	public static final String ASSESSTARGET_STATUS_1 = "1";	//状态 完成
	public static final String ASSESSTARGET_STATUS_2 = "2";	//状态 忽略
	
	protected String id;						//主键
	protected String assessmentId;
	protected String userId;
	protected String userName;
	protected Timestamp submitTime;
	protected String result;
	protected String status;
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="ASSESSMENTID")
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
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
	
	@Column(name="SUBMITTIME")
	public Timestamp getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}
	
	@Column(name="RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
