package org.tpri.djcom.entity.tsk;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 材料报送bean
 * @author 易文俊
 * @since 2015-06-10
 */
@Entity
@Table(name = "TSK_MATERIAL_REPORT")
public class MaterialReport extends ObjectBase {
	/** */
	private static final long serialVersionUID = 2801515319652383529L;
	protected int objectType = ObjectType.TSK_MATERIAL_REPORT;
	
	//材料报送填报表单节点ID
	public static String NODE_FILLFORM="fillForm";
	//材料报送流程审核节点ID
	public static String NODE_AUDIT="audit";
	//材料报送上报节点ID
	public static String NODE_REPORT="report";

	public static int STATUS_INIT = 0;
	public static int STATUS_ONGOING = 1;
	public static int STATUS_COMPLETE = 2;

	protected String id;
	protected String name;
	protected String content;
	protected String toDepartment;
	protected String processInstanceId;
	protected String createUserId;
	protected String createUserName;
	protected Timestamp createTime;
	protected Timestamp endTime;
	protected int status;

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

	@Column(name = "TODEPARTMENT")
	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
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

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "PROCESSINSTANCEID")
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}
