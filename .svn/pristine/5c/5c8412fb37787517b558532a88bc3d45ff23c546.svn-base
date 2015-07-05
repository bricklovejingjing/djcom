package org.tpri.djcom.service.uam;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.uam.Privilege;
import org.tpri.djcom.manager.uam.PrivilegeManager;

/**
 * @description 授权服务类
 * @author 易文俊
 * @since 2015-04-09
 */

@Service("PrivilegeService")
public class PrivilegeService {

	@Resource(name = "PrivilegeManager")
	PrivilegeManager privilegeManager;	
	/**
	 * 获取角色
	 * 
	 * @return
	 */
	public Privilege getPrivilege(String id) {
		Privilege Privilege = privilegeManager.getPrivilege(id);
		return Privilege;
	}

	public List loadPrivilege(Integer start, Integer limit) {
		List list = privilegeManager.getPrivilegeList(start, limit);
		return list;
	}

	public boolean addPrivilege(String id, String name, String description,
			String pgid, String type) {

		Privilege Privilege = new Privilege();
		Privilege.setId(id);
		Privilege.setName(name);
		Privilege.setDescription(description);
		Privilege.setType(Integer.valueOf(type));
		Privilege.setPgid(pgid);
		privilegeManager.add(Privilege);
		return true;
	}

	public boolean editPrivilege(String id, String name, String description,
			String pgid, String type) {


		Privilege Privilege = new Privilege();
		Privilege.setId(id);
		Privilege.setName(name);
		Privilege.setDescription(description);
		Privilege.setType(Integer.valueOf(type));
		Privilege.setPgid(pgid);
		privilegeManager.update(Privilege);
		return true;

	}
	public boolean deletePrivilege(String id) {

		Privilege Privilege = privilegeManager.getPrivilege(id);
		privilegeManager.delete(Privilege);
		return true;

	}
	
	/**
	 * 保存or修改权限
	 * @param objs
	 * @return
	 */
	public boolean saveOrUpdatePrivilege(JSONObject objs) {
		Privilege privilege = new Privilege();
		privilege.setId(objs.getString("id"));
		privilege.setName(objs.getString("name"));
		privilege.setDescription(objs.getString("desc"));
		if(privilegeManager.saveOrUpdate(privilege)){
			return true;
		}else{
			return false;
		}
	}
}
