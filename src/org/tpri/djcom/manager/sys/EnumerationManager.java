package org.tpri.djcom.manager.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.entity.sys.Enumeration;

/**
 * @description 枚举管理类
 * @author 易文俊
 * @since 2015-06-30
 */

@Repository("EnumerationManager")
public class EnumerationManager  extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.SYS_ENUMERATION, Enumeration.class);
        mc.clearObject(ObjectType.SYS_ENUMERATION);
        initializeObjects(ObjectType.SYS_ENUMERATION);
    }
    /**
     * 添加枚举
     * @return
     */
    public void addEnumeration(Enumeration enumeration)  {
        add(enumeration);
        addCache(enumeration);
    }
    /**
     * 保存枚举
     * @return
     */
    public void saveEnumeration(Enumeration enumeration) {
    	update(enumeration);
    	updateCache(enumeration);
    }
    /**
     * 删除枚举
     * @return
     */
    public void deleteEnumeration(Enumeration enumeration){
        this.delete(enumeration);
        removeCache(enumeration);
    }
    /**
     * 根据ID获取枚举
     * @return
     */
    public Enumeration getEnumeration(String id)  {
    	Enumeration enumeration=(Enumeration)loadMcCacheObject(ObjectType.SYS_ENUMERATION,id);
        return enumeration;
    }
    
    /**
     * 获取所有枚举
     * @return
     */
    public List<Enumeration> getEnumerationList()  {
    	List<Enumeration> list=new ArrayList<Enumeration>();
    	List<ObjectBase> objectList=loadMcList(ObjectType.SYS_ENUMERATION);
    	for(ObjectBase objectBase:objectList){
    		list.add((Enumeration)objectBase);
    	}
        return list;
    }
   
}
