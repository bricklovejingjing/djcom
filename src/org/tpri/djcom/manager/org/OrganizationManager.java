package org.tpri.djcom.manager.org;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.org.Organization;


/**
 * @description 党组织管理类
 * @author 常华荣
 * @since 2015-05-04
 */

@Repository("OrganizationManager")
public class OrganizationManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ORG_ORGANIZATION, Organization.class);
    }
    /**
     * 获取用户
     * @return
     */
    public Organization getOrganization(String id)  {
    	Object obj=this.loadOne(ObjectType.ORG_ORGANIZATION, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
		Organization Organization=(Organization)obj;
        return Organization;
    }
    
    /**
     * 获取用户列表
     * @return
     */
    public List<Organization> getOrganizationList(String classId, Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Organization.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据父类id获取所有集合
     * @param parentId
     * @return
     */
	public List<Organization> getOrganizationListById(String parentId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Organization.class);
		daoPara.addCondition(Condition.EQUAL("parentId", parentId));
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
		return list;
	}
	
    
}
