
package org.tpri.djcom.entity.zbsc;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 文章文件列表bean
 * @author 易文俊
 * @since 2015-05-04
 */
@Entity
@Table(name="ZBSC_UPLOADFILE")
public class UploadFile extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_UPLOADFILE;
	
	/**文件类型-附件*/
	public static int FILETYPE_ATTACHMENT=0;
	/**文件类型-图片*/
	public static int FILETYPE_IMAGE=1;
	/**文件类型-视频*/
	public static int FILETYPE_VIDEO=2;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String articleId;
	protected String name;
	protected int fileType;
	protected long fileSize;
	protected String fileDataId;
	protected String postfix;
	protected int storageType;
	protected String filePath;
	protected int status;
	protected Timestamp createTime;

	@Id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ARTICLEID")
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="FILETYPE")
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	@Column(name="FILESIZE")
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="POSTFIX")
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	@Column(name="FILEDATAID")
	public String getFileDataId() {
		return fileDataId;
	}
	public void setFileDataId(String fileDataId) {
		this.fileDataId = fileDataId;
	}
	
	@Column(name="STORAGETYPE")
	public int getStorageType() {
		return storageType;
	}

	public void setStorageType(int storageType) {
		this.storageType = storageType;
	}
	@Column(name="FILEPATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
