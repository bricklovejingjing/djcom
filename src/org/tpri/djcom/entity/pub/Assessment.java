package org.tpri.djcom.entity.pub;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 问卷测评bean
 * @author zhaozijing
 * @since 2015-06-16
 */

@Entity
@Table(name="PUB_ASSESSMENT")
public class Assessment extends ObjectBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9018054539434507341L;

	protected int objectType = ObjectType.PUB_ASSESSMENT;
	
	public static final int ASSESSMENT_TYPE_0 = 0;	//TYPE测评
	public static final int ASSESSMENT_TYPE_1 = 1;	//TYPE投票
	
	public static final int ASSESSMENT_STATUS_0 = 0;	//起草
	public static final int ASSESSMENT_STATUS_1 = 1;	//发布
	public static final int ASSESSMENT_STATUS_2 = 2;	//关闭
	
	protected List<AssessmentTopic> topics ;	//试题集合
	
	protected String id;						//主键
	protected String name = "";			//测评标题
	protected String description = "";		//测评描述
	protected String content = "";			//测评内容
	protected String categary = "";		//测评分类
	protected int type = 0;				//测评类别 0测评 1投票
	protected int status = 0;				//测评状态 0起草 1发布 2关闭
	protected Timestamp expiryDate;		//测评截止日期
	protected String createUser = "";		//创建人
	protected Timestamp createTime;		//创建时间
	
	@Id
	@Column(name="ID")
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
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="CATEGARY")
	public String getCategary() {
		return categary;
	}
	public void setCategary(String categary) {
		this.categary = categary;
	}
	@Column(name="TYPE")
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="EXPIRYDATE")
	public Timestamp getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
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
	@Transient
	public List<AssessmentTopic> getTopics() {
		return topics;
	}
	public void setTopics(List<AssessmentTopic> topics) {
		this.topics = topics;
	}
	
	
}
