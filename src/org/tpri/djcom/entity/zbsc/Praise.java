
package org.tpri.djcom.entity.zbsc;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 点赞和收藏bean
 * @author 易文俊
 * @since 2015-05-12
 */
@Entity
@Table(name="ZBSC_PRAISE")
public class Praise extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_PRAISE;
	
	public static int TYPE_PRAISE=0;
	public static int TYPE_FAVORITE=0;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected int type;
	protected String articleId;
	protected String userId;
	protected String userName;
	protected Timestamp createTime;
	protected Timestamp updateTime;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name="CREATETIME")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Column(name="UPDATETIME")
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="ARTICLEID")
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
