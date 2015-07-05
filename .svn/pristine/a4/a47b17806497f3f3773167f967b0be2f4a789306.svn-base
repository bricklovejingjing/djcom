package org.tpri.djcom.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 问卷测评试题答案bean
 * @author zhaozijing
 * @since 2015-06-26
 */
@Entity
@Table(name="PUB_ASSESSMENT_TOPIC_ANSWER")
public class TopicAnswer extends ObjectBase{
	private static final long serialVersionUID = 1L;
	protected int objectType = ObjectType.PUB_TOPIC_ANSWER;
	
	protected String id;		//主键
	protected String topicId;	//试题ID
	protected String answer;	//答案
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="TOPICID")
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	@Column(name="ANSWER")
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
