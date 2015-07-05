package org.tpri.djcom.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 问卷测评试题选项bean
 * @author zhaozijing
 * @since 2015-06-26
 */
@Entity
@Table(name="PUB_ASSESSMENT_TOPIC_OPTION")
public class TopicOption extends ObjectBase{
	private static final long serialVersionUID = 1L;
	protected int objectType = ObjectType.PUB_TOPIC_OPTION;
	
	protected String id;
	protected String topicId;	//试题ID
	protected String content;	//选项内容
	protected String seq;	//选项序号 	ABCD
	
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
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="SEQ")
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
