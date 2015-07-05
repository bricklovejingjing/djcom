package org.tpri.djcom.manager.pub;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.pub.TopicAnswer;
import org.tpri.djcom.entity.pub.TopicOption;
import org.tpri.djcom.entity.zbsc.Article;

/**
 * @description 问卷测评试题答案管理类
 * @author zhaozijing
 * @since 2015-06-26
 */

@Repository("TopicAnswerManager")
public class TopicAnswerManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_TOPIC_ANSWER, TopicAnswer.class);
    }
    
    /**
     * 根据试卷ID删除试题答案
     * @param topicId
     * @return
     */
    public boolean deleteTopicAnswerByTopicId(String topicId){
    	DaoPara daoPara = new DaoPara();
    	Class clazz = ObjectRegister.getClassByClassType(ObjectType.PUB_TOPIC_ANSWER);
    	daoPara.setClazz(clazz);
    	daoPara.addCondition(Condition.EQUAL("topicId", topicId));
    	dao.delete(daoPara);
        return true;
    }
    
    /**
     * 根据试题ID获取答案集合
     * @param topicId
     * @return
     */
    public List<TopicAnswer> getAnswerByTopicId(String topicId){
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(TopicAnswer.class);
		daoPara.addCondition(Condition.EQUAL("topicId", topicId));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 获取标准答案
     * @return
     */
    public TopicAnswer getAnswerById(String id)  {
    	TopicAnswer answer=(TopicAnswer)super.load(id, ObjectType.PUB_TOPIC_ANSWER);
    	return answer;
    }

}