package org.tpri.djcom.manager.uam;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.uam.Privilege;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.UserRole;
/**
 * @description 用户权限管理类
 * @author zhaozijing
 * @since 2015-06-11
 */

@Repository("UserRoleManager")
public class UserRoleManager extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.UAM_USERROLE, UserRole.class);
    }
    
    /**
     * 根据用户ID获取权限列表
     * @param userId
     * @return
     */
    public List<Role> loadRoleByUserId(String userId)  {
    	List<Role> roles = new ArrayList<Role>();
    	StringBuffer sql = new StringBuffer();
    	if(userId!=null && !"".equals(userId)){
    		sql.append("SELECT ur.id,ur.name FROM uam_role AS ur WHERE ur.id IN(\n");
        	sql.append("	SELECT roleid FROM uam_userrole AS uu WHERE userid='").append(userId).append("'\n");
        	sql.append(")\n");
        	List list = new ArrayList();
        	try{
        		Session session=dao.getCurrentSession();
        		Query query=session.createSQLQuery(sql.toString());
        		list=query.list();
        		for(int i=0;i<list.size();i++){
        			Object[] objs = (Object[])list.get(i);
        			Role role = new Role();
        			role.setId((String.valueOf(objs[0])));
        			role.setName(String.valueOf(objs[1]));
        			roles.add(role);
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        		logger.error("根据用户ID获取权限列表："+e.getMessage());
        	}
    	}else{
    		return null;
    	}
    	
        return roles;
    }
    
    /**
     * 根据用户id加载分配的权限
     * @param roleId
     * @return
     */
    public List<Privilege> loadRolePrivilegeByRoleId(String roleId)  {
    	List<Privilege> privileges = new ArrayList<Privilege>();
    	StringBuffer sql = new StringBuffer();
    	if(roleId!=null && !"".equals(roleId)){
    		sql.append("SELECT up.id,up.name FROM uam_privilege AS up  WHERE up.id IN(\n");
        	sql.append("	SELECT ur.privilegeid FROM uam_roleprivilege AS ur WHERE ur.roleid='").append(roleId).append("'\n");
        	sql.append(")\n");
        	List list = new ArrayList();
        	try{
        		Session session=dao.getCurrentSession();
        		Query query=session.createSQLQuery(sql.toString());
        		list=query.list();
        		for(int i=0;i<list.size();i++){
        			Object[] objs = (Object[])list.get(i);
        			Privilege privilege = new Privilege();
        			privilege.setId((String.valueOf(objs[0])));
        			privilege.setName(String.valueOf(objs[1]));
        			privileges.add(privilege);
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        		logger.error("根据角色ID获取权限列表异常："+e.getMessage());
        	}
    	}else{
    		return null;
    	}
        return privileges;
    }
    
    /**
     * 根据用户ID获取未分配权限列表
     * @param userId
     * @return
     */
    public List<Role> loadNoAllocationRoleByUserId(String userId)  {
    	List<Role> roles = new ArrayList<Role>();
    	StringBuffer sql = new StringBuffer();
    	if(userId!=null && !"".equals(userId)){
    		sql.append("SELECT ur.id,ur.name FROM uam_role AS ur WHERE ur.id not IN(\n");
        	sql.append("	SELECT roleid FROM uam_userrole AS uu WHERE userid='").append(userId).append("'\n");
        	sql.append(")\n");
        	List list = new ArrayList();
        	try{
        		Session session=dao.getCurrentSession();
        		Query query=session.createSQLQuery(sql.toString());
        		list=query.list();
        		for(int i=0;i<list.size();i++){
        			Object[] objs = (Object[])list.get(i);
        			Role role = new Role();
        			role.setId((String.valueOf(objs[0])));
        			role.setName(String.valueOf(objs[1]));
        			roles.add(role);
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        		logger.error("根据用户ID获取未分配权限列表："+e.getMessage());
        	}
    	}else{
    		return null;
    	}
    	
        return roles;
    }
    
    /**
     * 获取指定角色没有分配的权限信息
     * @param roleId
     * @return
     */
    public List<Privilege> loadNoAllocationRoleByRoleId(String roleId)  {
    	List<Privilege> privleges = new ArrayList<Privilege>();
    	StringBuffer sql = new StringBuffer();
    	if(roleId!=null && !"".equals(roleId)){
    		sql.append("SELECT up.id,up.name FROM uam_privilege AS up  WHERE up.id NOT IN(\n");
        	sql.append("	SELECT ur.privilegeid FROM uam_roleprivilege AS ur WHERE ur.roleid='").append(roleId).append("'\n");
        	sql.append(")\n");
        	List list = new ArrayList();
        	try{
        		Session session=dao.getCurrentSession();
        		Query query=session.createSQLQuery(sql.toString());
        		list=query.list();
        		for(int i=0;i<list.size();i++){
        			Object[] objs = (Object[])list.get(i);
        			Privilege privlege = new Privilege();
        			privlege.setId((String.valueOf(objs[0])));
        			privlege.setName(String.valueOf(objs[1]));
        			privleges.add(privlege);
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        		logger.error("根据角色ID获取未分配权限列表异常："+e.getMessage());
        	}
    	}else{
    		return null;
    	}
    	
        return privleges;
    }
    
    
    public boolean deleteUserRoleByUserId(String userId)  {
    	DaoPara daoPara = new DaoPara();
    	Class clazz = ObjectRegister.getClassByClassType(ObjectType.UAM_USERROLE);
    	daoPara.setClazz(clazz);
    	daoPara.addCondition(Condition.EQUAL("userid", userId));
    	dao.delete(daoPara);
        return true;
    	
    }
    
    public boolean addUserRole(String userId,String roleId)  {
    	StringBuffer sql = new StringBuffer();
    	if(userId!=null && !"".equals(userId)){
    		sql.append("insert into uam_userrole values('").append(userId).append("','").append(roleId);
        	try{
        		Session session=dao.getCurrentSession();
        		Query query=session.createSQLQuery(sql.toString());
        		query.executeUpdate();
        		return true;
        	}catch(Exception e){
        		e.printStackTrace();
        		logger.error("保存用户权限异常："+e.getMessage());
        		return false;
        	}
    	}else{
    		return false;
    	}
    	
    }

}
