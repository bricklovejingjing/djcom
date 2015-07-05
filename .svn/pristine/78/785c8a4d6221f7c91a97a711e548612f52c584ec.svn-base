package org.tpri.djcom.controller.uam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.result.QueryMyInfoResult;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.service.uam.UserService;
import org.tpri.djcom.util.MD5Util;

/**
 * @description 个人中心控制器
 * @author zhaozijing
 * @since 2015-06-04
 */
@Controller
@RequestMapping("/zbsc")
public class MySpaceController extends BaseController{
	@Resource(name = "UserService")
	private UserService userService;
	
	/**
	 * 获取我的个人资料
	 */
	@RequestMapping("getMyInfo")
	@ResponseBody
	public Map getMyInfo(HttpServletRequest request) {
		logger.debug(this.getClass()+" getMyInfo begin");
		List<QueryMyInfoResult> myInfo=userService.getMyInfo(loadUser(request));	//加载个人资料
		Map ret = new HashMap();
		ret.put("items",myInfo);
		logger.debug(this.getClass()+" getMyInfo end");
		return ret;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("updatePwd")
	@ResponseBody
	public Map updatePwd(HttpServletRequest request) {
		logger.debug(this.getClass()+" updatePwd begin");
		Map ret = new HashMap();
		String oldPwd = getString(request, "oldPwd");
		String newPwd = getString(request, "newPwd");
		User loginUser = loadUser(request);
		if(oldPwd!=null && !"".equals(oldPwd)){
			oldPwd = MD5Util.md5(oldPwd); 
			if(oldPwd.equals(loginUser.getPassword())){
				if(newPwd!=null && !"".equals(newPwd)){
					newPwd = MD5Util.md5(newPwd);
				}else{
					ret.put("success", false);
					ret.put("desc", "新密码为空");
					logger.debug(this.getClass()+" updatePwd end");
					return ret;
				}
				if(userService.updatePwd(loginUser,newPwd)){
					ret.put("success", true);
					ret.put("desc", "修改成功");
					logger.debug(this.getClass()+" updatePwd end");
					return ret;
				}else{
					ret.put("success", false);
					ret.put("desc", "修改失败");
					logger.debug(this.getClass()+" updatePwd end");
					return ret;
				}
			}else{
				ret.put("success", false);
				ret.put("desc", "原始密码不正确");
				logger.debug(this.getClass()+" updatePwd end");
				return ret;
			}
		}else{
			ret.put("success", false);
			ret.put("desc", "原始密码为空");
			logger.debug(this.getClass()+" updatePwd end");
			return ret;
		}
	}
	

}
