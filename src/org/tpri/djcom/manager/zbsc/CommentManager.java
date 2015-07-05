package org.tpri.djcom.manager.zbsc;

import java.sql.Timestamp;
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
import org.tpri.djcom.entity.zbsc.Comment;
import org.tpri.djcom.util.PageBean;

/**
 * @description 评论管理类
 * @author 易文俊
 * @since 2015-05-04
 */

@Repository("CommentManager")
public class CommentManager extends ManagerBase {
	private static boolean initialized = false;

    public  void initialize() {
        if (initialized)
            return;
        initialized =  true;
        ObjectRegister.registerClass(ObjectType.ZBSC_COMMENT, Comment.class);
    }
    /**
     * 获取文章的评论列表
     * @return
     */
    public List<Comment> getCommentByArticleId(String articleId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Comment.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		daoPara.addOrder(Order.asc("createTime"));
        List list = dao.loadList(daoPara);
        return list;
    }
    /**
     * 获取文章的评论总数
     * @return
     */
    public int getTotalCommentByArticleId(String articleId)  {
    	DaoPara daoPara = new DaoPara();
		daoPara.setClazz(Comment.class);
		daoPara.addCondition(Condition.EQUAL("articleId", articleId));
		int total=dao.getTotalCount(daoPara);
        return total;
    }
    /**
     * 获取评论
     * @return
     */
    public Comment getCommentById(String id)  {
    	Comment comment=(Comment)super.load(id, ObjectType.ZBSC_COMMENT);
    	return comment;
    }
    /**
     * 更新评论
     * @return
     */
    public boolean editComment(String id, Map<String, Object> fieldValues)  {
    	return super.update(id, ObjectType.ZBSC_COMMENT, fieldValues);
    }
    /**
     * 删除评论
     * @return
     */
    public boolean deleteComment(String id)  {
    	return super.delete(id, ObjectType.ZBSC_COMMENT);
    }
    
    /**
     * 根据用户ID获取我的评论列表
     * @return
     */
    public List<Comment> getMyRenderComment(String userId,PageBean pager) {
    	List<Comment> comments = new ArrayList<Comment>();
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuffer sql = new StringBuffer();
    	StringBuffer noPageSql = new StringBuffer();
    	sql.append("SELECT zc.id,zc.content,IFNULL(zc.createtime,'') createtime,IFNULL(za.name,'文章已删除')articlename, IFNULL(za.id,'') articleid \n");
    	sql.append("FROM zbsc_comment AS zc \n");
    	sql.append("LEFT JOIN zbsc_article AS za ON zc.articleid=za.id\n");
    	if(userId!=null && !"".equals(userId)){
    		sql.append("where zc.userid='").append(userId).append("'\n");
    	}else{
    		return null;
    	}
    	sql.append("order by zc.createtime desc\n");
    	noPageSql.append(sql.toString());
    	sql.append("limit ").append(pager.getStart()).append(",").append(pager.getPageSize()).append("\n");
    	List list = new ArrayList();
    	List noPageLists = new ArrayList();
    	try{
    		Session session=dao.getCurrentSession();
    		Query query=session.createSQLQuery(sql.toString());
    		list=query.list();
    		for(int i=0;i<list.size();i++){
    			Object[] objs = (Object[])list.get(i);
    			Comment comment = new Comment();
    			comment.setId(String.valueOf(objs[0]));
    			comment.setContent(String.valueOf(objs[1]));
    			String createTime = String.valueOf(objs[2]);
    			if(createTime!=null && !"".equals(createTime)){
    				Date d = (Date) f.parseObject(createTime);
    				Timestamp ts = new Timestamp(d.getTime());
    				comment.setCreateTime(ts);
    			}
    			comment.articleName=String.valueOf(objs[3]);
    			comment.setArticleId(String.valueOf(objs[4]));
    			comments.add(comment);
    		}
    		//不分页获取数据
    		Query noPageQuery = session.createSQLQuery(noPageSql.toString());
    		noPageLists = noPageQuery.list();
    		pager.setTotalCount(noPageLists.size());
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("查询我的评论异常："+e.getMessage());
    	}
        return comments;
	}
}
