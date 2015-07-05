package org.tpri.djcom.manager.pub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.pub.Assessment;
import org.tpri.djcom.entity.pub.Assesstarget;
/**
 * @description 问卷测评管理类
 * @author zhaozijing
 * @since 2015-06-16
 */

@Repository("AssessmentManager")
public class AssessmentManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_ASSESSMENT, Assessment.class);
    }

    /**
     * 获取问卷测评列表
     * @return
     */
    public List<Assessment> getAssessmentList()  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Assessment.class);
		//daoPara.addOrder(Order.desc("createTime"));
		List list = new ArrayList<Assessment>();
		try{
			list = dao.loadList(daoPara);
		}catch(Exception e){
			e.printStackTrace();
		}
        return list;
    }
    
    /**
	 * 删除某问卷测评下的所有通知人员
	 * 
	 * @return
	 */
	public boolean deleteApplicantsByAssessmentApplicationId(String assessmentApplicationId) {
		String hql = "delete from pub_assesstarget where assessmentid=?";
		Object[] params = new Object[] { assessmentApplicationId };
		dao.deleteNative(hql, params);
		return true;
	}
	
	/**
	 * 根据ID获取问卷测评
	 * 
	 * @return
	 */
	public Assessment getAssessmentApplicationById(String id) {
		Object obj = this.loadOne(ObjectType.PUB_ASSESSMENT, new String[] { "id" }, new Object[] { id });
		if (obj == null) {
			return null;
		}
		Assessment o = (Assessment) obj;
		return o;
	}
	
	/**
	 * 忽略问卷测评
	 */
	public boolean ignoreAssessment(String userId,String assessmentId) {
		String hql="update PUB_ASSESSTARGET a set a.STATUS=? where a.USERID=? and a.ASSESSMENTID=?";
    	Object[] params= new Object[]{Assesstarget.ASSESSTARGET_STATUS_2, userId,assessmentId};
        dao.updateNative(hql, params);
        return true;
	}
}
