package org.tpri.djcom.service.sys;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.sys.Enumeration;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.sys.EnumerationManager;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description 枚举服务类
 * @author 易文俊
 * @since 2015-06-30
 */

@Service("EnumerationService")
public class EnumerationService {
	
	@Autowired
	EnumerationManager enumerationManager;
	/**
	 * 添加或修改枚举
	 */
	public boolean saveEnumeration(User user,String id, String name, int status) {
		Enumeration enumeration;
		if (id == null || id.equals("")) {
			enumeration=new Enumeration();
			enumeration.setId(UUIDUtil.id());
		}else{
			enumeration=enumerationManager.getEnumeration(id);
		}
		enumeration.setName(name);
		enumeration.setStatus(status);
		enumerationManager.saveOrUpdate(enumeration);
		return true;
	}
	/**
	 * 删除枚举
	 */
	public boolean deleteEnumeration(User user,JSONArray ids) {
		for(int i=0;i<ids.size();i++){
			String id=ids.getString(i);
			Enumeration enumeration=enumerationManager.getEnumeration(id);
			enumerationManager.deleteEnumeration(enumeration);
		}
		return true;
	}
	/**
	 * 根据ID获取枚举
	 */
	public Enumeration getEnumerationById(String id){
		Enumeration enumeration=enumerationManager.getEnumeration(id);
		return enumeration;
	}
	/**
	 * 获取所有枚举
	 */
	public List getEnumerationList(){
		List<Enumeration> list=new ArrayList<Enumeration>();
		list=enumerationManager.getEnumerationList();
		return list;
	}
}
