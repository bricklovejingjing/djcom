package org.tpri.djcom.controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tpri.djcom.entity.sys.EnvironmentId;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.service.sys.EnvironmentService;
import org.tpri.djcom.service.uam.UserService;
import org.tpri.djcom.util.BaseConstants;
/**
 * @description 控制器基类
 * @author 易文俊
 * @since 2015-03-28
 */
@Controller
public class BaseController {
	private Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	private EnvironmentService environmentService;
	
	public JSONObject getJsonPara(HttpServletRequest request) {
        String para = ServletRequestUtils.getStringParameter(request, "para", "{}");
        return JSONObject.fromObject(para);
    }
	public String getString(HttpServletRequest request,String name) {
        String para = ServletRequestUtils.getStringParameter(request, name, null);
        return para;
    }
	public int getInt(HttpServletRequest request,String name) {
		int para = ServletRequestUtils.getIntParameter(request, name, 0);
        return para;
    }
	public Integer getInteger(HttpServletRequest request,String name) {
		Integer para;
		try {
			para = ServletRequestUtils.getIntParameter(request, name);
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
			return null;
		}
        return para;
    }
	public boolean getBoolean(HttpServletRequest request,String name) {
		boolean para = ServletRequestUtils.getBooleanParameter(request, name, false);
        return para;
    }
	 /**
     * 获取登录用户ID
     */
	public String loadUserId(HttpServletRequest request){
		String userId=(String) request.getAttribute(BaseConstants.REQ_CUR_USER_ID);
        return userId;
    }
	/**
     * 获取登录用户
     */
    public User loadUser(HttpServletRequest request){
        User user = userService.getUser(loadUserId(request));
        HashSet<String> allPrivilegeIds=user.loadAllPrivilegeIds();
        return user;
    }
    /**
     * 获取环境变量值
     */
    public Object getEnvironmentValueById(EnvironmentId id){
    	return environmentService.getEnvironmentValueById(id);
    }
    /**
     * 获取上传文件
     * @param request 请求
     * @return MultipartFile 上传文件
     */
    public static MultipartFile getUploadFile(HttpServletRequest request) {
        return getUploadFile(request, "file");
    }
    /**
     * 获取上传文件
     * @param request 请求
     * @param name 文件名
     * @return MultipartFile 上传文件
     */
    public static MultipartFile getUploadFile(HttpServletRequest request, String name) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        return multipartRequest.getFile(name);
    }
    
    public boolean hasPrivilege(HttpServletRequest request,String privilegeId){
		User user = loadUser(request);
		if(user.hasPrivilege(privilegeId)){
			return true;
		}
		return false;
	}
    public HashSet<String> getAllPrivileges(HttpServletRequest request){
		User user = loadUser(request);
		HashSet<String> allPrivilegeIds = user.loadAllPrivilegeIds();
		return allPrivilegeIds;
	}

}
