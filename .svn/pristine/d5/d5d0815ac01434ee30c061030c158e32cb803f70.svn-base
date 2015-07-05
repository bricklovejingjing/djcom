package org.tpri.djcom.entity.pub;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 待办事项bean
 * @author yiwenjun
 * @since 2015-06-17
 */

@Entity
@Table(name = "PUB_REMINDER")
public class Reminder extends ObjectBase {

	private static final long serialVersionUID = 1L;

	protected int objectType = ObjectType.PUB_REMINDER;

	public static final int TYPE_MATERIAL_REPORT = 0; 		// 材料报送
	public static final int TYPE_INFO_REPORT = 1; 			// 党务信息报送
	public static final int TYPE_WORK_APPLICATION = 2; 		// 工作报名
	public static final int TYPE_ASSESSMENT = 3; 			// 问卷测评
	public static final int TYPE_WORK_PLAN = 4; 			// 工作计划
	public static final int TYPE_ACTIVITY = 5; 			// 组织生活

	public static final int STATUS_UNDISPOSE = 0; // 未处理
	public static final int STATUS_DISPOSED = 1; // 已处理
	public static final int STATUS_IGNORE = 2; // 忽略

	protected String businessKey; 	// 业务ID
	protected int type; 			// 待办类型
	protected String content; // 待办详情
	protected int status; // 状态
	protected String url; // 待办链接
	protected Timestamp expired; // 过期时间
	protected String source; // 待办来源
	protected String fromUserId; 	// 发送用户ID
	protected String fromUserName; 	// 发送用户名称
	protected String userId; // 待办处理人ID
	protected String userName; // 待办处理人名称
	protected Timestamp disposeTime; // 处理时间
	protected Timestamp createTime; // 创建时间

	@Id
	@Column(name = "ID")
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
	@Column(name = "BUSINESSKEY")
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	@Column(name = "TYPE")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "EXPIRED")
	public Timestamp getExpired() {
		return expired;
	}

	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}
	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "FROMUSERID")
	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	@Column(name = "FROMUSERNAME")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	@Column(name = "USERID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "DISPOSETIME")
	public Timestamp getDisposeTime() {
		return disposeTime;
	}

	public void setDisposeTime(Timestamp disposeTime) {
		this.disposeTime = disposeTime;
	}

}
