package org.tpri.djcom.controller.uam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.service.uam.RoleService;
import org.tpri.djcom.service.uam.UserService;



/**
 * @description 登录控制器
 * @author 易文俊
 * @since 2015-04-25
 */
@Controller
@RequestMapping("/uam")
public class UserController  extends BaseController{

	@Resource(name = "UserService")
	private UserService userService;

	@Resource(name = "RoleService")
	private RoleService roleService;
	
	/**
	 * 加载用户列表
	 */
	@RequestMapping("loadUser")
	@ResponseBody
	public Map loadNotice(HttpServletRequest request) {
		logger.debug("UserController loadUser begin");
		
		JSONObject jo = getJsonPara(request);
		Integer start=jo.optString("start",null)==null?null:jo.getInt("start");
		Integer limit=jo.optString("limit",null)==null?null:jo.getInt("limit");
		Map ret = new HashMap();
		try {
			
			List<User> list=userService.loadUser(start,limit);
			/*for(int i=0;i<list.size();i++){
				User u=list.get(i);
				if(null!=u.getPartyMember()){
					u.setIdNumber(u.getPartyMember().getIdNumber());
				}
			}*/
			ret.put("items", list);
			ret.put("totalCount", list.size());
			
			logger.debug("UserController loadUser end");
			return ret;
		} catch (Exception e) {
			System.out.println(e);
		}
		return ret;
	}
	
	/**
	 * 新增用户
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public Map addUser(HttpServletRequest request) {
		logger.debug("UserController addUser begin");
		String id=getString(request,"id");
		String name=getString(request,"name");
		String workNo=getString(request,"workNo");
		String partyNo=getString(request,"partyNo");
		String password=getString(request,"password");
		String ccpartyId=getString(request,"ccpartyId");
		String description=getString(request,"description");
		String flag=getString(request,"flag");
		String identityNo=getString(request,"identityNo");
		String status = getString(request,"status");	//状态
		Map ret = new HashMap();
		
		//检测身份证号是否已经存在，存在则不允许增加用户
		if(identityNo!=null && !"".equals(identityNo)){
			List<User> lists=userService.loadUserByIdnumber(identityNo);
			if(lists!=null && lists.size()>0){
				ret.put("success", false);
				ret.put("msg", "身份证号已存在,请联系管理员解决!");
				return ret;
			}
		}else{
			ret.put("success", false);
			ret.put("msg", "身份证号不能为空！");
			return ret;
		}
		
		userService.addUser(loadUser(request),id,name,workNo,partyNo,password,ccpartyId,description,flag,identityNo,status);
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("UserController addUser end");
		return ret;
	}
	
	/**
	 * 编辑用户
	 */
	@RequestMapping("editUser")
	@ResponseBody
	public Map editUser(HttpServletRequest request) {
		logger.debug("UserController editUser begin");
		String id=getString(request,"id");
		String name=getString(request,"name");
		String workNo=getString(request,"workNo");
		String partyNo=getString(request,"partyNo");
		String password=getString(request,"password");
		String ccpartyId=getString(request,"ccpartyId");
		String description=getString(request,"description");
		String flag=getString(request,"flag");
		String identityNo=getString(request,"identityNo");
		String status = getString(request,"status");	//状态
		
		userService.editUser(id,name,workNo,partyNo,password,ccpartyId,description,flag,identityNo,status,loadUser(request));
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("UserController editUser end");
		return ret;
	}
	
	/**
	 * 新增或保存用户
	 */
	@RequestMapping("addOrUpdateUser")
	@ResponseBody
	public Map addOrUpdateUser(HttpServletRequest request) {
		logger.debug("UserController addOrUpdateUser begin");
		Map ret = new HashMap();
		JSONObject objs = new JSONObject();
		objs.put("id", getString(request, "id"));
		objs.put("name", getString(request, "name"));
		objs.put("idNumber", getString(request, "idNumber"));
		objs.put("password", getString(request, "password"));
		objs.put("workNo", getString(request, "workNo"));
		objs.put("status", getString(request, "status"));
		objs.put("flag", getString(request, "flag"));
		objs.put("ccpartyId", getString(request, "ccpartyId"));
		objs.put("desc", getString(request, "desc"));
		if(userService.addOrUpdateUser(loadUser(request),objs)){
			ret.put("success", true);
			ret.put("msg", "保存成功");
		}else{
			ret.put("success", false);
			ret.put("msg", "保存失败");
		}
		
		logger.debug("UserController addOrUpdateUser end");
		return ret;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("delUser")
	@ResponseBody
	public Map delUser(HttpServletRequest request) {
		logger.debug("UserController editUser begin");
		String id=getString(request,"userId");	
		Map ret = new HashMap();
		try {
			userService.deleteUser(id);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("UserController editUser end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("UserController editUser exception",e);
			return ret;
			
		}
	}
	/**
	 * 得到用户的角色
	 */
	@RequestMapping("getRoleByUser")
	@ResponseBody
	public Map getRoleByUser(HttpServletRequest request) {
		logger.debug("UserController getRoleByUser begin");
		String id=getString(request,"id");	
		Map ret = new HashMap();
		try {
			User u=userService.getUser(id);
			Set<Role> listSet=u.getRoles();
			List<Role> list=new ArrayList(listSet);
			List<Role> allList=roleService.loadRole(0, 10000);
			List<Role> noList=new ArrayList<Role>();
			if(list!=null && allList!=null ){
				for(int i=0;i<allList.size();i++){
					boolean flag=true;
					Role r=allList.get(i);
					for(int j=0;j<list.size();j++){		
						Role r2=list.get(j);
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
			
			logger.debug("UserController getRoleByUser end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("UserController editUser exception",e);
			return ret;
			
		}
	}
	
	/**
	 * 更新用户角色
	 */
	@RequestMapping("editUserRole")
	@ResponseBody
	public Map editUserRole(HttpServletRequest request) {
		logger.debug("UserController editUser begin");
		String id=getString(request,"id");
		String roleids=getString(request,"roleids");	
		
		Map ret = new HashMap();
		try {
			userService.editUserRole(id,roleids);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("UserController editUser end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("UserController editUser exception",e);
			return ret;
			
		}
	}
	
	
}
