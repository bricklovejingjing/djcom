package org.tpri.djcom.manager.pub;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.pub.Assessment;
import org.tpri.djcom.entity.pub.Assesstarget;

/**
 * @description 问卷测评通知对象管理类
 * @author zhaozijing
 * @since 2015-06-22
 */

@Repository("AssesstargetManager")
public class AssesstargetManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.PUB_ASSESSTARGET, Assesstarget.class);
    }
    
    /**
	 * 获取通知人员
	 * 
	 * @return
	 */
	public List<Assesstarget> getApplicantsByAssessmentId(String assessmentId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Assesstarget.class);
		daoPara.addCondition(Condition.EQUAL("assessmentid", assessmentId));
		daoPara.addOrder(Order.asc("assessmentid"));
		List list = dao.loadList(daoPara);
		return list;
	}
	
	/**
	 * 获取已经完成测评的通知人员列表
	 * 
	 * @return
	 */
	public List<Assesstarget> getFinishAssesstargetByAssessmentId(String assessmentId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Assesstarget.class);
		daoPara.addCondition(Condition.EQUAL("assessmentid", assessmentId));
		daoPara.addCondition(Condition.EQUAL("status", Assesstarget.ASSESSTARGET_STATUS_1));
		daoPara.addOrder(Order.desc("submitTime"));
		List list = dao.loadList(daoPara);
		return list;
	}
	
	/**
	 * 获取通知人员所属组织
	 * 
	 * @return
	 */
	public List<CCParty> getNoticeOrgByAssessmentId(String assessmentId) {
		DaoPara daoPara = new DaoPara();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM org_ccparty WHERE id IN(\n");
		sql.append("SELECT DISTINCT uu.ccpartyid FROM pub_assesstarget AS pa ,uam_user AS uu\n");
		sql.append("WHERE uu.id=pa.userid and pa.assessmentid='").append(assessmentId).append("')");
		List list = dao.loadNative(sql.toString(), null, CCParty.class);
		return list;
	}

	public List<Assessment> getMyAssessmentList(String userId) {
		String hql = "SELECT pa.* FROM pub_assessment AS pa ,pub_assesstarget AS pat WHERE pa.id=pat.assessmentid and pat.userid=?";
		Object[] params = new Object[] { userId};
		List list = dao.loadNative(hql, params, Assessment.class);
		return list;
	}
	
	/**
	 * 获取我的通知测评记录
	 * @param userId
	 * @param assessmentId
	 * @return
	 */
	public List<Assesstarget> getMyAssesstarget(String userId,String assessmentId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Assesstarget.class);
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		daoPara.addCondition(Condition.EQUAL("assessmentId", assessmentId));
		List list = dao.loadList(daoPara);
		return list;
	}
}
