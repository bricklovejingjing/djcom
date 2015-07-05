package org.tpri.djcom.service.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.sys.Environment;
import org.tpri.djcom.entity.sys.EnvironmentId;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.sys.EnvironmentManager;

/**
 * @description 环境变量服务类
 * @author 易文俊
 * @since 2015-05-11
 */

@Service("EnvironmentService")
public class EnvironmentService {
	public Logger logger = Logger.getLogger(EnvironmentService.class);
	
	@Resource(name="EnvironmentManager")
	EnvironmentManager environmentManager;
	
	/**
	 * 添加环境变量
	 */
	public boolean addEnvironment(User user,JSONObject json) {
		Environment environment=new Environment();
		environment.setId(json.getString("id"));
		environment.setType(json.getInt("type"));
		environment.setValue(json.getString("value"));
		environment.setValueRange(json.getString("valueRange"));
		environment.setDescription(json.getString("description"));
		environmentManager.addEnvironment(environment);
		return true;
	}
	/**
	 * 修改环境变量
	 */
	public boolean editEnvironment(User user,JSONObject json) {
		Environment environment=environmentManager.getEnvironment(EnvironmentId.valueOf(json.getString("id")));
		environment.setType(json.getInt("type"));
		environment.setValue(json.getString("value"));
		environment.setValueRange(json.getString("valueRange"));
		environment.setDescription(json.getString("description"));
		environmentManager.saveEnvironment(environment);
		return true;
	}
	/**
	 * 删除环境变量
	 */
	public boolean deleteEnvironment(User user,JSONArray ids) {
		for(int i=0;i<ids.size();i++){
			String id=ids.getString(i);
			Environment environment=environmentManager.getEnvironment(EnvironmentId.valueOf(id));
			environmentManager.deleteEnvironment(environment);
		}
		return true;
	}
	/**
	 * 根据ID获取环境变量
	 */
	public Environment getEnvironmentById(EnvironmentId id){
		Environment environment=environmentManager.getEnvironment(id);
		return environment;
	}
	/**
	 * 根据ID获取环境变量的值
	 */
	public Object getEnvironmentValueById(EnvironmentId id){
		Environment environment=environmentManager.getEnvironment(id);
		if(environment==null){
			return null;
		}
		int type=environment.getType();
		String value=environment.getValue();
		if(value==null||value.equals("")){
			return null;
		}
		if(type==Environment.TYPE_INT){
			try {
				Integer realValue=Integer.valueOf(value);
				return realValue;
			} catch (Exception e) {
				logger.error("环境变量"+EnvironmentId.FILES_STORAGE_TYPE.name()+"的值是非法的整数");
				return null;
			}
		}else if(type==Environment.TYPE_FLOAT){
			try {
				Float realValue=Float.valueOf(value);
				return realValue;
			} catch (Exception e) {
				logger.error("环境变量"+EnvironmentId.FILES_STORAGE_TYPE.name()+"的值是非法的浮点数");
				return null;
			}
		}else if(type==Environment.TYPE_BOOLEAN){
			try {
				Boolean realValue=Boolean.valueOf(value);
				return realValue;
			} catch (Exception e) {
				logger.error("环境变量"+EnvironmentId.FILES_STORAGE_TYPE.name()+"的值是非法的布尔值");
				return null;
			}
		}
		return value;
	}
	/**
	 * 获取所有环境变量
	 */
	public List getEnvironmentList(){
		List<Environment> list=new ArrayList<Environment>();
		list=environmentManager.getEnvironmentList();
		return list;
	}
}
