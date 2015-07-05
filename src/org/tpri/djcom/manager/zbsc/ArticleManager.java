package org.tpri.djcom.manager.zbsc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.tpri.djcom.core.ManagerBase;
import org.tpri.djcom.core.ObjectRegister;
import org.tpri.djcom.core.ObjectType;
import org.tpri.djcom.dao.condition.Condition;
import org.tpri.djcom.dao.condition.DaoPara;
import org.tpri.djcom.dao.condition.Order;
import org.tpri.djcom.entity.result.QueryResultArticle;
import org.tpri.djcom.entity.uam.User;
import org.tpri.djcom.entity.zbsc.Article;
import org.tpri.djcom.util.BaseConfig;
import org.tpri.djcom.util.PageBean;

/**
 * @description 文章管理类
 * @author 易文俊
 * @since 2015-05-02
 */

@Repository("ArticleManager")
public class ArticleManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_ARTICLE, Article.class);
    }
    /**
     * 获取文章列表
     * @return
     */
    public List<Article> getArticleList(List ids)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Article.class);
		daoPara.addCondition(Condition.IN("id", ids));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取某组织某栏目文章列表
     * @return
     */
    /*public List<Article> getArticleList(String cCPartyId, String categoryId,String orderby,boolean onlyRecommend)  {
    	String hql="select distinct a.id, a.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CATEGORYID=? and b.CCPARTYID=?";
    	if(onlyRecommend==true){
    		hql=hql+"and a.ISRECOMMEND=1";
    	}
    	if(orderby!=null){
    		hql=hql+" order by "+orderby;
    	}
    	Object[] params= new Object[]{ categoryId ,cCPartyId};;
        List list = dao.loadNative(hql, params, Article.class);
        return list;
    }*/
    /**
     * 获取某组织某些栏目文章列表
     * @return
     */
    public List<Article> getArticleList(String cCPartyId, String categoryIds,String orderby,boolean onlyRecommend,PageBean pager)  {
    	String hql="select distinct a.id, a.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CCPARTYID=?  and b.CATEGORYID IN("+categoryIds+")";
    	if(onlyRecommend==true){
    		hql=hql+"and a.ISRECOMMEND=1";
    	}
    	if(orderby!=null){
    		hql=hql+" order by "+orderby;
    	}
    	Object[] params= new Object[]{cCPartyId};;
    	String noPageSql = hql;
    	hql += " limit "+pager.getStart()+","+pager.getPageSize();
        List list = dao.loadNative(hql, params, Article.class);
        //不分页获取数据
        List listNoPage = dao.loadNative(noPageSql, params, Article.class);
        
        if(listNoPage!=null && listNoPage.size()>0){
        	pager.setTotalCount(listNoPage.size());
        }
        return list;
    }
    /**
     * 获取某组织文章列表
     * @return
     */
    public List<Article> getArticleList(String ccpartyId,String orderby,boolean onlyRecommend,PageBean pager)  {
    	String hql="select distinct a.id, a.* from ZBSC_ARTICLE a, ZBSC_ARTICLECATEGORY b where a.ID=b.ARTICLEID and b.CCPARTYID=?";
    	if(onlyRecommend==true){
    		hql=hql+"and a.ISRECOMMEND=1";
    	}
    	if(orderby!=null){
    		hql=hql+" order by "+orderby;
    	}
    	Object[] params= new Object[]{ccpartyId};
    	String noPageSql = hql;
    	hql += " limit "+pager.getStart()+","+pager.getPageSize();
        List list = dao.loadNative(hql, params, Article.class);
        //不分页获取数据
        List listNoPage = dao.loadNative(noPageSql, params, Article.class);
        
        if(listNoPage!=null && listNoPage.size()>0){
        	pager.setTotalCount(listNoPage.size());
        }
        return list;
    }
    /**
     * 根据用户ID文章列表
     * @return
     */
    public List<Article> getArticleByUserId(String userId,PageBean pager) {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Article.class);
		daoPara.addCondition(Condition.EQUAL("createUserId", userId));
		daoPara.addOrder(Order.desc("createTime"));
		daoPara.setStart(pager.getStart());
		daoPara.setLimit(pager.getPageSize());
        List list = dao.loadList(daoPara);
        //不分页获取数据
        DaoPara daoParaNoPage = new DaoPara();
        daoParaNoPage.setClazz(Article.class);
        daoParaNoPage.addCondition(Condition.EQUAL("createUserId", userId));
        daoParaNoPage.addOrder(Order.desc("createTime"));
        List listNoPage = dao.loadList(daoParaNoPage);
        if(listNoPage!=null && listNoPage.size()>0){
        	pager.setTotalCount(listNoPage.size());
        }
        return list;
	}
    /**
     * 获取文章
     * @return
     */
    public Article getArticleById(String id)  {
    	Article article=(Article)super.load(id, ObjectType.ZBSC_ARTICLE);
    	return article;
    }
    /**
     * 更新文章
     * @return
     */
    public boolean editArticle(String id, Map<String, Object> fieldValues)  {
    	return super.update(id, ObjectType.ZBSC_ARTICLE, fieldValues);
    }
    /**
     * 删除文章
     * @return
     */
    public boolean deleteArticle(String id)  {
    	return super.delete(id, ObjectType.ZBSC_ARTICLE);
    }
    
    /**
     * 统计与分析数据获取
     * @return
     */
    public List<QueryResultArticle> getArticlesViewList(String type,String year,String ccpartyId,User currentUser,String initialCcpartyId)  {
    	List<QueryResultArticle> articleCounts = new ArrayList<QueryResultArticle>();
    	StringBuffer sql = new StringBuffer();
    	//获取当前年
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    	String currentYear = sdf.format(new Date());
    	String queryDate = "";
    	if("quarter".equals(type)){
    		queryDate = " between '"+currentYear+"-";
    		if("1".equals(year)){
    			queryDate += "01'" + " and '"+currentYear+"-03' \n";
    		}else if("2".equals(year)){
    			queryDate += "04'" + " and '"+currentYear+"-06' \n";
    		}else if("3".equals(year)){
    			queryDate += "07'" + " and '"+currentYear+"-09' \n";
    		}else if("4".equals(year)){
    			queryDate += "10'" + " and '"+currentYear+"-12' \n";
    		}
    	}
    	sql.append("SELECT ccparty.id orgId,ccparty.name NAME,IFNULL(slmb.slmbcount,0) slmb,IFNULL(czwt.slmbcount,0) czwt,IFNULL(mqzr.slmbcount,0) mqzr,\n");
    	sql.append("IFNULL(jjwt.slmbcount,0) jjwt,IFNULL(zjpy.slmbcount,0) zjpy,\n");
    	sql.append("IFNULL(hitscount.hits,0) hit,\n");
    	sql.append("IFNULL(reply.replys,0) reply\n");
    	
    	sql.append("FROM\n");
    	sql.append("( \n");
    	sql.append("##组织\n");
    	sql.append("SELECT cc.id,cc.NAME,cc.type FROM org_ccparty AS cc \n");
    	sql.append(" where 1=1 ");
    	if(!currentUser.isSystemAdmin() && currentUser.getPartyMember()!=null && !"".equals(currentUser.getPartyMember())){
    		//不是超级管理员需要根据登陆用户的所属组织查询统计组织的数据
    		if(ccpartyId!=null && !"".equals(ccpartyId) && !initialCcpartyId.equals(ccpartyId)){
        		sql.append("and cc.id='").append(ccpartyId).append("'\n");
        	}else{
        		sql.append(" and cc.type = (").append(" SELECT orgc.type FROM obt_party_member AS orgp ,uam_user AS uu ,org_ccparty AS orgc \n");
        		sql.append(" WHERE orgp.id=uu.partymemberid AND orgc.id=orgp.ccpartyid \n");
        		sql.append(" AND uu.id='").append(currentUser.getId()).append("' )\n");
        	}
    	}else{
    		if(ccpartyId!=null && !"".equals(ccpartyId) && !initialCcpartyId.equals(ccpartyId)){
        		sql.append("and cc.id='").append(ccpartyId).append("'\n");
        	}
    	}
    	
    	sql.append(")AS ccparty \n");
    	//树立目标
    	sql.append("LEFT JOIN\n");
    	sql.append("##树立目标\n");
    	sql.append("(\n");
    	sql.append("SELECT result1.ccid ,result1.name NAME,COUNT(result1.aid) slmbcount \n");
    	sql.append("FROM(\n");
    	sql.append("	SELECT DISTINCT ag.ccpartyid ccid,'树立目标' NAME,a.id aid,c.id cid,a.hits,COUNT(c.id) applys\n");
    	sql.append("	FROM zbsc_article AS a \n");
    	sql.append("	LEFT JOIN zbsc_articlecategory AS ag ON  a.id=ag.articleid\n");
    	sql.append("	LEFT JOIN zbsc_comment AS c ON (a.id=c.articleid )\n");
    	sql.append("	WHERE ag.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='shulimubiao' OR c2.id='shulimubiao' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	GROUP BY ag.ccpartyid,a.id\n");
    	sql.append("	) AS result1 \n");
    	sql.append("GROUP BY result1.ccid\n");
    	sql.append(") AS slmb ON ccparty.id=slmb.ccid\n");
    	//查找问题
    	sql.append("LEFT JOIN\n");
    	sql.append("##查找问题\n");
    	sql.append("(\n");
    	sql.append("SELECT result1.ccid ,result1.name NAME,COUNT(result1.aid) slmbcount \n");
    	sql.append("FROM(\n");
    	sql.append("	SELECT DISTINCT ag.ccpartyid ccid,'查找问题' NAME,a.id aid,c.id cid,a.hits,COUNT(c.id) applys\n");
    	sql.append("	FROM zbsc_article AS a \n");
    	sql.append("	LEFT JOIN zbsc_articlecategory AS ag ON  a.id=ag.articleid\n");
    	sql.append("	LEFT JOIN zbsc_comment AS c ON (a.id=c.articleid )\n");
    	sql.append("	WHERE ag.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='chazhaowenti' OR c2.id='chazhaowenti' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	GROUP BY ag.ccpartyid,a.id\n");
    	sql.append("	) AS result1 \n");
    	sql.append("GROUP BY result1.ccid\n");
    	sql.append(") AS czwt ON ccparty.id=czwt.ccid\n");
    	//##明确责任
    	sql.append("LEFT JOIN\n");
    	sql.append("##明确责任\n");
    	sql.append("(\n");
    	sql.append("SELECT result1.ccid ,result1.name NAME,COUNT(result1.aid) slmbcount \n");
    	sql.append("FROM(\n");
    	sql.append("	SELECT DISTINCT ag.ccpartyid ccid,'明确责任' NAME,a.id aid,c.id cid,a.hits,COUNT(c.id) applys\n");
    	sql.append("	FROM zbsc_article AS a \n");
    	sql.append("	LEFT JOIN zbsc_articlecategory AS ag ON  a.id=ag.articleid\n");
    	sql.append("	LEFT JOIN zbsc_comment AS c ON (a.id=c.articleid )\n");
    	sql.append("	WHERE ag.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='mingquezeren' OR c2.id='mingquezeren' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	GROUP BY ag.ccpartyid,a.id\n");
    	sql.append("	) AS result1 \n");
    	sql.append("GROUP BY result1.ccid\n");
    	sql.append(") AS mqzr ON ccparty.id=mqzr.ccid\n");
    	//解决问题
    	sql.append("LEFT JOIN\n");
    	sql.append("##解决问题\n");
    	sql.append("(\n");
    	sql.append("SELECT result1.ccid ,result1.name NAME,COUNT(result1.aid) slmbcount \n");
    	sql.append("FROM(\n");
    	sql.append("	SELECT DISTINCT ag.ccpartyid ccid,'解决问题' NAME,a.id aid,c.id cid,a.hits,COUNT(c.id) applys\n");
    	sql.append("	FROM zbsc_article AS a \n");
    	sql.append("	LEFT JOIN zbsc_articlecategory AS ag ON  a.id=ag.articleid\n");
    	sql.append("	LEFT JOIN zbsc_comment AS c ON (a.id=c.articleid )\n");
    	sql.append("	WHERE ag.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='jiejuewenti' OR c2.id='jiejuewenti' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	GROUP BY ag.ccpartyid,a.id\n");
    	sql.append("	) AS result1 \n");
    	sql.append("GROUP BY result1.ccid\n");
    	sql.append(") AS jjwt ON ccparty.id=jjwt.ccid\n");
    	//总结评议
    	sql.append("LEFT JOIN\n");
    	sql.append("##总结评议\n");
    	sql.append("(\n");
    	sql.append("SELECT result1.ccid ,result1.name NAME,COUNT(result1.aid) slmbcount \n");
    	sql.append("FROM(\n");
    	sql.append("	SELECT DISTINCT ag.ccpartyid ccid,'总结评议' NAME,a.id aid,c.id cid,a.hits,COUNT(c.id) applys\n");
    	sql.append("	FROM zbsc_article AS a \n");
    	sql.append("	LEFT JOIN zbsc_articlecategory AS ag ON  a.id=ag.articleid\n");
    	sql.append("	LEFT JOIN zbsc_comment AS c ON (a.id=c.articleid )\n");
    	sql.append("	WHERE ag.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='zongjiepingyi' OR c2.id='zongjiepingyi' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	GROUP BY ag.ccpartyid,a.id\n");
    	sql.append("	) AS result1 \n");
    	sql.append("GROUP BY result1.ccid\n");
    	sql.append(") AS zjpy ON ccparty.id=zjpy.ccid\n");
    	//帖子浏览数
    	sql.append("LEFT JOIN\n");
    	sql.append("##统计浏览数\n");
    	sql.append("(\n");
    	sql.append("SELECT hit1.id id,SUM(hit1.hits) hits FROM (\n");
    	sql.append("	SELECT DISTINCT za.CCPARTYID id,zar.id artid,zar.`HITS` hits FROM zbsc_article AS zar LEFT JOIN zbsc_articlecategory AS za ON zar.id=za.ARTICLEID\n");
    	sql.append("	LEFT JOIN zbsc_article AS a ON za.`ARTICLEID`=a.id\n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" where DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" where DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	)AS hit1\n");
    	sql.append("GROUP BY hit1.id\n");
    	sql.append(") AS hitscount ON ccparty.id=hitscount.id\n");
    	//统计回复数
    	sql.append("LEFT JOIN\n");
    	sql.append("##统计回复数\n");
    	sql.append("(\n");
    	sql.append("SELECT comment1.id id,COUNT(comment1.id) replys FROM (\n");
    	sql.append("	SELECT DISTINCT za.CCPARTYID id,zc.articleid artid,zc.content FROM zbsc_comment AS zc LEFT JOIN zbsc_articlecategory AS za ON zc.articleid=za.ARTICLEID\n");
    	sql.append("	LEFT JOIN zbsc_article AS a ON za.`ARTICLEID`=a.id\n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" where DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" where DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	)AS comment1\n");
    	sql.append("GROUP BY comment1.id\n");
    	sql.append(") AS reply ON ccparty.id=reply.id\n");
    	sql.append("order by ccparty.type,ccparty.id asc\n");
    	//sql.append("LIMIT 0,8\n");
    	
    	List list = new ArrayList();
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			QueryResultArticle queryArticle = new QueryResultArticle();
    			queryArticle.setOrgId(String.valueOf(objs[0]));
    			queryArticle.setName(String.valueOf(objs[1]));
    			queryArticle.setSlmb(Integer.parseInt(String.valueOf(objs[2])));
    			queryArticle.setCzwt(Integer.parseInt(String.valueOf(objs[3])));
    			queryArticle.setMqzr(Integer.parseInt(String.valueOf(objs[4])));
    			queryArticle.setJjwt(Integer.parseInt(String.valueOf(objs[5])));
    			queryArticle.setZjpy(Integer.parseInt(String.valueOf(objs[6])));
    			queryArticle.setHit(Integer.parseInt(String.valueOf(objs[7])));
    			queryArticle.setReply(Integer.parseInt(String.valueOf(objs[8])));
    			articleCounts.add(queryArticle);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询统计分析异常："+e.getMessage());
    	}
        return articleCounts;
    }
    
    /**
     * 查询统计文章日期
     * @param type
     * @return
     */
    public List<QueryResultArticle> getArticlesGetDate(String type)  {
    	List<QueryResultArticle> articleYears = new ArrayList<QueryResultArticle>();
    	StringBuffer sql = new StringBuffer();
    	sql.append("SELECT DATE_FORMAT(createtime,'%Y') AS YEAR FROM zbsc_article \n");
    	sql.append("GROUP BY  DATE_FORMAT(createtime, '%Y') ORDER BY DATE_FORMAT(createtime,'%Y') DESC\n");
    	List list = new ArrayList();
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			QueryResultArticle queryArticle = new QueryResultArticle();
    			queryArticle.setYear((String.valueOf(list.get(i))));
    			articleYears.add(queryArticle);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询统计分析文章年份异常："+e.getMessage());
    	}
        return articleYears;
    }
    
    /**
     * 查询统计文章图表数据
     * @param type
     * @return
     */
    public List<QueryResultArticle> getArticlesCountChart(String type,String year,String ccpartyId,String articleType,User currentUser,String initialCcpartyId) {
    	List<QueryResultArticle> articleCharts = new ArrayList<QueryResultArticle>();
    	StringBuffer sql = new StringBuffer();
    	//获取当前年
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    	String currentYear = sdf.format(new Date());
    	String queryDate = "";
    	if("quarter".equals(type)){
    		queryDate = " between '"+currentYear+"-";
    		if("1".equals(year)){
    			queryDate += "01'" + " and '"+currentYear+"-03' \n";
    		}else if("2".equals(year)){
    			queryDate += "04'" + " and '"+currentYear+"-06' \n";
    		}else if("3".equals(year)){
    			queryDate += "07'" + " and '"+currentYear+"-09' \n";
    		}else if("4".equals(year)){
    			queryDate += "10'" + " and '"+currentYear+"-12' \n";
    		}
    	}
    	sql.append("SELECT ccparty.id orgId,ccparty.name NAME,\n");
    	sql.append("IFNULL(hitscount.hits,0) hit,\n");
    	sql.append("IFNULL(reply.replys,0) reply,\n");
    	sql.append("IFNULL(files.filescount,0) filescount\n");
    	sql.append("FROM\n");
    	sql.append("( \n");
    	sql.append("##组织\n");
    	sql.append("SELECT cc.id,cc.NAME,cc.type FROM org_ccparty AS cc \n");
    	sql.append("where 1=1\n");
    	if(!currentUser.isSystemAdmin() && currentUser.getPartyMember()!=null && !"".equals(currentUser.getPartyMember())){
    		//不是超级管理员需要根据登陆用户的所属组织查询统计组织的数据
    		if(ccpartyId!=null && !"".equals(ccpartyId) && !initialCcpartyId.equals(ccpartyId)){
        		sql.append("and cc.id='").append(ccpartyId).append("'\n");
        	}else{
        		sql.append(" and cc.type = (").append(" SELECT orgc.type FROM obt_party_member AS orgp ,uam_user AS uu ,org_ccparty AS orgc \n");
        		sql.append(" WHERE orgp.id=uu.partymemberid AND orgc.id=orgp.ccpartyid \n");
        		sql.append(" AND uu.id='").append(currentUser.getId()).append("' )\n");
        	}
    	}else{
    		if(ccpartyId!=null && !"".equals(ccpartyId) && !initialCcpartyId.equals(ccpartyId)){
        		sql.append("and cc.id='").append(ccpartyId).append("'\n");
        	}
    	}
    	sql.append(")AS ccparty \n");
    	//统计浏览数
    	sql.append("LEFT JOIN\n");
    	sql.append("##统计浏览数\n");
    	sql.append("(\n");
    	sql.append("SELECT hit1.id id,SUM(hit1.hits) hits FROM (\n");
    	sql.append("	SELECT DISTINCT za.CCPARTYID id,zar.id artid,zar.`HITS` hits FROM zbsc_article AS zar LEFT JOIN zbsc_articlecategory AS za ON zar.id=za.ARTICLEID\n");
    	sql.append("	LEFT JOIN zbsc_article AS a ON za.`ARTICLEID`=a.id\n");
    	sql.append("	where za.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='"+articleType+"' OR c2.id='"+articleType+"' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	)AS hit1\n");
    	sql.append("GROUP BY hit1.id\n");
    	sql.append(") AS hitscount ON ccparty.id=hitscount.id\n");
    	//统计回复数
    	sql.append("LEFT JOIN\n");
    	sql.append("##统计回复数\n");
    	sql.append("(\n");
    	sql.append("SELECT comment1.id id,COUNT(comment1.id) replys FROM (\n");
    	sql.append("	SELECT DISTINCT za.CCPARTYID id,zc.articleid artid,zc.content FROM zbsc_comment AS zc LEFT JOIN zbsc_articlecategory AS za ON zc.articleid=za.ARTICLEID\n");
    	sql.append("	LEFT JOIN zbsc_article AS a ON za.`ARTICLEID`=a.id\n");
    	sql.append("	where za.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='"+articleType+"' OR c2.id='"+articleType+"' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	)AS comment1\n");
    	sql.append("GROUP BY comment1.id\n");
    	sql.append(") AS reply ON ccparty.id=reply.id\n");
    	//统计文件数
    	sql.append("LEFT JOIN\n");
    	sql.append("##统计文件\n");
    	sql.append("(\n");
    	sql.append("SELECT file1.id id,COUNT(file1.id) filescount FROM (\n");
    	sql.append("	SELECT DISTINCT za.CCPARTYID id,zu.`ARTICLEID`,zu.`NAME` FROM zbsc_uploadfile AS zu LEFT JOIN zbsc_articlecategory AS za ON zu.ARTICLEID=za.ARTICLEID\n");
    	sql.append("	LEFT JOIN zbsc_article AS a ON za.`ARTICLEID`=a.id\n");
    	sql.append("	where za.categoryid IN\n");
    	sql.append("		(\n");
    	sql.append("		SELECT c2.id FROM zbsc_category AS c2 WHERE c2.parentid ='"+articleType+"' OR c2.id='"+articleType+"' \n");
    	sql.append("		) \n");
    	if("year".equals(type) && year!=null && !"".equals(year)){
    		sql.append("and DATE_FORMAT(a.createtime,'%Y')='").append(year).append("'");
    	}else if("quarter".equals(type) && year!=null && !"".equals(year)){
    		sql.append(" and DATE_FORMAT(a.createtime,'%Y-%m') ").append(queryDate);
    	}
    	sql.append("	) AS file1\n");
    	sql.append("GROUP BY file1.id\n");
    	sql.append(")AS files ON ccparty.id=files.id\n");
    	sql.append("order by ccparty.type,ccparty.id asc\n");
    	//sql.append("LIMIT 0,8\n");
    	
    	List list = new ArrayList();
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			QueryResultArticle queryArticle = new QueryResultArticle();
    			queryArticle.setYear((String.valueOf(objs[0])));
    			queryArticle.setName(String.valueOf(objs[1]));
    			queryArticle.setHit(Integer.parseInt(String.valueOf(objs[2])));
    			queryArticle.setReply(Integer.parseInt(String.valueOf(objs[3])));
    			queryArticle.setFilescount(Integer.parseInt(String.valueOf(objs[4])));
    			articleCharts.add(queryArticle);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询统计分析文章图表数据异常："+e.getMessage());
    	}
        return articleCharts;
    }
}