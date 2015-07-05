package org.tpri.djcom.manager.zbsc;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.entity.zbsc.ArticleCategory;

/**
 * @description 栏目管理类
 * @author 易文俊
 * @since 2015-05-02
 */

@Repository("ArticleCategoryManager")
public class ArticleCategoryManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_ARTICLECATEGORY, ArticleCategory.class);
    }
    /**
     * 根据id获取某条文章栏目关联记录
     * @return
     */
    public ArticleCategory getArticleCategoryById(String id)  {
    	Object obj=this.loadOne(ObjectType.ZBSC_ARTICLECATEGORY, new String[] { "id" }, new Object[] { id });
    	if (obj == null) {
			return null;
		}
    	ArticleCategory c=(ArticleCategory)obj;
        return c;
    }
    /**
     * 获取某组织某栏目下文章
     * @return
     */
    public List<ArticleCategory> getArticleList(String categoryId,String ccpartyId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(ArticleCategory.class);
		daoPara.addCondition(Condition.EQUAL("categoryId", categoryId));
		daoPara.addCondition(Condition.EQUAL("ccpartyId", ccpartyId));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取某文章所在的栏目
     * @return
     */
    public List<ArticleCategory> getArticleCategoryList(String articleId,String ccpartyId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(ArticleCategory.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		daoPara.addCondition(Condition.EQUAL("ccpartyId", ccpartyId));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取某组织某栏目下某段时间内的文章
     * @return
     */
    public List<ArticleCategory> getArticleList(String categoryId,String ccpartyId, Date startTime, Date endTime)  {
    	
    	String hql="select distinct a.id, b.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CATEGORYID=? and b.CCPARTYID=? and a.CREATETIME>=? and a.CREATETIME<=?";
    	
    	Object[] params= new Object[]{ categoryId ,ccpartyId,startTime, endTime};;
        List list = dao.loadNative(hql, params, ArticleCategory.class);
        return list;
    }
    /**
     * 获取某组织某栏目下某段时间内的文章
     * @return
     */
    public List<ArticleCategory> getArticleList(String categoryId, Date startTime, Date endTime)  {
    	
    	String hql="select distinct a.id, b.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CATEGORYID=? and a.CREATETIME>=? and a.CREATETIME<=?";
    	
    	Object[] params= new Object[]{ categoryId, startTime, endTime};;
        List list = dao.loadNative(hql, params, ArticleCategory.class);
        return list;
    }
  
    /**
     * 删除某组织某栏目下所有文章关联
     * @return
     */
    public boolean deleteArticleCategory(String categoryId,String cCPartyId)  {
    	DaoPara daoPara = new DaoPara();
    	daoPara.setClazz(ArticleCategory.class);
		daoPara.addCondition(Condition.EQUAL("categoryId", categoryId));
		daoPara.addCondition(Condition.EQUAL("cCPartyId", cCPartyId));
    	dao.delete(daoPara);
    	return true;
    }
    /**
     * 删除文章关联
     * @return
     */
    public boolean deleteArticleCategoryByArticleId(String articleId)  {
    	DaoPara daoPara = new DaoPara();
    	daoPara.setClazz(ArticleCategory.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
    	dao.delete(daoPara);
    	return true;
    }
    
    /**
     * 删除文章所属栏目
     * @return
     */
    public boolean deleteArticleCategory(String id)  {
    	return super.delete(id, ObjectType.ZBSC_ARTICLECATEGORY);
    }
    
    /**
     * 根据文章ID获取所属哪些栏目
     * @return
     */
    public List<ArticleCategory> getColumnsByArticleId(String articleId,String categoryId,String ccpartyId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(ArticleCategory.class);
		daoPara.addCondition(Condition.EQUAL("articleid", articleId));
		if(categoryId!=null && !"".equals(categoryId)){
			daoPara.addCondition(Condition.EQUAL("categoryid", categoryId));
		}
		if(ccpartyId!=null && !"".equals(ccpartyId)){
			daoPara.addCondition(Condition.EQUAL("ccpartyid", ccpartyId));
		}
        List list = dao.loadList(daoPara);
        return list;
    }
    
}
