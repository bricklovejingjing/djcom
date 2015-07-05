
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
 * @description 文章内容bean
 * @author 易文俊
 * @since 2015-05-02
 */
@Entity
@Table(name="ZBSC_ARTICLE")
public class Article extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_ARTICLE;
	
	public static int RECOMMEND_NO=0;
	public static int RECOMMEND_YES=1;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String name;
	protected String content;
	protected int hits;
	protected int reply;
	protected int praiseCount;
	protected int isRecommend;
	protected int status;
	protected int sourceType;
	protected String sourceUserId;
	protected String sourceUserName;
	protected String createUserName;
	protected String createUserId;
	protected Timestamp createTime;
	protected String updateUser;
	protected Timestamp updateTime;
	
	public List<UploadFile> images=new ArrayList<UploadFile>();
	public List<UploadFile> videos=new ArrayList<UploadFile>();
	public List<UploadFile> attachments=new ArrayList<UploadFile>();
	public List<ArticleCategory> articleCategoris=new ArrayList<ArticleCategory>();

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
	@Column(name="SOURCETYPE")
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	@Column(name="SOURCEUSERID")
	public String getSourceUserId() {
		return sourceUserId;
	}
	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	@Column(name="SOURCEUSERNAME")
	public String getSourceUserName() {
		return sourceUserName;
	}
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
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
	@Transient
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	@Transient
	public int getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}
	@Column(name="ISRECOMMEND")
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Transient
	public List<UploadFile> getImages() {
		return images;
	}
	public void setImages(List<UploadFile> images) {
		this.images = images;
	}
	@Transient
	public List<UploadFile> getVideos() {
		return videos;
	}
	public void setVideos(List<UploadFile> videos) {
		this.videos = videos;
	}
	@Transient
	public List<UploadFile> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<UploadFile> attachments) {
		this.attachments = attachments;
	}
	@Transient
	public List<ArticleCategory> getArticleCategoris() {
		return articleCategoris;
	}
	public void setArticleCategoris(List<ArticleCategory> articleCategoris) {
		this.articleCategoris = articleCategoris;
	}
	@Column(name="CREATEUSERName")
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Column(name="CREATEUSERID")
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
}
