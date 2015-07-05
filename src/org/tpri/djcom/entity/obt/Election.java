package org.tpri.djcom.entity.obt;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 换届选举和改选bean
 * @author 易文俊
 * @since 2015-07-01
 */
@Entity
@Table(name = "OBT_ELECTION")
public class Election extends ObjectBase {
	private static final long serialVersionUID = -820143208534988235L;
	/** */
	protected int objectType = ObjectType.OBT_ELECTION;
	
	public static final int TYPE_GENERALELECTION = 0; // 换届
	public static final int TYPE_REELECTION = 1; // 改选
	
	public static final int STATUS_UNSTART = 0; // 未启动
	public static final int STATUS_STARTED = 1; // 进行中
	public static final int STATUS_CLOSED = 2; 	// 已结束

	protected int type;
	protected String ccpartyId;
	protected int sequence;
	protected int selectMode;
	protected Timestamp startTime;
	protected Timestamp endTime;
	protected int participants;
	protected int attendance;
	protected int absence;
	protected int status;
	protected String processInstanceId;
	protected String createUserId;
	protected String createUserName;
	protected Timestamp createTime;

	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "STARTTIME")
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	@Column(name = "ENDTIME")
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name = "CREATEUSERID")
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name = "CREATEUSERNAME")
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Column(name = "CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name = "TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name = "CCPARTYID")
	public String getCcpartyId() {
		return ccpartyId;
	}
	public void setCcpartyId(String ccpartyId) {
		this.ccpartyId = ccpartyId;
	}
	@Column(name = "SEQUENCE")
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	@Column(name = "SELECTMODE")
	public int getSelectMode() {
		return selectMode;
	}
	public void setSelectMode(int selectMode) {
		this.selectMode = selectMode;
	}
	@Column(name = "PARTICIPANTS")
	public int getParticipants() {
		return participants;
	}
	public void setParticipants(int participants) {
		this.participants = participants;
	}
	@Column(name = "ATTENDANCE")
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	@Column(name = "ABSENCE")
	public int getAbsence() {
		return absence;
	}
	public void setAbsence(int absence) {
		this.absence = absence;
	}
	@Column(name = "PROCESSINSTANCEID")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}
