package org.tpri.djcom.entity.pub;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;

/**
 * @description 问卷测评试题bean
 * @author zhaozijing
 * @since 2015-06-26
 */
@Entity
@Table(name="PUB_ASSESSMENT_TOPIC")
public class AssessmentTopic extends ObjectBase{
	private static final long serialVersionUID = 1L;
	
	protected int objectType = ObjectType.PUB_ASSESSMENT_TOPIC;
	
	public static final String TOPIC_TYPE_1 = "1";	//试卷类型 单选
	public static final String TOPIC_TYPE_2 = "2";	//试卷类型 多选
	public static final String TOPIC_TYPE_3 = "3";	//试卷类型 简答
	public static final String TOPIC_ISMUST_0 = "0";	//必做
	public static final String TOPIC_ISMUST_1 = "1";	//不必做
	
	protected List<TopicOption> options;	//该试题的选项
	protected List<TopicAnswer> answers;	//该试题的标准答案
	
	protected List<Assessresult> myAssessresult;	//参与调查问卷者给出的答案
	
	protected String id;
	protected String assessmentId;	//试卷ID
	protected String title;		//试题标题
	protected String type;			//试题类型 1单选、2多选、3简答
	protected String isMust;		//是否必做 0必做、1不必做
	protected String seq;			//试题序号
	
	@Transient
	public List<TopicOption> getOptions() {
		return options;
	}
	public void setOptions(List<TopicOption> options) {
		this.options = options;
	}
	@Transient
	public List<TopicAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<TopicAnswer> answers) {
		this.answers = answers;
	}
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="ISMUST")
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}
	@Column(name="ASSESSMENTID")
	public String getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	@Column(name="SEQ")
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Transient
	public List<Assessresult> getMyAssessresult() {
		return myAssessresult;
	}
	public void setMyAssessresult(List<Assessresult> myAssessresult) {
		this.myAssessresult = myAssessresult;
	}
	
}
