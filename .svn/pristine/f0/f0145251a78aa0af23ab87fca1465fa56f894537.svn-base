package org.tpri.djcom.controller.uam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tpri.djcom.controller.BaseController;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.service.uam.UserService;
import org.tpri.djcom.util.BaseConstants;
import org.tpri.djcom.util.MD5Util;
import org.tpri.djcom.util.ResponseUtils;

/**
 * @description 登录控制器
 * @author 易文俊
 * @since 2015-04-25
 */
@Controller
@RequestMapping("/uam")
public class LoginController  extends BaseController{

	@Resource(name = "UserService")
	private UserService userService;
	
	/**
	 * 验证用户名密码
	 */
	@RequestMapping("loginValidate")
	@ResponseBody
	public Map login(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(this.getClass()+" login begin");
		
		String userId=getString(request,"userId");
		String password=getString(request,"password");
		
		if (logger.isDebugEnabled()) {
            logger.debug("userId=" + userId + ",password=" + password);
        }
		Map ret = new HashMap();
        if (StringUtils.isBlank(userId)) {
        	ret.put("success", false);
        	ret.put("msg", "请输入用户名！");
            return ret;
        }

        if (StringUtils.isBlank(password)) {
        	ret.put("success", false);
        	ret.put("msg", "请输入密码！");
            return ret;
        }
        //将密码进行md5加密
        password = MD5Util.md5(password);
        User user=userService.getUser(userId, password);
        if(user==null){
        	ret.put("success", false);
        	ret.put("msg", "用户名或者密码不正确！");
            return ret;
        }else{
        	//判断用户是否挂起
        	if(User.DB_TABLE_STATUS_1==user.getStatus()){
        		ret.put("success", false);
            	ret.put("msg", "抱歉，该用户已挂起，请联系管理员！");
                return ret;
        	}
        }
        String ccpartyId="";
        PartyMember partyMember=user.getPartyMember();
        if(partyMember!=null){
        	CCParty ccparty=partyMember.getCcparty();
        	if(ccparty!=null){
        		ccpartyId=ccparty.getId();
        	}
        }
        String roleStr="";
        Set<Role> roles=user.getRoles();
        Iterator<Role> iterator=roles.iterator();
        while(iterator.hasNext()){
        	Role role=iterator.next();
        	roleStr=roleStr+role.getId()+",";
        }
        if(roleStr.endsWith(",")){
        	roleStr=roleStr.substring(0, roleStr.length()-1);
        }

        // 设定验证键值
        StringBuffer validateKey = new StringBuffer();
        validateKey.append(user.getId());
        validateKey.append(BaseConstants.COOKIE_VALIDATE_KEY_SPLIT);
        validateKey.append(user.getName());
        validateKey.append(BaseConstants.COOKIE_VALIDATE_KEY_SPLIT);
        validateKey.append(roleStr);
        validateKey.append(BaseConstants.COOKIE_VALIDATE_KEY_SPLIT);
        validateKey.append(ccpartyId);
        ResponseUtils.setValidateKey(response, validateKey.toString());
        
        //将登陆标识保存session
        request.getSession().setAttribute(BaseConstants.REQ_CUR_USER_ID,user.getId());
		
		ret.put("success", true);
		ret.put("msg", "登录成功！");
		logger.debug(this.getClass()+" login end");
		return ret;
	}
	/**
	 * 登出
	 */
	@RequestMapping("logout")
	@ResponseBody
	public Map logout(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(this.getClass()+" logout begin");
		
        Cookie cookie = new Cookie(BaseConstants.COOKIE_VALIDATE_KEY, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Map ret = new HashMap();
		ret.put("success", true);
		ret.put("msg", "登出成功！");
		logger.debug(this.getClass()+" logout end");
		return ret;
	}
}
