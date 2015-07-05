package org.tpri.djcom.manager.obt;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.obt.OrgActivity;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.util.StringUtils;

/**
 * @description 组织生活管理类
 * @author zhaozijing
 * @since 2015-06-30
 */

@Repository("OrgActivityManager")
public class OrgActivityManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.OBT_ORG_ACTIVITY, OrgActivity.class);
    }
    
    
    
    /**
     * 获得组织生活
     * @param loginUser
     * @return
     */
    public List<OrgActivity> loadActivitys(User loginUser,JSONObject objs)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(OrgActivity.class);
		String type = objs.getString("type");
		if(!StringUtils.isEmpty(type)){
			daoPara.addCondition(Condition.EQUAL("type", Integer.parseInt(type)));
		}
		daoPara.addCondition(Condition.EQUAL("createUserId", loginUser.getId()));
		daoPara.addOrder(Order.desc("createTime"));
		List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据ID获得组织生活
     * @param id
     * @return
     */
    public OrgActivity getOrgActivity(String id)  {
    	Object obj=this.loadOne(ObjectType.OBT_ORG_ACTIVITY, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	OrgActivity activity=(OrgActivity)obj;
        return activity;
    }

}
