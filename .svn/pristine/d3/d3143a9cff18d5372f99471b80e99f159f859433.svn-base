package org.tpri.djcom.controller.org;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.org.Organization;
import org.tpri.djcom.service.org.OrganizationService;
import org.tpri.djcom.view.org.OrganizationView;

/**O
 * @description 行政单位控制器
 * @author 常华荣
 * @since 2015-04-30
 */
@Controller
@RequestMapping("/org")
public class OrganizationController  extends BaseController{

	@Resource(name = "OrganizationService")
	private OrganizationService organizationService;
	
	/**
	 * 加载用户列表
	 */
	@RequestMapping("loadOrganization")
	@ResponseBody
	public Map loadOrganization(HttpServletRequest request) {
		logger.debug("OrganizationController loadOrganization begin");
		
		JSONObject jo = getJsonPara(request);
		Integer start=jo.optString("start",null)==null?null:jo.getInt("start");
		Integer limit=jo.optString("limit",null)==null?null:jo.getInt("limit");
		
		List list=organizationService.loadOrganization(start,limit);
		Map ret = new HashMap();
		ret.put("items", list);
		ret.put("totalCount", list.size());
		
		logger.debug("OrganizationController loadOrganization end");
		return ret;
	}
	/**
	 * 加载用户列表
	 */
	@RequestMapping("getOrganizationList")
	@ResponseBody
	public Map getOrganizationList(HttpServletRequest request) {
		logger.debug("OrganizationController getOrganizationList begin");
		
		String parentId=getString(request,"parentId");
		
		List<OrganizationView> list=organizationService.getOrganizationList(parentId);
		
		Map ret = new HashMap();
		ret.put("items",list);
		
		
		logger.debug("OrganizationController getOrganizationList end");
		return ret;
	}
	/**
	 * 新增用户
	 */
	@RequestMapping("addOrganization")
	@ResponseBody
	public Map addOrganization(HttpServletRequest request) {
		logger.debug("OrganizationController addOrganization begin");
		JSONObject json = new JSONObject();
		
		json.put("name", getString(request,"name"));						//单位名称
		json.put("address", getString(request,"address"));					//单位地址
		json.put("perresentative", getString(request,"perresentative"));	//法定代表人
		json.put("parentId", getString(request,"parentId"));				//上级行政单位
		json.put("description", getString(request,"description"));			//描述
		json.put("status", getString(request,"status"));					//状态
		
		organizationService.addOrganization(loadUser(request),json);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("OrganizationController addOrganization end");
		return ret;
	}
	
	/**
	 * 编辑用户
	 */
	@RequestMapping("editOrganization")
	@ResponseBody
	public Map editOrganization(HttpServletRequest request) {
		logger.debug("OrganizationController editOrganization begin");
		JSONObject json=new JSONObject();
		json.put("id", getString(request,"id"));							//主键
		json.put("name", getString(request,"name"));						//单位名称
		json.put("address", getString(request,"address"));					//单位地址
		json.put("perresentative", getString(request,"perresentative"));	//法定代表人
		json.put("parentId", getString(request,"parentId"));				//上级行政单位
		json.put("description", getString(request,"description"));			//描述
		json.put("status", getString(request,"status"));					//状态
		
		organizationService.editOrganization(loadUser(request),json);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug("OrganizationController editOrganization end");
		return ret;
	}
	/**
	 * 编辑用户
	 */
	@RequestMapping("delOrganization")
	@ResponseBody
	public Map delOrganization(HttpServletRequest request) {
		logger.debug("OrganizationController editOrganization begin");
		String id=getString(request,"id");	
		Map ret = new HashMap();
		try {
			organizationService.deleteOrganization(id);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("OrganizationController editOrganization end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("OrganizationController editOrganization exception",e);
			return ret;
			
		}
	}
	/**
	 * 编辑用户
	 */
	@RequestMapping("getOrganization")
	@ResponseBody
	public Map getOrganization(HttpServletRequest request) {
		logger.debug("OrganizationController editOrganization begin");
		String id=getString(request,"id");	
		Map ret = new HashMap();
		try {
			Organization o=organizationService.getOrganization(id);
			ret.put("items", o);
			ret.put("success", true);
			ret.put("msg", "保存成功");
			logger.debug("OrganizationController editOrganization end");
			return ret;
			
		} catch (Exception e) {
			ret.put("success", false);
			ret.put("msg", "删除失败！");
			logger.debug("OrganizationController editOrganization exception",e);
			return ret;
			
		}
	}
}
