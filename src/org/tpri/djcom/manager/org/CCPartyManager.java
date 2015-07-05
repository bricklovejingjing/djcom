package org.tpri.djcom.manager.org;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.org.CCParty;
import org.tpri.djcom.entity.result.QueryResultArticle;

/**
 * @description 党组织管理类
 * @author 易文俊
 * @since 2015-04-24
 */

@Repository("CCPartyManager")
public class CCPartyManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ORG_CCPARTY, CCParty.class);
    }
    /**
     * 获取所有党组织
     * @return
     */
    public List<CCParty> getAllCCParty()  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(CCParty.class);
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
    
    /**
     * 根据文章ID获取已经转发的党组织
     * @param articleId 文章ID
     * @return
     */
    public List<CCParty> getExistTreeCCPartyByArticleId(String articleId)  {
    	List<CCParty> ccpartys = new ArrayList<CCParty>();
    	StringBuffer sql = new StringBuffer();
    	List list = new ArrayList();
    	sql.append("SELECT ccpartyid FROM zbsc_articlecategory\n");
    	sql.append("WHERE articleId='").append(articleId).append("' GROUP BY ccpartyid\n");
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			CCParty ccparty = new CCParty();
    			ccparty.setId(String.valueOf(list.get(i)));
    			ccpartys.add(ccparty);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询已经转发的党组织信息异常："+e.getMessage());
    	}
        return ccpartys;
    }
    
    /**
     * 根据党组织ID获取下级党组织列表
     * @return
     */
    public List<CCParty> getCCPartyListByParentId(String parentId,String showEnabledStatus)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(CCParty.class);
		if(StringUtils.isNotEmpty(parentId)){
			daoPara.addCondition(Condition.EQUAL("parentId", parentId));
		}
		if(showEnabledStatus!=null && !"".equals(showEnabledStatus) && "false".equals(showEnabledStatus)){
			daoPara.addCondition(Condition.EQUAL("status", 0));		//查询正常有效的组织
		}
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
        return list;
    }
	/*public CCParty getCCParty(String id) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(CCParty.class);
		daoPara.addCondition(Condition.EQUAL("id", id));
		daoPara.addOrder(Order.asc("id"));
		
		CCParty c=(CCParty)dao.loadOne(daoPara);
		return c;
	}*/
	
	 public CCParty getCCPartyById(String id)  {
    	Object obj=this.loadOne(ObjectType.ORG_CCPARTY, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	CCParty c=(CCParty)obj;
        return c;
    }
	 
	 /**
	 * 根据组织id获取所有集合
	 * @param orgId
	 * @return
	 */
	public List<CCParty> getCcpartyListByOrgId(String orgId) {
		DaoPara daoPara = new DaoPara();
		daoPara.setClazz(CCParty.class);
		daoPara.addCondition(Condition.EQUAL("id", orgId));
		daoPara.addOrder(Order.asc("id"));
        List list = dao.loadList(daoPara);
		return list;
	}
}
