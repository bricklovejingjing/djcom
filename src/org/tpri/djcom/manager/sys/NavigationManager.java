package org.tpri.djcom.manager.sys;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.sys.Navigation;

/**
 * @description 导航管理类
 * @author 易文俊
 * @since 2015-04-02
 */

@Repository("NavigationManager")
public class NavigationManager extends ManagerBase{
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.SYS_NAVIGATION, Navigation.class);
    }
    
    /**
     * 通过类型和父代码获取导航列表
     * @return
     */
    public List<Navigation> loadNavigationByTypeAndParentCode(String type, String parentCode)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Navigation.class);
		daoPara.addCondition(Condition.EQUAL("type", type));
		daoPara.addCondition(Condition.EQUAL("parentCode", parentCode));
		daoPara.addOrder(Order.asc("orderNo"));
        List list = dao.loadList(daoPara);
        return list;
    }

}
