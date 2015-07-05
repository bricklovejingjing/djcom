
package org.tpri.djcom.entity.zbsc;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 上传文件数据bean
 * @author 易文俊
 * @since 2015-05-04
 */
@Entity
@Table(name="ZBSC_UPLOADFILEDATA")
public class UploadFileData extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_UPLOADFILEDATA;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected String postfix;
	protected long fileSize;
	protected byte[] fileData;
	protected int status;
	protected Timestamp createTime;

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
	@Column(name="POSTFIX")
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
	@Column(name="FILESIZE")
	public long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="FILEDATA")
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
