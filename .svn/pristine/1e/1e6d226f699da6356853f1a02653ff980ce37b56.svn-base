package org.tpri.djcom.manager.uam;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.result.QueryMyInfoResult;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.entity.zbsc.Article;

/**
 * @description 用户管理类
 * @author 易文俊
 * @since 2015-04-09
 */

@Repository("UserManager")
public class UserManager  extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.UAM_USER, User.class);
    }
    /**
     * 获取用户
     * @return
     */
    public User getUser(String id)  {
    	Object obj=this.loadOne(ObjectType.UAM_USER, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
		User user=(User)obj;
        return user;
    }
    
    /**
     * 根据用户名和密码获取用户
     * @return
     */
    public User getUser(String userId, String password)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(User.class);
		daoPara.addCondition(Condition.EQUAL("id", userId));
		daoPara.addCondition(Condition.EQUAL("password", password));
        List list = dao.loadList(daoPara);
        if(list!=null&&list.size()>0){
        	return (User)list.get(0);
        }
        return null;
    }
    /**
     * 身份证号获取用户
     * @return
     */
    public User getUserByIdNumber(String idNumber)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(User.class);
		daoPara.addCondition(Condition.EQUAL("idNumber", idNumber));
		
        List list = dao.loadList(daoPara);
        if(list!=null&&list.size()>0){
        	return (User)list.get(0);
        }
        return null;
    }
    
    /**
     * 获取用户列表
     * @return
     */
    public List<User> getUserList(String classId, Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(User.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.desc("createtime"));
		List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 获取角色
     * @return
     */
    public Role getRole(String id)  {
    	Object obj=this.loadOne(ObjectType.UAM_ROLE, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
		Role role=(Role)obj;
        return role;
    }
    
    /**
     * 获取角色列表
     * @return
     */
    public List<Role> getRoleList(Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Role.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据身份证号获取符合的结果记录
     * @return
     */
    public List<User> getUsersByIdnumber(String idNumber)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(User.class);
		daoPara.addCondition(Condition.EQUAL("idNumber", idNumber));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 获取个人资料
     * @return
     */
    public List<QueryMyInfoResult> getMyInfo(User loginUser)  {
    	StringBuffer sql = new StringBuffer();
    	List<QueryMyInfoResult> myInfos = new ArrayList<QueryMyInfoResult>();
    	List list = new ArrayList();
    	sql.append("SELECT uu.id uid,IFNULL(uu.name,'') uname,IFNULL(uu.workno,'') workno,IFNULL(uu.password,'') password,IFNULL(op.id,'no') opid,zat.articlecount ,cct.commentcount,\n");
    	sql.append("CASE WHEN op.gender='0' THEN '男' WHEN op.gender='1' THEN '女'  ELSE '未知' END gender,IFNULL(op.NATION,'') NATION,IFNULL(op.BIRTHPLACE,'') BIRTHPLACE,\n");
    	sql.append("IFNULL(op.IDNUMBER,'') IDNUMBER,IFNULL(op.EDUCATION,'') EDUCATION,IFNULL(op.OCCUPATION,'') OCCUPATION,IFNULL(op.JOBTITLE,'') JOBTITLE,\n");
    	sql.append("IFNULL(op.OFFICEPHONE,'') OFFICEPHONE,IFNULL(op.MOBILE,'') MOBILE,IFNULL(op.EMAIL,'') EMAIL,IFNULL(op.PARTYNO,'') PARTYNO,IFNULL(op.JOINTIME,'') JOINTIME,\n");
    	sql.append("CASE WHEN op.TYPE='0' THEN '正式' ELSE '预备' END TYPE,IFNULL(oc.name,'') ocname,IFNULL(op.INTRODUCER1,'') INTRODUCER1,IFNULL(op.INTRODUCER2,'') INTRODUCER2\n");
    	sql.append(",IFNULL(uu.createuser,'') createuser,IFNULL(uu.createtime,'') createtime\n");
    	sql.append("FROM uam_user AS uu\n");
    	sql.append("LEFT JOIN obt_party_member AS op ON uu.partymemberid=op.id\n");
    	sql.append("LEFT JOIN(\n");
    	sql.append("SELECT uu.id uid,COUNT(za.id) articlecount FROM uam_user AS uu LEFT JOIN zbsc_article AS za ON uu.id=za.createuserid\n");
    	sql.append("GROUP BY uu.id\n");
    	sql.append(") AS zat ON uu.id=zat.uid\n");
    	sql.append("LEFT JOIN (\n");
    	sql.append("SELECT uu.id uid,COUNT(zc.id) commentcount FROM uam_user AS uu LEFT JOIN zbsc_comment AS zc ON uu.id=zc.userid\n");
    	sql.append("GROUP BY uu.id\n");
    	sql.append(") AS cct ON uu.id=cct.uid\n");
    	sql.append("LEFT JOIN org_ccparty AS oc ON op.CCPARTYID=oc.id\n");
    	if(loginUser!=null && loginUser.getId()!=null && !"".equals(loginUser.getId())){
    		sql.append("WHERE uu.id='").append(loginUser.getId()).append("'\n");
    	}else{
    		return null;
    	}
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			QueryMyInfoResult myInfo = new QueryMyInfoResult();
    			myInfo.setId(String.valueOf(objs[0]));
    			myInfo.setName(String.valueOf(objs[1]));
    			myInfo.setWorkno(String.valueOf(objs[2]));
    			myInfo.setPassword(String.valueOf(objs[3]));
    			myInfo.setIsparty(String.valueOf(objs[4]));
    			myInfo.setArticlecount(Integer.parseInt(String.valueOf(objs[5])));
    			myInfo.setCommentcount(Integer.parseInt(String.valueOf(objs[6])));
    			myInfo.setGender(String.valueOf(objs[7]));
    			myInfo.setNation(String.valueOf(objs[8]));
    			myInfo.setBirthPlace(String.valueOf(objs[9]));
    			myInfo.setIdNumber(String.valueOf(objs[10]));
    			myInfo.setEducation(String.valueOf(objs[11]));
    			myInfo.setOccupation(String.valueOf(objs[12]));
    			myInfo.setJobTitle(String.valueOf(objs[13]));
    			myInfo.setOfficePhone(String.valueOf(objs[14]));
    			myInfo.setMobile(String.valueOf(objs[15]));
    			myInfo.setEmail(String.valueOf(objs[16]));
    			myInfo.setPartyNo(String.valueOf(objs[17]));
    			myInfo.setJoindate(String.valueOf(objs[18]));
    			myInfo.setPartytype(String.valueOf(objs[19]));
    			myInfo.setCcpartyname(String.valueOf(objs[20]));
    			myInfo.setIntroducer1(String.valueOf(objs[21]));
    			myInfo.setIntroducer2(String.valueOf(objs[22]));
    			myInfo.setCreateuser(String.valueOf(objs[23]));
    			myInfo.setCreatetime(String.valueOf(objs[24]));
    			myInfos.add(myInfo);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("获取个人资料异常："+e.getMessage());
    	}
        return myInfos;
    }
    
}
