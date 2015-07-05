package org.tpri.djcom.entity.pub;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 通知公告bean
 * @author zhaozijing
 * @since 2015-06-16
 */

@Entity
@Table(name="PUB_NOTICE")
public class Notice extends ObjectBase{
	
	private static final long serialVersionUID = 1L;

	protected int objectType = ObjectType.PUB_NOTICE;
	
	public static final int NOTICE_LEVEL_0 = 0;	//本级可见
	public static final int NOTICE_LEVEL_1 = 1;	//本级和下级可见
	
	public static final int NOTICE_STATUS_0 = 0;	//起草
	public static final int NOTICE_STATUS_1 = 1;	//已发送
	public static final int NOTICE_STATUS_2 = 2;	//挂起
	
	public static final String NOTICE_SENDOBJECT_PUB = "pub";			//公开
	public static final String NOTICE_SENDOBJECT_ORG = "org";			//组织可见
	public static final String NOTICE_SENDOBJECT_PERSON = "person";	//人员可见
	
	protected String id;				//主键
	protected String type="";			//通知类型
	protected String name="";			//通知标题
	protected String content="";		//通知内容
	protected int hits = 0;			//点击数
	protected int level = 0;			//可见级别  0本级，1本级和下级
	protected int status = 0;			//状态 0有效，1挂起
	protected String createUser = "";	//创建者
	protected Timestamp createTime;	//创建时间
	protected String updateUser = "";	//更新者
	protected Timestamp updateTime;	//更新时间
	protected String sendObject = "pub";	//通知对象 pub公开、org组织、people人员
	
	protected String isRead = "false";	//是否已读
	
	@Id
	@Column(name="ID")
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
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="HITS")
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Column(name="LEVEL")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="CREATEUSER")
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@Column(name="CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name="UPDATEUSER")
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@Column(name="UPDATETIME")
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="SENDOBJECT")
	public String getSendObject() {
		return sendObject;
	}
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}
	@Transient
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
		
	

}
