package org.tpri.djcom.manager.uam;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Logic;
import org.tpri.djcom.dao.condition.Operator;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.uam.Privilege;
import org.tpri.djcom.entity.uam.Role;

/**
 * @description 授权管理类
 * @author 易文俊
 * @since 2015-04-09
 */

@Repository("RoleManager")
public class RoleManager  extends ManagerBase {
	private static boolean initialized = false;
    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.UAM_ROLE, Role.class);
    }
    
    /**
     * 获取角色
     * @return
     */
    public Role getRole(String id)  {
    	Object obj=this.loadOne(ObjectType.UAM_ROLE, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
		Role role=(Role)obj;
        return role;
    }
    
    /**
     * 获取角色列表
     * @return
     */
    public List<Role> getRoleList(Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Role.class);
		
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    
    /**
     * 获取角色列表
     * @return
     */
    public List<Role> getRoleListByIds(String rolesid)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Role.class);
		if(StringUtils.isNotEmpty(rolesid)){
			List<Condition> listc=new ArrayList<Condition>();
			String[] ids=rolesid.split(",");
			List list=new ArrayList();
			for(int i=0;i<ids.length;i++){
				list.add(ids[i]);
			}
			Condition c=new Condition();
			c.setValues(list);
			c.setFieldName("id");
			c.setOperator(Operator.IN);
			c.setLogic(Logic.AND);
			listc.add(c);
			
			daoPara.setConditions(listc);
		}
	
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }

	public List<Privilege> getPrivilegeListByIds(String privilegeid)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Privilege.class);
		if(StringUtils.isNotEmpty(privilegeid)){
			List<Condition> listc=new ArrayList<Condition>();
			String[] ids=privilegeid.split(",");
			List list=new ArrayList();
			for(int i=0;i<ids.length;i++){
				list.add(ids[i]);
			}
			Condition c=new Condition();
			c.setValues(list);
			c.setFieldName("id");
			c.setOperator(Operator.IN);
			c.setLogic(Logic.AND);
			listc.add(c);
			
			daoPara.setConditions(listc);
		}
	
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    
}
