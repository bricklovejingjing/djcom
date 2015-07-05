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
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.service.sys.EnumerationService;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description 枚举控制器
 * @author 易文俊
 * @since 2015-06-30
 */
@Controller
@RequestMapping("/sys")
public class EnumerationController extends BaseController {

	@Resource(name = "EnumerationService")
	private EnumerationService enumerationService;

	/**
	 * 获取所有枚举
	 */
	@RequestMapping("getEnumerationList")
	@ResponseBody
	public Map getEnumerationList(HttpServletRequest request) {
		logger.debug(this.getClass() + " getEnumerationList begin");
		List list = enumerationService.getEnumerationList();
		Map ret = new HashMap();
		ret.put("items", list);
		logger.debug(this.getClass() + " getEnumerationList end");
		return ret;
	}

	/**
	 * 新增或者修改枚举
	 */
	@RequestMapping("saveEnumeration")
	@ResponseBody
	public Map addEnumeration(HttpServletRequest request) {
		logger.debug(this.getClass() + " addEnumeration begin");

		JSONObject json = new JSONObject();
		String id = getString(request, "id");
		String name = getString(request, "name");
		int status = getInt(request, "status");
		User user = loadUser(request);

		logger.debug("id:" + json.getString("id"));
		logger.debug("name:" + json.getString("name"));
		logger.debug("status:" + json.getString("status"));
		
		enumerationService.saveEnumeration(user, id, name, status);

		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug(this.getClass() + " addEnumeration begin");
		return ret;
	}

	/**
	 * 刪除枚举
	 */
	@RequestMapping("deleteEnumeration")
	@ResponseBody
	public Map deleteEnumeration(HttpServletRequest request) {
		logger.debug(this.getClass() + " deleteEnumeration begin");
		String ids = getString(request, "ids");
		JSONArray idsArray = JSONArray.fromObject(ids);
		enumerationService.deleteEnumeration(loadUser(request), idsArray);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "删除成功");
		logger.debug(this.getClass() + " deleteEnumeration begin");
		return ret;
	}
}
