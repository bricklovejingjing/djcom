package org.tpri.djcom.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tpri.djcom.entity.obt.PartyMember;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.sys.Enumeration;
import org.tpri.djcom.entity.uam.Role;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.sys.EnumerationManager;
import org.tpri.djcom.manager.uam.UserManager;
import org.tpri.djcom.util.BaseConstants;

/**
 * 页面帮助类。 用于将一些参数在页面加载时传递给前台页面。 1、将所有参数放在一个JSONObject中，可以放字符串、数字、数组、List、Map等
 * 2、前台的Global会将JSONObject中的属性转换成Global对象自己的属性。
 * 3、跳转至某个页面前，可以将个性化的信息通过request.setAttribute
 * (BaseConstants.PAGE_GLOBAL_VALUES_IDENTIFIER, Map xxx)
 * 方法放入request中，本类会自动将其转换成Global的属性
 * 
 * @author 易文俊
 * @version 2015-05-05
 */
public class PageSupport extends BaseController{
	/**
	 * 构建要传给前台页面的属性
	 */
	public static String buildAttributes(HttpServletRequest request) {
		
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		
		JSONObject jo = new JSONObject();
		// 将各页面自定义的信息传递给Global
		Map globalValues = (Map) request.getAttribute(BaseConstants.PAGE_GLOBAL_VALUES_IDENTIFIER);
		if (globalValues != null && globalValues instanceof Map) {
			jo.putAll(globalValues);
		}
		// 用户信息
		String userId = (String) request.getAttribute(BaseConstants.REQ_CUR_USER_ID);
		if (userId != null && !userId.equals("")) {
			UserManager userManager =(UserManager) applicationContext.getBean("UserManager");
			User user=userManager.getUser(userId);
			String ccpartyId="";
			String ccpartyName="";
	        PartyMember partyMember=user.getPartyMember();
	        if(partyMember!=null){
	        	CCParty ccparty=partyMember.getCcparty();
	        	if(ccparty!=null){
	        		ccpartyId=ccparty.getId();
	        		ccpartyName=ccparty.getName();
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
	        HashSet<String> allPrivilegeIds = user.loadAllPrivilegeIds();
			jo.put("userId", user.getId());
			jo.put("userName", user.getName());
			jo.put("roles", roleStr);
			jo.put("ccpartyId", ccpartyId);
			jo.put("ccpartyName", ccpartyName);
			jo.put("isSystemAdmin", user.isSystemAdmin());
			jo.put("allPrivilegeIds", allPrivilegeIds);
		}else{
			jo.put("userId", "");
			jo.put("userName", "未登录用户");
		}
		
		Map enumerationsMap = getEnumerations(applicationContext);
		jo.put("enums", enumerationsMap);

		String jsonString = jo.toString();
		return jsonString;
	}
	// 获取枚举
	private static Map getEnumerations(ApplicationContext applicationContext) {
		EnumerationManager enumerationManager =(EnumerationManager) applicationContext.getBean("EnumerationManager");
		List<Enumeration> enumerations=enumerationManager.getEnumerationList();
		Map enumerationsMap = new HashMap();
		for(Enumeration enumeration:enumerations){
			enumerationsMap.put(enumeration.getId(), enumeration.getName());
		}
		return enumerationsMap;
	}

}
