package org.tpri.djcom.manager.sch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.sch.WorkApplicants;
import org.tpri.djcom.entity.sch.WorkApplication;
import org.tpri.djcom.entity.uam.User;

/**
 * @description 工作报名管理类
 * @author 易文俊
 * @since 2015-06-16
 */

@Repository("WorkApplicationManager")
public class WorkApplicationManager extends ManagerBase {
	private static boolean initialized = false;

	public void initialize() {
		if (initialized)
			return;
		initialized = true;
		ObjectRegister.registerClass(ObjectType.SCH_WORK_APPLICATION, WorkApplication.class);
		ObjectRegister.registerClass(ObjectType.SCH_WORK_APPLICANTS, WorkApplicants.class);
	}

	/**
	 * 获取工作报名
	 * 
	 * @return
	 */
	public List<WorkApplication> getWorkApplicationList(String userId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(WorkApplication.class);
		daoPara.addCondition(Condition.EQUAL("createUserId", userId));
		daoPara.addOrder(Order.desc("createTime"));
		List list = dao.loadList(daoPara);
		return list;
	}

	/**
	 * 根据ID获取工作报名
	 * 
	 * @return
	 */
	public WorkApplication getWorkApplicationById(String id) {
		Object obj = this.loadOne(ObjectType.SCH_WORK_APPLICATION, new String[] { "id" }, new Object[] { id });
		if (obj == null) {
			return null;
		}
		WorkApplication o = (WorkApplication) obj;
		return o;
	}

	/**
	 * 删除工作报名
	 * 
	 * @return
	 */
	public boolean deleteWorkApplication(String id) {
		return super.delete(id, ObjectType.SCH_WORK_APPLICATION);
	}

	/**
	 * 更新工作报名
	 * 
	 * @return
	 */
	public boolean updateWorkApplication(String id, Map<String, Object> fieldValues) {
		super.update(id, ObjectType.SCH_WORK_APPLICATION, fieldValues);
		return true;
	}

	/**
	 * 获取某工作报名下的通知人员
	 * 
	 * @return
	 */
	public List<WorkApplicants> getApplicantsByWorkApplicationId(String workApplicationId, Integer status) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(WorkApplicants.class);
		daoPara.addCondition(Condition.EQUAL("workApplicationId", workApplicationId));
		if (status != null) {
			daoPara.addCondition(Condition.EQUAL("status", status.intValue()));
		}
		daoPara.addOrder(Order.asc("ccpartyId"));
		List list = dao.loadList(daoPara);
		return list;
	}
	/**
	 * 获取某工作报名下的某人员报名信息
	 * 
	 * @return
	 */
	public WorkApplicants getApplicant(String workApplicationId, String userId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(WorkApplicants.class);
		daoPara.addCondition(Condition.EQUAL("workApplicationId", workApplicationId));
		daoPara.addCondition(Condition.EQUAL("userId", userId));
		List list = dao.loadList(daoPara);
		if(list!=null&&list.size()>0){
			return (WorkApplicants)list.get(0);
		}
		return null;
	}

	/**
	 * 删除某工作报名下的所有通知人员
	 * 
	 * @return
	 */
	public boolean deleteApplicantsByWorkApplicationId(String workApplicationId) {
		String hql = "delete from SCH_WORK_APPLICANTS where WORKAPPLICATIONID=?";
		Object[] params = new Object[] { workApplicationId };
		dao.deleteNative(hql, params);
		return true;
	}

	public List<WorkApplication> getMyWorkApplicationList(String userId,int status) {
		String hql = "select a.* from SCH_WORK_APPLICATION a, SCH_WORK_APPLICANTS b where a.ID=b.WORKAPPLICATIONID and b.APPLYUSERID=? and a.STATUS=1 and b.STATUS=?";
		Object[] params = new Object[] { userId ,status};
		List list = dao.loadNative(hql, params, WorkApplication.class);
		return list;
	}
	
	/**
	 * 申请报名
	 */
	public void applyWorkApplication(User user,String workApplicationId,CCParty ccparty) {
		String hql="update SCH_WORK_APPLICANTS a set a.STATUS=?, a.APPLYTIME=?,a.APPLYUSERID=?,a.APPLYUSERNAME=? where a.CCPARTYID=? and a.WORKAPPLICATIONID=?";
    	Object[] params= new Object[]{WorkApplicants.STATUS_APPLIED,new Timestamp(System.currentTimeMillis()),user.getId(),user.getName(),ccparty.getId(),workApplicationId};
        dao.updateNative(hql, params);
	}
	/**
	 * 忽略报名
	 */
	public void ignoreWorkApplication(String userId,String workApplicationId) {
		String hql="update SCH_WORK_APPLICANTS a set a.STATUS=? where a.APPLYUSERID=? and a.WORKAPPLICATIONID=?";
    	Object[] params= new Object[]{WorkApplicants.STATUS_IGNORE, userId,workApplicationId};
        dao.updateNative(hql, params);
	}
	
	/**
	 * 根据通知列表的组织 获取应通知的党务人员
	 * @param workApplicationId
	 * @param ccpartyId
	 * @return
	 */
	public List<User> getNotifyUserByCcparty(String workApplicationId,String ccpartyId){
		List<User> notifyUsers = new ArrayList<User>();
    	StringBuffer sql = new StringBuffer();
    	List list = new ArrayList();
    	sql.append("SELECT uu.id,uu.name\n");
    	sql.append("FROM sch_work_applicants AS swa ,obt_party_member AS opm ,uam_user AS uu\n");
    	sql.append("WHERE swa.ccpartyid=opm.ccpartyid AND opm.id=uu.partymemberid AND swa.ccpartyid='").append(ccpartyId).append("'\n");
    	sql.append("AND swa.workapplicationid='").append(workApplicationId).append("'\n");
    	sql.append("AND uu.id IN(\n");
    	sql.append("	#党务工作人员\n");
    	sql.append("	SELECT uu.id\n");
    	sql.append("	FROM uam_user AS uu ,uam_userrole AS uur WHERE uu.id=uur.userid AND uur.roleid='ROLE_1002' AND uu.status=0\n");
    	sql.append(")\n");
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			User user = new User();
    			user.setId(String.valueOf(objs[0]));
    			user.setName(String.valueOf(objs[1]));
    			notifyUsers.add(user);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询工作报名通知异常："+e.getMessage());
    	}
        return notifyUsers;
	}
}
