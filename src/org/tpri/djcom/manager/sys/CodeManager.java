package org.tpri.djcom.manager.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.entity.sys.Code;

/**
 * @description 代码表管理类
 * @author 易文俊
 * @since 2015-06-30
 */

@Repository("CodeManager")
public class CodeManager extends ManagerBase {
	private static boolean initialized = false;

	public void initialize() {
		if (initialized)
			return;
		initialized = true;
		ObjectRegister.registerClass(ObjectType.SYS_CODE, Code.class);
		mc.clearObject(ObjectType.SYS_CODE);
		initializeObjects(ObjectType.SYS_CODE);
	}

	/**
	 * 添加代码
	 * 
	 * @return
	 */
	public void addCode(Code code) {
		add(code);
		addCache(code);
	}

	/**
	 * 保存代码
	 * 
	 * @return
	 */
	public void saveCode(Code code) {
		update(code);
		updateCache(code);
	}

	/**
	 * 删除代码
	 * 
	 * @return
	 */
	public void deleteCode(Code code) {
		this.delete(code);
		removeCache(code);
	}

	/**
	 * 根据ID获取代码
	 * 
	 * @return
	 */
	public Code getCode(String id) {
		Code code = (Code) loadMcCacheObject(ObjectType.SYS_CODE, id);
		return code;
	}

	/**
	 * 获取某个分类的代码
	 * 
	 * @return
	 */
	public List<Code> getCodeListByParentCode(String parentCode) {
		List<Code> list = new ArrayList<Code>();
		if (parentCode == null || parentCode.equals("")) {
			return list;
		}
		List<ObjectBase> objectList = loadMcList(ObjectType.SYS_CODE);
		for (ObjectBase objectBase : objectList) {
			Code code = (Code) objectBase;
			if (code.getParentCode().equals(parentCode)) {
				list.add((Code) objectBase);
			}
		}
		return list;
	}

}
