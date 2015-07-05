package org.tpri.djcom.manager.zbsc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.zbsc.Category;

/**
 * @description 栏目管理类
 * @author 易文俊
 * @since 2015-04-30
 */

@Repository("CategoryManager")
public class CategoryManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_CATEGORY, Category.class);
    }
    /**
     * 获取所有栏目列表
     * @return
     */
    public List<Category> getAllCategory()  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Category.class);
		daoPara.addOrder(Order.asc("orderNo"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 根据类型获取栏目列表
     * @return
     */
    public List<Category> getCategoryList(String cCPartyId,int type,String parentId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Category.class);
		daoPara.addCondition(Condition.EQUAL("cCPartyId", cCPartyId));
		daoPara.addCondition(Condition.EQUAL("type", type));
		if(parentId!=null){
			daoPara.addCondition(Condition.EQUAL("parentId", parentId));
		}
		daoPara.addOrder(Order.asc("orderNo"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 根据栏目下的子栏目
     * @return
     */
    public List<Category> getCategoryList(String parentId,String ccpartyId)  {
    	List ccpartyIds=new ArrayList();
    	ccpartyIds.add(ccpartyId);
    	ccpartyIds.add("common");
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Category.class);
		daoPara.addCondition(Condition.IN("ccpartyId", ccpartyIds));
		daoPara.addCondition(Condition.EQUAL("parentId", parentId));
		daoPara.addOrder(Order.asc("orderNo"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取栏目
     * @return
     */
    public Category getCategoryById(String id)  {
    	Category category=(Category)super.load(id, ObjectType.ZBSC_CATEGORY);
    	return category;
    }
    /**
     * 更新栏目
     * @return
     */
    public boolean editCategory(String id, Map<String, Object> fieldValues)  {
    	return super.update(id, ObjectType.ZBSC_CATEGORY, fieldValues);
    }
    /**
     * 删除栏目
     * @return
     */
    public boolean deleteCategory(String id)  {
    	return super.delete(id, ObjectType.ZBSC_CATEGORY);
    }
}
