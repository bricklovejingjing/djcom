package org.tpri.djcom.entity.pub;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 消息提醒bean
 * @author yiwenjun
 * @since 2015-06-18
 */

@Entity
@Table(name = "PUB_MESSAGE")
public class Message extends ObjectBase {

	private static final long serialVersionUID = 1L;

	protected int objectType = ObjectType.PUB_MESSAGE;

	public static final int TYPE_SYSTEM = 0; 				// 系统消息
	public static final int TYPE_BIRTHDAY = 1; 				// 生日提醒
	public static final int TYPE_PARTYFEE = 2; 				// 党费提醒
	public static final int TYPE_OTHER = 3; 				// 其他

	public static final int STATUS_UNDISPOSE = 0; // 未处理
	public static final int STATUS_DISPOSED = 1; // 已处理
	public static final int STATUS_IGNORE = 2; // 忽略

	protected String id; 			// 主键
	protected String name; 			// 消息名称
	protected int type; 			// 消息类型
	protected String content; 		// 消息详情
	protected int status; 			// 状态
	protected Timestamp expired; 	// 过期时间
	protected String source; 		// 消息来源
	protected String fromUserId; 	// 发送用户ID
	protected String fromUserName; 	// 发送用户名称
	protected String userId; 		// 提醒用户ID
	protected String userName; 		// 提醒用户名称
	protected Timestamp disposeTime;// 读取时间
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

}
