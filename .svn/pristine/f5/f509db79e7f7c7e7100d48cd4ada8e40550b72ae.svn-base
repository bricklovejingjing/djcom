package org.tpri.djcom.manager.com;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.com.BaseInfo;

/**
 * @description 资源管理类
 * @author 易文俊
 * @since 2015-04-09
 */

@Repository("BaseInfoManager")
public class BaseInfoManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.COM_BASEINFO, BaseInfo.class);
    }
    /**
     * 获取资源
     * @return
     */
    public BaseInfo getBaseInfo(String id)  {
    	Object obj=this.loadOne(ObjectType.COM_BASEINFO, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	BaseInfo BaseInfo=(BaseInfo)obj;
        return BaseInfo;
    }
    
    /**
     * 获取资源列表
     * @return
     */
    public List<BaseInfo> getBaseInfoList(String classId, Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(BaseInfo.class);
		daoPara.addCondition(Condition.EQUAL("classId", classId));
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
}
