package org.tpri.djcom.entity.sch;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 工作报名bean
 * @author 易文俊
 * @since 2015-06-16
 */
@Entity
@Table(name = "SCH_WORK_APPLICATION")
public class WorkApplication extends ObjectBase {
	private static final long serialVersionUID = -820143208534988235L;
	/** */
	protected int objectType = ObjectType.SCH_WORK_APPLICATION;
	
	public static final int STATUS_UNSTART = 0; // 未启动
	public static final int STATUS_STARTED = 1; // 已启动
	public static final int STATUS_CLOSED = 2; 	// 已关闭

	protected String content;
	protected Timestamp startTime;
	protected Timestamp endTime;
	protected String source;
	protected int status;
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
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
	
}