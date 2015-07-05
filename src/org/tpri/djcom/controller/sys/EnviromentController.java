package org.tpri.djcom.controller.sys;

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
import org.tpri.djcom.entity.sys.Environment;
import org.tpri.djcom.service.sys.EnvironmentService;

/**
 * @description 环境变量控制器
 * @author 易文俊
 * @since 2015-05-11
 */
@Controller
@RequestMapping("/sys")
public class EnviromentController  extends BaseController{

	@Resource(name = "EnvironmentService")
	private EnvironmentService environmentService;
	/**
	 * 获取所有环境变量
	 */
	@RequestMapping("getEnvironmentList")
	@ResponseBody
	public Map getEnvironmentList(HttpServletRequest request) {
		logger.debug(this.getClass()+" getEnvironmentList begin");
		List list=environmentService.getEnvironmentList();
		Map ret = new HashMap();
		ret.put("items",list);
		logger.debug(this.getClass()+" getEnvironmentList end");
		return ret;
	}
	/**
	 * 根据ID获取环境变量
	 */
	/*@RequestMapping("getEnvironment")
	@ResponseBody
	public Map getEnvironmentById(HttpServletRequest request) {
		logger.debug(this.getClass()+" getEnvironmentById begin");
		String id=getString(request,"id");
		Environment environment=environmentService.getEnvironmentById(id);
		Map ret = new HashMap();
		ret.put("item",environment);
		logger.debug(this.getClass()+" getEnvironmentById end");
		return ret;
	}*/
	/**
	 * 新增环境变量
	 */
	@RequestMapping("addEnvironment")
	@ResponseBody
	public Map addEnvironment(HttpServletRequest request) {
		logger.debug(this.getClass()+" addEnvironment begin");
		
		JSONObject json=new JSONObject();
		json.put("id", getString(request,"id"));
		json.put("type", getString(request,"type"));
		json.put("value", getString(request,"value"));
		json.put("valueRange", getString(request,"valueRange"));
		json.put("description", getString(request,"description"));
		
		logger.debug("id:"+json.getString("id"));
		logger.debug("type:"+json.getString("type"));
		logger.debug("value:"+json.getString("value"));
		logger.debug("valueRange:"+json.getString("valueRange"));
		logger.debug("description:"+json.getString("description"));
		
		environmentService.addEnvironment(loadUser(request), json);
		
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug(this.getClass()+" addEnvironment begin");
		return ret;
	}
	/**
	 *	修改环境变量
	 */
	@RequestMapping("editEnvironment")
	@ResponseBody
	public Map editEnvironment(HttpServletRequest request) {
		logger.debug(this.getClass()+" editEnvironment begin");
		
		JSONObject json=new JSONObject();
		json.put("id", getString(request,"id"));
		json.put("type", getString(request,"type"));
		json.put("value", getString(request,"value"));
		json.put("valueRange", getString(request,"valueRange"));
		json.put("description", getString(request,"description"));
		environmentService.editEnvironment(loadUser(request), json);
		
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug(this.getClass()+" editEnvironment begin");
		return ret;
	}
	/**
	 *	刪除环境变量
	 */
	@RequestMapping("deleteEnvironment")
	@ResponseBody
	public Map deleteEnvironment(HttpServletRequest request) {
		logger.debug(this.getClass()+" deleteEnvironment begin");
		String ids=getString(request,"ids");
		JSONArray idsArray=JSONArray.fromObject(ids);
		environmentService.deleteEnvironment(loadUser(request), idsArray);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "删除成功");
		logger.debug(this.getClass()+" deleteEnvironment begin");
		return ret;
	}
}
