package org.tpri.djcom.manager.obt;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.obt.OrgActivityApplicants;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.pub.Assesstarget;

/**
 * 
 * @Description: 组织生活通知管理
 * @author zhaozijing
 * @date 2015年7月1日 上午11:52:19
 */
@Repository("OrgActivityApplicantsManager")
public class OrgActivityApplicantsManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.OBT_ORG_ACTIVITY_APPLICANTS, OrgActivityApplicants.class);
    }
    
    /**
     * 
     * @Description: 根据组织生活ID删除关联的所有通知
     * @param activityId
     * @return
     */
    public boolean deleteApplicantsByActivityId(String activityId){
    	String hql = "delete from OBT_ORG_ACTIVITY_APPLICANTS where ACTIVITYID=?";
		Object[] params = new Object[] { activityId };
		dao.deleteNative(hql, params);
		return true;
    }
    
    /**
     * 
     * @Description: 根据组织生活ID获取通知的组织信息
     * @param activityId
     * @return
     */
    public List<CCParty> getNoticeOrgByActivityId(String activityId) {
		DaoPara daoPara = new DaoPara();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM org_ccparty WHERE id IN(\n");
		sql.append("SELECT DISTINCT uu.ccpartyid FROM obt_org_activity_applicants AS ooaa ,uam_user AS uu\n");
		sql.append("WHERE uu.id=ooaa.userid and ooaa.activityid='").append(activityId).append("')");
		List list = dao.loadNative(sql.toString(), null, CCParty.class);
		return list;
	}
    
    /**
     * 
     * @Description: 根据组织生活ID获取通知列表
     * @param activityId
     * @return
     */
    public List<OrgActivityApplicants> getApplicants(String activityId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(OrgActivityApplicants.class);
		daoPara.addCondition(Condition.EQUAL("activityId", activityId));
		daoPara.addOrder(Order.asc("ccpartyId"));
		daoPara.addOrder(Order.desc("applyTime"));
		List list = dao.loadList(daoPara);
		return list;
	}
    
    /**
     * 
     * @Description: 根据组织生活ID获取通知列表
     * @param activityId
     * @return
     */
    public List<OrgActivityApplicants> getApplicants(String activityId,int status) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(OrgActivityApplicants.class);
		daoPara.addCondition(Condition.EQUAL("activityId", activityId));
		daoPara.addCondition(Condition.EQUAL("status", status));
		daoPara.addOrder(Order.asc("ccpartyId"));
		daoPara.addOrder(Order.desc("applyTime"));
		List list = dao.loadList(daoPara);
		return list;
	}
    
    /**
     * 
     * @Description: 根据组织生活ID获取通知列表
     * @param activityId
     * @return
     */
    public List<OrgActivityApplicants> getApplicants(String activityId,String userId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(OrgActivityApplicants.class);
		daoPara.addCondition(Condition.EQUAL("activityId", activityId));
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		List list = dao.loadList(daoPara);
		return list;
	}
    
}
