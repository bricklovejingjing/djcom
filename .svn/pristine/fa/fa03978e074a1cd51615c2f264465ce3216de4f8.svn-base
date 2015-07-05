
package org.tpri.djcom.entity.zbsc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 资源基本信息bean
 * @author 易文俊
 * @since 2015-04-02
 */
@Entity
@Table(name="ZBSC_INFORMATION")
public class Information extends ObjectBase {
	
	protected int objectType = ObjectType.COM_BASEINFO;
	
	public static String TYPEID_NOTICE="notice";
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected String content;
	protected String type;
	protected String ccpartyId;
	protected int hits;
	protected int status;
	protected String createUser;
	protected Timestamp createTime;
	protected String updateUser;
	protected Timestamp updateTime;

	public List<UploadFile> attachments = new ArrayList<UploadFile>();
	protected String attachment1Name ="请选择";
	protected String attachment2Name ="请选择";
	protected String attachment3Name ="请选择";
	protected String attachment4Name ="请选择";
	protected String attachment5Name ="请选择";
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="HITS")
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
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
	@Column(name="CCPARTYID")
	public String getCcpartyId() {
		return ccpartyId;
	}
	public void setCcpartyId(String ccpartyId) {
		this.ccpartyId = ccpartyId;
	}
	@Transient
	public List<UploadFile> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<UploadFile> attachments) {
		this.attachments = attachments;
		for (int i=0;i<attachments.size();i++) {
			switch (i) {
			case 0:
				this.setAttachment1Name(attachments.get(i).getName());
				break;
			case 1:
				this.setAttachment2Name(attachments.get(i).getName());
				break;
			case 2:
				this.setAttachment3Name(attachments.get(i).getName());
				break;
			case 3:
				this.setAttachment4Name(attachments.get(i).getName());
				break;
			case 4:
				this.setAttachment5Name(attachments.get(i).getName());
				break;
			default:
				break;
			}
		}
	}
	@Transient
	public String getAttachment1Name() {
		return attachment1Name;
	}
	public void setAttachment1Name(String attachment1Name) {
		this.attachment1Name = attachment1Name;
	}
	@Transient
	public String getAttachment2Name() {
		return attachment2Name;
	}
	public void setAttachment2Name(String attachment2Name) {
		this.attachment2Name = attachment2Name;
	}
	@Transient
	public String getAttachment3Name() {
		return attachment3Name;
	}
	public void setAttachment3Name(String attachment3Name) {
		this.attachment3Name = attachment3Name;
	}
	@Transient
	public String getAttachment4Name() {
		return attachment4Name;
	}
	public void setAttachment4Name(String attachment4Name) {
		this.attachment4Name = attachment4Name;
	}
	@Transient
	public String getAttachment5Name() {
		return attachment5Name;
	}
	public void setAttachment5Name(String attachment5Name) {
		this.attachment5Name = attachment5Name;
	}
	
	
	
}
