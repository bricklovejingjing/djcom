package org.tpri.djcom.manager.pub;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.pub.Assessresult;
import org.tpri.djcom.entity.pub.TopicAnswer;
/**
 * @description 问卷测评结果对象管理类
 * @author zhaozijing
 * @since 2015-06-26
 */

@Repository("AssessresultManager")
public class AssessresultManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_ASSESSRESULT, Assessresult.class);
    }
    
	/**
	 * 获取我的试题答案
	 * @param userId
	 * @param assessmentId
	 * @return
	 */
    public List<Assessresult> getAssessresultByMy(String userId,String assessmentId,String topicId){
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Assessresult.class);
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		daoPara.addCondition(Condition.EQUAL("assessmentId",assessmentId));
		daoPara.addCondition(Condition.EQUAL("topicId",topicId));
        List list = dao.loadList(daoPara);
        return list;
    }

}
