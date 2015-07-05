package org.tpri.djcom.service.sys;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpri.djcom.entity.sys.Code;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.manager.sys.CodeManager;
import org.tpri.djcom.util.UUIDUtil;

/**
 * @description 代码表服务类
 * @author 易文俊
 * @since 2015-06-30
 */

@Service("CodeService")
public class CodeService {
	
	@Autowired
	CodeManager codeManager;
	/**
	 * 添加或修改代码表
	 */
	public boolean saveCode(User user,String id, String name, int status) {
		Code code;
		if (id == null || id.equals("")) {
			code=new Code();
			code.setId(UUIDUtil.id());
		}else{
			code=codeManager.getCode(id);
		}
		code.setName(name);
		code.setStatus(status);
		codeManager.saveOrUpdate(code);
		return true;
	}
	/**
	 * 删除代码表
	 */
	public boolean deleteCode(User user,JSONArray ids) {
		for(int i=0;i<ids.size();i++){
			String id=ids.getString(i);
			Code code=codeManager.getCode(id);
			codeManager.deleteCode(code);
		}
		return true;
	}
	/**
	 * 根据ID获取代码表
	 */
	public Code getCodeById(String id){
		Code code=codeManager.getCode(id);
		return code;
	}
	/**
	 * 获取某个分类的代码表
	 */
	public List getCodeListByParentCode(String parentCode){
		List<Code> list=new ArrayList<Code>();
		list=codeManager.getCodeListByParentCode(parentCode);
		return list;
	}
}
