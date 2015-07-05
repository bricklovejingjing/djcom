package org.tpri.djcom.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 通知公告发送实体bean
 * @author zhaozijing
 * @since 2015-06-23
 */

@Entity
@Table(name="PUB_NOTICE_RECORD")
public class NoticeRecord extends ObjectBase{
	
	private static final long serialVersionUID = 1L;

	protected int objectType = ObjectType.PUB_NOTICE_RECORD;
	
	protected String id;				//主键
	protected String noticeId;			//通知ID
	protected String sendPartyId;		//通知发送组织
	protected String sendUamUserId;	//通知发送人员
	protected String sendNoticeType;	//通知发送类别 pub公共、org组织、person人员
	protected String readStatus;		//通知阅读状态 0未读、1已读
	protected String reader;			//通知阅读者  类型为人员的不需要
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="NOTICEID")
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	
	@Column(name="SENDPARTYID")
	public String getSendPartyId() {
		return sendPartyId;
	}
	public void setSendPartyId(String sendPartyId) {
		this.sendPartyId = sendPartyId;
	}
	
	@Column(name="SENDUAMUSERID")
	public String getSendUamUserId() {
		return sendUamUserId;
	}
	public void setSendUamUserId(String sendUamUserId) {
		this.sendUamUserId = sendUamUserId;
	}
	
	@Column(name="SENDNOTICETYPE")
	public String getSendNoticeType() {
		return sendNoticeType;
	}
	public void setSendNoticeType(String sendNoticeType) {
		this.sendNoticeType = sendNoticeType;
	}
	
	@Column(name="READSTATUS")
	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	
	@Column(name="READER")
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}

}
