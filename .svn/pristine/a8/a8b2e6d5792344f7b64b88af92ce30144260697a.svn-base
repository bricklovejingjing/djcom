package org.tpri.djcom.service.uam;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.uam.Privilege;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.UserRole;
import org.tpri.djcom.manager.uam.RoleManager;
import org.tpri.djcom.manager.uam.UserRoleManager;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description 授权服务类
 * @author 易文俊
 * @since 2015-04-09
 */

@Service("RoleService")
public class RoleService {

	@Resource(name = "RoleManager")
	RoleManager roleManager;	
	
	@Resource(name = "UserRoleManager")
	UserRoleManager userRoleManager;	
	/**
	 * 获取角色
	 * 
	 * @return
	 */
	public Role getRole(String id) {
		Role role = roleManager.getRole(id);
		return role;
	}

	public List loadRole(Integer start, Integer limit) {
		List list = roleManager.getRoleList(start, limit);
		return list;
	}
	
	/**
	 * 根据用户id加载分配的权限
	 * @param userId
	 * @return
	 */
	public List loadRoleByUserId(String userId) {
		List<Role> list = userRoleManager.loadRoleByUserId(userId);
		return list;
	}
	
	/**
	 * 获取指定用户的角色信息
	 * @param roleId
	 * @return
	 */
	public List loadRolePrivilegeByRoleId(String roleId) {
		List<Privilege> list = userRoleManager.loadRolePrivilegeByRoleId(roleId);
		return list;
	}
	
	/**
	 * 根据用户id加载未分配的权限
	 * @param userId
	 * @return
	 */
	public List loadNoAllocationRoleByUserId(String userId) {
		List<Role> list = userRoleManager.loadNoAllocationRoleByUserId(userId);
		return list;
	}
	
	/**
	 * 获取指定角色没有分配的权限信息
	 * @param roleId
	 * @return
	 */
	public List loadNoAllocationRoleByRoleId(String roleId) {
		List<Privilege> list = userRoleManager.loadNoAllocationRoleByRoleId(roleId);
		return list;
	}

	public boolean addRole(String id, String name, String description,
			String status, String system) {

		Role role = new Role();
		role.setId(id);
		role.setName(name);
		role.setDescription(description);
		role.setStatus(Integer.valueOf(status));
		role.setSystem(system);
		roleManager.add(role);
		return true;
	}

	public boolean editRole(String id, String name, String description,
			String status, String system) {
		Role role = new Role();
		role.setId(id);
		role.setName(name);
		role.setDescription(description);
		role.setStatus(Integer.valueOf(status));
		role.setSystem(system);
		roleManager.update(role);
		return true;
	}
	
	/**
	 * 保存或者修改角色
	 * @param objs
	 * @return
	 */
	public boolean addOrEditRole(JSONObject objs) {
		Role role = new Role();
		String id = objs.getString("id");
		role.setName(objs.getString("name"));
		role.setDescription(objs.getString("desc"));
		role.setStatus(objs.getInt("status"));
		role.setSystem(objs.getString("system"));
		role.setId(id);
		if(roleManager.saveOrUpdate(role)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean deleteRole(String id) {

		Role role = roleManager.getRole(id);
		roleManager.delete(role);
		return true;

	}

	public boolean editRolePrivilege(String id, String privilegeIds) {
		Role role = roleManager.getRole(id);
		List<Privilege> list=roleManager.getPrivilegeListByIds(privilegeIds);
		role.setPrivileges(list);
		
		roleManager.update(role);
		return true;
	}
	
	public boolean updateRolesByRoleIds(String userId, String roleids) {
		boolean execute = false;
		//删除该用户所有权限
		if(userRoleManager.deleteUserRoleByUserId(userId)){
			if(roleids!=null && !"".equals(roleids)){
				JSONArray roleIdsArray=JSONArray.fromObject(roleids);
				for(int i=0;i<roleIdsArray.size();i++){
					//activitiService.deleteDeployment(roleIdsArray.getString(i));
					UserRole userRole = new UserRole();
					userRole.setId(UUIDUtil.id());
					userRole.setUserid(userId);
					userRole.setRoleid(roleIdsArray.getString(i));
					if(userRoleManager.saveOrUpdate(userRole)){
						execute = true;
					}else{
						execute = false;
					}
				}
			}
		}else{
			execute = false;
		}
		return execute;
		
	}
	
}
