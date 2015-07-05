package org.tpri.djcom.manager.pub;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.pub.AssessmentTopic;
import org.tpri.djcom.entity.pub.NoticeRecord;

/**
 * @description 问卷测评试题管理类
 * @author zhaozijing
 * @since 2015-06-26
 */

@Repository("AssessmentTopicManager")
public class AssessmentTopicManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_ASSESSMENT_TOPIC, AssessmentTopic.class);
    }

    /**
     * 根据试卷ID删除试题
     * @param assessmentId
     * @return
     */
    public boolean deleteTopicByAssessmentId(String assessmentId){
    	DaoPara daoPara = new DaoPara();
    	Class clazz = ObjectRegister.getClassByClassType(ObjectType.PUB_ASSESSMENT_TOPIC);
    	daoPara.setClazz(clazz);
    	daoPara.addCondition(Condition.EQUAL("assessmentId", assessmentId));
    	dao.delete(daoPara);
        return true;
    }
    
    /**
     * 根据ID删除试题
     * @param id
     * @return
     */
    public boolean deleteTopicById(String id){
    	DaoPara daoPara = new DaoPara();
    	Class clazz = ObjectRegister.getClassByClassType(ObjectType.PUB_ASSESSMENT_TOPIC);
    	daoPara.setClazz(clazz);
    	daoPara.addCondition(Condition.EQUAL("id", id));
    	dao.delete(daoPara);
        return true;
    }
    
    /**
     * 根据试卷ID获取试题集合
     * @param assessmentId
     * @return
     */
    public List<AssessmentTopic> getTopicByAssessmentId(String assessmentId){
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(AssessmentTopic.class);
		daoPara.addCondition(Condition.EQUAL("assessmentId", assessmentId));
		daoPara.addOrder(Order.asc("seq"));
        List list = dao.loadList(daoPara);
        return list;
    }
}
