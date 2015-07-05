package org.tpri.djcom.controller.uam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.uam.Privilege;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.manager.uam.UserRoleManager;
import org.tpri.djcom.service.uam.PrivilegeService;
import org.tpri.djcom.service.uam.RoleService;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description  角色控制器
 * @author 常华荣
 * @since 2015-04-25
 */
@Controller
@RequestMapping("/uam")
public class RoleController  extends BaseController{

	@Resource(name = "RoleService")
	private RoleService roleService;
	
	@Resource(name = "PrivilegeService")
	private PrivilegeService privilegeService;
	
	/**
	 * 加载角色列表
	 */
	@RequestMapping("loadRole")
	@ResponseBody
	public Map loadRole(HttpServletRequest request) {
		logger.debug("RoleController loadRole begin");
		
		JSONObject jo = getJsonPara(request);
		Integer start=jo.optString("start",null)==null?null:jo.getInt("start");
		Integer limit=jo.optString("limit",null)==null?null:jo.getInt("limit");
		
		List list=roleService.loadRole(start,limit);
		Map ret = new HashMap();
		ret.put("items", list);
		ret.put("totalCount", list.size());
		
		logger.debug("RoleController loadRole end");
		return ret;
	}
	
	/**
	 * 获取指定用户的角色和所有角色信息
	 */
	@RequestMapping("loadRoleForUserId")
	@ResponseBody
	public Map loadRoleForUserId(HttpServletRequest request) {
		logger.debug("RoleController loadRoleForUserId begin");
		Map ret = new HashMap();
		String userId = getString(request, "userId");
		if(userId!=null && !"".equals(userId)){
			//step1、获取该用户的权限列表
			List<Role> list=roleService.loadRoleByUserId(userId);
			List<Role> noRoleLists=roleService.loadNoAllocationRoleByUserId(userId);
			//step2、获取所有有效且未分配的权限列表
			ret.put("items", list);
			ret.put("noRoleItems", noRoleLists);
			System.out.println(noRoleLists.size());
		}else{
			ret.put("items", null);
			ret.put("noRoleItems", null);
		}
		logger.debug("RoleController loadRoleForUserId end");
		return ret;
	}
	
	/**
	 * 获取指定角色的权限和没有分配的权限信息
	 */
	@RequestMapping("loadRolePrivilegeForRoleId")
	@ResponseBody
	public Map loadRolePrivilegeForRoleId(HttpServletRequest request) {
		logger.debug("RoleController loadRolePrivilegeForRoleId begin");
		Map ret = new HashMap();
		String roleId = getString(request, "roleId");
		if(roleId!=null && !"".equals(roleId)){
			//step1、获取该角色的权限列表
			List<Privilege> list=roleService.loadRolePrivilegeByRoleId(roleId);
			List<Role> noRoleLists=roleService.loadNoAllocationRoleByRoleId(roleId);
			//step2、获取所有有效且未分配的权限列表
			ret.put("items", list);
			ret.put("noPrivilegeItems", noRoleLists);
			System.out.println(noRoleLists.size());
		}else{
			ret.put("items", null);
			ret.put("noPrivilegeItems", null);
		}
		logger.debug("RoleController loadRolePrivilegeForRoleId end");
		return ret;
	}
	
	/**
	 * 新增角色
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public Map addRole(HttpServletRequest request) {
		logger.debug("RoleController addRole begin");
		String id=getString(request,"id");
		String name=getString(request,"name");
		String description=getString(request,"description");
		String status=getString(request,"status");
		String system=getString(request,"system");
		
		
		roleService.addRole(id,name,description,status,system);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("RoleController addRole end");
		return ret;
	}
	
	/**
	 * 编辑角色
	 */
	@RequestMapping("editRole")
	@ResponseBody
	public Map editRole(HttpServletRequest request) {
		logger.debug("RoleController editRole begin");
		String id=getString(request,"id");
		String name=getString(request,"name");
		String status=getString(request,"status");
		String system=getString(request,"system");
		
		String description=getString(request,"description");
		
		roleService.editRole(id, name, description, status, system);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("RoleController editRole end");
		return ret;
	}
	
	/**
	 * 新增or编辑角色
	 */
	@RequestMapping("addOrEditRole")
	@ResponseBody
	public Map addOrEditRole(HttpServletRequest request) {
		logger.debug("RoleController addOrEditRole begin");
		Map ret = new HashMap();
		JSONObject objs = new JSONObject();
		String id = getString(request, "id");
		if(id==null || "".equals(id)){
			objs.put("id", UUIDUtil.id());
		}else{
			objs.put("id", getString(request,"id"));
		}
		objs.put("name", getString(request,"name"));
		objs.put("status", getString(request,"status"));
		objs.put("system", getString(request,"system"));
		objs.put("desc", getString(request,"desc"));
		if(roleService.addOrEditRole(objs)){
			ret.put("success", true);
			ret.put("msg", "保存成功");
		}else{
			ret.put("success", false);
			ret.put("msg", "保存失败");
		}
		logger.debug("RoleController addOrEditRole end");
		return ret;
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping("delRole")
	@ResponseBody
	public Map delRole(HttpServletRequest request) {
		logger.debug("RoleController delRole begin");
		String id=getString(request,"userId");	
		Map ret = new HashMap();
		try {
			roleService.deleteRole(id);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("RoleController delRole end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("RoleController delRole exception",e);
			return ret;
			
		}
	}
	
	
	/**
	 * 得到用户的角色
	 */
	@RequestMapping("getPrivilegeByRole")
	@ResponseBody
	public Map getPrivilegeByRole(HttpServletRequest request) {
		logger.debug("RoleController getPrivilegeByRole begin");
		String id=getString(request,"id");	
		Map ret = new HashMap();
		try {
			Role role=roleService.getRole(id);
			List<Privilege> list=role.getPrivileges();			
			List<Privilege> allList=privilegeService.loadPrivilege(0, 10000);
			List<Privilege> noList=new ArrayList<Privilege>();
			if(list!=null && allList!=null ){
				for(int i=0;i<allList.size();i++){
					boolean flag=true;
					Privilege r=allList.get(i);
					for(int j=0;j<list.size();j++){		
						Privilege r2=list.get(j);
						if(r.getId().equals(r2.getId())){
							flag=false;
							break;
						}			
					}
					if(flag){
						noList.add(r);
					}
				}		
			}
			JSONArray  noArray=new JSONArray();
			
			for(int i=0;i<noList.size();i++){
				String roleid=noList.get(i).getId();
				String name=noList.get(i).getName();
				JSONObject o=new JSONObject();
				o.put("value", roleid);
				o.put("text", name);				
				noArray.add(o);			
				
			}
			JSONArray  array=new JSONArray();
			for(int i=0;i<list.size();i++){
				String roleid=list.get(i).getId();
				String name=list.get(i).getName();
				JSONObject o=new JSONObject();
				o.put("value", roleid);
				o.put("text", name);
				array.add(o);			
				
			}
			
			ret.put("noList", noArray.toString());
			ret.put("list", array.toString());
			
			logger.debug("RoleController getRoleByUser end");
			return ret;
			
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("RoleController getPrivilegeByRole exception",e);
			return ret;
			
		}
	}
	
	/**
	 * 更新用户角色
	 */
	@RequestMapping("editRolePrivilege")
	@ResponseBody
	public Map editRolePrivilege(HttpServletRequest request) {
		logger.debug("UserController editUser begin");
		String id=getString(request,"id");
		String roleids=getString(request,"roleids");	
		
		Map ret = new HashMap();
		try {
			roleService.editRolePrivilege(id,roleids);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("UserController editRolePrivilege end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("UserController editRolePrivilege exception",e);
			return ret;
			
		}
	}
	
	
	
	/**
	 * 更新用户的权限角色
	 */
	@RequestMapping("saveUserRole")
	@ResponseBody
	public Map saveUserRole(HttpServletRequest request) {
		logger.debug("UserController saveUserRole begin");
		String userId = getString(request,"userId");
		String roleids = getString(request,"roleids");	
		
		Map ret = new HashMap();
		try {
			if(roleService.updateRolesByRoleIds(userId,roleids)){
				ret.put("success", true);
				ret.put("msg", "保存成功！");
			}else{
				ret.put("success", false);
				ret.put("msg", "保存失败！");
			}
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "保存失败！");
			logger.debug("UserController saveUserRole exception",e);
		}
		return ret;
	}
	
	
}
