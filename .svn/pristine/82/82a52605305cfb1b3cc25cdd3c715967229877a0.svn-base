package org.tpri.djcom.manager.zbsc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.zbsc.Information;
import org.tpri.djcom.util.PageBean;

/**
 * @description 资源管理类
 * @author 易文俊
 * @since 2015-04-09
 */

@Repository("InformationManager")
public class InformationManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_INFOMATION, Information.class);
    }
    /**
     * 获取资源
     * @return
     */
    public Information getInformation(String id)  {
    	Object obj=this.loadOne(ObjectType.ZBSC_INFOMATION, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	Information Information=(Information)obj;
        return Information;
    }
    
    /**
     * 获取资源列表
     * @return
     */
    public List<Information> getInformationList(String classId, Integer start, Integer limit)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Information.class);
		daoPara.addCondition(Condition.EQUAL("type", classId));
		if (start != null && limit != null) {
			daoPara.setStart(start);
			daoPara.setLimit(limit);
		}
		daoPara.addOrder(Order.asc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据类型获取资料列表
     * @return
     */
    public List<Information> getInformationList(String type,String status,String orderby,PageBean pager)  {
    	//String hql="select distinct a.id, a.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CATEGORYID=? and b.CCPARTYID=?";
    	String hql = " select i.* from ZBSC_INFORMATION as i where i.TYPE=? AND I.STATUS=? ";
    	String noPageSql = hql;
    	if(orderby!=null && !"".equals(orderby)){
    		hql=hql+"order by "+orderby;
    	}
    	hql+=" limit "+pager.getStart()+","+pager.getPageSize();
    	Object[] params= new Object[]{ type,status};;
        List list = dao.loadNative(hql, params, Information.class);
        List noPageLists = dao.loadNative(noPageSql, params,Information.class);
        if(noPageLists!=null && noPageLists.size()>0){
        	pager.setTotalCount(noPageLists.size());
        }
        return list;
    }
    
    /**
     * 获取资料文章
     * @return
     */
    public Information getInformationById(String id)  {
    	Information information=(Information)super.load(id, ObjectType.ZBSC_INFOMATION);
    	return information;
    }
}
