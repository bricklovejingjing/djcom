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
import org.tpri.djcom.service.sys.CodeService;

/**
 * @description 代码表控制器
 * @author 易文俊
 * @since 2015-06-30
 */
@Controller
@RequestMapping("/sys")
public class CodeController extends BaseController {

	@Resource(name = "CodeService")
	private CodeService codeService;

	/**
	 * 获取某个分类的代码表
	 */
	@RequestMapping("getCodeList")
	@ResponseBody
	public Map getCodeListByParentCode(HttpServletRequest request) {
		logger.debug(this.getClass() + " getCodeList begin");
		String parentCode = getString(request, "parentCode");
		List list = codeService.getCodeListByParentCode(parentCode);
		Map ret = new HashMap();
		ret.put("items", list);
		logger.debug(this.getClass() + " getCodeList end");
		return ret;
	}

	/**
	 * 新增或者修改代码表
	 */
	@RequestMapping("saveCode")
	@ResponseBody
	public Map addCode(HttpServletRequest request) {
		logger.debug(this.getClass() + " addCode begin");

		JSONObject json = new JSONObject();
		String id = getString(request, "id");
		String name = getString(request, "name");
		int status = getInt(request, "status");
		User user = loadUser(request);

		logger.debug("id:" + json.getString("id"));
		logger.debug("name:" + json.getString("name"));
		logger.debug("status:" + json.getString("status"));
		
		codeService.saveCode(user, id, name, status);

		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "保存成功");
		logger.debug(this.getClass() + " addCode begin");
		return ret;
	}

	/**
	 * 刪除代码表
	 */
	@RequestMapping("deleteCode")
	@ResponseBody
	public Map deleteCode(HttpServletRequest request) {
		logger.debug(this.getClass() + " deleteCode begin");
		String ids = getString(request, "ids");
		JSONArray idsArray = JSONArray.fromObject(ids);
		codeService.deleteCode(loadUser(request), idsArray);
		Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "删除成功");
		logger.debug(this.getClass() + " deleteCode begin");
		return ret;
	}
}
