package org.tpri.djcom.controller.sys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.sys.Navigation;
import org.tpri.djcom.service.sys.NavigationService;
/**
 * @description 导航栏控制器
 * @author 易文俊
 * @since 2015-04-06
 */
@Controller
@RequestMapping("/sys")
public class NavigationController extends BaseController{
	
	@Resource(name="NavigationService")
	private NavigationService navigationService;
	
	/**
	 * 获取导航列表
	 */
	@RequestMapping("loadNavigations")
	@ResponseBody
	public Map loadNotice(HttpServletRequest request) {
		logger.debug("NavigationController loadNavigations begin");
		
		String parentCode=getString(request,"parentCode");
		String type=getString(request,"type");
		
		HashSet<String> allPrivilegeIds =getAllPrivileges(request);
		List<Navigation> list=navigationService.loadNavigations(type,parentCode,allPrivilegeIds);
		
		Map ret = new HashMap();
		ret.put("items", list);
		
		logger.debug("NavigationController loadNavigations end");
		return ret;
	}

}
