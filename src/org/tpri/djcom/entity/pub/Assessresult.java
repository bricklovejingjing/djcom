package org.tpri.djcom.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 问卷测评结果bean
 * @author zhaozijing
 * @since 2015-06-26
 */

@Entity
@Table(name="PUB_ASSESSRESULT")
public class Assessresult extends ObjectBase{
	private static final long serialVersionUID = 9018054539434507341L;

	protected int objectType = ObjectType.PUB_ASSESSRESULT;
	
	protected String id;
	protected String userId;		//测评人
	protected String assessmentId;	//问卷ID
	protected String topicId;		//试题ID
	protected String result;		//测评结果
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="ASSESSMENTID")
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	@Column(name="TOPICID")
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	@Column(name="RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	

}
